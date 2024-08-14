package com.brayan.buscadorDNI.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.brayan.buscadorDNI.exceptions.ErrorHandler;
import com.brayan.buscadorDNI.exceptions.NoInternetException;

public class InternetChecker {

    public static void checkInternetConnection() throws NoInternetException {
        try (Socket socket = new Socket()) {
            // Intenta conectarte a google.com en el puerto 80
            socket.connect(new InetSocketAddress("www.google.com", 80), 2000);
        } catch (IOException e) {
                ErrorHandler.throwNotInternetException(
	                "No hay conexi�n a Internet. Por favor, verifica tu conexi�n",
	                new Throwable(e.getMessage()));
            
        }
    }
}
