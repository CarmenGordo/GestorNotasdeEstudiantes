package com.dam.accesodata;

import java.util.List;
import java.util.Scanner;

public class GestorNotas {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ManejadorArchivos manejador = new ManejadorArchivos();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            switch (opcion) {
                case 1:
                    añadirEstudiante();
                    break;
                case 2:
                    mostrarEstudiantes();
                    break;
                case 3:
                    buscarEstudiante();
                    break;
                case 4:
                    calcularMedia();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Gestor de Notas de Estudiantes ---");
        System.out.println("1. Añadir estudiante");
        System.out.println("2. Mostrar todos los estudiantes");
        System.out.println("3. Buscar estudiante");
        System.out.println("4. Calcular nota media");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void añadirEstudiante() {
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la nota del estudiante: ");
        double nota = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer
        Estudiante estudiante = new Estudiante(nombre, nota);
        manejador.añadirEstudiante(estudiante);
    }

    private static void mostrarEstudiantes() {
        manejador.mostrarEstudiantes();
    }

    private static void buscarEstudiante() {
        List<Estudiante> estudiantes = manejador.leerEstudiantes(); // Obtener la lista de estudiantes

        // Comprobar si la lista está vacía
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados. No se puede realizar la búsqueda.");
        } else {
            System.out.print("Ingrese el nombre del estudiante a buscar: ");
            String nombre = scanner.nextLine();

            // Buscar el estudiante
            Estudiante estudianteEncontrado = manejador.buscarEstudiante(nombre);

            // Comprobar si el estudiante fue encontrado
            if (estudianteEncontrado != null) {
                System.out.println("Estudiante encontrado: " + estudianteEncontrado.getNombre() + ", Nota: " + estudianteEncontrado.getNota());
            } else {
                System.out.println("Estudiante no encontrado.");
            }
        }
    }

    private static void calcularMedia() {
        manejador.calcularMedia();
    }
}
