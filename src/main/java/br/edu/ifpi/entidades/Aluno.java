package br.edu.ifpi.entidades;

public class Aluno {
    private String nome;
    private int id;
    private String email;
    private String curso;

    public Aluno(int id, String nome, String email, String curso) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.curso = curso;
    }

    public Aluno(String email2) {
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

    public String getCurso() {
        return curso;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void add(Aluno aluno) {
    }
}
