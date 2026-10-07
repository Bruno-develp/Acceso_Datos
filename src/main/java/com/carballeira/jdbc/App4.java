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

        try (Connection conexion = DriverManager.getConnection(url);
             Scanner teclado = new Scanner(System.in)) {

            System.out.println("Conexión realizada correctamente");

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

                    case 1 -> {

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

                        System.out.print("Introduce nota Eva1: ");
                        double eva1 = Double.parseDouble(teclado.nextLine());

                        System.out.print("Introduce nota Eva2: ");
                        double eva2 = Double.parseDouble(teclado.nextLine());

                        System.out.print("Introduce nota Eva3: ");
                        double eva3 = Double.parseDouble(teclado.nextLine());

                        // CALCULAR LA NOTA FINAL
                        double notaFinal = (eva1 + eva2 + eva3) / 3.0;

                        String sql = """
                                INSERT INTO alumno
                                (dni, nombre, apellido1, apellido2, ciclo, curso,
                                 eva1, eva2, eva3, notaFinal)
                                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                                """;

                        try (PreparedStatement psInsertar =
                                     conexion.prepareStatement(sql)) {

                            psInsertar.setString(1, dni);
                            psInsertar.setString(2, nombre);
                            psInsertar.setString(3, apellido1);
                            psInsertar.setString(4, apellido2);
                            psInsertar.setString(5, ciclo);
                            psInsertar.setInt(6, curso);

                            psInsertar.setDouble(7, eva1);
                            psInsertar.setDouble(8, eva2);
                            psInsertar.setDouble(9, eva3);
                            psInsertar.setDouble(10, notaFinal);

                            psInsertar.executeUpdate();

                            System.out.println("Alumno insertado correctamente");
                            System.out.println("Nota final: " + notaFinal);
                        }
                    }

                    case 2 -> {

                        System.out.println("\nLISTADO DE ALUMNOS");

                        String sql = """
                                SELECT * FROM alumno
                                ORDER BY apellido1, apellido2, nombre
                                """;

                        try (Statement sentencia = conexion.createStatement();
                             ResultSet resultado = sentencia.executeQuery(sql)) {

                            while (resultado.next()) {

                                System.out.println(
                                        resultado.getString("dni") + "  " +
                                        resultado.getString("apellido1") + " " +
                                        resultado.getString("apellido2") + ", " +
                                        resultado.getString("nombre") + "  " +
                                        resultado.getString("ciclo") +
                                        "  Curso " +
                                        resultado.getInt("curso") +
                                        "  Notas: " +
                                        resultado.getDouble("eva1") + ", " +
                                        resultado.getDouble("eva2") + ", " +
                                        resultado.getDouble("eva3") +
                                        "  Final: " +
                                        resultado.getDouble("notaFinal")
                                );
                            }
                        }
                    }

                    case 3 -> {

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

                            case 1 -> {

                                System.out.print("Introduce el nuevo nombre: ");
                                String nuevoNombre = teclado.nextLine();

                                String sql = """
                                        UPDATE alumno
                                        SET nombre = ?
                                        WHERE dni = ?
                                        """;

                                try (PreparedStatement ps =
                                             conexion.prepareStatement(sql)) {

                                    ps.setString(1, nuevoNombre);
                                    ps.setString(2, dniModificar);

                                    int filas = ps.executeUpdate();

                                    if (filas > 0) {
                                        System.out.println("Nombre actualizado correctamente");
                                    } else {
                                        System.out.println("No existe ningún alumno con ese DNI");
                                    }
                                }
                            }


                            case 2 -> {

                                System.out.print("Introduce el nuevo primer apellido: ");
                                String nuevoApellido1 = teclado.nextLine();

                                String sql = """
                                        UPDATE alumno
                                        SET apellido1 = ?
                                        WHERE dni = ?
                                        """;

                                try (PreparedStatement ps =
                                             conexion.prepareStatement(sql)) {

                                    ps.setString(1, nuevoApellido1);
                                    ps.setString(2, dniModificar);

                                    int filas = ps.executeUpdate();

                                    if (filas > 0) {
                                        System.out.println("Primer apellido actualizado correctamente");
                                    } else {
                                        System.out.println("No existe ningún alumno con ese DNI");
                                    }
                                }
                            }


                            case 3 -> {

                                System.out.print("Introduce el nuevo segundo apellido: ");
                                String nuevoApellido2 = teclado.nextLine();

                                String sql = """
                                        UPDATE alumno
                                        SET apellido2 = ?
                                        WHERE dni = ?
                                        """;

                                try (PreparedStatement ps =
                                             conexion.prepareStatement(sql)) {

                                    ps.setString(1, nuevoApellido2);
                                    ps.setString(2, dniModificar);

                                    int filas = ps.executeUpdate();

                                    if (filas > 0) {
                                        System.out.println("Segundo apellido actualizado correctamente");
                                    } else {
                                        System.out.println("No existe ningún alumno con ese DNI");
                                    }
                                }
                            }


                            case 4 -> {

                                System.out.print("Introduce el nuevo ciclo: ");
                                String nuevoCiclo = teclado.nextLine();

                                String sql = """
                                        UPDATE alumno
                                        SET ciclo = ?
                                        WHERE dni = ?
                                        """;

                                try (PreparedStatement ps =
                                             conexion.prepareStatement(sql)) {

                                    ps.setString(1, nuevoCiclo);
                                    ps.setString(2, dniModificar);

                                    int filas = ps.executeUpdate();

                                    if (filas > 0) {
                                        System.out.println("Ciclo actualizado correctamente");
                                    } else {
                                        System.out.println("No existe ningún alumno con ese DNI");
                                    }
                                }
                            }

                            case 5 -> {

                                System.out.print("Introduce el nuevo curso: ");
                                int nuevoCurso =
                                        Integer.parseInt(teclado.nextLine());

                                String sql = """
                                        UPDATE alumno
                                        SET curso = ?
                                        WHERE dni = ?
                                        """;

                                try (PreparedStatement ps =
                                             conexion.prepareStatement(sql)) {

                                    ps.setInt(1, nuevoCurso);
                                    ps.setString(2, dniModificar);

                                    int filas = ps.executeUpdate();

                                    if (filas > 0) {
                                        System.out.println("Curso actualizado correctamente");
                                    } else {
                                        System.out.println("No existe ningún alumno con ese DNI");
                                    }
                                }
                            }

                            case 6 -> {

                                System.out.print("Introduce el nuevo DNI: ");
                                String nuevoDni = teclado.nextLine();

                                String sql = """
                                        UPDATE alumno
                                        SET dni = ?
                                        WHERE dni = ?
                                        """;

                                try (PreparedStatement ps =
                                             conexion.prepareStatement(sql)) {

                                    ps.setString(1, nuevoDni);
                                    ps.setString(2, dniModificar);

                                    int filas = ps.executeUpdate();

                                    if (filas > 0) {
                                        System.out.println("DNI actualizado correctamente");
                                    } else {
                                        System.out.println("No existe ningún alumno con ese DNI");
                                    }
                                }
                            }


                            case 7 -> {

                                System.out.print("Nota de la 1ª evaluación: ");
                                double notaEva1 =
                                        Double.parseDouble(teclado.nextLine());

                                System.out.print("Nota de la 2ª evaluación: ");
                                double notaEva2 =
                                        Double.parseDouble(teclado.nextLine());

                                System.out.print("Nota de la 3ª evaluación: ");
                                double notaEva3 =
                                        Double.parseDouble(teclado.nextLine());

                                // CALCULAR MEDIA
                                double notaFinal =
                                        (notaEva1 + notaEva2 + notaEva3) / 3.0;

                                String sql = """
                                        UPDATE alumno
                                        SET eva1 = ?,
                                            eva2 = ?,
                                            eva3 = ?,
                                            notaFinal = ?
                                        WHERE dni = ?
                                        """;

                                try (PreparedStatement ps =
                                             conexion.prepareStatement(sql)) {

                                    ps.setDouble(1, notaEva1);
                                    ps.setDouble(2, notaEva2);
                                    ps.setDouble(3, notaEva3);
                                    ps.setDouble(4, notaFinal);
                                    ps.setString(5, dniModificar);

                                    int filas = ps.executeUpdate();

                                    if (filas > 0) {
                                        System.out.println("Notas actualizadas correctamente");
                                        System.out.println(
                                                "Nota final: " + notaFinal
                                        );
                                    } else {
                                        System.out.println(
                                                "No existe ningún alumno con ese DNI"
                                        );
                                    }
                                }
                            }

                            default ->
                                    System.out.println("Opción no válida");
                        }
                    }

                    case 4 -> {

                        System.out.print(
                                "\nIntroduce el DNI del alumno que quieres eliminar: "
                        );

                        String dniEliminar = teclado.nextLine();

                        String sql =
                                "DELETE FROM alumno WHERE dni = ?";

                        try (PreparedStatement ps =
                                     conexion.prepareStatement(sql)) {

                            ps.setString(1, dniEliminar);

                            int filasEliminadas = ps.executeUpdate();

                            if (filasEliminadas > 0) {
                                System.out.println(
                                        "Alumno eliminado correctamente"
                                );
                            } else {
                                System.out.println(
                                        "No existe ningún alumno con ese DNI"
                                );
                            }
                        }
                    }

                    case 5 -> {

                        System.out.print(
                                "\nIntroduce el DNI del alumno que quieres buscar: "
                        );

                        String dniBuscar = teclado.nextLine();

                        String sql =
                                "SELECT * FROM alumno WHERE dni = ?";

                        try (PreparedStatement ps =
                                     conexion.prepareStatement(sql)) {

                            ps.setString(1, dniBuscar);

                            try (ResultSet resultado =
                                         ps.executeQuery()) {

                                if (resultado.next()) {

                                    System.out.println("\nALUMNO ENCONTRADO:");

                                    System.out.println(
                                            "DNI: " +
                                            resultado.getString("dni")
                                    );

                                    System.out.println(
                                            "Nombre completo: " +
                                            resultado.getString("nombre") + " " +
                                            resultado.getString("apellido1") + " " +
                                            resultado.getString("apellido2")
                                    );

                                    System.out.println(
                                            "Ciclo: " +
                                            resultado.getString("ciclo") +
                                            " | Curso: " +
                                            resultado.getInt("curso")
                                    );

                                    System.out.println(
                                            "Notas: " +
                                            resultado.getDouble("eva1") + ", " +
                                            resultado.getDouble("eva2") + ", " +
                                            resultado.getDouble("eva3")
                                    );

                                    System.out.println(
                                            "Nota Final: " +
                                            resultado.getDouble("notaFinal")
                                    );

                                } else {

                                    System.out.println(
                                            "No existe ningún alumno con el DNI "
                                            + dniBuscar
                                    );
                                }
                            }
                        }
                    }

                    case 6 -> {

                        System.out.print(
                                "\nIntroduce el ciclo a consultar: "
                        );

                        String cicloBuscar = teclado.nextLine();

                        String sql = """
                                SELECT * FROM alumno
                                WHERE ciclo = ?
                                ORDER BY apellido1, apellido2, nombre
                                """;

                        try (PreparedStatement ps =
                                     conexion.prepareStatement(sql)) {

                            ps.setString(1, cicloBuscar);

                            try (ResultSet resultado =
                                         ps.executeQuery()) {

                                boolean encontrado = false;

                                System.out.println(
                                        "\nALUMNOS DEL CICLO "
                                        + cicloBuscar.toUpperCase() + ":"
                                );

                                while (resultado.next()) {

                                    encontrado = true;

                                    System.out.println(
                                            resultado.getString("dni") + "  " +
                                            resultado.getString("apellido1") +
                                            " " +
                                            resultado.getString("apellido2") +
                                            ", " +
                                            resultado.getString("nombre") +
                                            "  Curso " +
                                            resultado.getInt("curso") +
                                            "  Notas: " +
                                            resultado.getDouble("eva1") + ", " +
                                            resultado.getDouble("eva2") + ", " +
                                            resultado.getDouble("eva3") +
                                            "  Final: " +
                                            resultado.getDouble("notaFinal")
                                    );
                                }

                                if (!encontrado) {
                                    System.out.println(
                                            "No hay alumnos del ciclo "
                                            + cicloBuscar
                                    );
                                }
                            }
                        }
                    }

                    case 0 ->
                            System.out.println("Saliendo del programa...");


                    default ->
                            System.out.println("Opción no válida");
                }

            } while (opcion != 0);

        } catch (SQLException e) {

            System.out.println(
                    "Error al trabajar con la base de datos"
            );

            e.printStackTrace();
        }
    }
}