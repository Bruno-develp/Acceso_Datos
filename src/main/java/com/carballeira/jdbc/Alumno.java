package com.carballeira.jdbc;

public class Alumno {
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String ciclo;
	private int curso;
	private int eva1;
	private int eva2;
	private int eva3;
	private int notaFinal;

	public Alumno(String dni, String nombre, String apellido1, String apellido2, int notaFinal) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.notaFinal = notaFinal;
	}



	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getApellido1() {
		return apellido1;
	}



	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}



	public String getApellido2() {
		return apellido2;
	}



	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}



	public String getCiclo() {
		return ciclo;
	}



	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}



	public int getCurso() {
		return curso;
	}



	public void setCurso(int curso) {
		this.curso = curso;
	}



	public int getEva1() {
		return eva1;
	}



	public void setEva1(int eva1) {
		this.eva1 = eva1;
	}



	public int getEva2() {
		return eva2;
	}



	public void setEva2(int eva2) {
		this.eva2 = eva2;
	}



	public int getEva3() {
		return eva3;
	}



	public void setEva3(int eva3) {
		this.eva3 = eva3;
	}



	public int getNotaFinal() {
		return notaFinal;
	}



	public void setNotaFinal(int notaFinal) {
		this.notaFinal = notaFinal;
	}



	@Override
	public String toString() {
		return "Alumno [dni=" + dni + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", notaFinal=" + notaFinal + "]";
	}

}
