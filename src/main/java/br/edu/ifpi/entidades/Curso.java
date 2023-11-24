package br.edu.ifpi.entidades;

import br.edu.ifpi.enums.StatusCurso;

public class Curso {

    private String nome;
    private int id;
    private String cargaHoraria;
    private StatusCurso status;

    public Curso(int id, String nome, StatusCurso status, String cargaHoraria) {
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

    public String getCargaHoraria() {
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

    public void setCargaHoraria(String cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public StatusCurso getStatus() {
        return status;
    }
}
