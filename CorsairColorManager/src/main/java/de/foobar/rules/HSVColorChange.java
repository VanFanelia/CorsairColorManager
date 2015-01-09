package de.foobar.rules;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.foobar.color.ColorHelper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.awt.*;

/**
 * Editor: van on 28.10.14.
 */
public class HSVColorChange extends LinearColorChange {

	@JsonIgnoreProperties
	private int direction = 1;

    public HSVColorChange() {
        super();
    }



	@Override
	public synchronized void run() {
		super.run();
	}

	@Override
	public Color calculateCurrentColor(final Color startColor, final Color endColor,final float percentDone)
	{
		return ColorHelper.calculateHSVColorChange(startColor, endColor, percentDone, direction);
	}

	@Override
	public HSVColorChange clone() throws CloneNotSupportedException
	{
		final HSVColorChange clone = (HSVColorChange) super.clone();
		clone.direction = this.direction;
		return clone;
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
