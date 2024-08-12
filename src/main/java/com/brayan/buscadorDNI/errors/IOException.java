package com.brayan.buscadorDNI.errors;

public class IOException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IOException(String message) {
        super(message);
    }

    public IOException(String message, Throwable cause) {
        super(message, cause);
    }
}
