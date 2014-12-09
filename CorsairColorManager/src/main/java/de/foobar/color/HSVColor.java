package de.foobar.color;

import java.awt.*;

/**
 * Editor: van on 17.11.14.
 *
 *
 *  The HSVColor class provides methods to manipulate HSV (Hue, Saturation
 *  Value) values to create a corresponding Color object using the RGB
 *  ColorSpace.
 *
 *  The Hue is specified as an angel between 0 - 360 degrees where red is 0,
 *  green is 120 and blue is 240. In between you have the colors of the rainbow.
 *  Saturation is specified as a percentage between 0 - 100 where 100 is fully
 *  saturated and 0 approaches gray. Value is specified as a percentage
 *  between 0 - 100 where 0 is black and 100 is white.
 *
 *  In particular the HSV color space makes it easier change the Tone or Shade
 *  of a color by adjusting the luminance value.
 */
public class HSVColor
{
	private Color rgb;
	private float[] hsv;
	private float alpha;

	public HSVColor() {
	}

	/**
	 *  Create a HSVColor object using an RGB Color object.
	 *
	 *  @param rgb the RGB Color object
	 */
	public HSVColor(final Color rgb)
	{
		this.rgb = rgb;
		hsv = fromRGB( rgb );
		alpha = rgb.getAlpha() / 255.0f;
	}

	/**
	 *  Create a HSVColor object using individual HSV values and a default
	 * alpha value of 1.0.
	 *
	 *  @param h is the Hue value in degrees between 0 - 360
	 *  @param s is the Saturation percentage between 0 - 100
	 *  @param v is the value percentage between 0 - 100
	 */
	public HSVColor(final float h, final float s, final float v)
	{
		this(h, s, v, 1.0f);
	}

	/**
	 *  Create a HSVColor object using individual HSV values.
	 *
	 *  @param h     the Hue value in degrees between 0 - 360
	 *  @param s     the Saturation percentage between 0 - 100
	 *  @param v     the value percentage between 0 - 100
	 *  @param alpha the alpha value between 0 - 1
	 */
	public HSVColor(final float h, final float s, final float v, final float alpha)
	{
		hsv = new float[] {h, s, v};
		this.alpha = alpha;
		rgb = toRGB(hsv, alpha);
	}

	/**
	 *  Create a HSVColor object using an an array containing the
	 *  individual HSV values and with a default alpha value of 1.
	 *
	 *  @param hsv  array containing HSV values
	 */
	public HSVColor(final float[] hsv)
	{
		this(hsv, 1.0f);
	}

	/**
	 *  Create a HSVColor object using an an array containing the
	 *  individual HSV values.
	 *
	 *  @param hsv  array containing HSV values
	 *  @param alpha the alpha value between 0 - 1
	 */
	public HSVColor(final float[] hsv, final float alpha)
	{
		this.hsv = hsv;
		this.alpha = alpha;
		rgb = toRGB(hsv, alpha);
	}

	/**
	 *  Get the Alpha value.
	 *
	 *  @return the Alpha value.
	 */
	public float getAlpha()
	{
		return alpha;
	}

	/**
	 *  Create a RGB Color object that is the complementary color of this
	 *  HSVColor. This is a convenience method. The complementary color is
	 *  determined by adding 180 degrees to the Hue value.
	 *  @return the RGB Color object
	 */
	public Color getComplementary()
	{
		final float hue = (hsv[0] + 180.0f) % 360.0f;
		return toRGB(hue, hsv[1], hsv[2]);
	}

	/**
	 *  Get the Hue value.
	 *
	 *  @return the Hue value.
	 */
	public float getHue()
	{
		return hsv[0];
	}

	/**
	 *  Get the HSV values.
	 *
	 *  @return the HSV values.
	 */
	public float[] getHSV()
	{
		return hsv;
	}

	/**
	 *  Get the Value value.
	 *
	 *  @return the Value value.
	 */
	public float getValue()
	{
		return hsv[2];
	}

	/**
	 *  Get the RGB Color object represented by this HDLColor.
	 *
	 *  @return the RGB Color object.
	 */
	public Color getRGB()
	{
		return rgb;
	}

	/**
	 *  Get the Saturation value.
	 *
	 *  @return the Saturation value.
	 */
	public float getSaturation()
	{
		return hsv[1];
	}

	public String toString()
	{
		return "HSVColor[h=" + hsv[0] +
						",s=" + hsv[1] +
						",v=" + hsv[2] +
						",alpha=" + alpha + "]";
	}

	/**
	 *  Convert a RGB Color to it corresponding HSV values.
	 *
	 *  @return an array containing the 3 HSV values.
	 */
	public static float[] fromRGB(final Color color)
	{
		//  Get RGB values in the range 0 - 1

		final float[] rgb = color.getRGBColorComponents( null );
		final float r = rgb[0];
		final float g = rgb[1];
		final float b = rgb[2];

		//	Minimum and Maximum RGB values are used in the HSV calculations

		final float min = Math.min(r, Math.min(g, b));
		final float max = Math.max(r, Math.max(g, b));

		//  Calculate the Hue

		float h = 0;

		if (max == min) {
			h = 0;
		}
		else if (max == r)
		{
			h = ((60 * (g - b) / (max - min)));
		}
		else if (max == g)
		{
			h = (60 * (b - r) / (max - min)) + 120;
		}else if (max == b)
		{
			h = (60 * (r - g) / (max - min)) + 240;
		}
		h = (h+360) % 360;

		//  Calculate the Value
		//final float v = max;

		//  Calculate the Saturation
		float s = 0;

		if (max == 0)
		{
			s = 0;
		}
		else
		{
			s = (max - min) / max;

		}

		return new float[] {h, s * 100, max * 100};
	}

	/**
	 *  Convert HSV values to a RGB Color with a default alpha value of 1.
	 *  H (Hue) is specified as degrees in the range 0 - 360.
	 *  S (Saturation) is specified as a percentage in the range 1 - 100.
	 *  V (Value) is specified as a percentage in the range 1 - 100.
	 *
	 *  @param hsv an array containing the 3 HSV values
	 *
	 *  @returns the RGB Color object
	 */
	public static Color toRGB(final float[] hsv)
	{
		return toRGB(hsv, 1.0f);
	}

	/**
	 *  Convert HSV values to a RGB Color.
	 *  H (Hue) is specified as degrees in the range 0 - 360.
	 *  S (Saturation) is specified as a percentage in the range 1 - 100.
	 *  V (value) is specified as a percentage in the range 1 - 100.
	 *
	 *  @param hsv    an array containing the 3 HSV values
	 *  @param alpha  the alpha value between 0 - 1
	 *
	 *  @returns the RGB Color object
	 */
	public static Color toRGB(final float[] hsv, final float alpha)
	{
		return toRGB(hsv[0], hsv[1], hsv[2], alpha);
	}

	/**
	 *  Convert HSV values to a RGB Color with a default alpha value of 1.
	 *
	 *  @param h Hue is specified as degrees in the range 0 - 360.
	 *  @param s Saturation is specified as a percentage in the range 1 - 100.
	 *  @param v value is specified as a percentage in the range 1 - 100.
	 *
	 *  @returns the RGB Color object
	 */
	public static Color toRGB(final float h, final float s,final float v)
	{
		return toRGB(h, s, v, 1.0f);
	}

	/**
	 *  Convert HSV values to a RGB Color.
	 *
	 *  @param h Hue is specified as degrees in the range 0 - 360.
	 *  @param s Saturation is specified as a percentage in the range 1 - 100.
	 *  @param v value is specified as a percentage in the range 1 - 100.
	 *  @param alpha  the alpha value between 0 - 1
	 *
	 *  @returns the RGB Color object
	 */
	public static Color toRGB( float h,  float s,  float v, final float alpha)
	{
		if (s <0.0f || s > 100.0f)
		{
			final String message = "Color parameter outside of expected range - Saturation";
			throw new IllegalArgumentException( message );
		}

		if (v <0.0f || v > 100.0f)
		{
			final String message = "Color parameter outside of expected range - Value";
			throw new IllegalArgumentException( message );
		}

		if (alpha <0.0f || alpha > 1.0f)
		{
			final String message = "Color parameter outside of expected range - Alpha";
			throw new IllegalArgumentException( message );
		}

		h = h % 360.0f;
		//  Formula needs all values between 0 - 1.
		s /= 100f;
		v /= 100f;


		final int hi = (int) Math.floor(h / 60);
		final float f = (h / 60) - hi;

		final float p = v * (1-s);
		final float q = v * (1 - (s * f));
		final float t = v * (1 - (s * (1-f)));

		switch (hi) {
			case 1:
				return new Color(q,v,p);
			case 2:
				return new Color(p,v,t);
			case 3:
				return new Color(p,q,v);
			case 4:
				return new Color(t,p,v);
			case 5:
				return new Color(v,p,q);
			case 0:
			case 6:
				return new Color(v,t,p);
			default:
				throw new IllegalComponentStateException("what the fuck?");
		}
	}
}