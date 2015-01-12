package de.foobar;

import de.foobar.keys.KeyboardLayout;
import de.foobar.window.ProgramOption;
import de.foobar.window.SettingsWindow;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;

/**
 * Editor: van on 28.10.14.
 */
public class CorsairColorManager {


	public CorsairColorManager() {

	}

	public static void main(final String args[]) throws Exception
	{
		// Keyboard Layout
		final Map<String,String> params = new HashMap<String, String>();
		for(int i=1; i < args.length; i++)
		{
			final int indexOfEqual = args[i].indexOf('=');
			if(indexOfEqual == -1)
			{
				params.put(args[i].toLowerCase(), "");
			}
			else
			{
				final String key = args[i].substring(0, indexOfEqual);
				final String value = args[i].substring(indexOfEqual+1);
				params.put(key.toLowerCase(), value.toLowerCase());
			}
		}

		KeyboardLayout keyboardLayout = KeyboardLayout.DE;
		if(params.containsKey("layout"))
		{
			try {
				keyboardLayout = KeyboardLayout.valueOf(params.get("layout").toUpperCase());
			}catch (final IllegalArgumentException e)
			{
				System.out.println("cannot find keyboard layout! ");
			}
		}
		System.out.println("set Keyboard Layout to: "+ keyboardLayout.toString());

		// Program
		System.out.println("Start load Program ...");
		File file = null;
		String json = "";
		if(args.length > 0 )
		{
			file = new File(args[0]);
			final FileInputStream stream = new FileInputStream(file);
			json = IOUtils.toString(stream, "UTF8");
		}


		final boolean debugMode = params.containsKey("debug");

		final boolean ignoreKeyboardMode = params.containsKey("ignorekeyboard");

		int executionTime = -1;
		if(params.containsKey("duration"))
		{
			try {
				executionTime = Integer.valueOf(params.get("duration"));
			}
			catch (final Exception e)
			{
				System.out.println("cannot parse duration time.. use default");
			}
		}

		int frameRate = 30;
		if(params.containsKey("framerate"))
		{
			try{
				frameRate = Integer.valueOf(params.get("framerate"));
			}catch (final Exception e)
			{
				System.out.println("cannot parse framerate.. use default");
			}
		}

		final ProgramOption programOption = new ProgramOption();
		programOption.setCurrentProgram(file);
		programOption.setFrameRate(frameRate);
		programOption.setIgnoreNonexistentKeyboard(ignoreKeyboardMode);
		programOption.setShowVirtualKeyboard(debugMode);
		programOption.setProgramCode(json);
		programOption.setKeyboardLayout(keyboardLayout);
		programOption.setProgramDuration(executionTime);

		final SettingsWindow window = new SettingsWindow(programOption);



    }
}
