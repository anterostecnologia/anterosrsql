package br.com.anteros.rsql.parser;

/**
 * A top level exception of RSQL parser that wraps all exceptions occurred in parsing.
 */
public class RSQLParserException extends RuntimeException {

	public RSQLParserException() {
		super();
	}

	public RSQLParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RSQLParserException(String message, Throwable cause) {
		super(message, cause);
	}

	public RSQLParserException(String message) {
		super(message);
	}

	public RSQLParserException(Throwable cause) {
		super(cause);
	}
	
}
