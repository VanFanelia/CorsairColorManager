package de.foobar.window.listener;

import de.foobar.window.ProgramOption;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 * Editor: van on 11.01.15.
 */
public class VirtualKeyboardItemListener implements ItemListener
{
	private ProgramOption programOption;

	private JCheckBox virtualKeyboard;


	public VirtualKeyboardItemListener(final ProgramOption programOption, final JCheckBox virtualKeyboard)
	{
		this.programOption = programOption;
		this.virtualKeyboard = virtualKeyboard;
	}

	public ProgramOption getProgramOption()
	{
		return programOption;
	}

	public void setProgramOption(final ProgramOption programOption)
	{
		this.programOption = programOption;
	}

	public JCheckBox getVirtualKeyboard() {
		return virtualKeyboard;
	}

	public void setVirtualKeyboard(final JCheckBox virtualKeyboard) {
		this.virtualKeyboard = virtualKeyboard;
	}

	@Override
	public void itemStateChanged(final ItemEvent e)
	{
		this.programOption.setShowVirtualKeyboard(this.virtualKeyboard.isSelected());
		System.out.println(e);
	}
}
