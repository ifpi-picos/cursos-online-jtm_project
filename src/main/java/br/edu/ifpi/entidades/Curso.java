package br.edu.ifpi.entidades;

import br.edu.ifpi.enums.StatusCurso;

public class Curso {

    private String nome;
    private int id;
    private int cargaHoraria;
    private StatusCurso status;

    public Curso(int id2, String nome2, String status2, int cargaHoraria2) {
        this.nome = id2;
        this.id = nome2;
        this.cargaHoraria = status2;
        this.status = cargaHoraria2;
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
