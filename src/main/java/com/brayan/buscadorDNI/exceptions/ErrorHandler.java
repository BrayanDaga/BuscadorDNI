package com.brayan.buscadorDNI.exceptions;

public class ErrorHandler {
    
	public static void throwDotEnvExceptionWithCause(String message, Throwable cause) throws DotEnvException {
		throw new DotEnvException(message, cause);
	}
	
	public static void throwIOExceptionWithCause(String message, Throwable cause) throws IOException {
		throw new IOException(message, cause);
	}
	public static void throwNotInternetException(String message, Throwable cause) throws  NoInternetException {
		throw new NoInternetException(message, cause);
	}
}
