package com.carballeira.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App21 {
	public static void main(String[] args) {
		
//		Alumno2 alumno = new Alumno(
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
//		);
//		
//		System.out.println(alumno.getNombre());
//		System.out.println(alumno.getNotaFinal());
		
		String url = "jdbc:sqlite:java26.db";
		
		try(Connection conexion = DriverManager.getConnection(url)){
			
			String sql = "SELECT * FROM alumno";
			
			PreparedStatement ps = conexion.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			System.out.println("ALUMNO CREADO DESDE LA BASE DE DATOS");
			while(rs.next()) {
				Alumno2 alumnoBD = new Alumno2(
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

				
				System.out.println(
	                    alumnoBD.getDni() + " - " +
	                            alumnoBD.getNombre() + " " +
	                            alumnoBD.getApellido1() + " - " +
	                            "Nota final: " +
	                            alumnoBD.getNotaFinal()
	                  );		
			}
		}catch(SQLException e) {
			System.out.println("Error: " + e.getMessage()) ;
			
		}
	}
}