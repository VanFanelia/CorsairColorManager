package de.foobar.timemanager.rules;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.awt.*;

/**
 * Editor: van on 28.10.14.
 */
public class LinearColorChange extends AbstractColorRule {

	@JsonProperty("startColor")
    private Color startColor;

	@JsonProperty("endColor")
    private Color endColor;

	@JsonProperty("delay")
    private int delay;

	@JsonProperty("duration")
    private int duration;

    public LinearColorChange() {
        super();
    }

	@Override
	public void run() {

	}

	protected void initObject() throws ProgramParseException{
		//TODO IMPLEMENT
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

    public int getDelay() {
        return delay;
    }

    public void setDelay(final int delay) {
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
