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

		KEY_BOUNDARIES.put(Key.ESC, new Rectangle(0,60,40,40));
		KEY_BOUNDARIES.put(Key.F1, new Rectangle(70,60,40,40));
		KEY_BOUNDARIES.put(Key.F2, new Rectangle(110,60,40,40));
		KEY_BOUNDARIES.put(Key.F3, new Rectangle(150,60,40,40));
		KEY_BOUNDARIES.put(Key.F4, new Rectangle(190,60,40,40));
		KEY_BOUNDARIES.put(Key.F5, new Rectangle(260,60,40,40));
		KEY_BOUNDARIES.put(Key.F6, new Rectangle(300,60,40,40));
		KEY_BOUNDARIES.put(Key.F7, new Rectangle(340,60,40,40));
		KEY_BOUNDARIES.put(Key.F8, new Rectangle(380,60,40,40));
		KEY_BOUNDARIES.put(Key.F9, new Rectangle(440,60,40,40));
		KEY_BOUNDARIES.put(Key.F10, new Rectangle(480,60,40,40));
		KEY_BOUNDARIES.put(Key.F11, new Rectangle(520,60,40,40));
		KEY_BOUNDARIES.put(Key.F12, new Rectangle(560,60,40,40));

		KEY_BOUNDARIES.put(Key.SPECIAL_KEY_LIGHT,   new Rectangle(630,30,20,20));
		KEY_BOUNDARIES.put(Key.SPECIAL_KEY_LOCK,    new Rectangle(670,30,20,20));
		KEY_BOUNDARIES.put(Key.MEDIA_MUTE,          new Rectangle(800,25,40,30));

		KEY_BOUNDARIES.put(Key.PRINT,   new Rectangle(620,60,40,40));
		KEY_BOUNDARIES.put(Key.ROLL,    new Rectangle(660,60,40,40));
		KEY_BOUNDARIES.put(Key.BREAK,   new Rectangle(700,60,40,40));

		KEY_BOUNDARIES.put(Key.INSERT,  new Rectangle(620,105,40,40));
		KEY_BOUNDARIES.put(Key.POS1,    new Rectangle(660,105,40,40));
		KEY_BOUNDARIES.put(Key.PAGE_UP, new Rectangle(700,105,40,40));

		KEY_BOUNDARIES.put(Key.ENTF,    new Rectangle(620,150,40,40));
		KEY_BOUNDARIES.put(Key.END,     new Rectangle(660,150,40,40));
		KEY_BOUNDARIES.put(Key.PAGE_DOWN,new Rectangle(700,150,40,40));

		KEY_BOUNDARIES.put(Key.ARROW_UP,    new Rectangle(660,240,40,40));
		KEY_BOUNDARIES.put(Key.ARROW_LEFT,    new Rectangle(620,285,40,40));
		KEY_BOUNDARIES.put(Key.ARROW_DOWN,     new Rectangle(660,285,40,40));
		KEY_BOUNDARIES.put(Key.ARROW_RIGHT, new Rectangle(700,285,40,40));

		KEY_BOUNDARIES.put(Key.MEDIA_STOP,   new Rectangle(760,65,40,30));
		KEY_BOUNDARIES.put(Key.MEDIA_LEFT,    new Rectangle(800,65,40,30));
		KEY_BOUNDARIES.put(Key.MEDIA_PLAY,   new Rectangle(840,65,40,30));
		KEY_BOUNDARIES.put(Key.MEDIA_RIGHT,   new Rectangle(880,65,40,30));

		KEY_BOUNDARIES.put(Key.NUM,   new Rectangle(760,105,40,40));
		KEY_BOUNDARIES.put(Key.NUM_DIVIDE,    new Rectangle(800,105,40,40));
		KEY_BOUNDARIES.put(Key.NUM_MULTIPLY,   new Rectangle(840,105,40,40));
		KEY_BOUNDARIES.put(Key.NUM_MINUS,   new Rectangle(880,105,40,40));

		KEY_BOUNDARIES.put(Key.NUM_7,   new Rectangle(760,150,40,40));
		KEY_BOUNDARIES.put(Key.NUM_8,    new Rectangle(800,150,40,40));
		KEY_BOUNDARIES.put(Key.NUM_9,   new Rectangle(840,150,40,40));

		KEY_BOUNDARIES.put(Key.NUM_4,   new Rectangle(760,195,40,40));
		KEY_BOUNDARIES.put(Key.NUM_5,    new Rectangle(800,195,40,40));
		KEY_BOUNDARIES.put(Key.NUM_6,   new Rectangle(840,195,40,40));
		KEY_BOUNDARIES.put(Key.NUM_PLUS,   new Rectangle(880,150,40,85));

		KEY_BOUNDARIES.put(Key.NUM_1,   new Rectangle(760,240,40,40));
		KEY_BOUNDARIES.put(Key.NUM_2,    new Rectangle(800,240,40,40));
		KEY_BOUNDARIES.put(Key.NUM_3,   new Rectangle(840,240,40,40));
		KEY_BOUNDARIES.put(Key.NUM_ENTER,   new Rectangle(880,240,40,85));

		KEY_BOUNDARIES.put(Key.NUM_0,    new Rectangle(760,285,80,40));
		KEY_BOUNDARIES.put(Key.NUM_COMMA,    new Rectangle(840,285,40,40));


		KEY_BOUNDARIES.put(Key.ACCENT_KEY_CIRCUMFLEX, new Rectangle(0,105,40,40));
		KEY_BOUNDARIES.put(Key.N1, new Rectangle(40,105,40,40));
		KEY_BOUNDARIES.put(Key.N2, new Rectangle(80,105,40,40));
		KEY_BOUNDARIES.put(Key.N3, new Rectangle(120,105,40,40));
		KEY_BOUNDARIES.put(Key.N4, new Rectangle(160,105,40,40));
		KEY_BOUNDARIES.put(Key.N5, new Rectangle(200,105,40,40));
		KEY_BOUNDARIES.put(Key.N6, new Rectangle(240,105,40,40));
		KEY_BOUNDARIES.put(Key.N7, new Rectangle(280,105,40,40));
		KEY_BOUNDARIES.put(Key.N8, new Rectangle(320,105,40,40));
		KEY_BOUNDARIES.put(Key.N9, new Rectangle(360,105,40,40));
		KEY_BOUNDARIES.put(Key.N0, new Rectangle(400,105,40,40));
		KEY_BOUNDARIES.put(Key.SS, new Rectangle(440,105,40,40));
		KEY_BOUNDARIES.put(Key.ACCENT_KEY_ACUTE, new Rectangle(480,105,40,40));
		KEY_BOUNDARIES.put(Key.BACKSPACE, new Rectangle(520,105,80,40));

		KEY_BOUNDARIES.put(Key.TAB, new Rectangle(0,150,60,40));
		KEY_BOUNDARIES.put(Key.Q, new Rectangle(60,150,40,40));
		KEY_BOUNDARIES.put(Key.W, new Rectangle(100,150,40,40));
		KEY_BOUNDARIES.put(Key.E, new Rectangle(140,150,40,40));
		KEY_BOUNDARIES.put(Key.R, new Rectangle(180,150,40,40));
		KEY_BOUNDARIES.put(Key.T, new Rectangle(220,150,40,40));
		KEY_BOUNDARIES.put(Key.Z, new Rectangle(260,150,40,40));
		KEY_BOUNDARIES.put(Key.U, new Rectangle(300,150,40,40));
		KEY_BOUNDARIES.put(Key.I, new Rectangle(340,150,40,40));
		KEY_BOUNDARIES.put(Key.O, new Rectangle(380,150,40,40));
		KEY_BOUNDARIES.put(Key.P, new Rectangle(420,150,40,40));
		KEY_BOUNDARIES.put(Key.UE, new Rectangle(460,150,40,40));
		KEY_BOUNDARIES.put(Key.PLUS, new Rectangle(500,150,40,40));

		KEY_BOUNDARIES.put(Key.SHIFT_HOLD, new Rectangle(0,195,70,40));
		KEY_BOUNDARIES.put(Key.A, new Rectangle(70,195,40,40));
		KEY_BOUNDARIES.put(Key.S, new Rectangle(110,195,40,40));
		KEY_BOUNDARIES.put(Key.D, new Rectangle(150,195,40,40));
		KEY_BOUNDARIES.put(Key.F, new Rectangle(190,195,40,40));
		KEY_BOUNDARIES.put(Key.G, new Rectangle(230,195,40,40));
		KEY_BOUNDARIES.put(Key.H, new Rectangle(270,195,40,40));
		KEY_BOUNDARIES.put(Key.J, new Rectangle(310,195,40,40));
		KEY_BOUNDARIES.put(Key.K, new Rectangle(350,195,40,40));
		KEY_BOUNDARIES.put(Key.L, new Rectangle(390,195,40,40));
		KEY_BOUNDARIES.put(Key.OE, new Rectangle(430,195,40,40));
		KEY_BOUNDARIES.put(Key.AE, new Rectangle(470,195,40,40));
		KEY_BOUNDARIES.put(Key.HASH, new Rectangle(510,195,40,40));
		KEY_BOUNDARIES.put(Key.ENTER, new Rectangle(550,150,50,85));

		KEY_BOUNDARIES.put(Key.SHIFT_LEFT, new Rectangle(0,240,50,40));
		KEY_BOUNDARIES.put(Key.ARROW_GREATER, new Rectangle(50,240,40,40));
		KEY_BOUNDARIES.put(Key.Y, new Rectangle(90,240,40,40));
		KEY_BOUNDARIES.put(Key.X, new Rectangle(130,240,40,40));
		KEY_BOUNDARIES.put(Key.C, new Rectangle(170,240,40,40));
		KEY_BOUNDARIES.put(Key.V, new Rectangle(210,240,40,40));
		KEY_BOUNDARIES.put(Key.B, new Rectangle(250,240,40,40));
		KEY_BOUNDARIES.put(Key.N, new Rectangle(290,240,40,40));
		KEY_BOUNDARIES.put(Key.M, new Rectangle(330,240,40,40));
		KEY_BOUNDARIES.put(Key.SEMICOLON, new Rectangle(370,240,40,40));
		KEY_BOUNDARIES.put(Key.DOUBLE_POINT, new Rectangle(410,240,40,40));
		KEY_BOUNDARIES.put(Key.MINUS, new Rectangle(450,240,40,40));
		KEY_BOUNDARIES.put(Key.SHIFT_RIGHT, new Rectangle(490,240,110,40));

		KEY_BOUNDARIES.put(Key.STRG_LEFT, new Rectangle(0,285,60,40));
		KEY_BOUNDARIES.put(Key.WINDOWS_LEFT, new Rectangle(60,285,40,40));
		KEY_BOUNDARIES.put(Key.ALT_LEFT, new Rectangle(100,285,50,40));
		KEY_BOUNDARIES.put(Key.SPACE, new Rectangle(150,285,260,40));
		KEY_BOUNDARIES.put(Key.ALT_GR, new Rectangle(410,285,50,40));
		KEY_BOUNDARIES.put(Key.WINDOWS_RIGHT, new Rectangle(460,285,40,40));
		KEY_BOUNDARIES.put(Key.CONTEXT_MENU, new Rectangle(500,285,40,40));
		KEY_BOUNDARIES.put(Key.STRG_RIGHT, new Rectangle(540,285,60,40));


	}
}
