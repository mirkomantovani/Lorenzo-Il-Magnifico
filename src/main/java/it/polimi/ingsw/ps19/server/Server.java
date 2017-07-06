package it.polimi.ingsw.ps19.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import it.polimi.ingsw.ps19.constant.FileConstants;
import it.polimi.ingsw.ps19.constant.NetworkConstants;
import it.polimi.ingsw.ps19.server.controller.InitialTimer;
import it.polimi.ingsw.ps19.server.controller.MatchHandler;
import it.polimi.ingsw.ps19.server.rmi.ServerRMIListener;
import it.polimi.ingsw.ps19.server.socket.ServerSocketListener;

/**
 * This is the Server Manager, it handles the Listeners, creates the Matches and starts them.
 *
 * @author Mirko
 */
public class Server implements Runnable, ServerInterface {

	/** The waiting clients. */
	private Deque<ClientHandler> waitingClients;

	/** List of matches created. */
	private Deque<MatchHandler> createdMatches;
	
	/** A map that stores the futures associated to the execution with ExecutionService of MatchHandler threads, needed in order to stop the thread. */
	private Map<MatchHandler,Future> futures;

	/** The socket listener. */
	private ServerSocketListener socketListener;

	/** The rmi listener. */
	private ServerRMIListener rmiListener;

	/** The executor. */
	private ExecutorService executor;

	/** The in keyboard. */
	private BufferedReader inKeyboard;
	
	/** The port. */
	private int port;
	
	/** The instance. */
	private static Server instance;
	
	/** The timer. */
	private Thread timer;
	
	/** The suppress server. */
	private boolean suppressServer = false;

	/**
	 * Instantiates a new server.
	 *
	 * @param port the port
	 */
	private Server(int port) {
		this.port = port;
		inKeyboard = new BufferedReader(new InputStreamReader(System.in));
		futures=new HashMap<MatchHandler,Future>();
	}


	/**
	 * Gets the single instance of Server.
	 *
	 * @return single instance of Server
	 */
	public static synchronized Server getInstance() {
		if (instance == null) {
			return new Server(NetworkConstants.PORT);
		} else
			return instance;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("Starting the server");
		startServer();

	}


	/**
	 * Start server.
	 */
	private void startServer() {
		executor = Executors.newCachedThreadPool();
		waitingClients = new ConcurrentLinkedDeque<ClientHandler>();
		createdMatches = new ConcurrentLinkedDeque<MatchHandler>();
		socketListener = new ServerSocketListener(this, port);
		executor.submit(socketListener);
		rmiListener = new ServerRMIListener(this);
		executor.submit(rmiListener);
		while (!suppressServer) {
			String scelta = null;
			try {
				scelta = inKeyboard.readLine();
			} catch (IOException e) {
			}

			if ("Q".equals(scelta) || "q".equals(scelta)) {
				suppressServer = true;
				suppress();
			}
		}

	}

	/**
	 * adds a new client handler to the list of waiting clients and if the timer has
	 * expired or the queue has reached the max number of players for a match, starts
	 * a new match.
	 *
	 * @param clientHandler            :the client handler that has to be added
	 */
	@Override
	public synchronized void addClient(ClientHandler clientHandler) {
		if (waitingClients.size() == NetworkConstants.MINPLAYERS - 1)
			startInitialTimer();
		waitingClients.add(clientHandler);
		executor.submit(clientHandler); // useless
		System.out.println("Waiting clients: " + waitingClients.size());
		if (waitingClients.size() == NetworkConstants.MAXPLAYERS) {
			timer.interrupt();

			createMatch();
		}
		System.out.println("Client Successfully added, dajeeee");
	}

	/**
	 * this method check if there's the minimum number of waiting client to start a match.
	 *
	 * @return true if there are at least 2 players
	 */
	public synchronized boolean checkWaitingList() {
		return waitingClients.size() >= NetworkConstants.MINPLAYERS;

	}

	/**
	 * this method is invoked by the InitialTimer.
	 */
	public synchronized void timerExpired() {
		if (checkWaitingList())
			createMatch();
	}

	/**
	 * Start initial timer.
	 */
	private void startInitialTimer() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(FileConstants.INITIAL_TIME));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int timeMillis = 0;
		try {
			timeMillis = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		timer = new Thread(new InitialTimer(this, timeMillis));
		timer.start();
	}

	/**
	 * Creates the match.
	 */
	private synchronized void createMatch() {

		List<ClientHandler> list = new ArrayList<ClientHandler>();
		for (ClientHandler c : waitingClients)
			list.add(c);

		MatchHandler matchH = new MatchHandler(list, this);
		
		futures.put(matchH, executor.submit(matchH));
								
		createdMatches.add(matchH);
		waitingClients = new ConcurrentLinkedDeque<ClientHandler>();

	}

	/**
	 * this method closes everything.
	 */
	private void suppress() {
		closeWaitingList();
		if (timer != null && timer.isAlive())
			timer.interrupt();
		try {

			socketListener.endListening();
			inKeyboard.close();
			socketListener.endListening();
			rmiListener.endListening();
			closeMatches();
			executor.shutdown();

		} catch (IOException e) {
		}

	}

	/**
	 * method called when the server is shutting down itself and than notifies the
	 * closing connection to all waiting clients.
	 */
	private void closeWaitingList() {
		if (!waitingClients.isEmpty())
			for (ClientHandler c : waitingClients)
				try {
					c.closedByServer();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
	}

	/**
	 * this method closes every Match created.
	 */
	private void closeMatches() {
		List<MatchHandler> matchToClose = new ArrayList<MatchHandler>();
		for (MatchHandler m : createdMatches)
			matchToClose.add(m);
		for (MatchHandler m : matchToClose)
			m.closeMatch();

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.server.ServerInterface#closeMatch(it.polimi.ingsw.ps19.server.controller.MatchHandler)
	 */
	@Override
	public synchronized void closeMatch(MatchHandler mh) {
		
		futures.get(mh).cancel(true);
			
			
		createdMatches.remove(mh);
		mh=null;

	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.ps19.server.ServerInterface#removeClient(it.polimi.ingsw.ps19.server.ClientHandler)
	 */
	/* 
	 * This method removes the clientHandler from the waitingList
	 */
	@Override
	public synchronized void removeClient(ClientHandler c) {
		waitingClients.remove(c);
		if (!checkWaitingList() && timer != null)
			timer.interrupt();
	}
}
