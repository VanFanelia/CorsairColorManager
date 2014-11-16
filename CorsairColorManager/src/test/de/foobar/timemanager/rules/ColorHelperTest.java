package de.foobar.timemanager.rules;

import de.foobar.timemanager.exception.ProgramParseException;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static de.foobar.timemanager.common.ColorHelper.convertColorToHexRGAString;
import static de.foobar.timemanager.common.ColorHelper.convertRGBAHexColorToColor;

/**
 * Editor: van on 15.11.14.
 */
public class ColorHelperTest
{

	@Test
	public void convertRGBAHexColorToColorTestRedWithoutAlpha() throws ProgramParseException {
		final Color red = convertRGBAHexColorToColor("ff0000");
		Assert.assertEquals(red, Color.red);
	}

	@Test
	public void convertRGBAHexColorToColorTestWithBlueColorAnd43Alpha() throws ProgramParseException {
		final Color blueConverted = convertRGBAHexColorToColor("0000ff2b");
		final Color blue = new Color(0,0,255,43);
		Assert.assertEquals(blueConverted, blue);
	}



	@Test
	public void convertColorToHexRGAStringTestRedGreenAndBlue() throws ProgramParseException {
		final String redCalc = convertColorToHexRGAString(Color.RED);
		final String red = "ff0000ff";
		Assert.assertEquals(red, redCalc);

		final String greenCalc = convertColorToHexRGAString(Color.GREEN);
		final String green = "00ff00ff";
		Assert.assertEquals(green, greenCalc);

		final String blueCalc = convertColorToHexRGAString(Color.BLUE);
		final String blue = "0000ffff";
		Assert.assertEquals(blue, blueCalc);
	}

	@Test
	public void convertColorToHexRGAStringTestAlpha() throws ProgramParseException
	{
		final String blueColorWithAlphaCalc = convertColorToHexRGAString(new Color(0,0,255,43));
		final String blueColorWithAlpha = "0000ff2b";
		Assert.assertEquals(blueColorWithAlpha, blueColorWithAlphaCalc);
	}

}
