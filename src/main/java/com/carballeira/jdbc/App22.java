package com.carballeira.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class App22 {
	public static void main(String[] args) {
		ArrayList<Alumno> listaAlumnos = new ArrayList<>();
		System.out.println("Numero de alumnos: " + listaAlumnos.size());
		String url = "jdbc:sqlite:java26.db";
		
		
//		Alumno alumno1 = new Alumno(
//				"11111111A",
//				"Ana",
//				"Garcia",
//				"López",
//				"DAM",
//				2,
//				3,
//				8,
//				9,
//				8			
//				);
//		listaAlumnos.add(alumno1);
//		System.out.println("Numero de alumnos despuesde añadir: " + listaAlumnos.size());
		
		
		try(Connection conexion = DriverManager.getConnection(url)){
			
			String sql = "SELECT * FROM alumno";
			
			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Alumno alumnoBD = new Alumno(
						rs.getString("dni"),
						rs.getString("nombre"),
						rs.getString("apellido1"),
						rs.getString("apellido2"),
						rs.getString("ciclo"),
						rs.getInt("curso"),
						rs.getInt("eva1"),
						rs.getInt("eva2"),
						rs.getInt("eva3"),
						rs.getInt("notaFinal")
				);
				listaAlumnos.add(alumnoBD);
			}
			System.out.println("Numero de alumnos guardados: " + listaAlumnos.size());
			System.out.println("\nAlumnos guardados en el arrayList\n");
			
			for(Alumno alumno : listaAlumnos) {
				System.out.println(
	                    alumno.getDni() + " - " +
	                    alumno.getNombre() + " " +
	                    alumno.getApellido1() + " - " +
	                        "Nota final: " +
	                   alumno.getNotaFinal()
	                  );
			}
			
			
			System.out.println("\nAlumnos Suspensos\n");
			for(Alumno alumno : listaAlumnos) {
				if(alumno.getNotaFinal() < 5) {
					System.out.println(
		                    alumno.getNombre() + " " +
		                    alumno.getApellido1() + " - " +
		                        "Nota final: " +
		                   alumno.getNotaFinal()
		            );
					
				}
			}
			
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}