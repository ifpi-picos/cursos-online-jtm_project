package br.edu.ifpi.entidades;

public class CursoAluno {
    private int idCurso;
    private int idAluno;
    private float notas;
    private String situacao;

    public CursoAluno(int idCurso, int idAluno, float notas, String situacao) {
        this.idCurso = idCurso;
        this.idAluno = idAluno;
        this.notas = notas;
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

    public float getNotas() {
        return notas;
    }

    public void setNotas(float notas) {
        this.notas = notas;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public void setNomeAluno(String string) {
    }

    public String getNomeAluno() {
        return null;
    }

    public static void cadastrar(CursoAluno cursoAluno) {
    }
}
