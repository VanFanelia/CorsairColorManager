package de.foobar.window;

import de.foobar.keys.Key;
import de.foobar.window.keyboards.DEKeyLabel;
import de.foobar.window.keyboards.K70RGBDePositions;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Editor: van on 12.12.14.
 */
public class JKeyboardButton extends JButton {

	private Key key;

	private Color color;

	public JKeyboardButton(final Key key,final Color color) {
		this.key = key;
		this.init();
		this.setColor(color);
	}

	private void init() {

		final Rectangle bounds = K70RGBDePositions.KEY_BOUNDARIES.get(this.key);
		if(bounds != null) {
			this.setBounds(bounds);
		}

		this.setMargin(new Insets(2, 2, 2, 2));
		this.setContentAreaFilled(true);
		this.setForeground(this.color);
		String label = DEKeyLabel.getLabelForKey(this.key);
		label = "".equals(label) ? this.key.name() : label;
		this.setText(label);
		this.setFocusPainted(false);
	}

	public void setColor(final Color color)
	{
		this.color = color;
		this.setForeground(color);
		this.setBackground(color.BLACK);

		final Border border = BorderFactory.createLineBorder(this.color,2,false);
		this.setBorder(border);
	}
}
