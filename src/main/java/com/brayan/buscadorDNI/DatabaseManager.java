package com.brayan.buscadorDNI;


import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.*;
public class DatabaseManager {

	 private static String dbPath;

	    public static Connection connect() {
	        if (dbPath == null) {
	            try {
					dbPath = copyDatabaseFromJar();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }

	        // Conectar a la base de datos SQLite usando el archivo extraído
	        String url = "jdbc:sqlite:" + dbPath;
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(url);
	            System.out.println("Conexión a la base de datos establecida.");
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }

	    private static String copyDatabaseFromJar() throws Exception {
	        // Ruta del archivo dentro del JAR
	        InputStream dbStream = DatabaseManager.class.getResourceAsStream("/resources/database.sqlite");
	        if (dbStream == null) {
	            System.out.println("No se pudo encontrar el archivo de base de datos dentro del JAR.");
	            return null;
	        }

	        try {
	            // Crear un archivo temporal en el sistema de archivos
	            Path tempFile = Files.createTempFile("database", ".sqlite");
	            tempFile.toFile().deleteOnExit(); // Eliminar el archivo temporal al cerrar la aplicación

	            // Copiar el contenido del archivo dentro del JAR al archivo temporal
	            Files.copy(dbStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
	            return tempFile.toAbsolutePath().toString();
	        } catch (Exception e) {
	            System.out.println("Error al copiar la base de datos desde el JAR: " + e.getMessage());
	            return null;
	        }
	    }
	public static void createTable() throws Exception {
		String sql = "CREATE TABLE IF NOT EXISTS personas ( dni CHAR(8) PRIMARY KEY,\n"
				+ "    nombres TEXT NOT NULL,\n" + "    apellidoPaterno TEXT NOT NULL,\n"
				+ "    apellidoMaterno TEXT NOT NULL,\n"
				+ "    nombreCompleto TEXT \n"
				+ ");";

		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
			stmt.execute(sql);
			System.out.println("Tabla creada.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// Método para insertar una persona
	public static void insertPerson(Persona persona) throws Exception {
		String sql = "INSERT INTO personas(dni, nombres, apellidoPaterno, apellidoMaterno, nombreCompleto ) VALUES(?, ?, ?, ?, ?)";

		try (Connection conn = connect(); java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, persona.getDni());
			pstmt.setString(2, persona.getNombres());
			pstmt.setString(3, persona.getApellidoPaterno());
			pstmt.setString(4, persona.getApellidoMaterno());
			pstmt.setString(5, persona.getNombreCompleto());
			pstmt.executeUpdate();
			System.out.println("Persona insertada.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	// Método para consultar personas
	public static void queryPersons() throws Exception {
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
        } catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
    }
}
