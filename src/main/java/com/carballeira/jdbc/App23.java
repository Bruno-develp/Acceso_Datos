package com.carballeira.jdbc;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class App23 {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:java26.db";

        try (Connection conexion = DriverManager.getConnection(url)) {
            System.out.println("Conexión establecida con éxito.");

            Scanner sc = new Scanner(System.in);

            System.out.println("\nBÚSQUEDA COMBINADA (NOMBRE O APELLIDO)");
            System.out.print("Introduce un texto para buscar en nombre o primer apellido: ");
            String dato = sc.nextLine();

            String sqlReto = "SELECT * FROM alumno " +
                             "WHERE nombre LIKE ? OR apellido1 LIKE ? " +
                             "ORDER BY apellido1, nombre";

            PreparedStatement psReto = conexion.prepareStatement(sqlReto);
            
            psReto.setString(1, "%" + dato + "%");
            psReto.setString(2, "%" + dato + "%");


            ResultSet rsReto = psReto.executeQuery();

            System.out.println("\nRESULTADOS DE LA BÚSQUEDA COMBINADA");
            while (rsReto.next()) {
                System.out.println(
                    rsReto.getString("dni") + " - " +
                    rsReto.getString("nombre") + " " +
                    rsReto.getString("apellido1") + " " +
                    rsReto.getString("apellido2")
                );
            }

            sc.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
