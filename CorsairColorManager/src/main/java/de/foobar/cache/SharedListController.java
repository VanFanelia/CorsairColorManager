package de.foobar.cache;


import de.foobar.color.ColorHelper;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Editor: van on 08.12.14.
 */
public class SharedListController {

	private static final Map<Integer, ArrayList<Color>> SYNCHRONIZED_MAP = new ConcurrentHashMap<Integer, ArrayList<Color>>();

	private static SharedListController instance;

	public static Color BACKGROUND_COLOR = Color.white;

	static {
		instance = new SharedListController();
	}

	public SharedListController() {
	}

	public static void flushAll()
	{
		for(int i=0; i< 144; i++)
		{
			final ArrayList<Color> initList = new ArrayList<>();
			for(int j=0; j< 5; j++)
			{
				initList.add(new Color(0, 0, 0, 0));
			}
			SYNCHRONIZED_MAP.put(i, initList);
		}
	}

	public Map<Integer, Color> calculateCurrentColors()
	{
		final HashMap<Integer, Color> result= new HashMap<Integer, Color>();
		for(int i=0; i< 144; i++)
		{
			final ArrayList<Color> currentColorList = SYNCHRONIZED_MAP.get(i);
			Color calculated = BACKGROUND_COLOR;
			for(int c=0; c<5; c++)
			{
				if(currentColorList.isEmpty() || c > currentColorList.size())
				{
					break;
				}
				if(currentColorList.get(c) != null)
				{
					calculated = ColorHelper.addColors(currentColorList.get(c), calculated);
				}
			}
			result.put(i, calculated);
		}
		return result;
	}

	public void add(final int key, final int layer, final Color color) {
		//System.out.println("set " +color.toString() + " to key: "+key + " on layer "+layer);
		SYNCHRONIZED_MAP.get(key).set(layer, color);

	}

	public static SharedListController get() {
		return instance;
	}

}
