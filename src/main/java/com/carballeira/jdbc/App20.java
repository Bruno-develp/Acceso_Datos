package com.carballeira.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App20 {

    public static void main(String[] args) {
    	
        mostrarTitulo();

        String url = "jdbc:sqlite:java26.db";

        try (Connection conexion = DriverManager.getConnection(url);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("\nLISTADO DE ALUMNOS");
            	listarAlumnos(conexion);

            System.out.println("\nALUMNOS CON NOTA INFERIOR AL LÍMITE");
            	mostrarSuspensos(conexion, sc);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void mostrarTitulo() {
        System.out.println("=== GESTIÓN DE ALUMNOS ===");
    }

    public static void listarAlumnos(Connection conexion) throws SQLException {
        String sql = "SELECT * FROM alumno";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(
                rs.getString("dni") + " - " +
                rs.getString("nombre") + " " +
                rs.getString("apellido1") + " - " +
                "Nota final: " +
                rs.getInt("notaFinal")
            );
        }
    }

    public static void mostrarSuspensos(Connection conexion, Scanner sc) throws SQLException {
        System.out.print("Introduce la nota limite: ");
        	int notaLimite = Integer.parseInt(sc.nextLine());

        String sqlSuspensos = "SELECT * FROM alumno WHERE notaFinal < ?";
        
        PreparedStatement psSuspensos = conexion.prepareStatement(sqlSuspensos);
        psSuspensos.setInt(1, notaLimite);

        ResultSet rsSuspensos = psSuspensos.executeQuery();

        while (rsSuspensos.next()) {
            System.out.println(
                rsSuspensos.getString("dni") + " - " +
                rsSuspensos.getString("nombre") + " " +
                rsSuspensos.getString("apellido1") + " - " +
                "Nota final: " +
                rsSuspensos.getInt("notaFinal")
            );
        }
    }
}