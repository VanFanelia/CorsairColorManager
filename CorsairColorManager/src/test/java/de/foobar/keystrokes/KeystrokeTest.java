package de.foobar.keystrokes;

import java.util.ArrayList;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * Editor: van on 03.01.15.
 */
public class KeystrokeTest implements NativeKeyListener {


	public static void main(final String[] args) throws Exception{

		KeystrokeTest keystrokeTest = new KeystrokeTest();

		ExecutorService es = new ExecutorService();

//		GlobalScreen.getInstance().setEventDispatcher(es);

		GlobalScreen.getInstance().addNativeKeyListener(keystrokeTest);

		GlobalScreen.registerNativeHook();


		while(true)
		{
			Thread.sleep(10);
		}

	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
		System.out.println(nativeKeyEvent);
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
		System.out.println(nativeKeyEvent);
		System.out.println(nativeKeyEvent.getKeyChar());
		System.out.println(nativeKeyEvent.getKeyCode());
		System.out.println(nativeKeyEvent.getKeyLocation());
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
		System.out.println(nativeKeyEvent);
	}

	private static class ExecutorService extends AbstractExecutorService {

		public ExecutorService() {
		}

		public void shutdown() {

		}

		public java.util.List<Runnable> shutdownNow() {
			return new ArrayList<Runnable>(0);
		}

		public boolean isShutdown() {
			return true;
		}

		public boolean isTerminated() {
			return true;
		}

		public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
			return true;
		}

		public void execute(Runnable r) {
			r.run();
		}
	}
}
