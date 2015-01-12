package de.foobar.window;

import de.foobar.keys.KeyboardLayout;
import java.io.File;

/**
 * Editor: van on 11.01.15.
 */
public class ProgramOption {

	private File currentProgram;

	private KeyboardLayout keyboardLayout;

	private boolean showVirtualKeyboard;

	private boolean ignoreNonexistentKeyboard;

	private int programDuration;

	private int frameRate;

	private String programCode;

	public ProgramOption() {
	}

	public static ProgramOption getDebugProgramOptions()
	{
		ProgramOption result = new ProgramOption();
		result.setFrameRate(30);
		result.setIgnoreNonexistentKeyboard(true);
		result.setShowVirtualKeyboard(true);
		result.setProgramDuration(300);
		result.setKeyboardLayout(KeyboardLayout.DE);
		result.setProgramCode("{}");
		return result;
	}

	public File getCurrentProgram() {
		return currentProgram;
	}

	public void setCurrentProgram(final File currentProgram) {
		this.currentProgram = currentProgram;
	}

	public KeyboardLayout getKeyboardLayout() {
		return keyboardLayout;
	}

	public void setKeyboardLayout(final KeyboardLayout keyboardLayout) {
		this.keyboardLayout = keyboardLayout;
	}

	public boolean isShowVirtualKeyboard() {
		return showVirtualKeyboard;
	}

	public void setShowVirtualKeyboard(final boolean showVirtualKeyboard) {
		this.showVirtualKeyboard = showVirtualKeyboard;
	}

	public boolean isIgnoreNonexistentKeyboard() {
		return ignoreNonexistentKeyboard;
	}

	public void setIgnoreNonexistentKeyboard(final boolean ignoreNonexistentKeyboard) {
		this.ignoreNonexistentKeyboard = ignoreNonexistentKeyboard;
	}

	public int getProgramDuration() {
		return programDuration;
	}

	public void setProgramDuration(final int programDuration) {
		this.programDuration = programDuration;
	}

	public int getFrameRate() {
		return frameRate;
	}

	public void setFrameRate(final int frameRate) {
		this.frameRate = frameRate;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(final String programCode) {
		this.programCode = programCode;
	}

}
