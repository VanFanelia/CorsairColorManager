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
		try {
			final CustomMemcachedClient client = MemcachedClientPool.getInstance();
			for(final Key key: this.getKeys())
			{
				client.set(KeyToNumber.getNumber(this.getBasicProgram().getKeyboardLayout(), key), 0, this.color);
			}
			super.scheduleDoAfter(this.getDelay());
		}catch (final IOException e){
			System.err.println(e.getMessage());
		}
	}

	public void initObjects(final BasicProgram basicProgram) throws ProgramParseException
	{
		super.initObjects(basicProgram);

		if (this.getDelay() < 0) {
			throw new ProgramParseException("the delay has to be a unsigned integer value (32bit) ");
		}

		this.color = ColorHelper.convertRGBAHexColorToColor(this.colorTmp);
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
