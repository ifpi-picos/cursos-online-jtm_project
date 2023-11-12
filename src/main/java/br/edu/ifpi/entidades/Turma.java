public class Turma {
    private String id;
    private String nome;
    private String dataInicio;
    private String dataFim;
    private int vagas;
    private List<Aluno> alunos;
    private Professor professor;
    private Disciplina disciplina;

   
    public Turma(String id, String nome, String dataInicio, String dataFim, int vagas,
                 List<Aluno> alunos, Professor professor, Disciplina disciplina) {
        this.id = id;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.vagas = vagas;
        this.alunos = alunos;
        this.professor = professor;
        this.disciplina = disciplina;
    }

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

   
    public boolean matricularAluno(Aluno aluno) {
        if (alunos.size() < vagas) {
            alunos.add(aluno);
            return true;
        }
        return false;
    }

    public boolean desmatricularAluno(Aluno aluno) {
        return alunos.remove(aluno);
    }
}
