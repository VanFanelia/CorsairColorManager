package de.foobar.window.keyboards;

import de.foobar.keys.Key;
import java.util.HashMap;

/**
 * Editor: van on 12.12.14.
 */
public class DEKeyLabel {

	public static final HashMap<Key, String> KEY_LABEL = new HashMap<Key, String>();

	static {

		KEY_LABEL.put(Key.ESC, "ESC");
		KEY_LABEL.put(Key.ACCENT_KEY_CIRCUMFLEX, "<html>°<br>^</html>");
	}

	public static String getLabelForKey(final Key key) {
		if(KEY_LABEL.containsKey(key))
		{
			return KEY_LABEL.get(key);
		}
		return "";
	}
}
