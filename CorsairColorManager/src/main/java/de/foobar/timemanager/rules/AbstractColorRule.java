package de.foobar.timemanager.rules;

import com.fasterxml.jackson.annotation.*;
import de.foobar.timemanager.BasicProgram;
import de.foobar.timemanager.exception.ProgramParseException;
import de.foobar.timemanager.keys.Key;
import de.foobar.timemanager.keys.KeyGroup;
import de.foobar.timemanager.keys.KeyReference;
import de.foobar.timemanager.keys.KeyToNumber;
import de.foobar.timemanager.memcached.CustomMemcachedClient;
import de.foobar.timemanager.memcached.MemcachedClientPool;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Editor: van on 28.10.14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = SetColor.class, name = "SetColor"),
		@JsonSubTypes.Type(value = LinearColorChange.class, name = "LinearColorChange"),
		@JsonSubTypes.Type(value = HSVColorChange.class, name = "HSVColorChange"),
		@JsonSubTypes.Type(value = KeyLine.class, name = "KeyLine")
})
public abstract class AbstractColorRule extends TimerTask implements NeedInit{

    /**
     * Reference Name of alias
     */
    @JsonProperty("alias")
    private String alias = "";

	@JsonIgnore
	private BasicProgram basicProgram;

	@JsonProperty("keys")
	private List<String> keyListTmp = new ArrayList<String>();

	@JsonIgnore
	private List<KeyReference> keys = new ArrayList<KeyReference>();

    @JsonProperty("doAfter")
    private List<String> doAfterListTmp = new ArrayList<String>();

	@JsonIgnore
    private List<AbstractColorRule> doAfterRules = new ArrayList<AbstractColorRule>();

	@JsonProperty("delay")
	private int delay = 0;

    protected AbstractColorRule() {

    }

	protected AbstractColorRule(final String alias,final List<String> keyListTmp,final List<String> doAfterListTmp,final List<AbstractColorRule> doAfterRules) {
		this.alias = alias;
		this.keyListTmp = keyListTmp;
		this.doAfterListTmp = doAfterListTmp;
		this.doAfterRules = doAfterRules;
	}

	public void initObjects(final BasicProgram basicProgram) throws ProgramParseException
	{
		this.basicProgram = basicProgram;
		final Map<String,AbstractColorRule> ruleMap = basicProgram.getRuleMap();
		for(final String rule: doAfterListTmp)
		{
			if(!ruleMap.containsKey(rule))
			{
				throw new ProgramParseException("cannot find role: " + rule);
			}
			this.doAfterRules.add(ruleMap.get(rule));
		}
		for(final String key : keyListTmp)
		{
			try
			{
				this.keys.add(Key.valueOf(key));
			}
			catch (final IllegalArgumentException e)
			{
				// not found in Key, try in Key Group
				final KeyGroup group = basicProgram.getGroupMap().get(key);
				if(group == null)
				{
					throw new ProgramParseException("cannot find key or group: " + key);
				}
				this.keys.add(group);
			}
		}
	}

	/**
	 *
	 * @param delay delay in Milliseconds
	 */
	public void scheduleDoAfter(final int delay)
	{
		final ScheduledExecutorService programTimer = this.getBasicProgram().getTimerPool();

		for(final AbstractColorRule rule: this.getDoAfterRules())
		{
			programTimer.schedule( rule, delay, TimeUnit.MILLISECONDS );
		}
	}

	public void setColorForAllKeys(final Color color)
	{
		try{
			final CustomMemcachedClient client = MemcachedClientPool.getInstance();
			for (final KeyReference key : this.getKeys()) {
				final List<Integer> toSet = KeyToNumber.getNumber(this.getBasicProgram().getKeyboardLayout(), key);
				for(final int keyNr : toSet){
					client.set(keyNr, 0, color);
				}
			}
		} catch (final IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public void setColorForKey(final Color color, final KeyReference key)
	{
		try{
			final CustomMemcachedClient client = MemcachedClientPool.getInstance();
			final List<Integer> toSet = KeyToNumber.getNumber(this.getBasicProgram().getKeyboardLayout(), key);
			for(final int keyNr : toSet){
				client.set(keyNr, 0, color);
			}
		} catch (final IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public String getAlias() {
        return alias;
    }

    public void setAlias(final String alias) {
        this.alias = alias;
    }

    public List<AbstractColorRule> getDoAfterRules() {
        return doAfterRules;
    }

    public void setDoAfterRules(final List<AbstractColorRule> doAfterRules) {
        this.doAfterRules = doAfterRules;
    }

	public List<KeyReference> getKeys() {
		return keys;
	}

	public void setKeys(final List<KeyReference> keys) {
		this.keys = keys;
	}

	public BasicProgram getBasicProgram() {
		return basicProgram;
	}

	public void setBasicProgram(BasicProgram basicProgram) {
		this.basicProgram = basicProgram;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}



	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(final Object o) {
		return EqualsBuilder.reflectionEquals(this, o, false);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}


}

