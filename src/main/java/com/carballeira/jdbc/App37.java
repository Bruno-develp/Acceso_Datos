package com.carballeira.jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class App37 {
	public static void main(String[] args) {

		ArrayList<Alumno2> alumnos = new ArrayList<Alumno2>();

		try (FileInputStream fis = new FileInputStream("lista_alumnos.dat");
				ObjectInputStream ois = new ObjectInputStream(fis)) {

			ArrayList<Alumno2> listaRecuperada = (ArrayList<Alumno2>) ois.readObject();

			System.out.println("Alumnos recuperados: " + listaRecuperada.size());

			for (Alumno2 alumno : listaRecuperada) {
				System.out.println("DNI: " + alumno.getDni() + " |Nombre: " + alumno.getNombre() + " |Nota: "
						+ alumno.getNotaFinal());
			}

			Scanner sc = new Scanner(System.in);

			System.out.println("\nIntroduce el DNI del alumno: ");
			String dniBuscado = sc.nextLine();

			boolean encontrado = false;
			boolean modificado = false;

			for (Alumno2 alumno : listaRecuperada) {
				if (alumno.getDni().equalsIgnoreCase(dniBuscado)) {
					encontrado = true;
					System.out.println("\nAlumno encontrado: ");
					System.out.println("DNI: " + alumno.getDni() + " |Nombre: " + alumno.getNombre() + " |Nota: "
							+ alumno.getNotaFinal());

					System.out.println("\n¿Qué deseas modificar?");
					System.out.println("1. Nota final");
					System.out.println("2. Nombre");
					System.out.print("Elige una opción: ");
					int opcion = Integer.parseInt(sc.nextLine());

					switch (opcion) {
						case 1 -> {
							System.out.print("Introduce la nueva nota: ");
							int nuevaNota = Integer.parseInt(sc.nextLine());
							alumno.setNotaFinal(nuevaNota);
							System.out.println("Nota modificada correctamente.");
							System.out.println("Nueva nota: " + alumno.getNotaFinal());
							modificado = true;
						}
						case 2 -> {
							System.out.print("Introduce el nuevo nombre: ");
							String nuevoNombre = sc.nextLine();
							alumno.setNombre(nuevoNombre); 
							System.out.println("Nombre modificado correctamente.");
							System.out.println("Nuevo nombre: " + alumno.getNombre());
							modificado = true;
						}
						default -> System.out.println("Opción no válida.");
					}
					break;
				}
			}

			if (!encontrado) {
				System.out.println("No existe ningún alumno con ese DNI");
			}

			if (modificado) {
				try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream("lista_alumnos.dat"))) {

					salida.writeObject(listaRecuperada);

					System.out.println("\nLista de alumnos guardada correctamente.");

				} catch (IOException e) {
					System.out.println("Error: " + e.getMessage());
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}
}