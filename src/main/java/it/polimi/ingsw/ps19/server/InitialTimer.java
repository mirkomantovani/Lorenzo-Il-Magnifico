package it.polimi.ingsw.ps19.server;



public class InitialTimer implements Runnable {

	private Server creator;
	int millis;

	public InitialTimer(Server creator, int millis) {
		this.creator = creator;
		this.millis=millis;
	}


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