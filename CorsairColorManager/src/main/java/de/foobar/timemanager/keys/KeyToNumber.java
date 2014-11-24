package de.foobar.timemanager.keys;

import java.util.HashMap;
import java.util.Map;

import static de.foobar.timemanager.keys.Key.*;

/**
 * Editor: van on 09.11.14.
 */
public final class KeyToNumber {



	public static final HashMap<KeyboardLayout, HashMap<Integer,Key>> INTTOENUM = new HashMap<KeyboardLayout, HashMap<Integer,Key>>();

	public static final HashMap<KeyboardLayout, HashMap<Key, Integer>> ENUMTOINT = new HashMap<KeyboardLayout, HashMap<Key,Integer>>();

	static {

		final HashMap<Integer, Key> deIntToEnum = new HashMap<Integer, Key>();
		final HashMap<Key, Integer> deEnumToInt = new HashMap<Key, Integer>();

		deIntToEnum.put(0, ESC);
		deIntToEnum.put(1, ACCENT_KEY_CIRCUMFLEX);
		deIntToEnum.put(2, TAB);
		deIntToEnum.put(3, SHIFT_HOLD);
		deIntToEnum.put(4, SHIFT_LEFT);
		deIntToEnum.put(5, STRG_LEFT);
		deIntToEnum.put(6, F12);
		deIntToEnum.put(7, ACCENT_KEY_ACUTE);
		deIntToEnum.put(8, SPECIAL_KEY_LOCK);
		deIntToEnum.put(9, NUM_7);
		deIntToEnum.put(12, F1);
		deIntToEnum.put(13, N1);
		deIntToEnum.put(14, Q);
		deIntToEnum.put(15, A);
		deIntToEnum.put(16, ARROW_GREATER);
		deIntToEnum.put(17, WINDOWS_LEFT);
		deIntToEnum.put(18, PRINT);
		deIntToEnum.put(20, MEDIA_MUTE);
		deIntToEnum.put(21, NUM_8);
		deIntToEnum.put(24, F2);
		deIntToEnum.put(25, N2);
		deIntToEnum.put(26, W);
		deIntToEnum.put(27, S);
		deIntToEnum.put(28, Y);
		deIntToEnum.put(29, ALT_LEFT);
		deIntToEnum.put(30, ROLL);
		deIntToEnum.put(31, BACKSPACE);
		deIntToEnum.put(32, MEDIA_STOP);
		deIntToEnum.put(33, NUM_9);
		deIntToEnum.put(36, F3);
		deIntToEnum.put(37, N3);
		deIntToEnum.put(38, E);
		deIntToEnum.put(39, D);
		deIntToEnum.put(40, X);
		deIntToEnum.put(42, BREAK);
		deIntToEnum.put(43, ENTF);
		deIntToEnum.put(44, MEDIA_LEFT);
		deIntToEnum.put(48, F4);
		deIntToEnum.put(49, N4);
		deIntToEnum.put(50, R);
		deIntToEnum.put(51, F);
		deIntToEnum.put(52, C);
		deIntToEnum.put(53, SPACE);
		deIntToEnum.put(54, INSERT);
		deIntToEnum.put(55, END);
		deIntToEnum.put(56, MEDIA_PLAY);
		deIntToEnum.put(57, NUM_4);
		deIntToEnum.put(60, F5);
		deIntToEnum.put(61, N5);
		deIntToEnum.put(62, T);
		deIntToEnum.put(63, G);
		deIntToEnum.put(64, V);
		deIntToEnum.put(66, POS1);
		deIntToEnum.put(67, PAGE_DOWN);
		deIntToEnum.put(68, MEDIA_RIGHT);
		deIntToEnum.put(69, NUM_5);
		deIntToEnum.put(72, F6);
		deIntToEnum.put(73, N6);
		deIntToEnum.put(74, Z);
		deIntToEnum.put(75, H);
		deIntToEnum.put(76, B);
		deIntToEnum.put(78, PAGE_UP);
		deIntToEnum.put(79, SHIFT_RIGHT);
		deIntToEnum.put(80, NUM);
		deIntToEnum.put(81, NUM_6);
		deIntToEnum.put(84, F7);
		deIntToEnum.put(85, N7);
		deIntToEnum.put(86, U);
		deIntToEnum.put(87, J);
		deIntToEnum.put(88, N);
		deIntToEnum.put(89, ALT_GR);
		deIntToEnum.put(90, PLUS);
		deIntToEnum.put(91, STRG_RIGHT);
		deIntToEnum.put(92, NUM_DIVIDE);
		deIntToEnum.put(93, NUM_1);
		deIntToEnum.put(96, F8);
		deIntToEnum.put(97, N8);
		deIntToEnum.put(98, I);
		deIntToEnum.put(99, K);
		deIntToEnum.put(100, M);
		deIntToEnum.put(101, WINDOWS_RIGHT);
		deIntToEnum.put(103, ARROW_UP);
		deIntToEnum.put(104, NUM_MULTIPLY);
		deIntToEnum.put(105, NUM_2);
		deIntToEnum.put(108, F9);
		deIntToEnum.put(109, N9);
		deIntToEnum.put(110, O);
		deIntToEnum.put(111, L);
		deIntToEnum.put(112, SEMICOLON);
		deIntToEnum.put(113, CONTEXT_MENU);
		deIntToEnum.put(114, HASH);
		deIntToEnum.put(115, ARROW_LEFT);
		deIntToEnum.put(116, NUM_MINUS);
		deIntToEnum.put(117, NUM_3);
		deIntToEnum.put(120, F10);
		deIntToEnum.put(121, N0);
		deIntToEnum.put(122, P);
		deIntToEnum.put(123, OE);
		deIntToEnum.put(124, DOUBLE_POINT);
		deIntToEnum.put(126, ENTER);
		deIntToEnum.put(127, ARROW_DOWN);
		deIntToEnum.put(128, NUM_PLUS);
		deIntToEnum.put(129, NUM_0);
		deIntToEnum.put(132, F11);
		deIntToEnum.put(133, SS);
		deIntToEnum.put(134, UE);
		deIntToEnum.put(135, AE);
		deIntToEnum.put(136, MINUS);
		deIntToEnum.put(137, SPECIAL_KEY_LIGHT);
		deIntToEnum.put(139, ARROW_RIGHT);
		deIntToEnum.put(140, NUM_ENTER);
		deIntToEnum.put(141, NUM_COMMA);

		for(final Map.Entry<Integer, Key> entry: deIntToEnum.entrySet())
		{
			deEnumToInt.put(entry.getValue(), entry.getKey());
		}

		// add to maps
		INTTOENUM.put(KeyboardLayout.DE, deIntToEnum);
		ENUMTOINT.put(KeyboardLayout.DE, deEnumToInt);


		// Englisch Layout
		final HashMap<Integer, Key> enIntToEnum = new HashMap<Integer, Key>();
		final HashMap<Key, Integer> enEnumToInt = new HashMap<Key, Integer>();

        deIntToEnum.put(1, TILDE);
        deIntToEnum.put(2, ESC);
        deIntToEnum.put(3, CAPS_LOCK);
        deIntToEnum.put(4, TAB );
        deIntToEnum.put(5, CTRL_LEFT);
        deIntToEnum.put(6, SHIFT_LEFT);
        deIntToEnum.put(7, PLUS);
        deIntToEnum.put(8, F12);
        deIntToEnum.put(9, NUM_7);
        deIntToEnum.put(10, SPECIAL_KEY_LOCK);
        deIntToEnum.put(13, N1);
        deIntToEnum.put(14, F1);
        deIntToEnum.put(15, A);
        deIntToEnum.put(16, Q);
        deIntToEnum.put(17, WINDOWS_LEFT);
        deIntToEnum.put(20, PRINT);
        deIntToEnum.put(21, NUM_8);
        deIntToEnum.put(22, MEDIA_MUTE);
        deIntToEnum.put(25, N2);
        deIntToEnum.put(26, F2);
        deIntToEnum.put(27, S);
        deIntToEnum.put(28, W);
        deIntToEnum.put(29, ALT_LEFT);
        deIntToEnum.put(30, Z);
        deIntToEnum.put(31, BACKSPACE);
        deIntToEnum.put(32, SCROLL_LOCK);
        deIntToEnum.put(33, NUM_9);
        deIntToEnum.put(34, MEDIA_STOP);
        deIntToEnum.put(37, N3);
        deIntToEnum.put(38, F3);
        deIntToEnum.put(39, D);
        deIntToEnum.put(40, E);
        deIntToEnum.put(42, X);
        deIntToEnum.put(43, DELETE);
        deIntToEnum.put(44, PAUSE_BREAK);
        deIntToEnum.put(46, MEDIA_LEFT);
        deIntToEnum.put(49, N4);
        deIntToEnum.put(50, F4);
        deIntToEnum.put(51, F);
        deIntToEnum.put(52, R);
        deIntToEnum.put(53, SPACE);
        deIntToEnum.put(54, C);
        deIntToEnum.put(55, END);
        deIntToEnum.put(56, INSERT);
        deIntToEnum.put(57, NUM_4);
        deIntToEnum.put(58, MEDIA_PLAY);
        deIntToEnum.put(61, N5);
        deIntToEnum.put(62, F5);
        deIntToEnum.put(63, G);
        deIntToEnum.put(64, T);
        deIntToEnum.put(66, V);
        deIntToEnum.put(67, PAGE_DOWN);
        deIntToEnum.put(68, HOME);
        deIntToEnum.put(69, NUM_5);
        deIntToEnum.put(70, MEDIA_RIGHT);
        deIntToEnum.put(73, N6);
        deIntToEnum.put(74, F6);
        deIntToEnum.put(75, H);
        deIntToEnum.put(76, Y);
        deIntToEnum.put(78, B);
        deIntToEnum.put(79, SHIFT_RIGHT);
        deIntToEnum.put(80, PAGE_UP);
        deIntToEnum.put(81, NUM_6);
        deIntToEnum.put(82, NUM_LOCK);
        deIntToEnum.put(85, N7);
        deIntToEnum.put(86, F7);
        deIntToEnum.put(87, J);
        deIntToEnum.put(88, U);
        deIntToEnum.put(89, ALT_RIGHT);
        deIntToEnum.put(90, N);
        deIntToEnum.put(91, CTRL_RIGHT);
        deIntToEnum.put(92, BRACKET_CLOSE);
        deIntToEnum.put(93, NUM_1);
        deIntToEnum.put(94, NUM_DIVIDE);
		deIntToEnum.put(97, N8);
        deIntToEnum.put(98, F8);
		deIntToEnum.put(99, K);
		deIntToEnum.put(100, I);
		deIntToEnum.put(101, WINDOWS_RIGHT);
		deIntToEnum.put(102, M);
		deIntToEnum.put(103, ARROW_UP);
		deIntToEnum.put(104, PIPE);
        deIntToEnum.put(105, NUM_2);
        deIntToEnum.put(106, NUM_MULTIPLY);
		deIntToEnum.put(109, N9);
        deIntToEnum.put(110, F9);
		deIntToEnum.put(111, L);
		deIntToEnum.put(112, O);
		deIntToEnum.put(113, MENU);
		deIntToEnum.put(114, ARROW_SMALLER);
		deIntToEnum.put(115, ARROW_LEFT);
		deIntToEnum.put(117, NUM_3);
        deIntToEnum.put(118, NUM_MINUS);
        deIntToEnum.put(121, N0);
        deIntToEnum.put(122, F10);
		deIntToEnum.put(123, DOUBLE_POINT);
        deIntToEnum.put(124, P);
		deIntToEnum.put(126, ARROW_GREATER);
        deIntToEnum.put(127, ARROW_DOWN);
		deIntToEnum.put(128, ENTER);
		deIntToEnum.put(129, NUM_0);
        deIntToEnum.put(130, NUM_PLUS);
		deIntToEnum.put(133, MINUS);
        deIntToEnum.put(134, F11);
		deIntToEnum.put(135, QUOTATION_MARK);
        deIntToEnum.put(136, BRACKET_OPEN);
        deIntToEnum.put(137, SPECIAL_KEY_LIGHT);
        deIntToEnum.put(138, QUESTION_MARK);
        deIntToEnum.put(139, ARROW_RIGHT);
        deIntToEnum.put(141, NUM_DOT);
        deIntToEnum.put(142, NUM_ENTER);

		for(final Map.Entry<Integer, Key> entry: deIntToEnum.entrySet())
		{
			deEnumToInt.put(entry.getValue(), entry.getKey());
		}

		// add to maps
		INTTOENUM.put(KeyboardLayout.EN, enIntToEnum);
		ENUMTOINT.put(KeyboardLayout.EN, enEnumToInt);
	}

	public KeyToNumber() {
	}

	/**
	 * get Key by number
	 * @param number
	 * @param keyboardLayout
	 * @return
	 */
	public static Key getKey(final KeyboardLayout keyboardLayout, final int number)
	{

		return INTTOENUM.get(keyboardLayout).get(number);
	}

	public static int getNumber(final KeyboardLayout keyboardLayout, final Key key) {
		try {
			return ENUMTOINT.get(keyboardLayout).get(key);
		}catch (NullPointerException e) {
			return -1;
		}
	}

}
