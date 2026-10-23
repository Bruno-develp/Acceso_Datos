package com.carballeira.jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class App31 {
    public static void main(String[] args) {
        try (
            FileInputStream entrada = new FileInputStream("datos.txt");
            FileOutputStream salida = new FileOutputStream("copia_s31.txt")
        ) {
            byte[] buffer = new byte[10];
            int cantidad;

            while ((cantidad = entrada.read(buffer)) != -1) {
                salida.write(buffer, 0, cantidad); 
                System.out.println("Bytes leidos: " + cantidad);
                
            }
            
            System.out.println("Archivo copiado correctamente.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try (
            BufferedWriter bw = new BufferedWriter(new FileWriter("flush_s31.txt"))
        ) {
            bw.write("Primera linea escrita con buffer.");
            bw.newLine();
            bw.write("Segunda linea escrita con buffer.");
            bw.newLine();
            
            System.out.println("Escritura realizada.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}