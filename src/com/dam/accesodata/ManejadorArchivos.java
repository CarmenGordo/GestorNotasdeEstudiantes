package com.dam.accesodata;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejadorArchivos {
    private static final String ARCHIVO = "resources/notas_estudiantes.txt";

    public void añadirEstudiante(Estudiante estudiante) {
        //TODO: Implementar la lógica para añadir un estudiante al archivo
        try {
            //crear el archivo si no existe
            File file = new File(ARCHIVO);
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Crear directorio si no existe
                file.createNewFile(); // Crear el archivo
            }
            //escribir en el archivo el nuevo estudiante
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
                writer.write(estudiante.toString());
                //newLin(): añade un salto de linea
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al añadir este estudiante: " + e.getMessage());
        }
    }

    public void mostrarEstudiantes() {
        // TODO: Implementar la lógica para mostrar todos los estudiantes del archivo

        List<Estudiante> estudiantes = leerEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados");

        } else {
            for (int i = 0; i < estudiantes.size(); i++) {
                System.out.println(estudiantes.get(i));
            }
        }
    }

    public Estudiante buscarEstudiante(String nombre) {
        // TODO: Implementar la lógica para buscar un estudiante en el archivo

        List<Estudiante> estudiantes = leerEstudiantes(); // Leer todos los estudiantes

        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante estudiante = estudiantes.get(i);
            if (estudiante.getNombre().equalsIgnoreCase(nombre)) {
                return estudiante;
            }
        }
        return null;

    }

    public void calcularMedia() {
        // TODO: Implementar la lógica para calcular la nota media de todos los estudiantes

        List<Estudiante> estudiantes = leerEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes, por lo que la media no se puede calcular");
            return;
        }
        double suma = 0;
        for (int i = 0; i < estudiantes.size(); i++) {
            suma += estudiantes.get(i).getNota();
        }
        double media = suma / estudiantes.size();
        System.out.println("La nota media es: " + media);
    }

    public List<Estudiante> leerEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        // TODO: Implementar la lógica para leer todos los estudiantes del archivo

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombre = partes[0];
                    double nota = Double.parseDouble(partes[1]);
                    estudiantes.add(new Estudiante(nombre, nota));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return estudiantes;
    }
}