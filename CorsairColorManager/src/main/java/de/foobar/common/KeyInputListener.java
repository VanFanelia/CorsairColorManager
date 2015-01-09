package de.foobar.common;

import de.foobar.keys.Key;
import de.foobar.keys.KeyToNumber;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 * Editor: van on 05.01.15.
 */
public class KeyInputListener implements NativeKeyListener {

	private BasicProgram currentProgram;

	public KeyInputListener(final BasicProgram currentProgram) {
		this.currentProgram = currentProgram;
	}

	@Override
	public void nativeKeyPressed(final NativeKeyEvent nativeKeyEvent) {
		printlnNativeKeyEvent(nativeKeyEvent);
		final Key pressed = KeyToNumber.getKeyForKeyPressInt(this.currentProgram.getKeyboardLayout(), nativeKeyEvent.getKeyCode());
		try {
			currentProgram.runRulesForKeyPressed(pressed);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void nativeKeyReleased(final NativeKeyEvent nativeKeyEvent) {
		printlnNativeKeyEvent(nativeKeyEvent);
	}

	@Override
	public void nativeKeyTyped(final NativeKeyEvent nativeKeyEvent) {
		printlnNativeKeyEvent(nativeKeyEvent);
	}

	public static void printlnNativeKeyEvent(final NativeKeyEvent event)
	{
		System.out.println("Char: "+ event.getKeyChar());
		System.out.println("Code: "+ event.getKeyCode());
		System.out.println("paramString: "+ event.paramString());
		System.out.println("rawCode: " + event.getRawCode());
	}

	public BasicProgram getCurrentProgram() {
		return currentProgram;
	}

	public void setCurrentProgram(final BasicProgram currentProgram) {
		this.currentProgram = currentProgram;
	}

}
