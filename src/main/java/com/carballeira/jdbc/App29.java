package com.carballeira.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

public class App29 {

	public static void main(String[] args) {
		String nombreFichero = "texto_s29.txt";

		try {
			FileWriter escritor = new FileWriter(nombreFichero, StandardCharsets.UTF_8);
			escritor.write("Primera línea del archivo.\n");
			escritor.write("Texto con tildes: á é í ó ú.\n");
			escritor.write("Otros caracteres: ñ ü €.");
			escritor.close();
			System.out.println("Texto escrito correctamente.\n");
		} catch (Exception e) {
			System.out.println("Error al escribir el archivo: " + e.getMessage());
		}

		try {
			FileReader lector = new FileReader(nombreFichero, StandardCharsets.UTF_8);

			int caracter;
			int contadorCaracteres = 0;
			int contadorVocales = 0;
			String listaVocales = "aeiouáéíóúüAEIOUÁÉÍÓÚÜ";

			while ((caracter = lector.read()) != -1) {
				char c = (char) caracter;
				System.out.print(c);
				contadorCaracteres++;

				if (listaVocales.indexOf(c) >= 0) {
					contadorVocales++;
				}
			}
			lector.close();

			System.out.println();
			System.out.println("Número de caracteres: " + contadorCaracteres);
			System.out.println("Número de vocales: " + contadorVocales);

			File fichero = new File(nombreFichero);
			System.out.println("Tamaño del fichero: " + fichero.length() + " bytes");

		} catch (Exception e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		}
	}
}