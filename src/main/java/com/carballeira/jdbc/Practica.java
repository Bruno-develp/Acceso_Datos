package com.carballeira.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Practica {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:java26.db";
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce el DNI del alumno a eliminar: ");
        String dni = sc.nextLine();

        String sql = "DELETE FROM alumno WHERE dni = ?";

        try (Connection conexion = DriverManager.getConnection(url);
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, dni);

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("El alumno con DNI " + dni + " ha sido eliminado correctamente.");
            } else {
                System.out.println("No se ha encontrado ningún alumno con el DNI " + dni + ".");
            }

        } catch (SQLException e) {
            System.out.println("Error en la base de datos: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}