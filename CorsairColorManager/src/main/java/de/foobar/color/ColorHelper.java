package de.foobar.color;

import de.foobar.exception.ProgramParseException;

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

	public static Color calculateHSVColorChange(final Color startColor, final Color endColor, final float percentDone, float direction)
	{
		final HSVColor startHSVColor = new HSVColor(startColor);
		final HSVColor endHSVColor = new HSVColor(endColor);


		final float difference = Math.max(startHSVColor.getHue(), endHSVColor.getHue()) -
								 Math.min(startHSVColor.getHue(), endHSVColor.getHue());

		final float distance;

		if(startHSVColor.getHue() <= endHSVColor.getHue())
		{
			distance = direction > 0 ? difference : 360f - difference;
		}
		else
		{
			// startHSVColor.getHue() > endHSVColor.getHue()
			distance = direction < 0 ? difference : 360f - difference;
		}

		final float h = (startHSVColor.getHue() + ( distance * percentDone * direction)) % 360;
		final float s = startHSVColor.getSaturation() - ((startHSVColor.getSaturation() - endHSVColor.getSaturation()) * percentDone);
		final float v = startHSVColor.getValue() - ((startHSVColor.getValue() - endHSVColor.getValue()) * percentDone);
		final float a = startHSVColor.getAlpha() - ((startHSVColor.getAlpha() - endHSVColor.getAlpha()) * percentDone);

		return new HSVColor(h,s,v,a).getRGB();
	}

	public static Color addColors(final Color src, final Color dst)
	{
		final float src_alpha = src.getAlpha() / 255f;
		final float dst_alpha = dst.getAlpha() / 255f;
		final float final_alpha = (src_alpha + dst_alpha) - (src_alpha * dst_alpha);

		final float src_red = src.getRed() * src_alpha;
		final float src_green = src.getGreen() * src_alpha;
		final float src_blue = src.getBlue() * src_alpha;

		final float dst_red = dst.getRed() * dst_alpha;
		final float dst_green = dst.getGreen() * dst_alpha;
		final float dst_blue = dst.getBlue() * dst_alpha;

		final float final_red = (src_red + dst_red * (1- src_alpha)) / final_alpha;
		final float final_green = (src_green + dst_green * (1- src_alpha)) / final_alpha;
		final float final_blue = (src_blue + dst_blue * (1- src_alpha)) / final_alpha;

		return new Color((int) final_red, (int) final_green, (int) final_blue ,(int) (final_alpha * 255));
	}

	public static Color randomColor() {
		final int color = (int) (Math.random()* 12);
		switch (color) {
			case 0:
				return Color.BLACK;
			case 1:
				return Color.WHITE;
			case 2:
				return Color.RED;
			case 3:
				return Color.GREEN;
			case 4:
				return Color.BLUE;
			case 5:
				return Color.YELLOW;
			case 6:
				return Color.ORANGE;
			case 7:
				return Color.CYAN;
			case 8:
				return Color.GRAY;
			case 9:
				return Color.PINK;
			case 10:
				return Color.LIGHT_GRAY;
			case 11:
				return Color.DARK_GRAY;
			default:
				return Color.WHITE;
		}

	}
}
