package de.foobar.rules;

import com.fasterxml.jackson.annotation.JsonProperty;
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

	@JsonProperty("cancelOld")
	private boolean cancelOld = false;

	@Override
	public void run()
	{
		super.run();
		// do nothing, just jump to doAfter
		super.scheduleDoAfter(this.getDelay());
	}

	public void initObjects(final BasicProgram basicProgram) throws ProgramParseException
	{
		super.initObjects(basicProgram);

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
		clone.cancelOld = this.cancelOld;
		return clone;
	}

	public OnKeyPress() {
	}

	public OnKeyPress(final int delay) {
        this.setDelay(delay);
    }


	public boolean isCancelOld() {
		return cancelOld;
	}

	public void setCancelOld(boolean cancelOld) {
		this.cancelOld = cancelOld;
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
