package com.brayan.buscadorDNI;

public class prueba {

	public static void main(String[] args) {
        DatabaseManager.createTable(); // Crear la tabla

	DatabaseManager.searchPersonByDni("74953214");

	}

}
