package de.foobar.rules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.foobar.common.BasicProgram;
import de.foobar.color.ColorHelper;
import de.foobar.exception.ProgramParseException;
import de.foobar.keys.Key;
import de.foobar.keys.KeyGroup;
import de.foobar.keys.KeyReference;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Editor: van on 28.10.14.
 */
public class KeyLine extends AbstractColorRule {

    @JsonProperty("lineColor")
    private String lineColorTmp;

	@JsonIgnore
	private Color lineColor;

	@JsonProperty("keyShowDuration")
	private int keyShowDuration;

	@JsonProperty("keyInterval")
	private int keyInterval;

	@JsonProperty("backgroundColor")
	private String backgroundColorTemp;

	@JsonIgnore
	private Color backgroundColor;

	@JsonIgnore
	private boolean inLoop = false;

	@JsonIgnore
	private int timeRunning = 0;

	@JsonIgnore
	private ScheduledFuture periodicExecution;

	@JsonIgnore
	private HashMap<Integer, KeyReference> currentFocus = new HashMap<Integer, KeyReference>();

	@Override
	public synchronized void run()
	{
		super.run();
		if(!this.isInLoop()) {
			//start Linear
			this.setInLoop(true);
			final ScheduledExecutorService programTimer = this.getBasicProgram().getTimerPool();
			this.setPeriodicExecution(programTimer.scheduleWithFixedDelay( this, BasicProgram.FRAME_RATE, BasicProgram.FRAME_RATE, TimeUnit.MILLISECONDS ));

		}
		else
		{
			this.setTimeRunning( this.getTimeRunning() + BasicProgram.FRAME_RATE);
			final int currentKey = this.getTimeRunning() / this.getKeyInterval();

			// remove Keys from show list & set background color on removed keys
			//float percentDone = ((float) this.getTimeRunning()) / ((float) this.get());
			final float keyToHide = (float) (this.getTimeRunning() - this.getKeyShowDuration()) / (float) this.getKeyInterval();
			final ArrayList<Key> toRemoveList = new ArrayList<>();
			if(keyToHide >= 0f)
			{
				final int ikeyToHide = (int) Math.floor(keyToHide);
				if(ikeyToHide < this.getKeys().size())
				{
					for(int i = ikeyToHide; i>=0; i--)
					{
						if( currentFocus.containsKey(i)) {
							final KeyReference toRemove = currentFocus.remove(i);
							if(toRemove instanceof Key)
							{
								toRemoveList.add((Key) toRemove);
							}else if(toRemove instanceof KeyGroup)
							{
								toRemoveList.addAll(((KeyGroup) toRemove).getKeyList());
							}
						}
					}
				}
			}

			//Add keys to show list
			if(currentKey < this.getKeys().size() && !currentFocus.containsKey(currentKey))
			{
				final KeyReference toAdd = this.getKeys().get(currentKey);
				currentFocus.put(currentKey, toAdd);
				if(toAdd instanceof Key && toRemoveList.contains(toAdd))
				{
					toRemoveList.remove(toAdd);
				}else if(toAdd instanceof KeyGroup)
				{
					for(final Key toAddFromList: ((KeyGroup) toAdd).getKeyList())
					{
						if(toRemoveList.contains(toAddFromList))
						{
							toRemoveList.remove(toAddFromList);
						}
					}
				}
				super.setColorForKey(this.getLineColor(), toAdd);
			}

			// all removed keys who are not added new will be set to background color
			for(final Key setToBackgroundColor: toRemoveList)
			{
				super.setColorForKey(this.getBackgroundColor(), setToBackgroundColor);
			}

			// if focus list is empty, stop execution
			if(this.currentFocus.isEmpty())
			{
				this.getPeriodicExecution().cancel(false);
				super.scheduleDoAfter(this.getDelay());
				this.reset();
			}
		}
	}

	public void reset()
	{
		this.setInLoop(false);
		this.timeRunning = 0;

	}

	public void initObjects(final BasicProgram basicProgram) throws ProgramParseException
	{
		super.initObjects(basicProgram);

		this.lineColor = ColorHelper.convertRGBAHexColorToColor(this.lineColorTmp);
		this.backgroundColor = ColorHelper.convertRGBAHexColorToColor(this.backgroundColorTemp);
	}

	public KeyLine() {
	}

	@Override
	public KeyLine clone() throws CloneNotSupportedException
	{
		final KeyLine clone = (KeyLine) super.clone();
		clone.lineColorTmp = this.lineColorTmp;
		clone.lineColor = this.lineColor;
		clone.keyShowDuration = this.keyShowDuration;
		clone.keyInterval = this.keyInterval;
		clone.backgroundColorTemp = this.backgroundColorTemp;
		clone.backgroundColor = this.backgroundColor;
		clone.inLoop = this.inLoop;
		clone.timeRunning = this.timeRunning;
		clone.periodicExecution = null;
		clone.currentFocus = new HashMap<Integer, KeyReference>();
		return clone;
	}

	public String getLineColorTmp() {
		return lineColorTmp;
	}

	public void setLineColorTmp(String lineColorTmp) {
		this.lineColorTmp = lineColorTmp;
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public int getKeyShowDuration() {
		return keyShowDuration;
	}

	public void setKeyShowDuration(int keyShowDuration) {
		this.keyShowDuration = keyShowDuration;
	}

	public String getBackgroundColorTemp() {
		return backgroundColorTemp;
	}

	public void setBackgroundColorTemp(String backgroundColorTemp) {
		this.backgroundColorTemp = backgroundColorTemp;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public boolean isInLoop() {
		return inLoop;
	}

	public void setInLoop(boolean inLoop) {
		this.inLoop = inLoop;
	}

	public int getTimeRunning() {
		return timeRunning;
	}

	public void setTimeRunning(int timeRunning) {
		this.timeRunning = timeRunning;
	}

	public ScheduledFuture getPeriodicExecution() {
		return periodicExecution;
	}

	public void setPeriodicExecution(ScheduledFuture periodicExecution) {
		this.periodicExecution = periodicExecution;
	}

	public int getKeyInterval() {
		return keyInterval;
	}

	public void setKeyInterval(int keyInterval) {
		this.keyInterval = keyInterval;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(final Object o) {
        return EqualsBuilder.reflectionEquals(this,o,false);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this,false);
    }

}
