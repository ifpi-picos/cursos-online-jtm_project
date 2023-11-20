package br.edu.ifpi.entidades;

public class Professor {
    private String nome;
    private int id;
    private String email;
    private Curso curso;

    public Professor(String nome, int id, String email, Curso curso){
        this.nome = nome;
        this.id = id;
        this.email = email;
        this.curso = curso;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
