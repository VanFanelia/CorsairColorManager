package de.foobar.timemanager.rules;

import com.fasterxml.jackson.annotation.*;
import de.foobar.timemanager.BasicProgram;
import de.foobar.timemanager.exception.ProgramParseException;
import de.foobar.timemanager.keys.Key;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Editor: van on 28.10.14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = SetColor.class, name = "SetColor"),
		@JsonSubTypes.Type(value = LinearColorChange.class, name = "LinearColorChange")
})
public abstract class AbstractColorRule extends TimerTask {

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
	private List<Key> keys = new ArrayList<Key>();

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
				throw new ProgramParseException("cannot find key: "+ key);
			}
		}
	}

	public void scheduleDoAfter()
	{
		final ScheduledExecutorService programTimer = this.getBasicProgram().getTimerPool();

		for(final AbstractColorRule rule: this.getDoAfterRules())
		{
			programTimer.schedule( rule, this.getDelay(), TimeUnit.MILLISECONDS );
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

	public List<Key> getKeys() {
		return keys;
	}

	public void setKeys(final List<Key> keys) {
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

