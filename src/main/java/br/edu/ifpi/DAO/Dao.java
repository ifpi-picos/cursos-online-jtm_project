package br.edu.ifpi.DAO;

import java.util.List;

public interface Dao<T> {
    int cadastrar(T objeto);

    List<T> consultarTodos();

    int alterar(T objeto);

    int remover(T objeto);
}
