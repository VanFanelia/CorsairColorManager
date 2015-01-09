package de.foobar.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.foobar.cache.SharedListController;
import de.foobar.exception.ProgramParseException;
import de.foobar.keys.Key;
import de.foobar.keys.KeyGroup;
import de.foobar.keys.KeyReference;
import de.foobar.keys.KeyboardLayout;
import de.foobar.libusb.USBDeviceController;
import de.foobar.rules.AbstractColorRule;
import de.foobar.rules.ColorMixingRule;
import de.foobar.rules.OnKeyPress;
import de.foobar.window.VirtualKeyboardFrame;
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
import org.jnativehook.GlobalScreen;

/**
 *
 * Editor: van on 09.11.14.
 */
public class BasicProgram extends Thread {

	public static int FRAME_RATE = 33; // in Milliseconds 30 frames/Second;

	public static final int DEFAULT_LAYER = 3;

	public static final int MAX_PROGRAM_DURATION = 3600;// in seconds

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

	@JsonIgnore
	private boolean debugMode = false;

	@JsonIgnore
	private boolean ignoreKeyboardMode = false;

	@JsonIgnore
	private VirtualKeyboardFrame window;

	@JsonIgnore
	private StopProgramTask stopper;

	@JsonIgnore
	private int maxProgramDuration = MAX_PROGRAM_DURATION;

	@JsonIgnore
	private Map<Key, List<AbstractColorRule>> onKeyPressRuleMap = new HashMap<Key, List<AbstractColorRule>>();

	@JsonIgnore
	private Map<KeyReference, AbstractColorRule> currentlyPlayedAnimation = new HashMap<KeyReference, AbstractColorRule>();

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
			if(isDebugMode())
			{
				window = new VirtualKeyboardFrame(this.keyboardLayout, "K70RGB-Debug-Window");
			}
			//init keyboard
			try {
				this.controller = new USBDeviceController();
				SharedListController.flushAll();
				this.controller.init();
			}catch (final Exception e)
			{
				System.out.println(e.getMessage());
				if(!this.isIgnoreKeyboardMode()){
					throw e;
				}
			}
			// init global Hook
			GlobalScreen.getInstance().addNativeKeyListener(new KeyInputListener(this));
			GlobalScreen.registerNativeHook();

			this.stopper = new StopProgramTask(this);
			Runtime.getRuntime().addShutdownHook(stopper);
			this.timerPool = Executors.newScheduledThreadPool(10);
			this.timerPool.schedule(this.stopper, this.getMaxProgramDuration(), TimeUnit.SECONDS);

			this.timerPool.schedule(this.getStartActionRule(), 1, TimeUnit.MILLISECONDS);

			this.setPriority(this.getPriority()+1);
			this.timerPool.scheduleWithFixedDelay(this, FRAME_RATE, FRAME_RATE, TimeUnit.MILLISECONDS);
		}
		catch (final Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			this.controller.shutdownLibUsb();
		}
	}

	public void runRulesForKeyPressed(final Key pressed) throws CloneNotSupportedException {
		List<AbstractColorRule> toExecute = this.onKeyPressRuleMap.get(pressed);
		toExecute = toExecute == null? new ArrayList<AbstractColorRule>() : toExecute;
		for(final AbstractColorRule rule : toExecute)
		{
			for(final AbstractColorRule doAfterRule : rule.getDoAfterRules())
			{
				final AbstractColorRule clone = doAfterRule.clone();
				if (doAfterRule.getKeys().contains(Key.$PRESSED)) {
					final ArrayList<KeyReference> pressedKeys = new ArrayList<KeyReference>();
					pressedKeys.add(pressed);
					clone.setKeys(pressedKeys);
				}
				if(((OnKeyPress) rule).isCancelOld())
				{
					synchronized (this.currentlyPlayedAnimation)
					{
						if(this.currentlyPlayedAnimation.containsKey(pressed))
						{
							this.currentlyPlayedAnimation.get(pressed).cancel();
							this.currentlyPlayedAnimation.remove(pressed);
						}
						this.currentlyPlayedAnimation.put(pressed, clone);

					}
				}

				this.timerPool.schedule(clone, 1, TimeUnit.MICROSECONDS);
			}
		}
	}

	public void stopProgram() {
		System.out.println("Shutdown!");
		this.timerPool.shutdown();
		this.controller.shutdownLibUsb();
	}

	@Override
	public synchronized void run()
	{

		final Map<Integer, Color> keyboardColors = SharedListController.get().calculateCurrentColors(this.debugMode);
		if(this.isDebugMode())
		{
			for(final Map.Entry<Integer, Color> setting: keyboardColors.entrySet())
			{
				window.changeButtonColor(setting.getKey(), setting.getValue());
			}
		}
		try
		{
			this.controller.sendColors(keyboardColors);
		}
		catch (final Exception e)
		{
			if(!this.isIgnoreKeyboardMode())
			{
				throw e;
			}
		}
	}


	public void initObjects() throws ProgramParseException
	{
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
			try {
				rule.initObjects(this);
			}
			catch (final ProgramParseException e)
			{
				System.out.println("Error in rule (alias="+rule.getAlias()+"): " + e.getMessage());
			}
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

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(final boolean debugMode) {
		this.debugMode = debugMode;
	}

	public boolean isIgnoreKeyboardMode() {
		return ignoreKeyboardMode;
	}

	public void setIgnoreKeyboardMode(final boolean ignoreKeyboardMode) {
		this.ignoreKeyboardMode = ignoreKeyboardMode;
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

	public void setMaxProgramDuration(final int maxProgramDuration)
	{
		if (maxProgramDuration <= 0)
		{
			this.maxProgramDuration = MAX_PROGRAM_DURATION;
		}else{
			this.maxProgramDuration = maxProgramDuration;
		}
	}

	public int getMaxProgramDuration() {
		return maxProgramDuration;
	}

	public Map<Key, List<AbstractColorRule>> getOnKeyPressRuleMap() {
		return onKeyPressRuleMap;
	}

	public void setOnKeyPressRuleMap(final Map<Key, List<AbstractColorRule>> onKeyPressRuleMap) {
		this.onKeyPressRuleMap = onKeyPressRuleMap;
	}

	public void registerOnKeyPressRuleForKey(final Key key,final AbstractColorRule rule)
	{

		if (!this.onKeyPressRuleMap.containsKey(key))
		{
			this.onKeyPressRuleMap.put(key, new ArrayList<AbstractColorRule>());
		}
		if(! this.onKeyPressRuleMap.get(key).contains(rule))
		{
			this.onKeyPressRuleMap.get(key).add(rule);
		}
	}
}
