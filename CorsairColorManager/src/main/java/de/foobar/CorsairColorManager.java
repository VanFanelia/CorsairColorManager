package de.foobar;

import de.foobar.timemanager.TimeManager;
import de.foobar.timemanager.keys.KeyboardLayout;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;

/**
 * Editor: van on 28.10.14.
 */
public class CorsairColorManager {

	public CorsairColorManager() {
	}

	public static void main(final String args[]) throws Exception{
		if(args.length == 1 || args.length == 2)
		{
			// Keyboard Layout
			KeyboardLayout keyboardLayout = KeyboardLayout.DE;
			if(args.length == 2 && args[1] != null)
			{
				try {
					keyboardLayout = KeyboardLayout.valueOf(args[1].toUpperCase());
				}catch (final IllegalArgumentException e)
				{
					System.out.println("cannot find keyboard layout! ");
				}
			}

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
			try{
				final TimeManager tm = new TimeManager();
				tm.parseProgram(json, keyboardLayout);
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
			}

		} else {
			System.out.println("Need a path to a json file... ");
		}
    }
}
