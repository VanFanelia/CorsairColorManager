package de.foobar.timemanager.rules;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
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

	@JsonProperty( "keys")
	private List<String> keys = new ArrayList<String>();

    @JsonProperty(" doAfter")
    private List<String> doAfterAlias = new ArrayList<String>();

	@JsonIgnore
    private List<AbstractColorRule> doAfterRules = new ArrayList<AbstractColorRule>();

    protected AbstractColorRule() {

    }

	protected AbstractColorRule(final String alias,final List<String> keys,final List<String> doAfterAlias,final List<AbstractColorRule> doAfterRules) {
		this.alias = alias;
		this.keys = keys;
		this.doAfterAlias = doAfterAlias;
		this.doAfterRules = doAfterRules;
	}
	
	protected void initObject() throws ProgramParseException{
		//TODO IMPLEMENT
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

    public List<String> getDoAfterAlias() {
        return doAfterAlias;
    }

    public void setDoAfterAlias(final List<String> doAfterAlias) {
        this.doAfterAlias = doAfterAlias;
    }

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(final List<String> keys) {
		this.keys = keys;
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

