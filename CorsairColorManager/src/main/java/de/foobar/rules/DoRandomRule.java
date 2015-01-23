package de.foobar.rules;

import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Editor: van on 28.10.14.
 */
public class DoRandomRule extends AbstractColorRule {

	@Override
	public void run()
	{
		super.run();

		final ScheduledExecutorService programTimer = this.getBasicProgram().getTimerPool();

		if(!this.getDoAfterRules().isEmpty())
		{
			Collections.shuffle(this.getDoAfterRules());
			final AbstractColorRule first = this.getDoAfterRules().get(0);
			programTimer.schedule( first, (this.getDelay() + first.getStartDelay()), TimeUnit.MILLISECONDS );
		}
	}

	@Override
	public DoRandomRule clone() throws CloneNotSupportedException
	{
		return (DoRandomRule) super.clone();
	}

	public DoRandomRule() {
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
