package de.foobar.rules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.foobar.common.BasicProgram;
import de.foobar.color.ColorHelper;
import de.foobar.exception.ProgramParseException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.awt.*;

/**
 * Editor: van on 28.10.14.
 */
public class SetColor extends AbstractColorRule {

    @JsonProperty("color")
    private String colorTmp;

	@JsonIgnore
	private Color color;



	@Override
	public void run()
	{
		super.run();
		super.setColorForAllKeys( this.color);
		super.scheduleDoAfter(this.getDelay());
	}

	public void initObjects(final BasicProgram basicProgram) throws ProgramParseException
	{
		super.initObjects(basicProgram);

		this.color = ColorHelper.convertRGBAHexColorToColor(this.colorTmp);
	}

	@Override
	public SetColor clone() throws CloneNotSupportedException
	{
		final SetColor clone = (SetColor) super.clone();
		clone.colorTmp = this.colorTmp;
		clone.color = this.color;
		return clone;
	}

	public SetColor() {
	}

	public SetColor(final String colorTmp, final int delay) {
        this.colorTmp = colorTmp;
        this.setDelay(delay);
    }

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		this.color = color;
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
