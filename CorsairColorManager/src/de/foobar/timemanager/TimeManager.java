package de.foobar.timemanager;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.foobar.timemanager.rules.AbstractColorRule;
import de.foobar.timemanager.rules.SetColor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Editor: van on 28.10.14.
 */
public class TimeManager {

    private HashMap<String, AbstractColorRule> ruleMap;

    public TimeManager() {
    }

    public TimeManager(final HashMap<String, AbstractColorRule> ruleMap) {
        this.ruleMap = ruleMap;
    }

    /**
     * Convert a json rule string in objects and execute them.
     * @param jsonRules
     */
    public TimeManager parseProgram(final String jsonRules) throws IOException {
        TimeManager tm = new TimeManager();

		BasicProgram program = parseJsonToObject(jsonRules);

	    tm.registerObjects(program.getAbstractColorRules());
        tm.initObjects();
	    tm.start();
        return tm;
    }

	private void initObjects() {
		//TODO IMPLEMENT initObject()
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
		//objectMapper.enableDefaultTyping();
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

		SimpleModule customModule = new SimpleModule("CustomModule", new Version(1, 0, 0, null,"g1","CustomModule"))
			//	.addDeserializer(AbstractColorRule.class, new RuleDeserializer())
			//	.addDeserializer(BasicProgram.class, new BasicProgramDeserializer())
				.addAbstractTypeMapping(AbstractColorRule.class, SetColor.class);
		objectMapper.registerModule(customModule);
		objectMapper.registerSubtypes(AbstractColorRule.class);


		return objectMapper.readValue(jsonRules, BasicProgram.class);
	}

    /**
     * TODO: implement
     * parse string to jsonObjects
     * @param jsonRules
     * @return
     */
    private Object parseObjects(String jsonRules) {
        return null;
    }

    /**
     * TODO: implement
     * Convert JSON Objects to parse Rules
     * @param jsonObjectTree
     * @return
     */
    private List<AbstractColorRule> parseRules(Object jsonObjectTree) {
        List<AbstractColorRule> result = new ArrayList<AbstractColorRule>();
        return result;
    }

    /**
     * TODO: implement
     * add rules to
     * @param colorRules
     */
    private void registerObjects(List<AbstractColorRule> colorRules) {
        HashMap<String, AbstractColorRule> aliasMap = new HashMap<String, AbstractColorRule>();
        for(AbstractColorRule cr :colorRules) {
            aliasMap.put(cr.getAlias(),cr);
        }
        this.ruleMap = aliasMap;
    }

    /**
     * TODO implement
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
     * remove all events from this manager
     */
    private void flush() {
        this.stop();
        this.ruleMap = new HashMap<String, AbstractColorRule>();
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
