package it.polimi.ingsw.ps19.server;



public class RoundTimer implements Runnable {

	private MatchHandler handler;
	private int millis;

	public RoundTimer(MatchHandler handler, int millis) {
		this.handler = handler;
		this.millis=millis;
	}

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