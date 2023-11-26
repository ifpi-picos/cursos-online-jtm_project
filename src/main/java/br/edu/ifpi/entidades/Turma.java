package br.edu.ifpi.entidades;

public class Turma {
    private int idCurso;
    private int idAluno;
    private float nota;

    public Turma(int idCurso, int idAluno, float nota) {
        this.idCurso = idCurso;
        this.idAluno = idAluno;
        this.nota = nota;
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


    @Override
    public String toString() {
        return "Turma [ID do Curso=" + idCurso + ", ID do Aluno=" + idAluno + ", Notas=" + nota + "]";
    }

    public void setNota(String notas) {
    }

    public void setNota(float[] notas) {
    }

    public void setIdAluno(String string) {
    }
}
