package br.edu.ifpi.entidades;

import br.edu.ifpi.enums.StatusCurso;

public class Curso {

    private String nome;
    private int id;
    private int cargaHoraria;
    private StatusCurso status;

    public Curso(String nome, int id, int cargaHoraria, StatusCurso status) {
        this.nome = nome;
        this.id = id;
        this.cargaHoraria = cargaHoraria;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public long getId() {
        return id;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setStatus(StatusCurso status) {
        this.status = status;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Object getStatus() {
        return null;
    }
}
