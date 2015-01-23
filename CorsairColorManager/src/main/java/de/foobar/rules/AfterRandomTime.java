package de.foobar.rules;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.foobar.common.BasicProgram;
import de.foobar.exception.ProgramParseException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Editor: van on 28.10.14.
 */
public class AfterRandomTime extends AbstractColorRule {

	@JsonProperty("timeRange")
	private int timeRange = 1000;

	@Override
	public void run()
	{
		super.run();
		// do nothing, just jump to doAfter
		final int randomDelay = (int) (Math.random() * timeRange);
		//System.out.println(randomDelay);
		super.scheduleDoAfter(this.getDelay() + randomDelay);
	}

	public void initObjects(final BasicProgram basicProgram) throws ProgramParseException
	{
		super.initObjects(basicProgram);

		if (this.getTimeRange() < 0) {
			throw new ProgramParseException("the timeRange has to be a unsigned integer value (32bit) ");
		}
	}

	@Override
	public AfterRandomTime clone() throws CloneNotSupportedException
	{
		final AfterRandomTime clone = (AfterRandomTime) super.clone();
		clone.timeRange = this.timeRange;
		return clone;
	}

	public AfterRandomTime() {
	}

	public int getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(int timeRange) {
		this.timeRange = timeRange;
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
