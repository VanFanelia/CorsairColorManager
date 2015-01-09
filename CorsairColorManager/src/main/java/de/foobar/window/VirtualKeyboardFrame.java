package de.foobar.window;

import de.foobar.keys.Key;
import de.foobar.keys.KeyToNumber;
import de.foobar.keys.KeyboardLayout;
import java.awt.*;
import java.util.HashMap;
import javax.swing.*;

/**
 * Editor: van on 12.12.14.
 */
public class VirtualKeyboardFrame extends JFrame {

	private HashMap<Key, JKeyboardButton> buttons = new HashMap<Key, JKeyboardButton>();

	private KeyboardLayout keyboardLayout;

	public VirtualKeyboardFrame(final KeyboardLayout keyboardLayout, final String title) throws HeadlessException {
		super(title);
		this.keyboardLayout = keyboardLayout;
		initWindow();
	}

	private void initWindow() {
		this.setSize(940,340);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

	public void changeButtonColor(final Integer keyNumber, final Color color)
	{
		final Key currentKey = KeyToNumber.getKeyForUSBInt(this.keyboardLayout, keyNumber);
		if(this.buttons.containsKey(currentKey))
		{
			this.buttons.get(currentKey).setColor(color);
		}
	}


}
