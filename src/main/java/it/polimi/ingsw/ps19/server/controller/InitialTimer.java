package it.polimi.ingsw.ps19.server.controller;

import it.polimi.ingsw.ps19.server.Server;

/**
 * The Class InitialTimer.
 */
public class InitialTimer implements Runnable {

	/** The creator. */
	private Server creator;
	
	/** The millis. */
	int millis;

	/**
	 * Instantiates a new initial timer.
	 *
	 * @param creator the creator
	 * @param millis the millis
	 */
	public InitialTimer(Server creator, int millis) {
		this.creator = creator;
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
			e.printStackTrace();
		}
		creator.timerExpired();

	}

}