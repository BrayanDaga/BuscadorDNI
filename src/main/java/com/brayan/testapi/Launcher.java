package com.brayan.testapi;

import javax.swing.JOptionPane;
import com.brayan.testapi.errors.ErrorHandler;

import io.github.cdimascio.dotenv.Dotenv;

public class Launcher {

	public static void main(String[] args) throws Exception  {
	    
	    try {
	    	 final Dotenv dotenv = Dotenv.load(); // Cargar las variables del archivo .env
	 	    final String TOKEN = dotenv.get("API_TOKEN"); // Obtener el token desde el archivo .env
			BuscadorDNI buscadorDNI = new  BuscadorDNI(TOKEN);
			buscadorDNI.setVisible(true);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e.getMessage());
			ErrorHandler.throwDotEnvExceptionWithCause("No se pudo encontrar el archivo .env, por favor crealo", new Throwable(e.getMessage()));
		}

	}

}
