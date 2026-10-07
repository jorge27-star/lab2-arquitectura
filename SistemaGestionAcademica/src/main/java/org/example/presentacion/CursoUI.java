package org.example.presentacion;

import org.example.business.Curso;
import org.example.business.CursoService;
import java.util.Scanner;

public class CursoUI {
    private static final CursoService service = new CursoService();

    public static void mostrarMenu(Scanner sc) {
        int opcion = -1;
        do {
            System.out.println("\n=== GESTIÓN DE CURSOS ===");
            System.out.println("1. Registrar Curso");
            System.out.println("2. Listar Cursos");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Código del curso: ");
                    String cod = sc.nextLine();
                    System.out.print("Nombre del curso: ");
                    String nom = sc.nextLine();
                    System.out.print("Créditos: ");
                    int cred = Integer.parseInt(sc.nextLine());

                    service.registrar(new Curso(cod, nom, cred));
                    System.out.println("-> Curso registrado con éxito.");
                    break;

                case 2:
                    System.out.println("\n--- Lista de Cursos ---");
                    if (service.listar().isEmpty()) {
                        System.out.println("No hay cursos registrados.");
                    } else {
                        service.listar().forEach(c ->
                                System.out.printf("Código: %s | Nombre: %s | Créditos: %d%n",
                                        c.getCodigo(), c.getNombre(), c.getCreditos())
                        );
                    }
                    break;

                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}
