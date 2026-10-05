package com.carballeira.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App2 {
	public static void main(String[] args) {
		String url = "jdbc:sqlite:java26.db";

		try (Scanner teclado = new Scanner(System.in);
			 Connection conexion = DriverManager.getConnection(url)) {
			
			System.out.println("Conexión realizada correctamente");
			
			String sqlTabla = """
					CREATE TABLE IF NOT EXISTS alumno (
						dni TEXT PRIMARY KEY,
						nombre TEXT NOT NULL,
						apellido1 TEXT NOT NULL,
						apellido2 TEXT NOT NULL,
						ciclo TEXT NOT NULL,
						curso INTEGER NOT NULL,
						eva1 INTEGER, eva2 INTEGER, eva3 INTEGER,
						notaFinal INTEGER
					)
					""";

			try (Statement sentencia = conexion.createStatement()) {
				sentencia.execute(sqlTabla);
			}

			System.out.print("\nIntroduce el DNI del alumno que quieres modificar: ");
			String dniModificar = teclado.nextLine();
			
			System.out.println("""
					¿Qué campo quieres modificar?
					1. Nombre
					2. Primer apellido
					3. Segundo apellido
					4. Ciclo
					5. Curso
					6. DNI
					7. Notas
					""");
			
			int opcion = Integer.parseInt(teclado.nextLine());

			switch (opcion) {
				case 1 -> {
					System.out.print("Introduce el nuevo nombre: ");
					String nuevoNombre = teclado.nextLine();
					String sql = "UPDATE alumno SET nombre = ? WHERE dni = ?";
					try (PreparedStatement ps = conexion.prepareStatement(sql)) {
						ps.setString(1, nuevoNombre);
						ps.setString(2, dniModificar);
						int filas = ps.executeUpdate();
						System.out.println(filas > 0 ? "Nombre actualizado correctamente" : "No existe ningún alumno con ese DNI");
					}
				}
				case 2 -> {
					System.out.print("Introduce el nuevo primer apellido: ");
					String valor = teclado.nextLine();
					String sql = "UPDATE alumno SET apellido1 = ? WHERE dni = ?";
					try (PreparedStatement ps = conexion.prepareStatement(sql)) {
						ps.setString(1, valor);
						ps.setString(2, dniModificar);
						int filas = ps.executeUpdate();
						System.out.println(filas > 0 ? "Primer apellido actualizado correctamente" : "No existe ningún alumno con ese DNI");
					}
				}
				case 3 -> {
					System.out.print("Introduce el nuevo segundo apellido: ");
					String valor = teclado.nextLine();
					String sql = "UPDATE alumno SET apellido2 = ? WHERE dni = ?";
					try (PreparedStatement ps = conexion.prepareStatement(sql)) {
						ps.setString(1, valor);
						ps.setString(2, dniModificar);
						int filas = ps.executeUpdate();
						System.out.println(filas > 0 ? "Segundo apellido actualizado correctamente" : "No existe ningún alumno con ese DNI");
					}
				}
				case 4 -> {
					System.out.print("Introduce el nuevo ciclo: ");
					String valor = teclado.nextLine();
					String sql = "UPDATE alumno SET ciclo = ? WHERE dni = ?";
					try (PreparedStatement ps = conexion.prepareStatement(sql)) {
						ps.setString(1, valor);
						ps.setString(2, dniModificar);
						int filas = ps.executeUpdate();
						System.out.println(filas > 0 ? "Ciclo actualizado correctamente" : "No existe ningún alumno con ese DNI");
					}
				}
				case 5 -> {
					System.out.print("Introduce el nuevo curso: ");
					int valor = Integer.parseInt(teclado.nextLine());
					String sql = "UPDATE alumno SET curso = ? WHERE dni = ?";
					try (PreparedStatement ps = conexion.prepareStatement(sql)) {
						ps.setInt(1, valor);
						ps.setString(2, dniModificar);
						int filas = ps.executeUpdate();
						System.out.println(filas > 0 ? "Curso actualizado correctamente" : "No existe ningún alumno con ese DNI");
					}
				}
				case 6 -> {
					System.out.print("Introduce el nuevo DNI: ");
					String nuevoDni = teclado.nextLine();
					String sql = "UPDATE alumno SET dni = ? WHERE dni = ?";
					try (PreparedStatement ps = conexion.prepareStatement(sql)) {
						ps.setString(1, nuevoDni);
						ps.setString(2, dniModificar);
						int filas = ps.executeUpdate();
						System.out.println(filas > 0 ? "DNI actualizado correctamente" : "No existe ningún alumno con ese DNI");
					}
				}
				case 7 -> {
					System.out.print("Nota de la 1ª evaluación: ");
					int n1 = Integer.parseInt(teclado.nextLine());
					System.out.print("Nota de la 2ª evaluación: ");
					int n2 = Integer.parseInt(teclado.nextLine());
					System.out.print("Nota de la 3ª evaluación: ");
					int n3 = Integer.parseInt(teclado.nextLine());
					int nf = (n1 + n2 + n3) / 3;

					String sql = "UPDATE alumno SET eva1=?, eva2=?, eva3=?, notaFinal=? WHERE dni=?";
					try (PreparedStatement ps = conexion.prepareStatement(sql)) {
						ps.setInt(1, n1);
						ps.setInt(2, n2);
						ps.setInt(3, n3);
						ps.setInt(4, nf);
						ps.setString(5, dniModificar);
						int filas = ps.executeUpdate();
						System.out.println(filas > 0 ? "Notas actualizadas correctamente" : "No existe ningún alumno con ese DNI");
					}
				}
				default -> System.out.println("Opción no válida");
			}

			System.out.print("\nIntroduce el DNI del alumno que quieres eliminar: ");
			String dniEliminar = teclado.nextLine();
			
			String sqlDelete = "DELETE FROM alumno WHERE dni = ?";
			try (PreparedStatement psDelete = conexion.prepareStatement(sqlDelete)) {
				psDelete.setString(1, dniEliminar);
				int filasEliminadas = psDelete.executeUpdate();
				System.out.println(filasEliminadas > 0 ? "Alumno eliminado correctamente" : "No existe ningún alumno con ese DNI");
			}

			String sqlConsulta = "SELECT * FROM alumno ORDER BY apellido1, apellido2, nombre";
			try (Statement sentencia = conexion.createStatement();
				 ResultSet resultado = sentencia.executeQuery(sqlConsulta)) {
				
				System.out.println("\n--- LISTADO DE ALUMNOS ---");
				while (resultado.next()) {
					String e1 = resultado.getString("eva1");
					String e2 = resultado.getString("eva2");
					String e3 = resultado.getString("eva3");
					String nf = resultado.getString("notaFinal");

					System.out.println(resultado.getString("dni") + " - " 
							+ resultado.getString("apellido1") + " " 
							+ resultado.getString("apellido2") + ", " 
							+ resultado.getString("nombre") + " - "
							+ resultado.getString("ciclo") + " - Curso " 
							+ resultado.getInt("curso") + " - Notas: "
							+ (e1 == null ? "Sin nota" : e1) + ", " 
							+ (e2 == null ? "Sin nota" : e2) + ", "
							+ (e3 == null ? "Sin nota" : e3) + " - Final: " 
							+ (nf == null ? "Sin calcular" : nf));
				}
			}

		} catch (NumberFormatException e) {
			System.out.println("Error: Has introducido un formato numérico no válido.");
		} catch (SQLException e) {
			System.out.println("Error al trabajar con la base de datos.");
			e.printStackTrace();
		}
	}
}