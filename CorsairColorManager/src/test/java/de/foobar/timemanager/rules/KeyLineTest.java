package de.foobar.timemanager.rules;

import de.foobar.timemanager.BasicProgram;
import de.foobar.timemanager.TimeManager;
import de.foobar.timemanager.keys.KeyboardLayout;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;

/**
 * Editor: van on 26.11.14.
 */
public class KeyLineTest {


	@Test
	public void testSimpleKeyLineRule() throws Exception
	{
		final TimeManager tm = new TimeManager();

		final ClassLoader classLoader = getClass().getClassLoader();
		final InputStream stream = classLoader.getResource("KeyLineProgram.json").openStream();

		final String json = IOUtils.toString(stream, "UTF8");

		tm.parseProgram(json, KeyboardLayout.DE);
		System.out.println(json);
		final BasicProgram bp = tm.getCurrentProgram();

	}
}