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
