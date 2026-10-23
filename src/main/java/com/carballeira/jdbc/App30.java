package com.carballeira.jdbc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class App30 {
    public static void main(String[] args) {
    	
    	try (FileReader fr = new FileReader("datos.txt")) {
            int caracter;
            
            while ((caracter = fr.read()) != -1) {
                System.out.print((char) caracter);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    	try (BufferedReader br = new BufferedReader(new FileReader("datos.txt"))) {
            

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea); 
            }
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    	
    	try (BufferedWriter bw = new BufferedWriter(new FileWriter("resultado.txt"))) {
            
            bw.write("Primera línea escrita con BufferedWriter");
            bw.newLine();
            bw.write("Segunda línea escrita con BufferedWriter");
            bw.newLine();
            
            bw.write("Tercera línea escrita con BufferedWriter");
            
            System.out.println("Archivo creado correctamente.");
            
        } catch (IOException e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    	
    	try (FileWriter fw = new FileWriter("resultado_sin_buffer.txt", true)) {
    	    
    	    fw.write("\nCuarta línea añadida al archivo.");
    	    fw.write("\nQuinta línea añadida al archivo.");
    	    
    	    System.out.println("Cuarta línea añadida al archivo.");
    	    System.out.println("Quinta línea añadida al archivo.");
    	    
    	} catch (IOException e) {
    	    System.out.println("Error al escribir: " + e.getMessage());
    	}
    }
}