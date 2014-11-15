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
			return new Color(Integer.valueOf(hexColor.substring(1, 3), 16),
					Integer.valueOf(hexColor.substring(3, 5), 16),
					Integer.valueOf(hexColor.substring(5, 7), 16));
		} else {
			return new Color(Integer.valueOf(hexColor.substring(1, 3), 16),
					Integer.valueOf(hexColor.substring(3, 5), 16),
					Integer.valueOf(hexColor.substring(5, 7), 16),
					Integer.valueOf(hexColor.substring(7, 9), 16));
		}
	}
}
