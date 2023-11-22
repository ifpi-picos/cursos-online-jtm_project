package br.edu.ifpi.entidades;

import br.edu.ifpi.enums.StatusCurso;

public class Curso {

    private String nome;
    private int id;
    private int cargaHoraria;
    private StatusCurso status;

    public Curso(int id, String nome, StatusCurso status, int cargaHoraria) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.cargaHoraria = cargaHoraria;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
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

    public StatusCurso getStatus() {
        return status;
    }
}
