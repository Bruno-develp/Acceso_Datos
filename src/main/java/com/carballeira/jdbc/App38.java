package com.carballeira.jdbc;

import java.io.RandomAccessFile;
import java.io.IOException;

public class App38 {
    public static void main(String[] args) {
        try (RandomAccessFile fichero = new RandomAccessFile("numeros.dat", "rw")) {
            
            // Inicialización y escritura (comentar para probar la persistencia en ejecuciones posteriores)
            // fichero.setLength(0); // borra el contenido anterior[cite: 3]
            // fichero.writeInt(10);[cite: 1]
            // fichero.writeInt(20);[cite: 1]
            // fichero.writeInt(30);[cite: 1]

            System.out.println("Posición del puntero: " + fichero.getFilePointer());
            System.out.println("Tamaño del fichero: " + fichero.length() + " bytes");
            // Modificaciones opcionales (ejemplo: modificar segundo o tercer número)
            // fichero.seek(4);[cite: 5]
            // fichero.writeInt(200);[cite: 5]
            
            // fichero.seek(8);[cite: 8]
            // fichero.writeInt(300);[cite: 8]

            // Lectura y desglose en formato decimal y hexadecimal
            System.out.println("\nDecimal     Hexadecimal");
            fichero.seek(0);
            
            while (fichero.getFilePointer() < fichero.length()) {
                int valorHex = fichero.readInt();
                System.out.printf("%-11d %02X %02X %02X %02X%n",
                        valorHex,
                        (valorHex >>> 24) & 0xFF,
                        (valorHex >>> 16) & 0xFF,
                        (valorHex >>> 8) & 0xFF,
                        valorHex & 0xFF);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}