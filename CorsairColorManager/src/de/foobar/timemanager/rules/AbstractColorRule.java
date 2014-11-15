package de.foobar.timemanager.rules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.foobar.timemanager.BasicProgram;
import de.foobar.timemanager.exception.ProgramParseException;
import de.foobar.timemanager.keys.Key;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

/**
 * Editor: van on 28.10.14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class AbstractColorRule extends TimerTask {

    /**
     * Reference Name of alias
     */
    @JsonProperty( "alias" )
    private String alias = "";

	@JsonIgnore
	private BasicProgram basicProgram;

	@JsonProperty( "keys")
	private List<String> keyListTmp = new ArrayList<String>();

	@JsonIgnore
	private List<Key> keysTmp = new ArrayList<Key>();

    @JsonProperty(" doAfter")
    private List<String> doAfterListTmp = new ArrayList<String>();

	@JsonIgnore
    private List<AbstractColorRule> doAfterRules = new ArrayList<AbstractColorRule>();

    protected AbstractColorRule() {

    }

	protected AbstractColorRule(final String alias,final List<String> keyListTmp,final List<String> doAfterListTmp,final List<AbstractColorRule> doAfterRules) {
		this.alias = alias;
		this.keyListTmp = keyListTmp;
		this.doAfterListTmp = doAfterListTmp;
		this.doAfterRules = doAfterRules;
	}

	public void initObjects(final BasicProgram basicProgram) throws ProgramParseException
	{
		this.basicProgram = basicProgram;
		final Map<String,AbstractColorRule> ruleMap = basicProgram.getRuleMap();
		for(final String rule: doAfterListTmp)
		{
			if(!ruleMap.containsKey(rule))
			{
				throw new ProgramParseException("cannot find role: " + rule);
			}
			this.doAfterRules.add(ruleMap.get(rule));
		}
	}

	public String getAlias() {
        return alias;
    }

    public void setAlias(final String alias) {
        this.alias = alias;
    }

    public List<AbstractColorRule> getDoAfterRules() {
        return doAfterRules;
    }

    public void setDoAfterRules(final List<AbstractColorRule> doAfterRules) {
        this.doAfterRules = doAfterRules;
    }

    public List<String> getDoAfterListTmp() {
        return doAfterListTmp;
    }

    public void setDoAfterListTmp(final List<String> doAfterListTmp) {
        this.doAfterListTmp = doAfterListTmp;
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

