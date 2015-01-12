package de.foobar.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.foobar.exception.ProgramParseException;
import de.foobar.window.ProgramOption;
import java.io.IOException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Editor: van on 28.10.14.
 */
public class TimeManager {

	private BasicProgram currentProgram;

    public TimeManager() { }

	public void parseProgram(final ProgramOption programOption) throws IOException, ProgramParseException {
		try {
			this.currentProgram = parseJsonToObject(programOption.getProgramCode());
			this.currentProgram.initProgram(programOption);
			this.currentProgram.initObjects();
		}catch (final IOException e){
			e.printStackTrace();
			throw new ProgramParseException("General IOException", e);
		}
	}

	/**
	 * use object Mapper to create Object
	 * @param jsonRules
	 * @return
	 * @throws IOException
	 */
	public BasicProgram parseJsonToObject (final String jsonRules) throws IOException
	{
			final ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			objectMapper.enable(MapperFeature.USE_ANNOTATIONS);
			objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

			return objectMapper.readValue(jsonRules, BasicProgram.class);
	}

    /**
     * start current program
     */
    public boolean start()
    {
	    if(this.currentProgram != null)
	    {
		    this.currentProgram.startProgram();
		    return true;
	    }
	    return false;
    }

    /**
     * TODO implement
     * stop current program
     */
    private void stop() {

    }

    /**
     * remove program from this manager
     */
    private void flush() {
        this.stop();
	    this.currentProgram = null;
    }

	public BasicProgram getCurrentProgram() {
		return currentProgram;
	}

	public void setCurrentProgram(BasicProgram currentProgram) {
		this.currentProgram = currentProgram;
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
