package de.foobar.timemanager.common;

import de.foobar.timemanager.exception.ProgramParseException;

import java.awt.*;

/**
 * Editor: van on 15.11.14.
 */
public class ColorHelper {

	public static Color convertRGBAHexColorToColor(final String hexColor) throws ProgramParseException {

		if (hexColor == null || (hexColor.length() != 6 && hexColor.length() != 8)) {
			throw new ProgramParseException("color value not valid. please use RGB or RGBA Color code in HEX cods like: ff0000 for red or ff0000ff for Red with 100% Alpha");
		}

		if (hexColor.length() == 6) {
			return new Color(Integer.valueOf(hexColor.substring(0, 2), 16),
					Integer.valueOf(hexColor.substring(2, 4), 16),
					Integer.valueOf(hexColor.substring(4, 6), 16));
		} else {
			return new Color(Integer.valueOf(hexColor.substring(0, 2), 16),
					Integer.valueOf(hexColor.substring(2, 4), 16),
					Integer.valueOf(hexColor.substring(4, 6), 16),
					Integer.valueOf(hexColor.substring(6, 8), 16));
		}
	}
}
