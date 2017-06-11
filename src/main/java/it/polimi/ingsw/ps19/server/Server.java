package it.polimi.ingsw.ps19.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import it.polimi.ingsw.ps19.constant.NetworkConstants;
import it.polimi.ingsw.ps19.server.rmi.ServerRMIListener;
import it.polimi.ingsw.ps19.server.socket.ServerSocketListener;


/**
 * @author Mirko
 *
 */
public class Server implements Runnable,ServerInterface {
	
	
	private Deque<ClientHandler> waitingClients;
	
	private Deque<MatchHandler> createdMatch;
	
	private ServerSocketListener socketListener;
	
	private ServerRMIListener rmiListener;
	
	private ExecutorService executor;
	
//	private boolean goOn;
	private BufferedReader inKeyboard;
	private int port;
	private static Server instance;
	private Thread timer;
	private boolean goOn=true;


	private Server(int port) {
		this.port = port;
		inKeyboard = new BufferedReader(new InputStreamReader(System.in));
	}

	/**
	 * 
	 * @return the only possible instance of starter server
	 */
	public static synchronized Server getInstance() {
		if (instance == null) {
			return new Server(NetworkConstants.PORT);
		} else
			return instance;
	}

	/**
	 * start server and listener
	 */
	@Override
	public void run() {
		startServer();

	}

	/**
	 * create the socket listener and rmi listener and listen itself if the
	 * server need to be shut down
	 */

	private void startServer() {
		executor = Executors.newCachedThreadPool();
		waitingClients = new ConcurrentLinkedDeque<ClientHandler>();
		createdMatch = new ConcurrentLinkedDeque<MatchHandler>();
		socketListener = new ServerSocketListener(this, port);
		executor.submit(socketListener);
		rmiListener = new ServerRMIListener(this);
		executor.submit(rmiListener);
		// the right procedure to close all that is on when closing the server
//		goOn = true;
//		LOGGER.warn("Press Q to exit the server");
		while (goOn) {
			String scelta = null;
			try {
				scelta = inKeyboard.readLine();
			} catch (IOException e) {
			}

			if ("Q".equals(scelta) || "q".equals(scelta)) {
				goOn = false;
				suppress();}}
//			} else
//				LOGGER.warn("command not recognized");

//		}
	}

	/**
	 * add a new client handler to the list of waiting client an if the timer
	 * expired or the queue reach the max number of a player for a match, launch
	 * a new match
	 * 
	 * @param clientHandler
	 *            :the client handler that is going to be added
	 */
	@Override
	public synchronized void addClient(ClientHandler clientHandler) {
		System.out.println("client aggiunto");
//		if (waitingClients.size() == NetworkConstants.MINPLAYERS - 1)
//			createTimer();
		waitingClients.add(clientHandler);
		executor.submit(clientHandler);
		System.out.println(waitingClients.size());
		if (waitingClients.size() == NetworkConstants.MAXPLAYERS) {
//			timer.interrupt();
			createMatch();
		}
	}

	/**
	 * this method check if in the list there are the minimum number of players
	 * 
	 * @return true if there are almost two players
	 */
	public synchronized boolean checkWaitingList() {
		return waitingClients.size() >= NetworkConstants.MINPLAYERS;

	}

	/**
	 * this method is invoked by the timer and handle when it expired
	 */
	public synchronized void timerExpired() {
		if (checkWaitingList())
			createMatch();
	}

//	private void createTimer() {
//		timer = new Thread(new Timer(this));
//		timer.start();
//	}

	private synchronized void createMatch() {
		System.out.println("creando il match");
		List<ClientHandler> list = new ArrayList<ClientHandler>();
		for (ClientHandler c : waitingClients)
			list.add(c);
		MatchHandler mc = new MatchHandler(list, this);
		executor.submit(mc);  //I think this is doing an implicit run on the matchHandler?
		createdMatch.add(mc);
		waitingClients = new ConcurrentLinkedDeque<ClientHandler>();

	}

	/**
	 * this method closed all
	 */
	private void suppress() {
		closeWaitingList();
		if (timer != null && timer.isAlive())
			timer.interrupt();
		try {

			socketListener.endListening();
			inKeyboard.close();
			socketListener.endListening();
//			rmiListener.endListening();
			closeMatches();
			executor.shutdown();

		} catch (IOException e) {
		}
//		try {
////			Thread.sleep(NetworkConstants.SLEEPBEFORECLOSEALL);
//		} catch (InterruptedException e) {
//		}
//		System.exit(NetworkConstants.EXITSTATUS);
	}

	/**
	 * method called when the server is shutting down itself and than notify the
	 * closing connection to all waiting clients
	 */
	private void closeWaitingList() {
//		if (!waitingClients.isEmpty())
//			for (ClientHandler c : waitingClients)
////				c.closeByServer();
	}

	/**
	 * this method close the match on
	 */
	private void closeMatches() {
		List<MatchHandler> matchToClose = new ArrayList<MatchHandler>();
		for (MatchHandler m : createdMatch)
			matchToClose.add(m);
		for (MatchHandler m : matchToClose)
			m.closeMatch();

	}



	@Override
	public synchronized void closeMatch(MatchHandler mh) {
		createdMatch.remove(mh);

	}

	@Override
	public synchronized void removeClient(ClientHandler c) {
		waitingClients.remove(c);
		if (!checkWaitingList() && timer != null)
			timer.interrupt();
	}
}
