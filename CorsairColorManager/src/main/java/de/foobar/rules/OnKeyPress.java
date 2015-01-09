package de.foobar.rules;

import de.foobar.common.BasicProgram;
import de.foobar.exception.ProgramParseException;
import de.foobar.keys.Key;
import de.foobar.keys.KeyGroup;
import de.foobar.keys.KeyReference;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Editor: van on 28.10.14.
 */
public class OnKeyPress extends AbstractColorRule {

	@Override
	public void run()
	{
		// do nothing, just jump to doAfter
		super.scheduleDoAfter(this.getDelay());
	}

	public void initObjects(final BasicProgram basicProgram) throws ProgramParseException
	{
		super.initObjects(basicProgram);

		if (this.getDelay() < 0) {
			throw new ProgramParseException("the delay has to be a unsigned integer value (32bit) ");
		}
		for(final KeyReference keyReference: this.getKeys())
		{
			if(keyReference instanceof KeyGroup)
			{
				final KeyGroup group = (KeyGroup) keyReference;
				for(final Key keyInGroup : group.getKeyList())
				{
					basicProgram.registerOnKeyPressRuleForKey(keyInGroup, this);
				}
			}
			else {
				basicProgram.registerOnKeyPressRuleForKey((Key) keyReference, this);
			}
		}
	}

	@Override
	public OnKeyPress clone() throws CloneNotSupportedException
	{
		final OnKeyPress clone = (OnKeyPress) super.clone();
		return clone;
	}

	public OnKeyPress() {
	}

	public OnKeyPress(final int delay) {
        this.setDelay(delay);
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
