package com.brayan.buscadorDNI;


import java.sql.*;
import com.brayan.buscadorDNI.Persona;

public class DatabaseManager {
	private static final String DB_URL = "jdbc:sqlite:src/main/resources/database.sqlite";

	public static Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DB_URL);
			System.out.println("Conectado a la base de datos SQLite.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public static void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS personas ( dni CHAR(8) PRIMARY KEY,\n"
				+ "    nombres TEXT NOT NULL,\n" + "    apellidoPaterno TEXT NOT NULL,\n"
				+ "    apellidoMaterno TEXT NOT NULL,\n"
				+ "    nombreCompleto TEXT GENERATED ALWAYS AS (nombres || ' ' || apellidoPaterno || ' ' || apellidoMaterno) STORED\n"
				+ ");";

		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			System.out.println("Tabla creada.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// Método para insertar una persona
	public static void insertPerson(Persona persona) {
		String sql = "INSERT INTO personas(dni, nombres, apellidoPaterno, apellidoMaterno) VALUES(?, ?, ?, ?)";

		try (Connection conn = connect(); java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, persona.getDni());
			pstmt.setString(2, persona.getNombres());
			pstmt.setString(3, persona.getApellidoPaterno());
			pstmt.setString(4, persona.getApellidoMaterno());
			pstmt.executeUpdate();
			System.out.println("Persona insertada.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// Método para consultar personas
	public static void queryPersons() {
		String sql = "SELECT dni, nombres, apellidoPaterno, apellidoMaterno, nombreCompleto FROM personas";

		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				System.out.println(
						rs.getString("dni") + "\t" + rs.getString("nombres") + "\t" + rs.getString("apellidoPaterno")
								+ "\t" + rs.getString("apellidoMaterno") + "\t" + rs.getString("nombreCompleto"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// Método para buscar una persona por DNI
    public static Persona searchPersonByDni(String dni) {
        String sql = "SELECT dni, nombres, apellidoPaterno, apellidoMaterno, nombreCompleto FROM personas WHERE dni = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();

            // Procesar el resultado
            if (rs.next()) {
            	Persona persona  = new Persona();
            	persona.setDni(dni);
            	persona.setNombres(rs.getString("nombres"));
            	persona.setApellidoPaterno(rs.getString("apellidoPaterno"));
            	persona.setApellidoMaterno(rs.getString("apellidoMaterno"));
            	persona.setNombreCompleto(rs.getString("nombreCompleto"));
                return persona;
                
            } else {
                System.out.println("No se encontró ninguna persona con el DNI: " + dni);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return null;
    }
}
