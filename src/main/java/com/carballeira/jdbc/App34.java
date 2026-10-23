package com.carballeira.jdbc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class App34 {
	public static void main(String[] args) {
		
		Alumno2 alumno = new Alumno2(
					"12345678A",
					"Ana",
					"Garcia",
					"Lopez",
					"DAM",
					1,
					7,
					8,
					9,
					8
				);
		
		
		try(FileOutputStream fos = new FileOutputStream("alumnos.dat");
				ObjectOutputStream oos = new ObjectOutputStream(fos)){
			
			oos.writeObject(alumno);
			
			System.out.println("Fichero Preparado para guardar objetos");
			
		}catch(IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
			
		
	}
}
