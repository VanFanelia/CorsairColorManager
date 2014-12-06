package de.foobar.timemanager.rules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.foobar.timemanager.BasicProgram;
import de.foobar.timemanager.common.ColorHelper;
import de.foobar.timemanager.exception.ProgramParseException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.awt.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Editor: van on 28.10.14.
 */
public class SetContinuousColor extends AbstractColorRule {

    @JsonProperty("color")
    private String lineColorTmp;

	@JsonIgnore
	private Color color;

	@JsonProperty("interval")
	private int interval;

	@JsonIgnore
	private boolean inLoop = false;

	@JsonIgnore
	private int timeRunning = 0;

	@JsonIgnore
	private ScheduledFuture periodicExecution;

	@Override
	public synchronized void run() {
		if(!this.isInLoop()) {
			//start Linear
			this.setInLoop(true);
			final ScheduledExecutorService programTimer = this.getBasicProgram().getTimerPool();
			this.setPeriodicExecution(programTimer.scheduleWithFixedDelay( this, this.getInterval(), this.getInterval(), TimeUnit.MILLISECONDS ));

			super.scheduleDoAfter(this.getDelay());
		}
		else
		{
			this.setTimeRunning( this.getTimeRunning() + this.getInterval());

			super.setColorForAllKeys(this.getColor());

			// if focus list is empty, stop execution
			if((this.getDelay() - this.getTimeRunning()) <= this.getTimeRunning())
			{
				this.getPeriodicExecution().cancel(false);
			}
		}
	}

	public void initObjects(final BasicProgram basicProgram) throws ProgramParseException
	{
		super.initObjects(basicProgram);

		if (this.getDelay() < 0) {
			throw new ProgramParseException("the delay has to be a unsigned integer value (32bit) ");
		}

		this.color = ColorHelper.convertRGBAHexColorToColor(this.lineColorTmp);
	}

	public SetContinuousColor() {
	}

	public String getLineColorTmp() {
		return lineColorTmp;
	}

	public void setLineColorTmp(final String lineColorTmp) {
		this.lineColorTmp = lineColorTmp;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		this.color = color;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(final int interval) {
		this.interval = interval;
	}

	public boolean isInLoop() {
		return inLoop;
	}

	public void setInLoop(final boolean inLoop) {
		this.inLoop = inLoop;
	}

	public int getTimeRunning() {
		return timeRunning;
	}

	public void setTimeRunning(final int timeRunning) {
		this.timeRunning = timeRunning;
	}

	public ScheduledFuture getPeriodicExecution() {
		return periodicExecution;
	}

	public void setPeriodicExecution(final ScheduledFuture periodicExecution) {
		this.periodicExecution = periodicExecution;
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
