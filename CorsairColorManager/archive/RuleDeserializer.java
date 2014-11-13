



package de.foobar.timemanager;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import de.foobar.timemanager.rules.AbstractColorRule;
import de.foobar.timemanager.rules.SetColor;

import java.io.IOException;

/**
 * Editor: van on 10.11.14.
 */
// <EMap<String, EList<Value<?>>>>
public class RuleDeserializer extends StdDeserializer<AbstractColorRule> {


	protected RuleDeserializer() {
		super(AbstractColorRule.class);
	}

	@Override
	public AbstractColorRule deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException{

		jp.canReadTypeId();


		return new SetColor();
	}

}
