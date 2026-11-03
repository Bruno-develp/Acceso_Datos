package com.carballeira.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App42 {
	public static void main(String[] args) {
		
		
		
		
		String url = "jdbc:mariadb://10.10.32.6/academia";
		String usuario = "root";
		String password = "abc123.";
		String sql = "SELECT * FROM matriculas ORDER BY id";
		
		try (Connection conexion =
				 DriverManager.getConnection(url, usuario, password);
				 PreparedStatement ps = conexion.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery()) {
				 System.out.println("MATRICULAS REGISTRADAS");
				 while (rs.next()) {
				 int id = rs.getInt("id");
				 int idAlumno = rs.getInt("id_alumno");
				 String asignatura = rs.getString("asignatura");
				 System.out.println(id + " - Alumno " + idAlumno
				 + " - " + asignatura);
				 }
				 } catch (SQLException e) {
				 System.out.println("Error: " + e.getMessage());
				 }
		
//		try (Scanner sc = new Scanner(System.in);
//				Connection conexion = DriverManager.getConnection(url, usuario, password);
//				PreparedStatement ps = conexion.prepareStatement(sql)) {
//			System.out.print("Introduce el ID del alumno: ");
//			int idAlumno = sc.nextInt();
//			sc.nextLine();
//			System.out.print("Introduce la asignatura: ");
//			String asignatura = sc.nextLine();
//			ps.setInt(1, idAlumno);
//			ps.setString(2, asignatura);
//			int filas = ps.executeUpdate();
//			System.out.println("Filas insertadas: " + filas);
//		} catch (SQLException e) {
//				System.out.println("Error: " + e.getMessage());
//		}

	}
}