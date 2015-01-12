package de.foobar.window.listener;

import de.foobar.common.BasicProgram;
import de.foobar.common.TimeManager;
import de.foobar.window.ProgramOption;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Editor: van on 11.01.15.
 */
public class StartStopProgramActionListener implements ActionListener
{
	private BasicProgram basicProgram;

	private ProgramOption programOption;

	private JButton startButton;

	private Boolean isRunning;

	public StartStopProgramActionListener(final BasicProgram basicProgram, final ProgramOption programOption,
	                                      final JButton startButton, final Boolean isRunning) {
		this.basicProgram = basicProgram;
		this.programOption = programOption;
		this.startButton = startButton;
		this.isRunning = isRunning;
	}

	@Override
	public void actionPerformed(final ActionEvent event) {

		if(isRunning) {
			//stop
			if (basicProgram != null)
			{
				basicProgram.stopProgram();
			}
			startButton.setText(" Start Program ");
			this.isRunning = false;
		} else {
			//start
			try{
				final TimeManager tm = new TimeManager();
				tm.parseProgram(programOption);
				tm.start();
				startButton.setText(" Stop Program ");
				this.isRunning = true;
			}
			catch (final Exception e)
			{
				System.out.println("Cannot parse and execute program ... Exception: " + e.getMessage());
			}

		}

	}
}
