package de.foobar.timemanager.rules;

import de.foobar.timemanager.BasicProgram;
import de.foobar.timemanager.exception.ProgramParseException;

/**
 * Editor: van on 28.11.14.
 */
public interface NeedInit {

	abstract void initObjects(final BasicProgram basicProgram) throws ProgramParseException;
}
