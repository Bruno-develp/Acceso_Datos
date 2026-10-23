package com.carballeira.jdbc;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class App35 {
	public static void main(String[] args){
	
		try(FileInputStream fis = new FileInputStream("alumnos.dat");
				ObjectInputStream ois = new ObjectInputStream(fis)){
			
			Alumno2 alumno = (Alumno2) ois.readObject();
			
			System.out.println("Alumno recuperado correctamente");
			System.out.println("Nombre: " + alumno.getNombre());
			System.out.println("DNI: " + alumno.getDni());
			System.out.println("Primer Apellido: " + alumno.getApellido1());
			System.out.println("Segundo Apellido: " + alumno.getApellido2());
			System.out.println("Ciclo: " + alumno.getCiclo());
			System.out.println("Curso: " + alumno.getCurso());
			System.out.println("Eva 1: " + alumno.getEva1());
			System.out.println("Eva 2: " + alumno.getEva2());
			System.out.println("Eva 3: " + alumno.getEva3());
			System.out.println("Nota Final: " + alumno.getNotaFinal());
			
			
			System.out.println("Fichero abierto para leer objetos");
			
		}catch(IOException | ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
	}
  }
}
