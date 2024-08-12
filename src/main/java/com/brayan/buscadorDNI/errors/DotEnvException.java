package com.brayan.buscadorDNI.errors;

public class DotEnvException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DotEnvException(String message) {
        super(message);
    }

    public DotEnvException(String message, Throwable cause) {
        super(message, cause);
    }
}
