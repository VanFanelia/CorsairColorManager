package de.foobar.rules;

import de.foobar.common.BasicProgram;
import de.foobar.exception.ProgramParseException;

/**
 * Editor: van on 28.11.14.
 */
public interface NeedInit {

	abstract void initObjects(final BasicProgram basicProgram) throws ProgramParseException;
}
