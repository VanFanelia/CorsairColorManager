package de.foobar.timemanager.rules;

import de.foobar.timemanager.exception.ProgramParseException;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

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
}
