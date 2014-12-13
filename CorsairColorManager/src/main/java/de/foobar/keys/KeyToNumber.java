package de.foobar.keys;

import static de.foobar.keys.Key.A;
import static de.foobar.keys.Key.ACCENT_KEY_ACUTE;
import static de.foobar.keys.Key.ACCENT_KEY_CIRCUMFLEX;
import static de.foobar.keys.Key.AE;
import static de.foobar.keys.Key.ALT_GR;
import static de.foobar.keys.Key.ALT_LEFT;
import static de.foobar.keys.Key.ALT_RIGHT;
import static de.foobar.keys.Key.ARROW_DOWN;
import static de.foobar.keys.Key.ARROW_GREATER;
import static de.foobar.keys.Key.ARROW_LEFT;
import static de.foobar.keys.Key.ARROW_RIGHT;
import static de.foobar.keys.Key.ARROW_SMALLER;
import static de.foobar.keys.Key.ARROW_UP;
import static de.foobar.keys.Key.B;
import static de.foobar.keys.Key.BACKSPACE;
import static de.foobar.keys.Key.BRACKET_CLOSE;
import static de.foobar.keys.Key.BRACKET_OPEN;
import static de.foobar.keys.Key.BREAK;
import static de.foobar.keys.Key.C;
import static de.foobar.keys.Key.CAPS_LOCK;
import static de.foobar.keys.Key.CONTEXT_MENU;
import static de.foobar.keys.Key.CTRL_LEFT;
import static de.foobar.keys.Key.CTRL_RIGHT;
import static de.foobar.keys.Key.D;
import static de.foobar.keys.Key.DELETE;
import static de.foobar.keys.Key.DOUBLE_POINT;
import static de.foobar.keys.Key.E;
import static de.foobar.keys.Key.END;
import static de.foobar.keys.Key.ENTER;
import static de.foobar.keys.Key.ENTF;
import static de.foobar.keys.Key.ESC;
import static de.foobar.keys.Key.F;
import static de.foobar.keys.Key.F1;
import static de.foobar.keys.Key.F10;
import static de.foobar.keys.Key.F11;
import static de.foobar.keys.Key.F12;
import static de.foobar.keys.Key.F2;
import static de.foobar.keys.Key.F3;
import static de.foobar.keys.Key.F4;
import static de.foobar.keys.Key.F5;
import static de.foobar.keys.Key.F6;
import static de.foobar.keys.Key.F7;
import static de.foobar.keys.Key.F8;
import static de.foobar.keys.Key.F9;
import static de.foobar.keys.Key.G;
import static de.foobar.keys.Key.H;
import static de.foobar.keys.Key.HASH;
import static de.foobar.keys.Key.HOME;
import static de.foobar.keys.Key.I;
import static de.foobar.keys.Key.INSERT;
import static de.foobar.keys.Key.J;
import static de.foobar.keys.Key.K;
import static de.foobar.keys.Key.L;
import static de.foobar.keys.Key.M;
import static de.foobar.keys.Key.MEDIA_LEFT;
import static de.foobar.keys.Key.MEDIA_MUTE;
import static de.foobar.keys.Key.MEDIA_PLAY;
import static de.foobar.keys.Key.MEDIA_RIGHT;
import static de.foobar.keys.Key.MEDIA_STOP;
import static de.foobar.keys.Key.MENU;
import static de.foobar.keys.Key.MINUS;
import static de.foobar.keys.Key.N;
import static de.foobar.keys.Key.N0;
import static de.foobar.keys.Key.N1;
import static de.foobar.keys.Key.N2;
import static de.foobar.keys.Key.N3;
import static de.foobar.keys.Key.N4;
import static de.foobar.keys.Key.N5;
import static de.foobar.keys.Key.N6;
import static de.foobar.keys.Key.N7;
import static de.foobar.keys.Key.N8;
import static de.foobar.keys.Key.N9;
import static de.foobar.keys.Key.NUM;
import static de.foobar.keys.Key.NUM_0;
import static de.foobar.keys.Key.NUM_1;
import static de.foobar.keys.Key.NUM_2;
import static de.foobar.keys.Key.NUM_3;
import static de.foobar.keys.Key.NUM_4;
import static de.foobar.keys.Key.NUM_5;
import static de.foobar.keys.Key.NUM_6;
import static de.foobar.keys.Key.NUM_7;
import static de.foobar.keys.Key.NUM_8;
import static de.foobar.keys.Key.NUM_9;
import static de.foobar.keys.Key.NUM_COMMA;
import static de.foobar.keys.Key.NUM_DIVIDE;
import static de.foobar.keys.Key.NUM_DOT;
import static de.foobar.keys.Key.NUM_ENTER;
import static de.foobar.keys.Key.NUM_LOCK;
import static de.foobar.keys.Key.NUM_MINUS;
import static de.foobar.keys.Key.NUM_MULTIPLY;
import static de.foobar.keys.Key.NUM_PLUS;
import static de.foobar.keys.Key.O;
import static de.foobar.keys.Key.OE;
import static de.foobar.keys.Key.P;
import static de.foobar.keys.Key.PAGE_DOWN;
import static de.foobar.keys.Key.PAGE_UP;
import static de.foobar.keys.Key.PAUSE_BREAK;
import static de.foobar.keys.Key.PIPE;
import static de.foobar.keys.Key.PLUS;
import static de.foobar.keys.Key.POS1;
import static de.foobar.keys.Key.PRINT;
import static de.foobar.keys.Key.Q;
import static de.foobar.keys.Key.QUESTION_MARK;
import static de.foobar.keys.Key.QUOTATION_MARK;
import static de.foobar.keys.Key.R;
import static de.foobar.keys.Key.ROLL;
import static de.foobar.keys.Key.S;
import static de.foobar.keys.Key.SCROLL_LOCK;
import static de.foobar.keys.Key.SEMICOLON;
import static de.foobar.keys.Key.SHIFT_HOLD;
import static de.foobar.keys.Key.SHIFT_LEFT;
import static de.foobar.keys.Key.SHIFT_RIGHT;
import static de.foobar.keys.Key.SPACE;
import static de.foobar.keys.Key.SPECIAL_KEY_LIGHT;
import static de.foobar.keys.Key.SPECIAL_KEY_LOCK;
import static de.foobar.keys.Key.SS;
import static de.foobar.keys.Key.STRG_LEFT;
import static de.foobar.keys.Key.STRG_RIGHT;
import static de.foobar.keys.Key.T;
import static de.foobar.keys.Key.TAB;
import static de.foobar.keys.Key.TILDE;
import static de.foobar.keys.Key.U;
import static de.foobar.keys.Key.UE;
import static de.foobar.keys.Key.V;
import static de.foobar.keys.Key.W;
import static de.foobar.keys.Key.WINDOWS_LEFT;
import static de.foobar.keys.Key.WINDOWS_RIGHT;
import static de.foobar.keys.Key.X;
import static de.foobar.keys.Key.Y;
import static de.foobar.keys.Key.Z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        enIntToEnum.put(1, TILDE);
        enIntToEnum.put(2, ESC);
        enIntToEnum.put(3, CAPS_LOCK);
        enIntToEnum.put(4, TAB );
        enIntToEnum.put(5, CTRL_LEFT);
        enIntToEnum.put(6, SHIFT_LEFT);
        enIntToEnum.put(7, PLUS);
        enIntToEnum.put(8, F12);
        enIntToEnum.put(9, NUM_7);
        enIntToEnum.put(10, SPECIAL_KEY_LOCK);
        enIntToEnum.put(13, N1);
        enIntToEnum.put(14, F1);
        enIntToEnum.put(15, A);
        enIntToEnum.put(16, Q);
        enIntToEnum.put(17, WINDOWS_LEFT);
        enIntToEnum.put(20, PRINT);
        enIntToEnum.put(21, NUM_8);
        enIntToEnum.put(22, MEDIA_MUTE);
        enIntToEnum.put(25, N2);
        enIntToEnum.put(26, F2);
        enIntToEnum.put(27, S);
        enIntToEnum.put(28, W);
        enIntToEnum.put(29, ALT_LEFT);
        enIntToEnum.put(30, Z);
        enIntToEnum.put(31, BACKSPACE);
        enIntToEnum.put(32, SCROLL_LOCK);
        enIntToEnum.put(33, NUM_9);
        enIntToEnum.put(34, MEDIA_STOP);
        enIntToEnum.put(37, N3);
        enIntToEnum.put(38, F3);
        enIntToEnum.put(39, D);
        enIntToEnum.put(40, E);
        enIntToEnum.put(42, X);
        enIntToEnum.put(43, DELETE);
        enIntToEnum.put(44, PAUSE_BREAK);
        enIntToEnum.put(46, MEDIA_LEFT);
        enIntToEnum.put(49, N4);
        enIntToEnum.put(50, F4);
        enIntToEnum.put(51, F);
        enIntToEnum.put(52, R);
        enIntToEnum.put(53, SPACE);
        enIntToEnum.put(54, C);
        enIntToEnum.put(55, END);
        enIntToEnum.put(56, INSERT);
        enIntToEnum.put(57, NUM_4);
        enIntToEnum.put(58, MEDIA_PLAY);
        enIntToEnum.put(61, N5);
        enIntToEnum.put(62, F5);
        enIntToEnum.put(63, G);
        enIntToEnum.put(64, T);
        enIntToEnum.put(66, V);
        enIntToEnum.put(67, PAGE_DOWN);
        enIntToEnum.put(68, HOME);
        enIntToEnum.put(69, NUM_5);
        enIntToEnum.put(70, MEDIA_RIGHT);
        enIntToEnum.put(73, N6);
        enIntToEnum.put(74, F6);
        enIntToEnum.put(75, H);
        enIntToEnum.put(76, Y);
        enIntToEnum.put(78, B);
        enIntToEnum.put(79, SHIFT_RIGHT);
        enIntToEnum.put(80, PAGE_UP);
        enIntToEnum.put(81, NUM_6);
        enIntToEnum.put(82, NUM_LOCK);
        enIntToEnum.put(85, N7);
        enIntToEnum.put(86, F7);
        enIntToEnum.put(87, J);
        enIntToEnum.put(88, U);
        enIntToEnum.put(89, ALT_RIGHT);
        enIntToEnum.put(90, N);
        enIntToEnum.put(91, CTRL_RIGHT);
        enIntToEnum.put(92, BRACKET_CLOSE);
        enIntToEnum.put(93, NUM_1);
        enIntToEnum.put(94, NUM_DIVIDE);
		enIntToEnum.put(97, N8);
        enIntToEnum.put(98, F8);
		enIntToEnum.put(99, K);
		enIntToEnum.put(100, I);
		enIntToEnum.put(101, WINDOWS_RIGHT);
		enIntToEnum.put(102, M);
		enIntToEnum.put(103, ARROW_UP);
		enIntToEnum.put(104, PIPE);
        enIntToEnum.put(105, NUM_2);
        enIntToEnum.put(106, NUM_MULTIPLY);
		enIntToEnum.put(109, N9);
        enIntToEnum.put(110, F9);
		enIntToEnum.put(111, L);
		enIntToEnum.put(112, O);
		enIntToEnum.put(113, MENU);
		enIntToEnum.put(114, ARROW_SMALLER);
		enIntToEnum.put(115, ARROW_LEFT);
		enIntToEnum.put(117, NUM_3);
        enIntToEnum.put(118, NUM_MINUS);
        enIntToEnum.put(121, N0);
        enIntToEnum.put(122, F10);
		enIntToEnum.put(123, DOUBLE_POINT);
        enIntToEnum.put(124, P);
		enIntToEnum.put(126, ARROW_GREATER);
        enIntToEnum.put(127, ARROW_DOWN);
		enIntToEnum.put(128, ENTER);
		enIntToEnum.put(129, NUM_0);
        enIntToEnum.put(130, NUM_PLUS);
		enIntToEnum.put(133, MINUS);
        enIntToEnum.put(134, F11);
		enIntToEnum.put(135, QUOTATION_MARK);
        enIntToEnum.put(136, BRACKET_OPEN);
        enIntToEnum.put(137, SPECIAL_KEY_LIGHT);
        enIntToEnum.put(138, QUESTION_MARK);
        enIntToEnum.put(139, ARROW_RIGHT);
        enIntToEnum.put(141, NUM_DOT);
        enIntToEnum.put(142, NUM_ENTER);

		for(final Map.Entry<Integer, Key> entry: enIntToEnum.entrySet())
		{
			enEnumToInt.put(entry.getValue(), entry.getKey());
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

	public static Set<Key> getKeyList(final KeyboardLayout layout)
	{
		return ENUMTOINT.get(layout).keySet();
	}

	public static List<Integer> getNumber(final KeyboardLayout keyboardLayout, final KeyReference key) {
		try {
			if(key instanceof Key) {
				final List<Integer> result = new ArrayList<Integer>();
				final Integer toAdd = ENUMTOINT.get(keyboardLayout).get(key);
				if(toAdd != null && toAdd >= 0)
				{
					result.add(toAdd);
				}
				return result;
			}
			if(key instanceof KeyGroup)
			{
				final List<Integer> result = new ArrayList<Integer>();
				final List<Key> keys = ((KeyGroup) key).getKeyList();
				for(final Key k : keys)
				{
					result.add(ENUMTOINT.get(keyboardLayout).get(k));
				}
				return result;
			}
			return new ArrayList<Integer>();
		}catch (NullPointerException e) {
			final List<Integer> errorList =  new ArrayList<Integer>();
			errorList.add(-1);
			return errorList;
		}
	}

}
