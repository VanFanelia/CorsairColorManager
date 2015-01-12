package de.foobar.window.listener;

import de.foobar.window.ProgramOption;
import javax.swing.*;

/**
 * Editor: van on 11.01.15.
 */
public class DurationFieldListener extends IntegerInputFieldListener {

	public DurationFieldListener(final ProgramOption programOption, final JFormattedTextField numberTextField) {
		super(programOption, numberTextField);
	}

	@Override
	public void setProgramOptionValue(final int value) {
		this.getProgramOption().setProgramDuration(value);
	}
}
