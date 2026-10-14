package com.carballeira.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App24 {
	public static void main(String[] args) {
		String url = "jdbc:sqlite:java26.db";

		try (Connection conexion = DriverManager.getConnection(url)) {
			System.out.println("Conexión establecida con éxito.");

			System.out.println("\nNÚMERO TOTAL DE ALUMNOS");
				String sqlTotal = "SELECT COUNT(*) AS total FROM alumno";
				
			PreparedStatement psTotal = conexion.prepareStatement(sqlTotal);
			ResultSet rsTotal = psTotal.executeQuery();
			
			if (rsTotal.next()) {
				int total = rsTotal.getInt("total");
				System.out.println("Total de alumnos: " + total);
			}

			System.out.println("\nNOTA MEDIA DE LOS ALUMNOS");
				String sqlMedia = "SELECT AVG(notaFinal) AS media FROM alumno";
				
			PreparedStatement psMedia = conexion.prepareStatement(sqlMedia);
			ResultSet rsMedia = psMedia.executeQuery();
			
			if (rsMedia.next()) {
				double media = rsMedia.getDouble("media");
				System.out.println("Nota media: " + media);
			}

			System.out.println("\nNOTA MÁXIMA");
				String sqlMax = "SELECT MAX(notaFinal) AS maxima FROM alumno";
				
			PreparedStatement psMax = conexion.prepareStatement(sqlMax);
			ResultSet rsMax = psMax.executeQuery();
			
			if (rsMax.next()) {
				int maxima = rsMax.getInt("maxima");
				System.out.println("Nota máxima: " + maxima);
			}

			System.out.println("\nNOTA MÍNIMA");
				String sqlMin = "SELECT MIN(notaFinal) AS minima FROM alumno";
				
			PreparedStatement psMin = conexion.prepareStatement(sqlMin);
			ResultSet rsMin = psMin.executeQuery();
			
			if (rsMin.next()) {
				int minima = rsMin.getInt("minima");
				System.out.println("Nota mínima: " + minima);
			}

			System.out.println("\nAPROBADOS");
				String sqlAprobados = "SELECT COUNT(*) AS aprobados FROM alumno WHERE notaFinal >= 5";
				
			PreparedStatement psAprobados = conexion.prepareStatement(sqlAprobados);
			ResultSet rsAprobados = psAprobados.executeQuery();
			
			if (rsAprobados.next()) {
				int aprobados = rsAprobados.getInt("aprobados");
				System.out.println("Aprobados: " + aprobados);
			}

			System.out.println("\nSUSPENSOS");
				String sqlSuspensos = "SELECT COUNT(*) AS suspensos FROM alumno WHERE notaFinal < 5";
				
			PreparedStatement psSuspensos = conexion.prepareStatement(sqlSuspensos);
			ResultSet rsSuspensos = psSuspensos.executeQuery();
			
			if (rsSuspensos.next()) {
				int suspensos = rsSuspensos.getInt("suspensos");
				System.out.println("Suspensos: " + suspensos);
			}

		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}