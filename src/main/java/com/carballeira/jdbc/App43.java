package com.carballeira.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App43 {
	public static void main(String[] args) {
		String url = "jdbc:mariadb://10.10.32.6:3306/academia";
		String usuario = "root";
		String password = "abc123.";
		
		//Este sql es para los dos primeros try 
		
//		String sql = """
//				SELECT alumnos.nombre, alumnos.apellidos, matriculas.asignatura
//				FROM alumnos
//				INNER JOIN matriculas
//				ON alumnos.id = matriculas.id_alumno
//				WHERE alumnos.id = ?
//				ORDER BY alumnos.id, matriculas.id
//				""";
		
//		try(Connection con = DriverManager.getConnection(url, usuario, password);
//				PreparedStatement ps = con.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery()){
//			
//			System.out.println("MATRICULAS REGISTRADAS");
//			while(rs.next()) {
//				String nombre = rs.getString("nombre");
//				String apellidos = rs.getString("apellidos");
//				String asignatura = rs.getString("asignatura");
//				
//				System.out.println(nombre + " " + apellidos + " - " + asignatura);
//			}
//			
//			
//		}catch(SQLException e) {
//			System.out.println("Error: " + e.getMessage());
//		}
		
//		try(Scanner sc = new Scanner(System.in);
//				Connection con = DriverManager.getConnection(url, usuario, password);
//				PreparedStatement ps = con.prepareStatement(sql)){
//			System.out.println("Introduce el ID del alumno: ");
//				int idAlumno = sc.nextInt();
//				ps.setInt(1, idAlumno);
//				
//				try(ResultSet rs = ps.executeQuery()){
//					int contador = 0;
//					boolean encontrado = false;
//					while(rs.next()) {
//						if(!encontrado) {
//							System.out.println("Asignaturas de " + rs.getString("nombre") + " " + rs.getString("apellidos") + ": ");
//						}
//						System.out.println("- " + rs.getString("asignatura"));
//						encontrado = true;
//						contador++;
//					}
//					if(!encontrado) {
//						System.out.println("No hay matrículas para ese alumnos");
//					}
//					System.out.println("Total de asignaturas: " + contador);
//				}
//				
//				
//		}catch(SQLException e) {
//			System.out.println("Error: " + e.getMessage());
//		}
		
		String sql = """
				SELECT alumnos.nombre, alumnos.apellidos FROM alumnos
				INNER JOIN matriculas ON alumnos.id = matriculas.id_alumno
				WHERE matriculas.asignatura = ?
				ORDER BY alumnos.apellidos, alumnos.nombre
				""";

		try (Scanner sc = new Scanner(System.in);
				Connection con = DriverManager.getConnection(url, usuario, password);
				PreparedStatement ps = con.prepareStatement(sql)) {
			System.out.print("Introduce una asignatura: ");
			String asignatura = sc.nextLine();
			ps.setString(1, asignatura);
			try (ResultSet rs = ps.executeQuery()) {
				int contador = 0;
				System.out.println("Alumnos matriculados:");
				while (rs.next()) {
					String nombre = rs.getString("nombre");
					String apellidos = rs.getString("apellidos");
					System.out.println("- " + nombre + " " + apellidos);
					contador++;
				}
				if (contador == 0) {
					System.out.println("No hay alumnos en esa asignatura.");
				}
				System.out.println("Total de alumnos: " + contador);
			}
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
