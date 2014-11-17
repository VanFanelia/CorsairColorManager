package de.foobar.timemanager.common;

import de.foobar.timemanager.exception.ProgramParseException;

import java.awt.*;

/**
 * Editor: van on 15.11.14.
 */
public class ColorHelper {

	public ColorHelper() {
	}

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


	public static String convertColorToHexRGAString(final Color startColor)
	{
		final StringBuilder result = new StringBuilder();
		String red = Integer.toHexString(startColor.getRed());
		red = red.length() == 1 ? "0"+red : red;
		result.append(red);

		String green = Integer.toHexString(startColor.getGreen());
		green = green.length() == 1 ? "0"+green : green;
		result.append(green);

		String blue = Integer.toHexString(startColor.getBlue());
		blue = blue.length() == 1 ? "0"+blue : blue;
		result.append(blue);

		String alpha = Integer.toHexString(startColor.getAlpha());
		alpha = alpha.length() == 1 ? "0"+alpha : alpha;
		result.append(alpha);

		return result.toString();

	}

	public static Color calculateLinearColorChange(final Color startColor, final Color endColor, final float percentDone)
	{
		final int red   = startColor.getRed() - Math.round(((float) (startColor.getRed() - endColor.getRed()) ) * percentDone);
		final int green = startColor.getGreen() - Math.round(((float) (startColor.getGreen() - endColor.getGreen()) ) * percentDone);
		final int blue  = startColor.getBlue() - Math.round(((float) (startColor.getBlue() - endColor.getBlue()) ) * percentDone);
		final int alpha = startColor.getAlpha() - Math.round(((float) (startColor.getAlpha() - endColor.getAlpha()) ) * percentDone);
		return new Color(red, green,blue,alpha);
	}

	public static Color calculateHSVColorChange(final Color startColor, final Color endColor, final float percentDone)
	{
		final HSVColor startHSVColor = new HSVColor(startColor);
		final HSVColor endHSVColor = new HSVColor(endColor);

		final float difference = Math.max(startHSVColor.getHue(), endHSVColor.getHue()) -
								 Math.min(startHSVColor.getHue(), endHSVColor.getHue());
		final float distance = difference > 180.0f ? difference - 180.0f : difference;
		final float direction = difference > 180.0f ? -1.0f : 1.0f; //walk back if difference is bigger then

		final float h = (startHSVColor.getHue() + ( distance * percentDone * direction)) % 360;
		final float s = startHSVColor.getSaturation() - ((startHSVColor.getSaturation() - endHSVColor.getSaturation()) * percentDone);
		final float v = startHSVColor.getValue() - ((startHSVColor.getValue() - endHSVColor.getValue()) * percentDone);
		final float a = startHSVColor.getAlpha() - ((startHSVColor.getAlpha() - endHSVColor.getAlpha()) * percentDone);

		return new HSVColor(h,s,v,a).getRGB();
	}
}
