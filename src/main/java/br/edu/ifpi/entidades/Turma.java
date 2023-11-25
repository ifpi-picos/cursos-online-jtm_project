package br.edu.ifpi.entidades;

public class Turma {
    private int id;
    private int idProfessor;
    private int idAluno;
    private String notas;
    private String situacao;

    public Turma(int idProfessor, int idAluno, String notas, String situacao) {
        this.idProfessor = idProfessor;
        this.idAluno = idAluno;
        this.notas = notas;
        this.situacao = situacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Turma [id=" + id + ", idProfessor=" + idProfessor + ", idAluno=" + idAluno + ", notas=" + notas
                + ", situacao=" + situacao + "]";
    }
}
