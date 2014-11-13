



package de.foobar.timemanager;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * Editor: van on 10.11.14.
 */
// <EMap<String, EList<Value<?>>>>
public class BasicProgramDeserializer extends StdDeserializer<BasicProgram> {


	protected BasicProgramDeserializer() {
		super(BasicProgram.class);
	}

	@Override
	public BasicProgram deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException{

		JsonNode node = jp.getCodec().readTree(jp);
		System.out.println(node);

		return new BasicProgram();
	}

}
