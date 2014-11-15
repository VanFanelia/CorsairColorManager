package de.foobar.timemanager;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.foobar.timemanager.exception.ProgramParseException;
import de.foobar.timemanager.rules.AbstractColorRule;
import de.foobar.timemanager.rules.LinearColorChange;
import de.foobar.timemanager.rules.SetColor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;

/**
 * Editor: van on 28.10.14.
 */
public class TimeManager {

	private BasicProgram currentProgram;

    public TimeManager() {
    }


    /**
     * Convert a json rule string in objects and execute them.
     * @param jsonRules
     */
    public void parseProgram(final String jsonRules) throws IOException, ProgramParseException {

	    try {
		    this.currentProgram = parseJsonToObject(jsonRules);
		    this.currentProgram.initObjects();
		    this.start();
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
	public BasicProgram parseJsonToObject (final String jsonRules) throws IOException {
			final ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			objectMapper.enable(MapperFeature.USE_ANNOTATIONS);
			objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

			final SimpleModule customModule = new SimpleModule("CustomModule", new Version(1, 0, 0, null, "g1", "CustomModule"));
					customModule.addAbstractTypeMapping(AbstractColorRule.class, SetColor.class);
					customModule.addAbstractTypeMapping(AbstractColorRule.class, LinearColorChange.class);
					customModule.registerSubtypes(SetColor.class);
					customModule.registerSubtypes(LinearColorChange.class);

			objectMapper.registerModule(customModule);

			//objectMapper.registerSubtypes(SetColor.class);
			//objectMapper.registerSubtypes(LinearColorChange.class);
			return objectMapper.readValue(jsonRules, BasicProgram.class);
	}

    /**
     * start current program
     */
    private void start() {

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
