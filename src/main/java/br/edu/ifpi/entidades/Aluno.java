package br.edu.ifpi.entidades;

public class Aluno {
    private String nome;
    private int id;
    private String email;
    private Curso curso;

    public Aluno(int id, String nome, String email, Curso curso) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.curso = curso;
    }

    public Aluno(String nome, String email, Curso curso) {
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
