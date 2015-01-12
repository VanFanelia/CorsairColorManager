package de.foobar.window;

import de.foobar.common.BasicProgram;
import de.foobar.keys.Key;
import de.foobar.keys.KeyToNumber;
import de.foobar.keys.KeyboardLayout;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import javax.swing.*;

/**
 * Editor: van on 12.12.14.
 */
public class VirtualKeyboardFrame extends JFrame {

	private HashMap<Key, JKeyboardButton> buttons = new HashMap<Key, JKeyboardButton>();

	private KeyboardLayout keyboardLayout;

	private BasicProgram basicProgram;

	public VirtualKeyboardFrame(final KeyboardLayout keyboardLayout, final BasicProgram basicProgram, final String title) throws HeadlessException {
		super(title);
		this.basicProgram = basicProgram;
		this.keyboardLayout = keyboardLayout;
		initWindow();
	}

	private void initWindow() {
		this.setSize(940,340);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		try
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}

		final Container container= this.getContentPane();
		container.setLayout(null);
		container.setBackground(Color.BLACK);

		// for k70RGB-DE

		for(final Key key: KeyToNumber.getKeyList(KeyboardLayout.DE))
		{
			final JKeyboardButton button = new JKeyboardButton(key, Color.white);
			container.add(button);
			this.buttons.put(key, button);
		}
	}

	public void windowClosing(final WindowEvent e){
		this.basicProgram.stopProgram();
	}

	public void changeButtonColor(final Integer keyNumber, final Color color)
	{
		final Key currentKey = KeyToNumber.getKeyForUSBInt(this.keyboardLayout, keyNumber);
		if(this.buttons.containsKey(currentKey))
		{
			this.buttons.get(currentKey).setColor(color);
		}
	}


}
