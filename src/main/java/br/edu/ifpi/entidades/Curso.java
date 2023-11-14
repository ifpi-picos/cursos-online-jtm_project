package br.edu.ifpi.entidades;

public class Curso {

    private String nome;
    private int id;
    private int cargaHoraria;

    public Curso(String nome, int id, int cargaHoraria) {
        this.nome = nome;
        this.id = id;
        this.cargaHoraria = cargaHoraria;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}
