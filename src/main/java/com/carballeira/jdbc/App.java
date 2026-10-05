package com.carballeira.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:java26.db";
        
        Scanner sc = new Scanner(System.in);
        
//        System.out.println("Introduce el DNI: ");
//        	String dni = sc.nextLine();
//        	
//        System.out.println("Introduce el nombre: ");
//        	String nombre = sc.nextLine();
//        	
//        System.out.println("Introduce el apellido: ");
//        	String apellido = sc.nextLine();
//        	
//        System.out.println("Introduce el apellido2: ");
//        	String apellido2 = sc.nextLine();
//        	
//        System.out.println("Introduce el ciclo: ");
//        	String ciclo = sc.nextLine();
//        	
//        System.out.println("Introduce el curso: ");
//        	int curso = sc.nextInt();
        
        System.out.println("Carpeta de trabajo: " + System.getProperty("user.dir"));
        
        
        
        try (Connection conexion = DriverManager.getConnection(url)) {
            System.out.println("Conexion realizada correctamente");
            
            String sql = """
                    CREATE TABLE IF NOT EXISTS alumno (
                    dni TEXT PRIMARY KEY,
                    nombre TEXT NOT NULL,
                    apellido1 TEXT NOT NULL,
                    apellido2 TEXT NOT NULL,
                    ciclo TEXT NOT NULL,
                    curso INTEGER NOT NULL,
                    eva1 INTEGER,
                    eva2 INTEGER,
                    eva3 INTEGER,
                    notaFinal INTEGER
                    )
                    """;
            
            Statement sentencia = conexion.createStatement();
            sentencia.execute(sql);
            System.out.println("Tabla alumno creada correctamente");
            
//            String insertar = """
//                    INSERT INTO alumno
//                    (dni, nombre, apellido1, apellido2, ciclo, curso)
//                    VALUES
//                    ('12345678B', 'Ana', 'García', 'López', 'DAM', 2)
//                    """;
//            
//            sentencia.executeUpdate(insertar);
//            
//            System.out.println("Alumno insertado correctamente");
            
            String insertar = """
                    INSERT INTO alumno
                    (dni, nombre, apellido1, apellido2, ciclo, curso)
                    VALUES
                    (?, ?, ?, ?, ?, ?)
                    """;
            
            PreparedStatement ps = conexion.prepareStatement(insertar);
            
//            System.out.print("¿Cuántos alumnos quieres introducir? ");
//           		int numAlumnos = Integer.parseInt(sc.nextLine());

            
//            for (int i = 1; i <= numAlumnos; i++) {
//                System.out.println("\nAlumno " + i);
//                
//                System.out.println("Introduce el DNI: ");
//                	String dni = sc.nextLine();
//                
//                System.out.println("Introduce el nombre: ");
//                	String nombre = sc.nextLine();
//                
//                System.out.println("Introduce el apellido: ");
//                	String apellido = sc.nextLine();
//                
//                System.out.println("Introduce el apellido2: ");
//                	String apellido2 = sc.nextLine();
//                
//                System.out.println("Introduce el ciclo: ");
//                	String ciclo = sc.nextLine();
//                
//                System.out.println("Introduce el curso: ");
//                	int curso = Integer.parseInt(sc.nextLine());
//                
//                ps.setString(1, dni);
//                ps.setString(2, nombre);
//                ps.setString(3, apellido);
//                ps.setString(4, apellido2);
//                ps.setString(5, ciclo);
//                ps.setInt(6, curso);
//                
//                ps.executeUpdate();
//                
//                System.out.println("Alumno insertado correctamente");
//            }
           		
           		String respuesta;
           		
           		do {
                    
                    System.out.println("Introduce el DNI: ");
                    	String dni = sc.nextLine();
                    
                    System.out.println("Introduce el nombre: ");
                    	String nombre = sc.nextLine();
                    
                    System.out.println("Introduce el apellido: ");
                    	String apellido = sc.nextLine();
                    
                    System.out.println("Introduce el apellido2: ");
                    	String apellido2 = sc.nextLine();
                    
                    System.out.println("Introduce el ciclo: ");
                    	String ciclo = sc.nextLine();
                    
                    System.out.println("Introduce el curso: ");
                    	int curso = Integer.parseInt(sc.nextLine());
                    
                    ps.setString(1, dni);
                    ps.setString(2, nombre);
                    ps.setString(3, apellido);
                    ps.setString(4, apellido2);
                    ps.setString(5, ciclo);
                    ps.setInt(6, curso);
                    
                    ps.executeUpdate();
           			
           			System.out.println("¿Quieres introducir otro usuario? (s/n): ");
           				respuesta = sc.nextLine();
           		}while(respuesta.equalsIgnoreCase("s"));

//          ps.setString(1, dni);
//          ps.setString(2, nombre);
//          ps.setString(3, apellido);
//          ps.setString(4, apellido2);
//          ps.setString(5, ciclo);
//          ps.setInt(6, curso);
//          
//          ps.executeUpdate();

//          sentencia.executeUpdate(insertar);

//            ResultSet resultado = sentencia.executeQuery("SELECT * FROM ALUMNO");
            
            ResultSet resultado = sentencia.executeQuery("SELECT * FROM alumno ORDER BY apellido1, apellido2, nombre");
            
            while (resultado.next()) {
                System.out.println(
                        resultado.getString("dni") + " - " +
                        resultado.getString("nombre") + " - " +
                        resultado.getString("apellido1") + " - " +
                        resultado.getString("apellido2") + " - " +
                        resultado.getString("ciclo") + " - " +
                        resultado.getString("curso")
                );
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Debes introducir un número entero válido para el curso.");
        }
        
//      System.out.println("Carpeta de trabajo: " + System.getProperty("user.dir"));
    }
}