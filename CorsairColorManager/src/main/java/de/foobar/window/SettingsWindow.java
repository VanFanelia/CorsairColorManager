package de.foobar.window;

import de.foobar.keys.KeyboardLayout;
import de.foobar.window.listener.FileChooserActionListener;
import de.foobar.window.listener.IgnoreNonExistentKeyboardItemListener;
import de.foobar.window.listener.VirtualKeyboardItemListener;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

/**
 * Editor: van on 11.01.15.
 */
public class SettingsWindow extends JFrame {

	private File selectedFile;

	private JTextField fileNameField;

	private JFormattedTextField durationTextField;

	private JFormattedTextField frameRateTextField;

	private ProgramOption programOption;

	public SettingsWindow() throws HeadlessException
	{
		super("Settings for Corsair Keyboard");
		initWindow();
	}

	public SettingsWindow(final ProgramOption programOption) throws HeadlessException {
		super("Settings for Corsair Keyboard");
		this.programOption = programOption;
		initWindow();

	}

	private void initWindow() {
		this.setSize(500, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		final JPanel container = new JPanel();
		this.getContentPane().add(container);
		container.setBorder(new EmptyBorder(10, 10, 10, 10));

		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}

		final GridBagLayout gbl = new GridBagLayout();
		container.setLayout(gbl);

		// Add components
		final JLabel announcement = new JLabel("Choose a Program to run:");
		addComponent(container, gbl, announcement, 0, 0, 2, 1, 1.0, 1.0);

		this.fileNameField = new JTextField();
		this.fileNameField.setEditable(false);
		addComponent(container, gbl, this.fileNameField, 0, 1, 1, 1, 1.0, 1.0);

		final JButton openFileButton = new JButton("Choose Program");
		openFileButton.addActionListener(new FileChooserActionListener(this));
		addComponent(container, gbl, openFileButton, 1, 1, 1, 1, 0, 1.0);

		//choose Keyboard Layout
		// Array für unsere JComboBox
		final KeyboardLayout layouts[] = { KeyboardLayout.DE};

		//JComboBox mit Bundesländer-Einträgen wird erstellt
		final JComboBox keyboardLayoutComboBox = new JComboBox(layouts);
		addComponent(container, gbl, keyboardLayoutComboBox, 0, 2, 2, 1, 1.0, 1.0);

		//boolean debugMode = params.containsKey("debug");
		final JCheckBox showKeyboardCheckBox = new JCheckBox( "show virtual keyboard", true );
		showKeyboardCheckBox.addItemListener(new VirtualKeyboardItemListener(this.getProgramOption(), showKeyboardCheckBox));
		showKeyboardCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
		addComponent(container, gbl, showKeyboardCheckBox, 0, 3, 1, 1, 0, 1.0);

		//boolean ignoreKeyboardMode = params.containsKey("ignorekeyboard");
		final JCheckBox ignoreKeyboardCheckBox = new JCheckBox( "ignore nonexistent keyboard", true );
		ignoreKeyboardCheckBox.addItemListener(new IgnoreNonExistentKeyboardItemListener(this.getProgramOption(), ignoreKeyboardCheckBox));
		ignoreKeyboardCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
		addComponent( container, gbl, ignoreKeyboardCheckBox, 0, 4, 1, 1, 0, 1.0 );

		//max duration
		final NumberFormat format = NumberFormat.getInstance();
		final NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		final DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
		otherSymbols.setDecimalSeparator((char) 0);
		otherSymbols.setGroupingSeparator((char) 0);
		final DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setDecimalFormatSymbols(otherSymbols);
		formatter.setFormat(decimalFormat);
		// If you want the value to be committed on each keystroke instead of focus lost
		formatter.setCommitsOnValidEdit(true);
		this.durationTextField = new JFormattedTextField(formatter);
		this.durationTextField.setText("300");

		addComponent(container, gbl, this.durationTextField, 0, 5, 1, 1, 0.25, 1.0);

		final JLabel durationLabel = new JLabel("Stop program after X seconds");
		addComponent(container, gbl, durationLabel, 1, 5, 1, 1, 0.75, 1.0);


		//framerate
		final NumberFormat frameRateFormat = NumberFormat.getInstance();
		final NumberFormatter frameRateFormatter = new NumberFormatter(frameRateFormat);
		frameRateFormatter.setValueClass(Integer.class);
		frameRateFormatter.setMinimum(0);
		frameRateFormatter.setMaximum(300);
		final DecimalFormatSymbols frameRateDecimalSymbol = new DecimalFormatSymbols();
		frameRateDecimalSymbol.setDecimalSeparator((char) 0);
		frameRateDecimalSymbol.setGroupingSeparator((char) 0);
		final DecimalFormat frameRateDecimalFormat = new DecimalFormat();
		frameRateDecimalFormat.setDecimalFormatSymbols(frameRateDecimalSymbol);
		frameRateFormatter.setFormat(frameRateDecimalFormat);
		// If you want the value to be committed on each keystroke instead of focus lost
		frameRateFormatter.setCommitsOnValidEdit(true);
		this.durationTextField = new JFormattedTextField(frameRateFormatter);
		this.durationTextField.setText("30");

		addComponent(container, gbl, this.durationTextField, 0, 6, 1, 1, 1.0, 1.0);

		final JLabel frameRateLabel = new JLabel("frames per second");
		addComponent(container, gbl, frameRateLabel, 1, 6, 1, 1, 1.0, 1.0);

		// Buttons
		final JButton startButton = new JButton(" Start Program ");
		startButton.addActionListener(new FileChooserActionListener(this));
		addComponent(container, gbl, startButton, 0, 7, 2, 1, 1.0, 1.0);

		this.setVisible(true);
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(final File selectedFile) {
		this.selectedFile = selectedFile;
		this.fileNameField.setText(selectedFile.getName());
	}


	protected static void addComponent( final Container cont, final GridBagLayout gbl, final Component toAdd,
	                                    final int x, final int y, final int width, final int height,
	                                    final double weightx, final double weighty )
	{
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x; gbc.gridy = y;
		gbc.gridwidth = width; gbc.gridheight = height;
		gbc.weightx = weightx; gbc.weighty = weighty;
		gbl.setConstraints( toAdd, gbc );

		cont.add(toAdd);
	}

	public ProgramOption getProgramOption() {
		return programOption;
	}

	public void setProgramOption(final ProgramOption programOption) {
		this.programOption = programOption;
	}

	public JTextField getFileNameField() {
		return fileNameField;
	}

	public void setFileNameField(final JTextField fileNameField) {
		this.fileNameField = fileNameField;
	}

	public JFormattedTextField getDurationTextField() {
		return durationTextField;
	}

	public void setDurationTextField(final JFormattedTextField durationTextField) {
		this.durationTextField = durationTextField;
	}

	public JFormattedTextField getFrameRateTextField() {
		return frameRateTextField;
	}

	public void setFrameRateTextField(final JFormattedTextField frameRateTextField) {
		this.frameRateTextField = frameRateTextField;
	}
}
