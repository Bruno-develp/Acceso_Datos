package com.carballeira.jdbc;

import java.io.Serializable;


public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private int notaFinal;

    public Alumno(String dni, String nombre, String apellido1, String apellido2, int notaFinal) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.notaFinal = notaFinal;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public int getNotaFinal() { return notaFinal; }

    @Override
    public String toString() {
        return dni + " - " + nombre + " " + apellido1 + " " + apellido2 + " (Nota: " + notaFinal + ")";
    }
}

