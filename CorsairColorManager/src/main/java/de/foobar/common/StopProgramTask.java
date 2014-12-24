package de.foobar.common;

/**
 * Editor: van on 11.12.14.
 */
public class StopProgramTask extends Thread implements Runnable {

	private BasicProgram basic;

	public StopProgramTask(BasicProgram basic) {
		this.basic = basic;
	}

	@Override
	public void run() {
		basic.stopProgram();
	}
}
