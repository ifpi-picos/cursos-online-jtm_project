package br.edu.ifpi.entidades;

import br.edu.ifpi.enums.StatusCurso;

public class Curso {

    private AndamentoCurso status;
    private int CargaHoraria;

    public Curso(String nome, AndamentoCurso status, int CargaHoraria) {

        this.Nome = nome;
        this.AndamentoCurso = status;
        this.cargahoraria = cargahoraria;

    }

    public String getNome() {
        return getNome;
    }

    public String getAnadamentoCurso() {
        return getAnadamentoCurso;
    }

    public String getCargaHoraria() {
        return CargaHoraria;
    }

}
