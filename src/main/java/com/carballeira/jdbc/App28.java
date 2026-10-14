package com.carballeira.jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class App28 {

    public static void main(String[] args) {
        
        try {
            FileInputStream entrada = new FileInputStream("datos.txt");
            	int dato = entrada.read(); 
            System.out.println("Primer byte leído: " + dato);
            System.out.println("Carácter correspondiente: " + (char) dato);
            entrada.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo.");
        }

        try {
            FileOutputStream salida = new FileOutputStream("bytes.txt");
            salida.write(65);
            salida.write(66);
            salida.write(67);
            salida.close();
            System.out.println("Archivo bytes.txt creado.");
        } catch (Exception e) {
            System.out.println("Error al escribir el archivo.");
        }

        try (
            FileInputStream entrada = new FileInputStream("datos.txt");
            FileOutputStream salida = new FileOutputStream("copia_datos2.txt")
        ) {
            int dato;
            while ((dato = entrada.read()) != -1) {
                salida.write(dato); 
            }
            System.out.println("Archivo copiado con try-with-resources.");
        } catch (Exception e) {
            System.out.println("Error al copiar el archivo.");
        }
    }
}