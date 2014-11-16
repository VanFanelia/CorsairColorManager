package de.foobar.timemanager.keys;

import java.util.HashMap;

/**
 * Editor: van on 09.11.14.
 */
public final class KeyToNumber {


	public static final HashMap<Integer,Key> INT_TO_ENUM = new HashMap<Integer, Key>();

	public static final HashMap<Key, Integer> ENUM_TO_INT = new HashMap<Key, Integer>();

	static {
		INT_TO_ENUM.put(0, Key.ESC);
		INT_TO_ENUM.put(0, Key.ESC);
		INT_TO_ENUM.put(1, Key.ACCENT_KEY_CIRCUMFLEX);
		INT_TO_ENUM.put(2, Key.TAB);
		INT_TO_ENUM.put(3, Key.SHIFT_HOLD);
		INT_TO_ENUM.put(4, Key.SHIFT_LEFT);
		INT_TO_ENUM.put(5, Key.STRG_LEFT);
		INT_TO_ENUM.put(6, Key.F12);
		INT_TO_ENUM.put(7, Key.ACCENT_KEY_ACUTE);
		INT_TO_ENUM.put(8, Key.SPECIAL_KEY_LOCK);
		INT_TO_ENUM.put(9, Key.NUM_7);
		INT_TO_ENUM.put(12, Key.F1);
		INT_TO_ENUM.put(13, Key.N1);
		INT_TO_ENUM.put(14, Key.Q);
		INT_TO_ENUM.put(15, Key.A);
		INT_TO_ENUM.put(16, Key.ARROW_GREATER);
		INT_TO_ENUM.put(17, Key.WINDOWS_LEFT);
		INT_TO_ENUM.put(18, Key.PRINT);
		INT_TO_ENUM.put(20, Key.MEDIA_MUTE);
		INT_TO_ENUM.put(21, Key.NUM_8);
		INT_TO_ENUM.put(24, Key.F2);
		INT_TO_ENUM.put(25, Key.N2);
		INT_TO_ENUM.put(26, Key.W);
		INT_TO_ENUM.put(27, Key.S);
		INT_TO_ENUM.put(28, Key.Y);
		INT_TO_ENUM.put(29, Key.ALT_LEFT);
		INT_TO_ENUM.put(30, Key.ROLL);
		INT_TO_ENUM.put(31, Key.BACKSPACE);
		INT_TO_ENUM.put(32, Key.MEDIA_STOP);
		INT_TO_ENUM.put(33, Key.NUM_9);
		INT_TO_ENUM.put(36, Key.F3);
		INT_TO_ENUM.put(37, Key.N3);
		INT_TO_ENUM.put(38, Key.E);
		INT_TO_ENUM.put(39, Key.D);
		INT_TO_ENUM.put(40, Key.X);
		INT_TO_ENUM.put(42, Key.BREAK);
		INT_TO_ENUM.put(43, Key.ENTF);
		INT_TO_ENUM.put(44, Key.MEDIA_LEFT);
		INT_TO_ENUM.put(48, Key.F4);
		INT_TO_ENUM.put(49, Key.N4);
		INT_TO_ENUM.put(50, Key.R);
		INT_TO_ENUM.put(51, Key.F);
		INT_TO_ENUM.put(52, Key.C);
		INT_TO_ENUM.put(53, Key.SPACE);
		INT_TO_ENUM.put(54, Key.INSERT);
		INT_TO_ENUM.put(55, Key.END);
		INT_TO_ENUM.put(56, Key.MEDIA_PLAY);
		INT_TO_ENUM.put(57, Key.NUM_4);
		INT_TO_ENUM.put(60, Key.F5);
		INT_TO_ENUM.put(61, Key.N5);
		INT_TO_ENUM.put(62, Key.T);
		INT_TO_ENUM.put(63, Key.G);
		INT_TO_ENUM.put(64, Key.V);
		INT_TO_ENUM.put(66, Key.POS1);
		INT_TO_ENUM.put(67, Key.PAGE_DOWN);
		INT_TO_ENUM.put(68, Key.MEDIA_RIGHT);
		INT_TO_ENUM.put(69, Key.NUM_5);
		INT_TO_ENUM.put(72, Key.F6);
		INT_TO_ENUM.put(73, Key.N6);
		INT_TO_ENUM.put(74, Key.Z);
		INT_TO_ENUM.put(75, Key.H);
		INT_TO_ENUM.put(76, Key.B);
		INT_TO_ENUM.put(78, Key.PAGE_UP);
		INT_TO_ENUM.put(79, Key.SHIFT_RIGHT);
		INT_TO_ENUM.put(80, Key.NUM);
		INT_TO_ENUM.put(81, Key.NUM_6);
		INT_TO_ENUM.put(84, Key.F7);
		INT_TO_ENUM.put(85, Key.N7);
		INT_TO_ENUM.put(86, Key.U);
		INT_TO_ENUM.put(87, Key.J);
		INT_TO_ENUM.put(88, Key.N);
		INT_TO_ENUM.put(89, Key.ALT_GR);
		INT_TO_ENUM.put(90, Key.PLUS);
		INT_TO_ENUM.put(91, Key.STRG_RIGHT);
		INT_TO_ENUM.put(92, Key.NUM_DIVIDE);
		INT_TO_ENUM.put(93, Key.NUM_1);
		INT_TO_ENUM.put(96, Key.F8);
		INT_TO_ENUM.put(97, Key.N8);
		INT_TO_ENUM.put(98, Key.I);
		INT_TO_ENUM.put(99, Key.K);
		INT_TO_ENUM.put(100, Key.M);
		INT_TO_ENUM.put(101, Key.WINDOWS_RIGHT);
		INT_TO_ENUM.put(103, Key.ARROW_UP);
		INT_TO_ENUM.put(104, Key.NUM_MULTIPLY);
		INT_TO_ENUM.put(105, Key.NUM_2);
		INT_TO_ENUM.put(108, Key.F9);
		INT_TO_ENUM.put(109, Key.N9);
		INT_TO_ENUM.put(110, Key.O);
		INT_TO_ENUM.put(111, Key.L);
		INT_TO_ENUM.put(112, Key.SEMICOLON);
		INT_TO_ENUM.put(113, Key.CONTEXT_MENU);
		INT_TO_ENUM.put(114, Key.HASH);
		INT_TO_ENUM.put(115, Key.ARROW_LEFT);
		INT_TO_ENUM.put(116, Key.NUM_MINUS);
		INT_TO_ENUM.put(117, Key.NUM_3);
		INT_TO_ENUM.put(120, Key.F10);
		INT_TO_ENUM.put(121, Key.N0);
		INT_TO_ENUM.put(122, Key.P);
		INT_TO_ENUM.put(123, Key.OE);
		INT_TO_ENUM.put(124, Key.DOUBLE_POINT);
		INT_TO_ENUM.put(126, Key.ENTER);
		INT_TO_ENUM.put(127, Key.ARROW_DOWN);
		INT_TO_ENUM.put(128, Key.NUM_PLUS);
		INT_TO_ENUM.put(129, Key.NUM_0);
		INT_TO_ENUM.put(132, Key.F11);
		INT_TO_ENUM.put(133, Key.SS);
		INT_TO_ENUM.put(134, Key.UE);
		INT_TO_ENUM.put(135, Key.AE);
		INT_TO_ENUM.put(136, Key.MINUS);
		INT_TO_ENUM.put(137, Key.SPECIAL_KEY_LIGHT);
		INT_TO_ENUM.put(139, Key.ARROW_RIGHT);
		INT_TO_ENUM.put(140, Key.NUM_ENTER);
		INT_TO_ENUM.put(141, Key.NUM_COMMA);

		ENUM_TO_INT.put(Key.ESC, 0);
		ENUM_TO_INT.put(Key.ACCENT_KEY_CIRCUMFLEX, 1);
		ENUM_TO_INT.put(Key.TAB, 2);
		ENUM_TO_INT.put(Key.SHIFT_HOLD, 3);
		ENUM_TO_INT.put(Key.SHIFT_LEFT, 4);
		ENUM_TO_INT.put(Key.STRG_LEFT, 5);
		ENUM_TO_INT.put(Key.F12, 6);
		ENUM_TO_INT.put(Key.ACCENT_KEY_ACUTE, 7);
		ENUM_TO_INT.put(Key.SPECIAL_KEY_LOCK, 8);
		ENUM_TO_INT.put(Key.NUM_7, 9);
		ENUM_TO_INT.put(Key.F1, 12);
		ENUM_TO_INT.put(Key.N1, 13);
		ENUM_TO_INT.put(Key.Q, 14);
		ENUM_TO_INT.put(Key.A, 15);
		ENUM_TO_INT.put(Key.ARROW_GREATER, 16);
		ENUM_TO_INT.put(Key.WINDOWS_LEFT, 17);
		ENUM_TO_INT.put(Key.PRINT, 18);
		ENUM_TO_INT.put(Key.MEDIA_MUTE, 20);
		ENUM_TO_INT.put(Key.NUM_8, 21);
		ENUM_TO_INT.put(Key.F2, 24);
		ENUM_TO_INT.put(Key.N2, 25);
		ENUM_TO_INT.put(Key.W, 26);
		ENUM_TO_INT.put(Key.S, 27);
		ENUM_TO_INT.put(Key.Y, 28);
		ENUM_TO_INT.put(Key.ALT_LEFT, 29);
		ENUM_TO_INT.put(Key.ROLL, 30);
		ENUM_TO_INT.put(Key.BACKSPACE, 31);
		ENUM_TO_INT.put(Key.MEDIA_STOP, 32);
		ENUM_TO_INT.put(Key.NUM_9, 33);
		ENUM_TO_INT.put(Key.F3, 36);
		ENUM_TO_INT.put(Key.N3, 37);
		ENUM_TO_INT.put(Key.E, 38);
		ENUM_TO_INT.put(Key.D, 39);
		ENUM_TO_INT.put(Key.X, 40);
		ENUM_TO_INT.put(Key.BREAK, 42);
		ENUM_TO_INT.put(Key.ENTF, 43);
		ENUM_TO_INT.put(Key.MEDIA_LEFT, 44);
		ENUM_TO_INT.put(Key.F4, 48);
		ENUM_TO_INT.put(Key.N4, 49);
		ENUM_TO_INT.put(Key.R, 50);
		ENUM_TO_INT.put(Key.F, 51);
		ENUM_TO_INT.put(Key.C, 52);
		ENUM_TO_INT.put(Key.SPACE, 53);
		ENUM_TO_INT.put(Key.INSERT, 54);
		ENUM_TO_INT.put(Key.END, 55);
		ENUM_TO_INT.put(Key.MEDIA_PLAY, 56);
		ENUM_TO_INT.put(Key.NUM_4, 57);
		ENUM_TO_INT.put(Key.F5, 60);
		ENUM_TO_INT.put(Key.N5, 61);
		ENUM_TO_INT.put(Key.T, 62);
		ENUM_TO_INT.put(Key.G, 63);
		ENUM_TO_INT.put(Key.V, 64);
		ENUM_TO_INT.put(Key.POS1, 66);
		ENUM_TO_INT.put(Key.PAGE_DOWN, 67);
		ENUM_TO_INT.put(Key.MEDIA_RIGHT, 68);
		ENUM_TO_INT.put(Key.NUM_5, 69);
		ENUM_TO_INT.put(Key.F6, 72);
		ENUM_TO_INT.put(Key.N6, 73);
		ENUM_TO_INT.put(Key.Z, 74);
		ENUM_TO_INT.put(Key.H, 75);
		ENUM_TO_INT.put(Key.B, 76);
		ENUM_TO_INT.put(Key.PAGE_UP, 78);
		ENUM_TO_INT.put(Key.SHIFT_RIGHT, 79);
		ENUM_TO_INT.put(Key.NUM, 80);
		ENUM_TO_INT.put(Key.NUM_6, 81);
		ENUM_TO_INT.put(Key.F7, 84);
		ENUM_TO_INT.put(Key.N7, 85);
		ENUM_TO_INT.put(Key.U, 86);
		ENUM_TO_INT.put(Key.J, 87);
		ENUM_TO_INT.put(Key.N, 88);
		ENUM_TO_INT.put(Key.ALT_GR, 89);
		ENUM_TO_INT.put(Key.PLUS, 90);
		ENUM_TO_INT.put(Key.STRG_RIGHT, 91);
		ENUM_TO_INT.put(Key.NUM_DIVIDE, 92);
		ENUM_TO_INT.put(Key.NUM_1, 93);
		ENUM_TO_INT.put(Key.F8, 96);
		ENUM_TO_INT.put(Key.N8, 97);
		ENUM_TO_INT.put(Key.I, 98);
		ENUM_TO_INT.put(Key.K, 99);
		ENUM_TO_INT.put(Key.M, 100);
		ENUM_TO_INT.put(Key.WINDOWS_RIGHT, 101);
		ENUM_TO_INT.put(Key.ARROW_UP, 103);
		ENUM_TO_INT.put(Key.NUM_MULTIPLY, 104);
		ENUM_TO_INT.put(Key.NUM_2, 105);
		ENUM_TO_INT.put(Key.F9, 108);
		ENUM_TO_INT.put(Key.N9, 109);
		ENUM_TO_INT.put(Key.O, 110);
		ENUM_TO_INT.put(Key.L, 111);
		ENUM_TO_INT.put(Key.SEMICOLON, 112);
		ENUM_TO_INT.put(Key.CONTEXT_MENU, 113);
		ENUM_TO_INT.put(Key.HASH, 114);
		ENUM_TO_INT.put(Key.ARROW_LEFT, 115);
		ENUM_TO_INT.put(Key.NUM_MINUS, 116);
		ENUM_TO_INT.put(Key.NUM_3, 117);
		ENUM_TO_INT.put(Key.F10, 120);
		ENUM_TO_INT.put(Key.N0, 121);
		ENUM_TO_INT.put(Key.P, 122);
		ENUM_TO_INT.put(Key.OE, 123);
		ENUM_TO_INT.put(Key.DOUBLE_POINT, 124);
		ENUM_TO_INT.put(Key.ENTER, 126);
		ENUM_TO_INT.put(Key.ARROW_DOWN, 127);
		ENUM_TO_INT.put(Key.NUM_PLUS, 128);
		ENUM_TO_INT.put(Key.NUM_0, 129);
		ENUM_TO_INT.put(Key.F11, 132);
		ENUM_TO_INT.put(Key.SS, 133);
		ENUM_TO_INT.put(Key.UE, 134);
		ENUM_TO_INT.put(Key.AE, 135);
		ENUM_TO_INT.put(Key.MINUS, 136);
		ENUM_TO_INT.put(Key.SPECIAL_KEY_LIGHT, 137);
		ENUM_TO_INT.put(Key.ARROW_RIGHT, 139);
		ENUM_TO_INT.put(Key.NUM_ENTER, 140);
		ENUM_TO_INT.put(Key.NUM_COMMA, 141);
	}

	public KeyToNumber() {
	}

	/**
	 * get Key by number
	 * @param number
	 * @return
	 */
	public static Key getKey(final int number){
		return INT_TO_ENUM.get(number);
	}

	public static int getNumber(final Key key) {
		try {
			return ENUM_TO_INT.get(key);
		}catch (NullPointerException e) {
			return -1;
		}
	}

	public void test(){

	}
}
