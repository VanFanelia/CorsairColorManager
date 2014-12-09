package de.foobar.rules;

import de.foobar.common.BasicProgram;
import de.foobar.common.TimeManager;
import de.foobar.exception.ProgramParseException;
import de.foobar.keys.Key;
import de.foobar.keys.KeyToNumber;
import de.foobar.keys.KeyboardLayout;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


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
		assertEquals(new ArrayList(), KeyToNumber.getNumber(KeyboardLayout.DE, Key.NONE));
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

	@Test
	public void testParseJsonToObjectSimpleSetColorProgramWithGroup() throws Exception {

		final TimeManager tm = new TimeManager();

		final ClassLoader classLoader = getClass().getClassLoader();
		final InputStream stream = classLoader.getResource("simpleSetColorProgramWithGroup.json").openStream();

		final String json = IOUtils.toString(stream, "UTF8");

		tm.parseProgram(json, KeyboardLayout.DE);
		System.out.println(json);
		final BasicProgram bp = tm.getCurrentProgram();

		assertNotNull(bp.getGroupMap().get("Group1"));

	}

	@Test(expected = ProgramParseException.class)
	public void testParseJsonToObjectSimpleSetColorProgramWithInvalidGroup() throws Exception {

		final TimeManager tm = new TimeManager();

		final ClassLoader classLoader = getClass().getClassLoader();
		final InputStream stream = classLoader.getResource("simpleSetColorProgramWithInvalidGroup.json").openStream();

		final String json = IOUtils.toString(stream, "UTF8");

		tm.parseProgram(json, KeyboardLayout.DE);
		System.out.println(json);
		final BasicProgram bp = tm.getCurrentProgram();

		assertNotNull(bp.getGroupMap().get("Group1"));

	}
}
