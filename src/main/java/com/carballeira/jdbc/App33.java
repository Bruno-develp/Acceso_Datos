package com.carballeira.jdbc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App33 {
    public static void main(String[] args) {
        String RUTA_ENTRADA = "D:\\damdu204\\EclipseWS\\jdbc-sqlite\\Alumnos.txt";
        String RUTA_RESUMEN = "D:\\damdu204\\EclipseWS\\jdbc-sqlite\\resumen.txt";
        String RUTA_SUSPENSOS = "D:\\damdu204\\EclipseWS\\jdbc-sqlite\\suspensos.txt";

        int totalAlumnos = 0;
        int aprobados = 0;
        int suspensos = 0;
        double sumaNotas = 0.0;

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ENTRADA))) {
            
            String linea;
            System.out.println("----- LISTADO DE ALUMNOS -----");
            
            try (BufferedWriter bwSuspensos = new BufferedWriter(new FileWriter(RUTA_SUSPENSOS))) {
                
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                    
                    try {
                        String[] partes = linea.trim().split(";");
                        if (partes.length > 0) {
                            String strNota = partes[partes.length - 1];
                            double nota = Double.parseDouble(strNota);
                            
                            totalAlumnos++;
                            sumaNotas += nota;
                            
                            if (nota >= 5.0) {
                                aprobados++;
                            } else {
                                suspensos++;
                                bwSuspensos.write(linea);
                                bwSuspensos.newLine();
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            } 

            double notaMedia = 0.0;
            if (totalAlumnos > 0) {
                notaMedia = sumaNotas / totalAlumnos;
            }

            System.out.println("\n----- RESUMEN DE ALUMNOS -----");
            System.out.println("Total de alumnos: " + totalAlumnos);
            System.out.println("Alumnos aprobados: " + aprobados);
            System.out.println("Alumnos suspensos: " + suspensos);
            System.out.println("Nota media del grupo: " + notaMedia);

            try (BufferedWriter bwResumen = new BufferedWriter(new FileWriter(RUTA_RESUMEN))) {
                bwResumen.write("----- RESUMEN DE ALUMNOS -----\n");
                bwResumen.write("Total de alumnos: " + totalAlumnos + "\n");
                bwResumen.write("Alumnos aprobados: " + aprobados + "\n");
                bwResumen.write("Alumnos suspensos: " + suspensos + "\n");
                bwResumen.write("Nota media del grupo: " + notaMedia + "\n");
                System.out.println("\nResumen guardado correctamente en resumen.txt.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("El fichero Alumnos.txt no existe en la ruta especificada.");
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }
}