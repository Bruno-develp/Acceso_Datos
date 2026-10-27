package com.carballeira.jdbc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class App40 {
	
	public static void main(String[] args) {
		ArrayList<Alumno> listaAlumnos = new ArrayList<>();
		String ficheroDAT = "alumnos.dat";
		String ficheroTXT = "alumnos.txt";
		
		Scanner sc = new Scanner(System.in);
		int opcion;
		
		do {
			System.out.println("----- Gestión alumnos -----");
			System.out.println("1. Añadir alumno");
			System.out.println("2. Mostrar alumnos");
			System.out.println("3. Guardar alumnos en fichero");
			System.out.println("4. Cargar alumnos desde fichero");
			System.out.println("5. Exportar alumnos a TXT");
			System.out.println("0. Salir");
			System.out.println("Elige una opción: ");
			
			switch(opcion) {
				case 1 -> {
					System.out.println("Introduce el DNI: ");
						String dni = sc.nextLine();
						
					boolean existe = false;
					for(Alumno a : listaAlumnos) {
						if(a.getDni().equalsIgnoreCase(dni)) {
							existe = true;
							break;
						}
					}
					
					if(existe) {
						System.out.println("El alumno con DNI " + dni + "ya existe");
						break;
					}
					
                    System.out.print("Introduce el nombre: ");
                    	String nombre = sc.nextLine();
                    System.out.print("Introduce el primer apellido: ");
                    	String apellido1 = sc.nextLine();
                    System.out.print("Introduce el segundo apellido: ");
                    	String apellido2 = sc.nextLine();
                    System.out.print("Introduce el nota: ");
                    	int notaFinal = Integer.parseInt(sc.nextLine());
                    	
                    Alumno alumnoNuevo = new Alumno(dni, nombre, apellido1, apellido2, notaFinal);
                    listaAlumnos.add(alumnoNuevo);
                    System.out.println("Alumno insertado correctamente");
				}
				
				case 2 -> {
					System.out.println("Listado alumnos");
					if(listaAlumnos.isEmpty()) {
						System.out.println("La lista de alumnos esta vacia");
					}else {
						for (Alumno a : listaAlumnos) {
							System.out.println(a);
						}
					}
				}
				
				case 3 -> {
					try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroDAT))){
						oos.writeObject(listaAlumnos);
						System.out.println("Alumno guardado correctamente en el .dat");
					}catch(IOException e) {
						System.out.println("Error: " + e.getMessage());
					}
				}
				
				case 4 -> {
					File fichero = new File(ficheroDAT);
				}
				
				
				
				
			}
			
		}while(opcion != 0);
		
		sc.close();
		
		
		
		
	}
}
