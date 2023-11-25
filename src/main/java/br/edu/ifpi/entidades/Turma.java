package br.edu.ifpi.entidades;

public class Turma {
    private int idCurso;
    private int idAluno;
    private float nota;
    private String situacao;

    public Turma(int idCurso, int idAluno, float nota, String situacao) {
        this.idCurso = idCurso;
        this.idAluno = idAluno;
        this.nota = nota;
        this.situacao = situacao;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public String toString() {
        return "Turma [ID do Curso=" + idCurso + ", ID do Aluno=" + idAluno + ", Notas=" + nota + ", Situacao="
                + situacao + "]";
    }

    public void setNota(String notas) {
    }

    public void setNota(float[] notas) {
    }

    public void setIdAluno(String string) {
    }
}
