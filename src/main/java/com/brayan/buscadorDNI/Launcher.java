package com.brayan.buscadorDNI;

import javax.swing.JOptionPane;

import com.brayan.buscadorDNI.errors.ErrorHandler;

import io.github.cdimascio.dotenv.Dotenv;

public class Launcher {

	public static void main(String[] args) throws Exception {

		try {
			final  Dotenv dotenv = Dotenv.configure().directory(".").load(); // Esto indica que el archivo .env está en la raíz del proyecto
		      // Cargar las variables del archivo .env
			final String TOKEN = dotenv.get("API_TOKEN"); // Obtener el token desde el archivo .env
			MainWindow buscadorDNI = new MainWindow(TOKEN);
			buscadorDNI.setVisible(true);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e.getMessage());
			ErrorHandler.throwDotEnvExceptionWithCause("No se pudo encontrar el archivo .env, por favor crealo",
					new Throwable(e.getMessage()));
		}
		
	}

}
