package com.carballeira.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App4 {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:java26.db";

        try (Connection conexion = DriverManager.getConnection(url)) {
            System.out.println("Conexión realizada correctamente");
            Scanner teclado = new Scanner(System.in);
            int opcion;

            do {
                System.out.println("\nGESTIÓN DE ALUMNOS");
                System.out.println("1. Insertar alumno");
                System.out.println("2. Listar alumnos");
                System.out.println("3. Modificar alumno");
                System.out.println("4. Eliminar alumno");
                System.out.println("5. Buscar alumno por DNI");
                System.out.println("6. Listar alumnos por ciclo");
                System.out.println("0. Salir");
                System.out.print("Elige una opción: ");

                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1 -> { // 1. CREATE / INSERT
                        System.out.print("Introduce el DNI: ");
                        	String dni = teclado.nextLine();
                        System.out.print("Introduce el nombre: ");
                        	String nombre = teclado.nextLine();
                        System.out.print("Introduce el primer apellido: ");
                        	String apellido1 = teclado.nextLine();
                        System.out.print("Introduce el segundo apellido: ");
                        	String apellido2 = teclado.nextLine();
                        System.out.print("Introduce el ciclo: ");
                        	String ciclo = teclado.nextLine();
                        System.out.print("Introduce el curso: ");
                        	int curso = Integer.parseInt(teclado.nextLine());

                        String sql = """
                                INSERT INTO alumno
                                (dni, nombre, apellido1, apellido2, ciclo, curso)
                                VALUES (?, ?, ?, ?, ?, ?)
                                """;

                        PreparedStatement psInsertar = conexion.prepareStatement(sql);
                        psInsertar.setString(1, dni);
                        psInsertar.setString(2, nombre);
                        psInsertar.setString(3, apellido1);
                        psInsertar.setString(4, apellido2);
                        psInsertar.setString(5, ciclo);
                        psInsertar.setInt(6, curso);

                        psInsertar.executeUpdate();
                        System.out.println("Alumno insertado correctamente");
                    }

                    case 2 -> { // 2. READ / SELECT
                        System.out.println("\nLISTADO DE ALUMNOS");
                        Statement sentencia = conexion.createStatement();
                        ResultSet resultado = sentencia.executeQuery(
                            "SELECT * FROM alumno ORDER BY apellido1, apellido2, nombre"
                        );

                        while (resultado.next()) {
                            String eva1 = resultado.getString("eva1");
                            String eva2 = resultado.getString("eva2");
                            String eva3 = resultado.getString("eva3");
                            String notaFinalBD = resultado.getString("notaFinal");

                            System.out.println(
                                resultado.getString("dni") + "  " +
                                resultado.getString("apellido1") + " " +
                                resultado.getString("apellido2") + ", " +
                                resultado.getString("nombre") + "  " +
                                resultado.getString("ciclo") + "  Curso " +
                                resultado.getInt("curso") + "  Notas: " +
                                (eva1 == null ? "Sin nota" : eva1) + ", " +
                                (eva2 == null ? "Sin nota" : eva2) + ", " +
                                (eva3 == null ? "Sin nota" : eva3) + "  Final: " +
                                (notaFinalBD == null ? "Sin calcular" : notaFinalBD)
                            );
                        }
                    }

                    case 3 -> { // 3. UPDATE / MODIFICAR
                        System.out.print("\nIntroduce el DNI del alumno que quieres modificar: ");
                        String dniModificar = teclado.nextLine();

                        System.out.println("""
                                ¿Qué campo quieres modificar?
                                1. Nombre
                                2. Primer apellido
                                3. Segundo apellido
                                4. Ciclo
                                5. Curso
                                6. DNI
                                7. Notas
                                """);
                        System.out.print("Elige una opción: ");
                        int opcionModificar = Integer.parseInt(teclado.nextLine());

                        switch (opcionModificar) {
                        
                            case 1 -> { // Modificar nombre
                                System.out.print("Introduce el nuevo nombre: ");
                                	String nuevoNombre = teclado.nextLine();

                                String sql = """
                                		UPDATE alumno SET nombre = ? WHERE dni = ?
                                		""";
                                
                                PreparedStatement psModificar = conexion.prepareStatement(sql);
                                	psModificar.setString(1, nuevoNombre);
                                	psModificar.setString(2, dniModificar);

                                int filas = psModificar.executeUpdate();
                                if (filas > 0) {
                                    System.out.println("Nombre actualizado correctamente");
                                } else {
                                    System.out.println("No existe ningún alumno con ese DNI");
                                }
                            }
                            
                            case 2 -> { // Modificar primer apellido
                                System.out.print("Introduce el nuevo primer apellido: ");
                                	String nuevoApellido1 = teclado.nextLine();

                                String sql = """
                                		UPDATE alumno SET apellido1 = ? WHERE dni = ?
                                		""";
                                
                                PreparedStatement psModificar = conexion.prepareStatement(sql);
                                	psModificar.setString(1, nuevoApellido1);
                                	psModificar.setString(2, dniModificar);

                                int filas = psModificar.executeUpdate();
                                if (filas > 0) {
                                    System.out.println("Primer apellido actualizado correctamente");
                                } else {
                                    System.out.println("No existe ningún alumno con ese DNI");
                                }
                            }
                            
                            case 3 -> { // Modificar segundo apellido
                                System.out.print("Introduce el nuevo segundo apellido: ");
                                	String nuevoApellido2 = teclado.nextLine();

                                String sql = """
                                		UPDATE alumno SET apellido2 = ? WHERE dni = ?
                                		""";
                                
                                PreparedStatement psModificar = conexion.prepareStatement(sql);
                                	psModificar.setString(1, nuevoApellido2);
                                	psModificar.setString(2, dniModificar);

                                int filas = psModificar.executeUpdate();
                                if (filas > 0) {
                                    System.out.println("Segundo apellido actualizado correctamente");
                                } else {
                                    System.out.println("No existe ningún alumno con ese DNI");
                                }
                            }
                            
                            case 4 -> { // Modificar ciclo
                                System.out.print("Introduce el nuevo ciclo: ");
                                	String nuevoCiclo = teclado.nextLine();

                                String sql = """
                                		UPDATE alumno SET ciclo = ? WHERE dni = ?
                                		""";
                                
                                PreparedStatement psModificar = conexion.prepareStatement(sql);
                                	psModificar.setString(1, nuevoCiclo);
                                	psModificar.setString(2, dniModificar);

                                int filas = psModificar.executeUpdate();
                                if (filas > 0) {
                                    System.out.println("Ciclo actualizado correctamente");
                                } else {
                                    System.out.println("No existe ningún alumno con ese DNI");
                                }
                            }
                            
                            case 5 -> { // Modificar curso
                                System.out.print("Introduce el nuevo curso: ");
                                	int nuevoCurso = Integer.parseInt(teclado.nextLine());

                                String sql = """
                                		UPDATE alumno SET curso = ? WHERE dni = ?
                                		""";
                                
                                PreparedStatement psModificar = conexion.prepareStatement(sql);
                                	psModificar.setInt(1, nuevoCurso);
                                	psModificar.setString(2, dniModificar);

                                int filas = psModificar.executeUpdate();
                                if (filas > 0) {
                                    System.out.println("Curso actualizado correctamente");
                                } else {
                                    System.out.println("No existe ningún alumno con ese DNI");
                                }
                            }
                            
                            case 6 -> { // Modificar DNI (Clave primaria)
                                System.out.print("Introduce el nuevo DNI: ");
                                	String nuevoDni = teclado.nextLine();

                                String sql = """
                                		UPDATE alumno SET dni = ? WHERE dni = ?
                                		""";
                                
                                PreparedStatement psModificar = conexion.prepareStatement(sql);
                                	psModificar.setString(1, nuevoDni);
                                	psModificar.setString(2, dniModificar);

                                int filas = psModificar.executeUpdate();
                                
                                if (filas > 0) {
                                    System.out.println("DNI actualizado correctamente");
                                } else {
                                    System.out.println("No existe ningún alumno con ese DNI");
                                }
                            }
                            
                            case 7 -> { // Modificar notas
                                System.out.print("Nota de la 1ª evaluación: ");
                                	int notaEva1 = Integer.parseInt(teclado.nextLine());
                                System.out.print("Nota de la 2ª evaluación: ");
                                	int notaEva2 = Integer.parseInt(teclado.nextLine());
                                System.out.print("Nota de la 3ª evaluación: ");
                                	int notaEva3 = Integer.parseInt(teclado.nextLine());

                                int notaFinal = (notaEva1 + notaEva2 + notaEva3) / 3;

                                String sql = """
                                        UPDATE alumno
                                        SET eva1 = ?, eva2 = ?, eva3 = ?, notaFinal = ?
                                        WHERE dni = ?
                                        """;
                                PreparedStatement psModificar = conexion.prepareStatement(sql);
                                psModificar.setInt(1, notaEva1);
                                psModificar.setInt(2, notaEva2);
                                psModificar.setInt(3, notaEva3);
                                psModificar.setInt(4, notaFinal);
                                psModificar.setString(5, dniModificar);

                                int filas = psModificar.executeUpdate();
                                
                                if (filas > 0) {
                                    System.out.println("Notas actualizadas correctamente");
                                } else {
                                    System.out.println("No existe ningún alumno con ese DNI");
                                }
                            }
                            default -> System.out.println("Opción no válida");
                        }
                    }

                    case 4 -> { // 4. DELETE / ELIMINAR
                        System.out.print("\nIntroduce el DNI del alumno que quieres eliminar: ");
                        	String dniEliminar = teclado.nextLine();

                        String eliminar = "DELETE FROM alumno WHERE dni = ?";
                        PreparedStatement psDelete = conexion.prepareStatement(eliminar);
                        psDelete.setString(1, dniEliminar);

                        int filasEliminadas = psDelete.executeUpdate();
                        if (filasEliminadas > 0) {
                            System.out.println("Alumno eliminado correctamente");
                        } else {
                            System.out.println("No existe ningún alumno con ese DNI");
                        }
                    }
                    
                    case 5 -> { // 5. BUSCAR ALUMNO POR DNI 
                        System.out.print("\nIntroduce el DNI del alumno que quieres buscar: ");
                        	String dniBuscar = teclado.nextLine();

                        String sql = """
                        	 SELECT * FROM alumno WHERE dni = ?
                        		""";
                        PreparedStatement psBuscar = conexion.prepareStatement(sql);
                        psBuscar.setString(1, dniBuscar);

                        ResultSet resultado = psBuscar.executeQuery();

                        if (resultado.next()) {
                            String eva1 = resultado.getString("eva1");
                            String eva2 = resultado.getString("eva2");
                            String eva3 = resultado.getString("eva3");
                            String notaFinalBD = resultado.getString("notaFinal");

                            System.out.println("\nALUMNO ENCONTRADO:");
                            System.out.println("DNI: " + resultado.getString("dni"));
                            System.out.println("Nombre completo: " + resultado.getString("nombre") + " " +
                                               resultado.getString("apellido1") + " " +
                                               resultado.getString("apellido2"));
                            System.out.println("Ciclo: " + resultado.getString("ciclo") +
                                               " | Curso: " + resultado.getInt("curso"));
                            System.out.println("Notas: 1ª Eva: " + (eva1 == null ? "Sin nota" : eva1) +
                                               ", 2ª Eva: " + (eva2 == null ? "Sin nota" : eva2) +
                                               ", 3ª Eva: " + (eva3 == null ? "Sin nota" : eva3) +
                                               " | Nota Final: " + (notaFinalBD == null ? "Sin calcular" : notaFinalBD));
                        } else {
                            System.out.println("No existe ningún alumno con el DNI " + dniBuscar);
                        }
                    }

                    case 6 -> { // 6. LISTAR ALUMNOS POR CICLO
                        System.out.print("\nIntroduce el ciclo a consultar: ");
                       		String cicloBuscar = teclado.nextLine();

                        String sql = "SELECT * FROM alumno WHERE ciclo = ? ORDER BY apellido1, apellido2, nombre";
                        PreparedStatement psCiclo = conexion.prepareStatement(sql);
                        psCiclo.setString(1, cicloBuscar);

                        ResultSet resultado = psCiclo.executeQuery();
                        boolean encontrado = false;

                        System.out.println("\nALUMNOS DEL CICLO " + cicloBuscar.toUpperCase() + ":");

                        while (resultado.next()) {
                            encontrado = true;
                            String eva1 = resultado.getString("eva1");
                            String eva2 = resultado.getString("eva2");
                            String eva3 = resultado.getString("eva3");
                            String notaFinalBD = resultado.getString("notaFinal");

                            System.out.println(
                                resultado.getString("dni") + "  " +
                                resultado.getString("apellido1") + " " +
                                resultado.getString("apellido2") + ", " +
                                resultado.getString("nombre") + "  Curso " +
                                resultado.getInt("curso") + "  Notas: " +
                                (eva1 == null ? "Sin nota" : eva1) + ", " +
                                (eva2 == null ? "Sin nota" : eva2) + ", " +
                                (eva3 == null ? "Sin nota" : eva3) + "  Final: " +
                                (notaFinalBD == null ? "Sin calcular" : notaFinalBD)
                            );
                        }

                        if (!encontrado) {
                            System.out.println("No hay alumnos del ciclo " + cicloBuscar);
                        }
                    }

                    case 0 -> System.out.println("Saliendo del programa...");

                    default -> System.out.println("Opción no válida");
                }

            } while (opcion != 0);

            teclado.close();

        } catch (SQLException e) {
            System.out.println("Error al trabajar con la base de datos");
            e.printStackTrace();
        }
    }
}