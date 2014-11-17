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
public class LinearColorChange extends AbstractColorRule {

	@JsonProperty("startColor")
	private String startColorTmp;

	@JsonIgnore
    private Color startColor;

	@JsonProperty("endColor")
    private String endColorTmp;

	@JsonIgnore
	private Color endColor;

	@JsonProperty("duration")
    private int duration;

    public LinearColorChange() {
        super();
    }

	// for period execution

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
			this.setPeriodicExecution(programTimer.scheduleWithFixedDelay( this, BasicProgram.FRAME_RATE, BasicProgram.FRAME_RATE, TimeUnit.MILLISECONDS ));

			super.setColorForAllKeys(this.getStartColor());
			final int delayCalculated = this.getDuration() + this.getDelay();
			super.scheduleDoAfter(delayCalculated);
		}
		else
		{
			this.setTimeRunning( this.getTimeRunning() + BasicProgram.FRAME_RATE);
			float percentDone = ((float) this.getTimeRunning()) / ((float) this.getDuration());
			// System.out.println("Percent Done: "+ String.valueOf(percentDone)); only 4 debug
			if(percentDone > 1f )
			{
				this.getPeriodicExecution().cancel(false);
				this.setInLoop(false);
				this.reset();
				percentDone = 1f;
			}
			final Color currentColor = this.calculateCurrentColor(this.getStartColor(), this.getEndColor(), percentDone);
			this.setColorForAllKeys(currentColor);
		}
	}

	public void reset()
	{
		this.setInLoop(false);
		this.timeRunning = 0;
	}

	public Color calculateCurrentColor(final Color startColor, final Color endColor,final float percentDone)
	{
		return ColorHelper.calculateLinearColorChange(startColor, endColor, percentDone);
	}

	public void initObjects(final BasicProgram basicProgram) throws ProgramParseException {
		super.initObjects(basicProgram);

		this.endColor = ColorHelper.convertRGBAHexColorToColor(this.endColorTmp);
		this.startColor = ColorHelper.convertRGBAHexColorToColor(this.startColorTmp);

	}


    public Color getStartColor() {
        return startColor;
    }

    public void setStartColor(final Color startColor) {
        this.startColor = startColor;
    }

    public Color getEndColor() {
        return endColor;
    }

    public void setEndColor(final Color endColor) {
        this.endColor = endColor;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
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
		return EqualsBuilder.reflectionEquals(this, o, false);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}

}
