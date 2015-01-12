package de.foobar.window.listener;

import de.foobar.window.ProgramOption;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 * Editor: van on 11.01.15.
 */
public class IgnoreNonExistentKeyboardItemListener implements ItemListener
{
	private ProgramOption programOption;

	private JCheckBox nonKeyboardCheckBox;


	public IgnoreNonExistentKeyboardItemListener(final ProgramOption programOption, final JCheckBox nonKeyboardCheckBox)
	{
		this.programOption = programOption;
		this.nonKeyboardCheckBox = nonKeyboardCheckBox;
	}

	public ProgramOption getProgramOption()
	{
		return programOption;
	}

	public void setProgramOption(final ProgramOption programOption)
	{
		this.programOption = programOption;
	}

	public JCheckBox getNonKeyboardCheckBox() {
		return nonKeyboardCheckBox;
	}

	public void setNonKeyboardCheckBox(final JCheckBox nonKeyboardCheckBox) {
		this.nonKeyboardCheckBox = nonKeyboardCheckBox;
	}

	@Override
	public void itemStateChanged(final ItemEvent e)
	{
		this.programOption.setIgnoreNonexistentKeyboard(this.nonKeyboardCheckBox.isSelected());
	}
}
