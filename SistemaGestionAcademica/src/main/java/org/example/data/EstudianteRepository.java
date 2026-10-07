package org.example.data;

import org.example.business.Estudiante;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepository {
    private final String archivo = "Data/Estudiantes.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public List<Estudiante> listar() {
        File file = new File(archivo);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            Type tipo = new TypeToken<List<Estudiante>>() {}.getType();
            List<Estudiante> estudiantes = gson.fromJson(reader, tipo);
            return estudiantes != null ? estudiantes : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void guardar(List<Estudiante> estudiantes) {
        File file = new File(archivo);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (Writer writer = new FileWriter(file)) {
            gson.toJson(estudiantes, writer);
        } catch (Exception e) {
            System.err.println("Error al guardar Estudiante: " + e.getMessage());
        }
    }

    public void agregar(Estudiante estudiante) {
        List<Estudiante> lista = listar();
        lista.add(estudiante);
        guardar(lista);
    }
}