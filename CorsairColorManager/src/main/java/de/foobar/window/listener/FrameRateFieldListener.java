package de.foobar.window.listener;

import de.foobar.window.ProgramOption;
import javax.swing.*;

/**
 * Editor: van on 11.01.15.
 */
public class FrameRateFieldListener extends IntegerInputFieldListener {

	public FrameRateFieldListener(final ProgramOption programOption, final JFormattedTextField numberTextField) {
		super(programOption, numberTextField);
	}

	@Override
	public void setProgramOptionValue(final int value) {
		this.getProgramOption().setFrameRate(value);
	}
}
