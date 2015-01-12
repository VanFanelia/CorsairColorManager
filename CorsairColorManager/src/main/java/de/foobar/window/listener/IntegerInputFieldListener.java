package de.foobar.window.listener;

import de.foobar.window.ProgramOption;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Editor: van on 11.01.15.
 */
public abstract class IntegerInputFieldListener implements DocumentListener
{

	private ProgramOption programOption;

	private JFormattedTextField numberTextField;

	public IntegerInputFieldListener(final ProgramOption programOption, final JFormattedTextField numberTextField) {
		this.programOption = programOption;
		this.numberTextField = numberTextField;
	}


	@Override
	public void insertUpdate(final DocumentEvent e) {
		valueChanged();
	}

	@Override
	public void removeUpdate(final DocumentEvent e) {
		valueChanged();
	}

	@Override
	public void changedUpdate(final DocumentEvent e) {
		valueChanged();
	}

	public void valueChanged()
	{
		int value = this.getProgramOption().getProgramDuration();
		try{
			value = (int) this.getNumberTextField().getValue();
			this.setProgramOptionValue(value);
		}catch (final NumberFormatException e)
		{
			e.printStackTrace();
		}
	}

	public abstract void setProgramOptionValue(int value);

	public ProgramOption getProgramOption() {
		return programOption;
	}

	public void setProgramOption(final ProgramOption programOption)
	{
		this.programOption = programOption;
	}

	public JFormattedTextField getNumberTextField()
	{
		return numberTextField;
	}

	public void setNumberTextField(final JFormattedTextField numberTextField)
	{
		this.numberTextField = numberTextField;
	}

}
