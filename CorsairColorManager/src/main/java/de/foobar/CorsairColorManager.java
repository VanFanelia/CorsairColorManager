package de.foobar;

import de.foobar.common.BasicProgram;
import de.foobar.common.TimeManager;
import de.foobar.keys.KeyboardLayout;
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

	public static void main(final String args[]) throws Exception{
		if(args.length >= 1)
		{
			// Keyboard Layout
			final Map<String,String> params = new HashMap<String, String>();
			for(int i=1; i < args.length; i++)
			{
				final int indexOfEqual = args[i].indexOf('=');
				if(indexOfEqual == -1){
					params.put(args[i].toLowerCase(), "");
				}else {
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
			final File file = new File(args[0]);
			if(!file.exists())
			{
				System.out.println("could not find file ...");
				System.exit(1);
			}
			final FileInputStream stream = new FileInputStream(file);
			final String json = IOUtils.toString(stream, "UTF8");

			boolean debugMode = params.containsKey("debug");

			boolean ignoreKeyboardMode = params.containsKey("ignorekeyboard");

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

			if(params.containsKey("framerate"))
			{
				try{
					int frameRate = Integer.valueOf(params.get("framerate"));
					frameRate = 1000 / frameRate;
					BasicProgram.FRAME_RATE = frameRate;
				}catch (final Exception e)
				{
					System.out.println("cannot parse framerate.. use default");
				}
			}

			try{
				final TimeManager tm = new TimeManager();
				tm.parseProgram(json, keyboardLayout, debugMode, ignoreKeyboardMode, executionTime);
			}
			catch (final Exception e)
			{
				System.out.println("Cannot parse and execute program ... Exception: " + e.getMessage());
				try{
					stream.close();
				}
				catch (final Exception closeException){
					//empty
				}
				System.exit(7);
			}

		} else {
			System.out.println("Need a path to a json file... ");
			System.exit(7);
		}
    }
}
