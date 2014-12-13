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
		KEY_LABEL.put(Key.ACCENT_KEY_CIRCUMFLEX, "<html><center>°<br>^</center></html>");

		KEY_LABEL.put(Key.N1, "<html><center>!<br>1</center></html>");
		KEY_LABEL.put(Key.N2, "<html><center>\"<br>2²</center></html>");
		KEY_LABEL.put(Key.N3, "<html><center>§<br>3³</center></html>");
		KEY_LABEL.put(Key.N4, "<html><center>$<br>4</center></html>");
		KEY_LABEL.put(Key.N5, "<html><center>%<br>5</center></html>");
		KEY_LABEL.put(Key.N6, "<html><center>&<br>6</center></html>");
		KEY_LABEL.put(Key.N7, "<html><center>/<br>7{</center></html>");
		KEY_LABEL.put(Key.N8, "<html><center>(<br>8[</center></html>");
		KEY_LABEL.put(Key.N9, "<html><center>)<br>9]</center></html>");
		KEY_LABEL.put(Key.N0, "<html><center>=<br>0}</center></html>");
		KEY_LABEL.put(Key.SS, "<html><center>?<br>ß\\</center></html>");
		KEY_LABEL.put(Key.ACCENT_KEY_ACUTE, "<html><center>`<br>'</center></html>");

		KEY_LABEL.put(Key.BACKSPACE, "\u2190");
		KEY_LABEL.put(Key.TAB, "\u21B9");
		KEY_LABEL.put(Key.UE, "Ü");
		KEY_LABEL.put(Key.PLUS, "<html><center>*<br>+~</center></html>");
		KEY_LABEL.put(Key.ENTER, "\u21B2");

		KEY_LABEL.put(Key.SHIFT_HOLD,"\u21D3");
		KEY_LABEL.put(Key.OE, "Ö");
		KEY_LABEL.put(Key.AE, "Ä");
		KEY_LABEL.put(Key.HASH, "<html><center>'<br/>#</center></html>");

		KEY_LABEL.put(Key.SHIFT_LEFT,"\u21D1");
		KEY_LABEL.put(Key.ARROW_GREATER, "<html><center>&lt;<br/>&gt;|</center></html>");
		KEY_LABEL.put(Key.M, "<html><center>M<br/>µ</center></html>");
		KEY_LABEL.put(Key.SEMICOLON, "<html><center>;<br/>,</center></html>");
		KEY_LABEL.put(Key.DOUBLE_POINT, "<html><center>:<br/>.</center></html>");
		KEY_LABEL.put(Key.MINUS, "<html><center>-<br/>_</center></html>");
		KEY_LABEL.put(Key.SHIFT_RIGHT,"\u21D1");

		KEY_LABEL.put(Key.STRG_LEFT,"Strg");
		KEY_LABEL.put(Key.WINDOWS_LEFT, "\u2328");
		KEY_LABEL.put(Key.ALT_LEFT, "Alt");
		KEY_LABEL.put(Key.SPACE, "\u2015");
		KEY_LABEL.put(Key.ALT_GR, "Alt");
		KEY_LABEL.put(Key.CONTEXT_MENU, "\u25A4");
		KEY_LABEL.put(Key.WINDOWS_RIGHT, "\u2328");
		KEY_LABEL.put(Key.STRG_RIGHT,"Strg");

		KEY_LABEL.put(Key.ARROW_UP, "\u2191");
		KEY_LABEL.put(Key.ARROW_LEFT, "\u2190");
		KEY_LABEL.put(Key.ARROW_RIGHT, "\u2192");
		KEY_LABEL.put(Key.ARROW_DOWN, "\u2193");

		KEY_LABEL.put(Key.PRINT, "Druc");
		KEY_LABEL.put(Key.ROLL, "Roll");
		KEY_LABEL.put(Key.BREAK, "Paus");

		KEY_LABEL.put(Key.INSERT, "Einf");
		KEY_LABEL.put(Key.POS1, "Pos1");
		KEY_LABEL.put(Key.PAGE_UP, "<html><center>Bild<br/>\u2303</center></html>");

		KEY_LABEL.put(Key.ENTF, "Entf");
		KEY_LABEL.put(Key.END, "Ende");
		KEY_LABEL.put(Key.PAGE_DOWN, "<html><center>Bild<br/>\u2304</center></html>");

		KEY_LABEL.put(Key.SPECIAL_KEY_LIGHT, "\u2055");
		KEY_LABEL.put(Key.SPECIAL_KEY_LOCK, "L");

		KEY_LABEL.put(Key.MEDIA_STOP, "\u25FC");
		KEY_LABEL.put(Key.MEDIA_LEFT, "|\u25C4\u25C4");
		KEY_LABEL.put(Key.MEDIA_PLAY, "\u25BA\u25AE\u25AE");
		KEY_LABEL.put(Key.MEDIA_RIGHT, "\u25BA\u25BA|");
		KEY_LABEL.put(Key.MEDIA_MUTE, "\u2205");

		KEY_LABEL.put(Key.NUM, "<html><center>Num<br/>\u2304</center></html>");
		KEY_LABEL.put(Key.NUM_DIVIDE, "/");
		KEY_LABEL.put(Key.NUM_MULTIPLY, "*");
		KEY_LABEL.put(Key.NUM_MINUS, "-");

		KEY_LABEL.put(Key.NUM_7, "<html><center>7<br/>pos1</center></html>");
		KEY_LABEL.put(Key.NUM_8, "<html><center>8<br/>\u2191</center></html>");
		KEY_LABEL.put(Key.NUM_9, "<html><center>9<br/>Bild</center></html>");
		KEY_LABEL.put(Key.NUM_PLUS, "+");

		KEY_LABEL.put(Key.NUM_4, "<html><center>4<br/>\u2190</center></html>");
		KEY_LABEL.put(Key.NUM_5, "<html><center>5<br/>&nbsp;</center></html>");
		KEY_LABEL.put(Key.NUM_6, "<html><center>6<br/>\u2192</center></html>");

		KEY_LABEL.put(Key.NUM_1, "<html><center>1<br/>Ende</center></html>");
		KEY_LABEL.put(Key.NUM_2, "<html><center>2<br/>\u2193</center></html>");
		KEY_LABEL.put(Key.NUM_3, "<html><center>3<br/>Bild</center></html>");

		KEY_LABEL.put(Key.NUM_ENTER, "<html><center>Entr</center></html>");

		KEY_LABEL.put(Key.NUM_0, "<html><center>0<br/>Einfg</center></html>");
		KEY_LABEL.put(Key.NUM_COMMA, "<html><center>,<br/>Entf</center></html>");


	}

	public static String getLabelForKey(final Key key) {
		if(KEY_LABEL.containsKey(key))
		{
			return KEY_LABEL.get(key);
		}
		return "";
	}
}
