package com.carballeira.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App19 {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:java26.db";

        try (Connection conexion = DriverManager.getConnection(url);
             Scanner teclado = new Scanner(System.in)) {

//            System.out.println("Conexión realizada correctamente.");
//
//            System.out.print("Introduce el DNI del alumno que quieres buscar: ");
//            	String dni = teclado.nextLine();
//
//            String sqlBuscarDni = "SELECT * FROM alumno WHERE dni = ?";
//            PreparedStatement psDni = conexion.prepareStatement(sqlBuscarDni);
//            psDni.setString(1, dni);
//
//            ResultSet rsDni = psDni.executeQuery();
//
//            if (rsDni.next()) {
//                System.out.println("Alumno encontrado:");
//                System.out.println("DNI: " + rsDni.getString("dni"));
//                System.out.println("Nombre: " + rsDni.getString("nombre"));
//                System.out.println("Primer Apellido: " + rsDni.getString("apellido1"));
//                System.out.println("Segundo Apellido: " + rsDni.getString("apellido2"));
//            } else {
//                System.out.println("No existe ningún alumno con ese DNI[cite: 5].");
//            }


            System.out.println("\nALUMNOS SUSPENSOS");
            System.out.print("Introduce la nota límite: ");
            	int notaLimite = Integer.parseInt(teclado.nextLine());

            String sqlSuspensos = "SELECT * FROM alumno WHERE notaFinal >= ?";
            PreparedStatement psSuspensos = conexion.prepareStatement(sqlSuspensos);
            psSuspensos.setInt(1, notaLimite);

            ResultSet rsSuspensos = psSuspensos.executeQuery();

            while (rsSuspensos.next()) {
                System.out.println(
                    rsSuspensos.getString("dni") + "  " +
                    rsSuspensos.getString("apellido1") + " " +
                    rsSuspensos.getString("apellido2") + ", " +
                    rsSuspensos.getString("nombre") + "  Nota final: " +
                    rsSuspensos.getInt("notaF")
                );
            }


            	//String sqlAprobados = "SELECT * FROM alumno WHERE notaFinal < ?";
             

        } catch (SQLException e) {
            System.out.println("Error al trabajar con la base de datos: " + e.getMessage());
        }
    }
}