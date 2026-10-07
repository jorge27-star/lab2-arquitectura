package org.example.presentacion;

import org.example.business.Estudiante;
import org.example.business.EstudianteService;
import java.util.Scanner;

public class EstudianteUI {
    private static final EstudianteService service = new EstudianteService();

    public static void mostrarMenu(Scanner sc) {
        int opcion = -1;
        do {
            System.out.println("\n=== GESTIÓN ESTUDIANTE ===");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();

                    service.registrar(new Estudiante(id, nombre, correo));
                    System.out.println("-> Estudiante registrado con éxito.");
                    break;

                case 2:
                    System.out.println("\n--- Lista de Estudiantes ---");
                    if (service.listar().isEmpty()) {
                        System.out.println("No hay estudiantes registrados.");
                    } else {
                        service.listar().forEach(e ->
                                System.out.printf("ID: %s | Nombre: %s | Correo: %s%n",
                                        e.getId(), e.getNombre(), e.getCorreo())
                        );
                    }
                    break;

                case 3:
                    System.out.print("ID del estudiante a actualizar: ");
                    String idAct = sc.nextLine();
                    System.out.print("Nuevo Nombre: ");
                    String nomAct = sc.nextLine();
                    System.out.print("Nuevo Correo: ");
                    String corAct = sc.nextLine();

                    boolean act = service.actualizar(new Estudiante(idAct, nomAct, corAct));
                    if (act) {
                        System.out.println("-> Estudiante actualizado con éxito.");
                    } else {
                        System.out.println("-> No se encontró el estudiante.");
                    }
                    break;

                case 4:
                    System.out.print("ID del estudiante a eliminar: ");
                    String idElim = sc.nextLine();
                    boolean elim = service.eliminar(idElim);
                    if (elim) {
                        System.out.println("-> Estudiante eliminado con éxito.");
                    } else {
                        System.out.println("-> No se encontró el estudiante.");
                    }
                    break;

                case 0:
                    System.out.println("Regresando...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}