package de.foobar.rules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import de.foobar.cache.SharedListController;
import de.foobar.common.BasicProgram;
import de.foobar.exception.ProgramParseException;
import de.foobar.keys.Key;
import de.foobar.keys.KeyGroup;
import de.foobar.keys.KeyReference;
import de.foobar.keys.KeyToNumber;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Editor: van on 28.10.14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(value = SetColor.class, name = "SetColor"),
		@JsonSubTypes.Type(value = SetContinuousColor.class, name = "SetContinuousColor"),
		@JsonSubTypes.Type(value = LinearColorChange.class, name = "LinearColorChange"),
		@JsonSubTypes.Type(value = HSVColorChange.class, name = "HSVColorChange"),
		@JsonSubTypes.Type(value = KeyLine.class, name = "KeyLine"),
		@JsonSubTypes.Type(value = OnKeyPress.class, name = "OnKeyPress"),
		@JsonSubTypes.Type(value = AfterRandomTime.class, name = "AfterRandomTime"),
		@JsonSubTypes.Type(value = DoRandomRule.class, name ="DoRandomRule")
})
public abstract class AbstractColorRule extends TimerTask implements NeedInit, Cloneable{

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

	@JsonProperty("startDelay")
	private int startDelay = 0;

	@JsonProperty("layer")
	private int layer = basicProgram.DEFAULT_LAYER;

    protected AbstractColorRule() {

    }

	protected AbstractColorRule(final String alias,final List<String> keyListTmp,final List<String> doAfterListTmp,final List<AbstractColorRule> doAfterRules) {
		this.alias = alias;
		this.keyListTmp = keyListTmp;
		this.doAfterListTmp = doAfterListTmp;
		this.doAfterRules = doAfterRules;
	}

	public void run()
	{

	}

	public void initObjects(final BasicProgram basicProgram) throws ProgramParseException
	{
		if (this.getDelay() < 0) {
			throw new ProgramParseException("the delay has to be a unsigned integer value (32bit) ");
		}
		this.basicProgram = basicProgram;
		final Map<String,AbstractColorRule> ruleMap = basicProgram.getRuleMap();
		for(final String rule: doAfterListTmp)
		{
			if(rule.contains("$PRESSED")){
				continue;
			}
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
			if(rule.getStartDelay() > 0) {

			}
			programTimer.schedule( rule, (delay + rule.getStartDelay()), TimeUnit.MILLISECONDS );
		}
	}

	public void setColorForAllKeys(final Color color)
	{
		final SharedListController cache = SharedListController.get();
		for (final KeyReference key : this.getKeys()) {
			final List<Integer> toSet = KeyToNumber.getNumber(this.getBasicProgram().getKeyboardLayout(), key);
			for(final int keyNr : toSet){
				cache.add (keyNr, this.layer, color);
			}
		}
	}

	public void setColorForKey(final Color color, final KeyReference key)
	{
		final SharedListController cache = SharedListController.get();
		final List<Integer> toSet = KeyToNumber.getNumber(this.getBasicProgram().getKeyboardLayout(), key);
		for(final int keyNr : toSet){
			cache.add(keyNr, this.layer, color);
		}
	}

	public AbstractColorRule clone() throws CloneNotSupportedException
	{
		final AbstractColorRule clone = (AbstractColorRule) super.clone();
		clone.setAlias(this.alias);
		clone.setBasicProgram(this.basicProgram);


		clone.keys = new ArrayList<>();
		for(final KeyReference key: this.keys)
		{
			clone.keys.add(key);
		}

		clone.doAfterRules = new ArrayList<>();
		for(final AbstractColorRule rule : this.doAfterRules){
			clone.getDoAfterRules().add(rule);
		}
		clone.setDelay(this.getDelay());
		clone.setStartDelay(this.getStartDelay());
		clone.setLayer(this.getLayer());
		return clone;
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

	public void setBasicProgram(final BasicProgram basicProgram) {
		this.basicProgram = basicProgram;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(final int delay) {
		this.delay = delay;
	}

	public List<String> getKeyListTmp() {
		return keyListTmp;
	}

	public void setKeyListTmp(final List<String> keyListTmp) {
		this.keyListTmp = keyListTmp;
	}

	public List<String> getDoAfterListTmp() {
		return doAfterListTmp;
	}

	public void setDoAfterListTmp(final List<String> doAfterListTmp) {
		this.doAfterListTmp = doAfterListTmp;
	}

	public void setLayer(final int layer) {
		this.layer = layer;
	}

	public int getStartDelay() {
		return startDelay;
	}

	public void setStartDelay(final int startDelay) {
		this.startDelay = startDelay;
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


	public int getLayer() {
		return layer;
	}
}

