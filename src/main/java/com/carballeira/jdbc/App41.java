package com.carballeira.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App41 {
	public static void main(String[] args) {

		String url = "jdbc:mariadb://10.10.32.6:3306/academia";
		String usuario = "root";
		String password = "abc123.";
		
		try (Connection conexion = DriverManager.getConnection(url, usuario, password);
			 Scanner sc = new Scanner(System.in)) {
			
			System.out.println("Conexión correcta con MariaDB\nBase de datos: academia\n");
			
			System.out.print("Introduce la nota mínima: ");
			double notaMinima = sc.nextDouble();
			
			String sql = "SELECT * FROM alumnos WHERE nota >= ?";
			
			try (PreparedStatement ps = conexion.prepareStatement(sql)) {
				ps.setDouble(1, notaMinima);
				
				try (ResultSet rs = ps.executeQuery()) {
					System.out.println("\nAlumnos encontrados:");
					
					boolean hayAlumnos = false;
					while(rs.next()) {
						hayAlumnos = true;
						int id = rs.getInt("id");
						String nombre = rs.getString("nombre");
						String apellidos = rs.getString("apellidos");
						double nota = rs.getDouble("nota");
						
						System.out.println(id + " - " + nombre + " " + apellidos + " - Nota: " + nota);
					}
					
					if (!hayAlumnos) {
						System.out.println("No se encontró ningún alumno con esa nota o superior.");
					}
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}
}