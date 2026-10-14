package com.carballeira.jdbc;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class App27 {

    public static void main(String[] args) {
        File fichero = new File("datos.txt");
        System.out.println("Nombre: " + fichero.getName());
        System.out.println("¿Existe? " + fichero.exists());

        try {
            boolean creado = fichero.createNewFile();
            System.out.println("¿Se ha creado? " + creado);
        } catch (Exception e) {
            System.out.println("Error al crear el archivo");
        }

        System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());
        System.out.println("¿Es un archivo? " + fichero.isFile());
        System.out.println("¿Es una carpeta? " + fichero.isDirectory());
        System.out.println("Tamaño: " + fichero.length() + " bytes");

        try {
            FileWriter escritor = new FileWriter(fichero);
            escritor.write("Primera línea del archivo.\n");
            escritor.write("Segunda línea del archivo.\n");
            escritor.write("Tercera línea del archivo.");
            escritor.close();
            System.out.println("Texto escrito correctamente.");
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo.");
        }

        System.out.println("Tamaño actual: " + fichero.length() + " bytes");

        try {
            BufferedReader br = new BufferedReader(new FileReader(fichero));
            String linea;
            int numero = 1;
            
            while ((linea = br.readLine()) != null) {
                System.out.println(numero + ": " + linea);
                numero++;
            }
            
            br.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo con BufferedReader.");
        }
    }
}