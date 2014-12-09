package de.foobar.keys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.foobar.common.BasicProgram;
import de.foobar.exception.ProgramParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Editor: van on 27.11.14.
 */
public class KeyGroup implements KeyReference{

	@JsonProperty("name")
	private String name;

	@JsonProperty("keys")
	private List<String> keyListTmp = new ArrayList<>();

	@JsonIgnore
	private List<Key> keyList = new ArrayList<Key>();

	public KeyGroup() {
	}

	public KeyGroup(final String name,final List<Key> keyList)
	{
		this.name = name;
		this.keyList = keyList;
	}

	public void initObjects(final BasicProgram basicProgram) throws ProgramParseException {

		final Map<String, KeyGroup> groupMap = basicProgram.getGroupMap();

		try {
			Key.valueOf(this.getName());
			throw new ProgramParseException("A group cannot have the same name like a key: " + getName());
		} catch (final IllegalArgumentException e)
		{
			// its ok
		}
		if(groupMap.containsKey(this.getName()))
		{
			throw new ProgramParseException("duplicated group name: " + this.getName());
		}

		for(final String key : keyListTmp)
		{
			try
			{
				this.keyList.add(Key.valueOf(key));
			}
			catch (final IllegalArgumentException e)
			{
				// Key not found.
				throw new ProgramParseException("cannot find key: " + key);
			}
		}
		groupMap.put(this.getName(), this);
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<Key> getKeyList() {
		return keyList;
	}

	public void setKeyList(final List<Key> keyList) {
		this.keyList = keyList;
	}

}
