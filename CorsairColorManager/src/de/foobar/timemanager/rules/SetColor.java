package de.foobar.timemanager.rules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.awt.*;

/**
 * Editor: van on 28.10.14.
 */
public class SetColor extends AbstractColorRule {

    @JsonProperty("color")
    private String color;

	@JsonIgnore
	private Color colorObject;

    @JsonProperty("delay")
    private int delay = 0;


	@Override
	public void run() {

	}

	protected void initObject() throws ProgramParseException {
		super.initObject();
		if(this.color == null || (this.color.length() != 6 && this.color.length() != 8 )) {
			throw new ProgramParseException("color value not valid. please use RGB or RGBA Color code in HEX cods like: ff0000 for red or ff0000ff for Red with 100% Alpha");
		}
		if(this.delay < 0 ) {
			throw new ProgramParseException("the delay has to be a unsigned integer value (32bit) ");
		}

		if(this.color.length() == 6) {
			this.colorObject = new Color( Integer.valueOf( this.color.substring( 1, 3 ), 16 ),
										  Integer.valueOf( this.color.substring( 3, 5 ), 16 ),
										  Integer.valueOf( this.color.substring( 5, 7 ), 16 ) );
		} else {
			this.colorObject = new Color( Integer.valueOf( this.color.substring( 1, 3 ), 16 ),
										  Integer.valueOf( this.color.substring( 3, 5 ), 16 ),
										  Integer.valueOf( this.color.substring( 5, 7 ), 16 ),
										  Integer.valueOf( this.color.substring( 7, 9 ), 16 ) );
		}

	}

	public SetColor() {
	}

	public SetColor(final String color, final int delay) {
        this.color = color;
        this.delay = delay;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = color;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(final int delay) {
        this.delay = delay;
    }

	public Color getColorObject() {
		return colorObject;
	}

	public void setColorObject(final Color colorObject) {
		this.colorObject = colorObject;
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
