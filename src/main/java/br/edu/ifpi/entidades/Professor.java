package br.edu.ifpi.entidades;

public class Professor extends Usuario{
    private Curso curso;

    public Professor(String nome, int id, String email, Curso curso) {
        super(nome, id, email);
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}