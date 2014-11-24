package de.foobar.timemanager.rules;

import de.foobar.timemanager.BasicProgram;
import de.foobar.timemanager.TimeManager;
import de.foobar.timemanager.exception.ProgramParseException;
import de.foobar.timemanager.keys.Key;
import de.foobar.timemanager.keys.KeyToNumber;
import de.foobar.timemanager.keys.KeyboardLayout;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Editor: van on 09.11.14.
 */

public class TimeManagerTest {



	@Test(expected = ProgramParseException.class)
	public void testParseJsonToObjectEmptyProgram() throws Exception
	{

		final TimeManager tm = new TimeManager();
		final ClassLoader classLoader = getClass().getClassLoader();
		final InputStream stream = classLoader.getResource("emptyProgram.json").openStream();

		final String json = IOUtils.toString(stream, "UTF8");

		System.out.println(json);

		tm.parseProgram(json, KeyboardLayout.DE);
	}

	@Test(expected = ProgramParseException.class)
	public void testParseJsonToObjectProgramWithIncorrectColorMixingRule() throws Exception
	{
		final TimeManager tm = new TimeManager();
		final ClassLoader classLoader = getClass().getClassLoader();
		final InputStream stream = classLoader.getResource("emptyProgramWithIncorrectColorMixingRule.json").openStream();

		final String json = IOUtils.toString(stream, "UTF8");
		System.out.println(json);
		tm.parseProgram(json, KeyboardLayout.DE);
	}

	@Test
	public void testKeyToNumber ()
	{
		// test unknown key;
		assertEquals(KeyToNumber.getNumber(KeyboardLayout.DE, Key.NONE),-1);
	}

	@Test
	public void testParseJsonToObjectSimpleSetColorProgram() throws Exception {

		final TimeManager tm = new TimeManager();

		final ClassLoader classLoader = getClass().getClassLoader();
		final InputStream stream = classLoader.getResource("simpleSetColorProgramWithDoAfterActionLoop.json").openStream();

		final String json = IOUtils.toString(stream, "UTF8");

		tm.parseProgram(json, KeyboardLayout.DE);
		System.out.println(json);
		final BasicProgram bp = tm.getCurrentProgram();

		assertEquals(bp.getAbstractColorRules().size(), 1);
		assertNotNull(bp.getStartActionRule());
		assertNotNull(bp.getRuleMap().get("setRedFGK").getDoAfterRules());
		assertEquals(bp.getRuleMap().get("setRedFGK").getDoAfterRules().size(), 1);

	}

}
