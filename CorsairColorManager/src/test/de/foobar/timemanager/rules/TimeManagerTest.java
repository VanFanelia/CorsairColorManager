package de.foobar.timemanager.rules;

import de.foobar.timemanager.BasicProgram;
import de.foobar.timemanager.TimeManager;
import de.foobar.timemanager.exception.ProgramParseException;
import de.foobar.timemanager.keys.Key;
import de.foobar.timemanager.keys.KeyToNumber;
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

		final InputStream stream = TimeManagerTest.class.getResourceAsStream("/de/foobar/resources/emptyProgram.json");

		final String json = IOUtils.toString(stream, "UTF8");

		System.out.println(json);

		tm.parseProgram(json);
	}

	@Test(expected = ProgramParseException.class)
	public void testParseJsonToObjectProgramWithIncorrectColorMixingRule() throws Exception
	{
		final TimeManager tm = new TimeManager();
		final InputStream stream = TimeManagerTest.class.getResourceAsStream("/de/foobar/resources/emptyProgramWithIncorrectColorMixingRule.json");
		final String json = IOUtils.toString(stream, "UTF8");
		System.out.println(json);
		tm.parseProgram(json);
	}

	@Test
	public void testKeyToNumber ()
	{
		// test unknown key;
		assertEquals(KeyToNumber.getNumber(Key.NONE),-1);
	}

	@Test
	public void testParseJsonToObjectSimpleSetColorProgram() throws Exception {

		final TimeManager tm = new TimeManager();

		final InputStream stream = TimeManagerTest.class.getResourceAsStream("/de/foobar/resources/simpleSetColorProgramWithDoAfterActionLoop.json");

		final String json = IOUtils.toString(stream, "UTF8");

		tm.parseProgram(json);
		System.out.println(json);
		final BasicProgram bp = tm.getCurrentProgram();

		assertEquals(bp.getAbstractColorRules().size(), 1);
		assertNotNull(bp.getStartActionRule());
		assertNotNull(bp.getRuleMap().get("setRedFGK").getDoAfterRules());
		assertEquals(bp.getRuleMap().get("setRedFGK").getDoAfterRules().size(), 1);

	}

}
