package com.carballeira.jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class App36 {
	public static void main(String[] args) {
		
//		ArrayList<Alumno2> listaAlumnos = new ArrayList<>();
//		
//		listaAlumnos.add(new Alumno2(
//				"12345678A", "Ana", "Garcia", "Lopez",
//				"DAM", 1, 7, 8, 9, 8 
//				));
//		
//		listaAlumnos.add(new Alumno2(
//				"23456789V", "Lola", "Drones", "Mendez",
//				"DAM", 1, 6, 7, 8, 7
//				));
//		
//		listaAlumnos.add(new Alumno2(
//				"34567891C", "Rosa", "Me", "Lano",
//				"DAM", 1, 9, 8, 10, 9
//				));
//		
//		System.out.println("Número de alumnos: " + listaAlumnos.size());
//		
//		for(Alumno2 alumno : listaAlumnos) {
//			System.out.println("Nombre: " + alumno.getNombre());
//		}
//		
//		try(FileOutputStream fos = new FileOutputStream("lista_alumnos.dat");
//				ObjectOutputStream oos = new ObjectOutputStream(fos)){
//			
//			oos.writeObject(listaAlumnos);
//			
//			System.out.println("Lista de alumnos guardada correctamente");
//			
//			
//		}catch(IOException e) {
//			System.out.println("Error: " + e.getMessage());
//		}
		
		try(FileInputStream fis = new FileInputStream("lista_alumnos.dat");
				ObjectInputStream ois = new ObjectInputStream(fis)){
			
			ArrayList<Alumno2> listaRecuperada = (ArrayList<Alumno2>) ois.readObject();
			
			System.out.println("Alumnos recuperados: " + listaRecuperada.size());
			
			for(Alumno2 alumno : listaRecuperada) {
				System.out.println("Alumno recuperado: " + alumno.getNombre());
			}
			
			
			System.out.println("Fichero abierto para recuperar la lista");
			
			
		}catch (IOException | ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}
