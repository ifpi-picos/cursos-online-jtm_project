package br.edu.ifpi.entidades;

public class Professor {
    private String nome;
    private int id;
    private String email;
    private int id_curso;
    private String nome_curso;

    public Professor(String nome, int id, String email, int id_curso, String nome_curso) {
        this.nome = nome;
        this.id = id;
        this.email = email;
        this.id_curso = id_curso;
        this.nome_curso = nome_curso;
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

    public int getId_curso() {
        return id_curso;
    }

    public String getNome_curso() {
        return nome_curso;
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

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public void setNome_curso(String nome_curso) {
        this.nome_curso = nome_curso;
    }
}
