package it.polimi.ingsw.ps19.server.controller;

/**
 * The Timer thread that awaits the end of one's roundtime
 */
public class RoundTimer implements Runnable {

	/** The handler. */
	private MatchHandler handler;
	
	/** The millis. */
	private int millis;

	/**
	 * Instantiates a new round timer.
	 *
	 * @param handler the handler
	 * @param millis the millis
	 */
	public RoundTimer(MatchHandler handler, int millis) {
		this.handler = handler;
		this.millis=millis;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(this.millis);
		} catch (InterruptedException e) {
			return;
		}
		handler.roundTimerExpired();

	}
}