package de.foobar.rules;

import de.foobar.common.BasicProgram;
import de.foobar.common.TimeManager;
import de.foobar.window.ProgramOption;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

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

		final ProgramOption programOption = ProgramOption.getDebugProgramOptions();
		programOption.setProgramCode(json);

		tm.parseProgram(programOption);
		tm.start();
		System.out.println(json);
		final BasicProgram bp = tm.getCurrentProgram();

	}
}