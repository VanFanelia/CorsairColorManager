package de.foobar.rules;

import de.foobar.common.BasicProgram;
import de.foobar.common.TimeManager;
import de.foobar.exception.ProgramParseException;
import de.foobar.window.ProgramOption;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

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

		final ProgramOption programOption = ProgramOption.getDebugProgramOptions();
		programOption.setProgramCode(json);

		tm.parseProgram(programOption);
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

		final ProgramOption programOption = ProgramOption.getDebugProgramOptions();
		programOption.setProgramCode(json);

		tm.parseProgram(programOption);
		System.out.println(json);
		final BasicProgram bp = tm.getCurrentProgram();

	}
}