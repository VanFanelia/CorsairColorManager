package de.foobar.window.listener;

import de.foobar.window.SettingsWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.io.IOUtils;

/**
 * Editor: van on 11.01.15.
 */
public class FileChooserActionListener implements ActionListener {

	private SettingsWindow settingsWindow;

	public FileChooserActionListener(final SettingsWindow settingsWindow)
	{
		this.settingsWindow = settingsWindow;
	}

	@Override
	public void actionPerformed(final ActionEvent e)
	{

		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter( new FileNameExtensionFilter("Corsair program ", "txt", "json", "keyboard" ) );

		final int state = fc.showOpenDialog( null );

		if ( state == JFileChooser.APPROVE_OPTION )
		{
			final File file = fc.getSelectedFile();
			this.settingsWindow.setSelectedFile(file);
			this.settingsWindow.getProgramOption().setCurrentProgram(file);
			final FileInputStream stream;
			try
			{
				stream = new FileInputStream(file);
				final String json = IOUtils.toString(stream, "UTF8");
				this.settingsWindow.getProgramOption().setProgramCode(json);
			}
			catch (final Exception exception)
			{
				exception.printStackTrace();
			}

			System.out.println( file.getName() );
		}
		else
		{
			System.out.println("");
		}
	}

}

