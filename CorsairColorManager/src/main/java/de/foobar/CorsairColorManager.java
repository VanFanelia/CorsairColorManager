package de.foobar;

import de.foobar.timemanager.TimeManager;
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
		if(args.length == 1)
		{
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
				tm.parseProgram(json);
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
