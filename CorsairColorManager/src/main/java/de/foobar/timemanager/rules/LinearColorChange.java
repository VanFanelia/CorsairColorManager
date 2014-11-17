package de.foobar.timemanager.rules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.foobar.timemanager.BasicProgram;
import de.foobar.timemanager.common.ColorHelper;
import de.foobar.timemanager.exception.ProgramParseException;
import de.foobar.timemanager.keys.Key;
import de.foobar.timemanager.keys.KeyToNumber;
import de.foobar.timemanager.memcached.CustomMemcachedClient;
import de.foobar.timemanager.memcached.MemcachedClientPool;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.awt.*;
import java.io.IOException;
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
		if(!this.inLoop) {
			//start Linear
			final ScheduledExecutorService programTimer = this.getBasicProgram().getTimerPool();
			this.inLoop = true;
			this.periodicExecution = programTimer.scheduleWithFixedDelay( this, BasicProgram.FRAME_RATE, BasicProgram.FRAME_RATE, TimeUnit.MILLISECONDS );
			try {
				final CustomMemcachedClient client = MemcachedClientPool.getInstance();
				for (final Key key : this.getKeys()) {
					client.set(KeyToNumber.getNumber(key), 0, startColor);
				}
				final int delayCalculated = this.duration + this.getDelay();
				super.scheduleDoAfter(delayCalculated);
			} catch (final IOException e) {
				System.err.println(e.getMessage());
			}
		}
		else
		{
			this.timeRunning += BasicProgram.FRAME_RATE;
			float percentDone = (float) this.timeRunning / (float) this.duration;
			System.out.println("Percent Done: "+ String.valueOf(percentDone));
			if(percentDone > 1 )
			{
				this.periodicExecution.cancel(false);
				System.out.println("Call Cancel");
				percentDone = 1;
			}
			final Color currentColor = ColorHelper.calculateLinearColorChange(this.startColor, this.endColor, percentDone);
			this.setColorForAllKeys(currentColor);
		}
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
