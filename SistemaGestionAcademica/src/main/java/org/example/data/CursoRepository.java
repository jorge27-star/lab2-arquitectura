package org.example.data;

import org.example.business.Curso;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CursoRepository {
    private final String archivo = "Data/cursos.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public List<Curso> listar() {
        File file = new File(archivo);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            Type tipo = new TypeToken<List<Curso>>() {}.getType();
            List<Curso> cursos = gson.fromJson(reader, tipo);
            return cursos != null ? cursos : new ArrayList<>();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void guardar(List<Curso> cursos) {
        File file = new File(archivo);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try (Writer writer = new FileWriter(file)) {
            gson.toJson(cursos, writer);
        } catch (Exception e) {
            System.err.println("Error al guardar Curso: " + e.getMessage());
        }
    }
}