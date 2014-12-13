package de.foobar.window.keyboards;

import de.foobar.keys.Key;
import java.awt.*;
import java.util.HashMap;

/**
 * Editor: van on 12.12.14.
 */
public class K70RGBDePositions {

	public static final HashMap<Key, Rectangle> KEY_BOUNDARIES = new HashMap<Key, Rectangle>();

	static {

		KEY_BOUNDARIES.put(Key.ESC, new Rectangle(0,0,40,40));
		KEY_BOUNDARIES.put(Key.ACCENT_KEY_CIRCUMFLEX, new Rectangle(0,45,40,40));
	}
}
