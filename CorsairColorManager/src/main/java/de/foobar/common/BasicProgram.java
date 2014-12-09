package de.foobar.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.foobar.cache.SharedListController;
import de.foobar.exception.ProgramParseException;
import de.foobar.keys.KeyGroup;
import de.foobar.keys.KeyboardLayout;
import de.foobar.libusb.USBDeviceController;
import de.foobar.rules.AbstractColorRule;
import de.foobar.rules.ColorMixingRule;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 *
 * Editor: van on 09.11.14.
 */
public class BasicProgram implements Runnable {

	public static final int FRAME_RATE = 33; // in Milliseconds 30 frames/Second;

	public static final int DEFAULT_LAYER = 3;

	@JsonIgnore()
	private ColorMixingRule colorMixingRule;

	@JsonProperty("colorMixingRule")
	private String colorMixingRuleString;

	@JsonProperty("startAction")
	private String startAction;

	@JsonIgnore()
	private AbstractColorRule startActionRule;

	@JsonProperty("rules")
	private List<AbstractColorRule> abstractColorRules;

	@JsonProperty("groups")
	private List<KeyGroup> groups = new ArrayList<>();

	@JsonIgnore
	private Map<String, AbstractColorRule> ruleMap;

	@JsonIgnore
	private ScheduledExecutorService timerPool;

	@JsonIgnore
	private KeyboardLayout keyboardLayout;

	@JsonIgnore
	private Map<String, KeyGroup> groupMap = new HashMap<String, KeyGroup>();

	@JsonIgnore
	private USBDeviceController controller;

	public Map<String, KeyGroup> getGroupMap() {
		return groupMap;
	}

	public void setGroupMap(final Map<String, KeyGroup> groupMap) {
		this.groupMap = groupMap;
	}



	public BasicProgram() {
	}

	/**
	 * Start the program
	 */
	public void startProgram()
	{
		try {
			//init keyboard
			this.controller = new USBDeviceController();
			this.controller.init();

			this.timerPool = Executors.newScheduledThreadPool(5000);

			SharedListController.flushAll();
			this.timerPool.schedule(this.getStartActionRule(), 1, TimeUnit.MILLISECONDS);
			this.timerPool.scheduleWithFixedDelay(this, FRAME_RATE, FRAME_RATE, TimeUnit.MILLISECONDS);
		}
		catch (final Exception e)
		{
			System.out.println(e.getMessage());
			this.controller.shutdownLibUsb();
		}
	}

	@Override
	public void run() {
		final Map<Integer, Color> keyboardColors = SharedListController.get().calculateCurrentColors();
		this.controller.sendColors( keyboardColors);
		//System.out.println(System.currentTimeMillis());
	}


	public void initObjects() throws ProgramParseException {



		this.ruleMap = new HashMap<String, AbstractColorRule>();
		for(final AbstractColorRule rule : this.getAbstractColorRules()) {
			this.ruleMap.put(rule.getAlias(), rule);

			//check layer
			if(rule.getLayer() > 5 || rule.getLayer() < 1)
			{
				throw new ProgramParseException("Layer is not in valid range (1-5)");
			}
		}
		try {
			this.colorMixingRule = ColorMixingRule.valueOf(this.colorMixingRuleString);
		}
		catch (final IllegalArgumentException e)
		{
			throw new ProgramParseException("Color Mixing Rule not found");
		}
		if(startAction == null || !this.getRuleMap().containsKey(startAction))
		{
			throw new ProgramParseException("Start Rule not found");
		}
		this.startActionRule = this.getRuleMap().get(this.startAction);

		// init groups
		for(final KeyGroup group: this.groups)
		{
			group.initObjects(this);
		}

		// init abstract Rules
		for(final AbstractColorRule rule: this.ruleMap.values()) {
			rule.initObjects(this);
		}
	}

	// Getter & Setter

	public ColorMixingRule getColorMixingRule() {
		return colorMixingRule;
	}

	public void setColorMixingRule(final ColorMixingRule colorMixingRule) {
		this.colorMixingRule = colorMixingRule;
	}

	public String getColorMixingRuleString() {
		return colorMixingRuleString;
	}

	public void setColorMixingRuleString(final String colorMixingRuleString) {
		this.colorMixingRuleString = colorMixingRuleString;
	}

	public String getStartAction() {
		return startAction;
	}

	public void setStartAction(final String startAction) {
		this.startAction = startAction;
	}

	public AbstractColorRule getStartActionRule() {
		return startActionRule;
	}

	public void setStartActionRule(final AbstractColorRule startActionRule) {
		this.startActionRule = startActionRule;
	}

	public List<AbstractColorRule> getAbstractColorRules() {
		return abstractColorRules;
	}

	public void setAbstractColorRules(final List<AbstractColorRule> abstractColorRules) {
		this.abstractColorRules = abstractColorRules;
	}

	public Map<String, AbstractColorRule> getRuleMap() {
		return this.ruleMap;
	}

	public void setRuleMap(final Map<String, AbstractColorRule> ruleMap) {
		this.ruleMap = ruleMap;
	}

	public ScheduledExecutorService getTimerPool() {
		return timerPool;
	}

	public void setTimerPool(final ScheduledExecutorService timerPool) {
		this.timerPool = timerPool;
	}

	public KeyboardLayout getKeyboardLayout() {
		return keyboardLayout;
	}

	public void setKeyboardLayout(final KeyboardLayout keyboardLayout) {
		this.keyboardLayout = keyboardLayout;
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

	public List<KeyGroup> getGroups() {
		return groups;
	}

	public void setGroups(final List<KeyGroup> groups) {
		this.groups = groups;
	}

}
