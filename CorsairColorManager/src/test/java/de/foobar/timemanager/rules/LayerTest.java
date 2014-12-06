package de.foobar.timemanager.rules;

import de.foobar.timemanager.BasicProgram;
import de.foobar.timemanager.TimeManager;
import de.foobar.timemanager.exception.ProgramParseException;
import de.foobar.timemanager.keys.KeyboardLayout;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.InputStream;

/**
 * Editor: van on 26.11.14.
 */
public class LayerTest {


	@Test
	public void testLayer() throws Exception
	{
		final TimeManager tm = new TimeManager();

		final ClassLoader classLoader = getClass().getClassLoader();
		final InputStream stream = classLoader.getResource("layerTestProgram.json").openStream();

		final String json = IOUtils.toString(stream, "UTF8");

		tm.parseProgram(json, KeyboardLayout.DE);
		System.out.println(json);
		final BasicProgram bp = tm.getCurrentProgram();

	}

	@Test(expected = ProgramParseException.class)
	public void testInvalidLayer() throws Exception
	{
		final TimeManager tm = new TimeManager();

		final ClassLoader classLoader = getClass().getClassLoader();
		final InputStream stream = classLoader.getResource("InvalidLayerTestProgram.json").openStream();

		final String json = IOUtils.toString(stream, "UTF8");

		tm.parseProgram(json, KeyboardLayout.DE);
		System.out.println(json);
		final BasicProgram bp = tm.getCurrentProgram();

	}
}