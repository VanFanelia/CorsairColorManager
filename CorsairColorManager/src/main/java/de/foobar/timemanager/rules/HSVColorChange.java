package de.foobar.timemanager.rules;

import de.foobar.timemanager.common.ColorHelper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.awt.*;

/**
 * Editor: van on 28.10.14.
 */
public class HSVColorChange extends LinearColorChange {

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
		return ColorHelper.calculateHSVColorChange(startColor, endColor, percentDone);
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
