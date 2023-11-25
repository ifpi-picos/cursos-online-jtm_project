package br.edu.ifpi.entidades;

public class Aluno {
    private String nome;
    private int id;
    private String email;

    public Aluno(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void add(Aluno aluno) {
    }

    public void setId(int id_alter) {
    }
}
