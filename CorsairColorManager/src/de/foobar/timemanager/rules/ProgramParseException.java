package de.foobar.timemanager.rules;

/**
 * Editor: van on 09.11.14.
 */
public class ProgramParseException extends RuntimeException {

	public ProgramParseException() {
	}

	public ProgramParseException(String message) {
		super(message);
	}

	public ProgramParseException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProgramParseException(Throwable cause) {
		super(cause);
	}

	public ProgramParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
