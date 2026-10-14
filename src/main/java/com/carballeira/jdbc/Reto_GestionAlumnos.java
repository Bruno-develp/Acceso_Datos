package com.carballeira.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Reto_GestionAlumnos {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:java26.db";

        try (Connection conexion = DriverManager.getConnection(url);
             Scanner sc = new Scanner(System.in)) {
            
            System.out.println("Conexión establecida con éxito.");
            int opcion = 0;

            do {
                System.out.println("\n--- GESTIÓN DE ALUMNOS ---");
                System.out.println("1. Buscar alumnos por apellido");
                System.out.println("2. Mostrar únicamente los alumnos aprobados");
                System.out.println("3. Mostrar únicamente los alumnos suspensos");
                System.out.println("4. Mostrar alumnos de un curso concreto");
                System.out.println("5. Salir");
                System.out.print("Elige una opción: ");
                
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Introduce un apellido o parte de él: ");
                        String apellidoBusqueda = sc.nextLine();
                        
                        String sqlApellido = "SELECT * FROM alumno WHERE apellido1 LIKE ? OR apellido2 LIKE ? ORDER BY apellido1, nombre";
                        try (PreparedStatement ps = conexion.prepareStatement(sqlApellido)) {
                            ps.setString(1, "%" + apellidoBusqueda + "%");
                            ps.setString(2, "%" + apellidoBusqueda + "%");
                            
                            try (ResultSet rs = ps.executeQuery()) {
                                System.out.println("\n--- RESULTADOS BÚSQUEDA POR APELLIDO ---");
                                boolean encontrado = false;
                                while (rs.next()) {
                                    encontrado = true;
                                    System.out.println(
                                        rs.getString("dni") + " - " +
                                        rs.getString("nombre") + " " +
                                        rs.getString("apellido1") + " " +
                                        rs.getString("apellido2") + " Nota: " +
                                        rs.getDouble("notaFinal")
                                    );
                                }
                                if (!encontrado) {
                                    System.out.println("No se encontraron alumnos con ese apellido.");
                                }
                            }
                        }
                        break;

                    case 2:
                        String sqlAprobados = "SELECT * FROM alumno WHERE notaFinal >= 5 ORDER BY notaFinal DESC";
                        try (PreparedStatement ps = conexion.prepareStatement(sqlAprobados);
                             ResultSet rs = ps.executeQuery()) {
                            System.out.println("\n--- ALUMNOS APROBADOS (>= 5) ---");
                            while (rs.next()) {
                                System.out.println(
                                    rs.getString("nombre") + " " +
                                    rs.getString("apellido1") + " - Nota: " +
                                    rs.getDouble("notaFinal")
                                );
                            }
                        }
                        break;

                    case 3:
                        String sqlSuspensos = "SELECT * FROM alumno WHERE notaFinal < 5 ORDER BY notaFinal ASC";
                        try (PreparedStatement ps = conexion.prepareStatement(sqlSuspensos);
                             ResultSet rs = ps.executeQuery()) {
                            System.out.println("\n--- ALUMNOS SUSPENSOS (< 5) ---");
                            while (rs.next()) {
                                System.out.println(
                                    rs.getString("nombre") + " " +
                                    rs.getString("apellido1") + " - Nota: " +
                                    rs.getDouble("notaFinal")
                                );
                            }
                        }
                        break;

                    case 4:
                        System.out.print("Introduce el nombre del curso: ");
                        String cursoBusqueda = sc.nextLine();
                        
                        String sqlCurso = "SELECT * FROM alumno WHERE curso LIKE ?";
                        try (PreparedStatement ps = conexion.prepareStatement(sqlCurso)) {
                            ps.setString(1, "%" + cursoBusqueda + "%");
                            try (ResultSet rs = ps.executeQuery()) {
                                System.out.println("\n--- ALUMNOS DEL CURSO: " + cursoBusqueda + " ---");
                                boolean encontrado = false;
                                while (rs.next()) {
                                    encontrado = true;
                                    System.out.println(
                                        rs.getString("dni") + " - " +
                                        rs.getString("nombre") + " " +
                                        rs.getString("apellido1")
                                    );
                                }
                                if (!encontrado) {
                                    System.out.println("No se encontraron alumnos para ese curso.");
                                }
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Saliendo de la aplicación...");
                        break;

                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }

            } while (opcion != 5);

        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        }
    }
}