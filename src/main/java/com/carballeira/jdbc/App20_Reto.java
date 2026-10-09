package com.carballeira.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App20_Reto {

    public static void main(String[] args) {
        
        mostrarTitulo();

        String url = "jdbc:sqlite:java26.db";

        try (Connection conexion = DriverManager.getConnection(url);
             Scanner sc = new Scanner(System.in)) {

            System.out.println("\nALUMNOS APROBADOS");
            mostrarAprobados(conexion);

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void mostrarTitulo() {
        System.out.println("=== GESTIÓN DE ALUMNOS ===");
    }


    public static void mostrarAprobados(Connection conexion) throws SQLException {
        String sqlAprobados = "SELECT * FROM alumno WHERE notaFinal >= 5";

        try (PreparedStatement psAprobados = conexion.prepareStatement(sqlAprobados);
             ResultSet rsAprobados = psAprobados.executeQuery()) {

            while (rsAprobados.next()) {
                System.out.println(
                    rsAprobados.getString("dni") + " - " +
                    rsAprobados.getString("nombre") + " " +
                    rsAprobados.getString("apellido1") + " - " +
                    "Nota final: " +
                    rsAprobados.getInt("notaFinal")
                );
            }
        }
    }
}