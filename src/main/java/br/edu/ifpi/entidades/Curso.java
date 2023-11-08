package br.edu.ifpi.entidades;

public class Aluno {

    private String nome;
    private long id;
    private String email;
    private Curso curso;

    public Aluno(String nome, int id, String email, Curso curso) {
        this.nome = nome;
        this.id = id;
        this.email = email;
        this.curso = curso;
    }

    public String getNome() {
        return nome;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
