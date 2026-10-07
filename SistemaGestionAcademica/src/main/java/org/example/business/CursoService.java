package org.example.business;

import org.example.data.CursoRepository;
import java.util.List;

public class CursoService {
    private final CursoRepository repository = new CursoRepository();

    public void registrar(Curso curso) {
        List<Curso> cursos = repository.listar();
        cursos.add(curso);
        repository.guardar(cursos);
    }

    public List<Curso> listar() {
        return repository.listar();
    }
}