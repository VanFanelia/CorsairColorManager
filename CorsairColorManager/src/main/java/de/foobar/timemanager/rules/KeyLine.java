package de.foobar.timemanager.rules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.foobar.timemanager.BasicProgram;
import de.foobar.timemanager.common.ColorHelper;
import de.foobar.timemanager.exception.ProgramParseException;
import de.foobar.timemanager.keys.KeyReference;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.awt.*;
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
	public synchronized void run() {
		if(!this.isInLoop()) {
			//start Linear
			this.setInLoop(true);
			final ScheduledExecutorService programTimer = this.getBasicProgram().getTimerPool();
			this.setPeriodicExecution(programTimer.scheduleWithFixedDelay( this, BasicProgram.FRAME_RATE, BasicProgram.FRAME_RATE, TimeUnit.MILLISECONDS ));


			final int delayCalculated = (this.getKeys().size() * this.getKeyInterval()) + this.getKeyShowDuration() + this.getDelay();
			super.scheduleDoAfter(delayCalculated);
		}
		else
		{
			this.setTimeRunning( this.getTimeRunning() + BasicProgram.FRAME_RATE);
			final int currentKey = this.getTimeRunning() / this.getKeyInterval();

			//Add keys to show list
			if(currentKey < this.getKeys().size() && !currentFocus.containsKey(currentKey))
			{
				final KeyReference toAdd = this.getKeys().get(currentKey);
				currentFocus.put(currentKey, toAdd);
				super.setColorForKey(this.getLineColor(), toAdd);
			}

			// remove Keys from show list & set background color on removed keys
			//float percentDone = ((float) this.getTimeRunning()) / ((float) this.get());
			final float keyToHide = (float) (this.getTimeRunning() - this.getKeyShowDuration()) / (float) this.getKeyInterval();
			if(keyToHide >= 0f)
			{
				final int ikeyToHide = (int) Math.floor(keyToHide);
				if(ikeyToHide < this.getKeys().size())
				{
					for(int i = ikeyToHide; i>=0; i--)
					{
						if( currentFocus.containsKey(i)) {
							final KeyReference toRemove = currentFocus.remove(i);
							super.setColorForKey(this.getBackgroundColor(), toRemove);
						}
					}
				}
			}

			// if focus list is empty, stop execution
			if(this.currentFocus.isEmpty())
			{
				this.getPeriodicExecution().cancel(false);
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

		if (this.getDelay() < 0) {
			throw new ProgramParseException("the delay has to be a unsigned integer value (32bit) ");
		}

		this.lineColor = ColorHelper.convertRGBAHexColorToColor(this.lineColorTmp);
		this.backgroundColor = ColorHelper.convertRGBAHexColorToColor(this.backgroundColorTemp);
	}

	public KeyLine() {
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
