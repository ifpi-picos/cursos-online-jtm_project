package br.edu.ifpi.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.CursoAluno;
import br.edu.ifpi.enums.StatusCurso;

public class CursoAlunoDao implements Dao<CursoAluno> {
    private Conexao conexao;

    public CursoAlunoDao(Conexao conexao) {
        this.conexao = conexao;
    }

    @Override
    public int cadastrar(CursoAluno cursoAluno) {
        String SQL_INSERT = "INSERT INTO CURSOALUNO (ID_CURSO, ID_ALUNO, NOTAS, SITUACAO) VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);

            preparedStatement.setInt(1, cursoAluno.getIdCurso());
            preparedStatement.setInt(2, cursoAluno.getIdAluno());
            preparedStatement.setFloat(3, cursoAluno.getNotas());
            preparedStatement.setString(4, cursoAluno.getSituacao());

            int row = preparedStatement.executeUpdate();

            System.out.println(row);
            preparedStatement.close();

            System.out.println("_____________________________________________\n");
            System.out.println(" M A T R Í C U L A  R E A L I Z A D A  C O M\n              S U C E S S O !");
            System.out.println("_____________________________________________\n");
            return row;

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<CursoAluno> consultarTodos() {
        List<CursoAluno> cursoAlunos = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT CURSOALUNO.ID, CURSOALUNO.ID_CURSO, CURSOALUNO.ID_ALUNO, CURSOALUNO.NOTAS, CURSOALUNO.SITUACAO, ALUNO.NOME "
                + "FROM CURSOALUNO INNER JOIN ALUNO ON CURSOALUNO.ID_ALUNO = ALUNO.ID";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CursoAluno cursoAluno = new CursoAluno(resultSet.getInt("ID_CURSO"), resultSet.getInt("ID_ALUNO"),
                        resultSet.getFloat("NOTAS"), resultSet.getString("SITUACAO"));
                cursoAluno.setIdAluno(resultSet.getInt("ID"));
                cursoAluno.setNomeAluno(resultSet.getString("NOME"));

                cursoAlunos.add(cursoAluno);
            }

            for (CursoAluno ca : cursoAlunos) {
                System.out.println("ID do Curso: " + ca.getIdCurso() + "\t Nome do Aluno: " + ca.getNomeAluno() +
                        "\t Notas: " + (ca.getNotas() != 0 ? ca.getNotas() : " ") +
                        "\t Situação: " + ca.getSituacao());
            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.format("Estado SQL %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }

        return cursoAlunos;
    }

    @Override
    public int alterar(CursoAluno cursoAluno) {
        String SQL_UPDATE = "UPDATE CURSOALUNO SET ID_CURSO=?, ID_ALUNO=?, NOTAS=?, SITUACAO=? WHERE ID=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_UPDATE);

            preparedStatement.setInt(1, cursoAluno.getIdCurso());
            preparedStatement.setInt(2, cursoAluno.getIdAluno());
            preparedStatement.setFloat(3, cursoAluno.getNotas());
            preparedStatement.setString(4, cursoAluno.getSituacao());
            preparedStatement.setInt(5, cursoAluno.getIdCurso());

            int row = preparedStatement.executeUpdate();

            System.out.println(row);
            preparedStatement.close();

            return row;

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int remover(CursoAluno cursoAluno) {
        String SQL_DELETE = "DELETE FROM CURSOALUNO WHERE ID=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_DELETE);

            preparedStatement.setInt(1, cursoAluno.getIdCurso());

            int row = preparedStatement.executeUpdate();

            System.out.println(row);
            preparedStatement.close();

            return row;

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    public void gerarSituacao(){
        String sqlSituacao = "UPDATE curso_aluno SET situacao = CASE WHEN nota >= 7.0 THEN 'Aprovado' ELSE 'Reprovado' END ";
        try {
            PreparedStatement psmt = conexao.prepareStatement(sqlSituacao);
            psmt.executeUpdate();
            System.out.println("situacao criada com sucesso");
        } catch (Exception e) {
            System.out.println("Algum erro ocorreu.");
            e.printStackTrace();
        }
    }

    public void mostrarBoletim(int idAluno) {
        String SQL_SELECT_NOTAS_ALUNO = "SELECT ID_CURSO, NOTAS, SITUACAO FROM CURSOALUNO WHERE ID_ALUNO = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_SELECT_NOTAS_ALUNO);
            preparedStatement.setInt(1, idAluno);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("\n________________B O L E T I M________________");
            while (resultSet.next()) {
                int idCurso = resultSet.getInt("ID_CURSO");
                float notas = resultSet.getFloat("NOTAS");
                String situacao = resultSet.getString("SITUACAO");

                System.out.println("ID do Curso: " + idCurso + "\t Notas: " + notas + "\t Situação: " + situacao);
            }
            System.out.println("\n_____________________________________________\n");
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.format("Estado SQL %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }
    }

    public double exibirNotaMediaGeralAlunos(Curso curso) {
        String SQL_SELECT_AVG_GRADE = "SELECT AVG(notas) AS MEDIA_GERAL FROM cursoaluno WHERE ID_CURSO = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(SQL_SELECT_AVG_GRADE)) {
            stmt.setInt(1, curso.getId());

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    double mediaGeral = resultSet.getDouble("MEDIA_GERAL");
                    // System.out.println("_____________________________________________\n");
                    // System.out.println(" Nota Média Geral dos Alunos no Curso: " + mediaGeral);
                    // System.out.println("_____________________________________________\n");
                    return mediaGeral;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    public double exibirPorcentagemAprovadosReprovados(Curso curso) {
        String SQL_COUNT_APROVADOS = "SELECT COUNT(*) AS APROVADOS FROM cursoaluno WHERE ID_CURSO = ? AND notas >= 7";
        String SQL_COUNT_TODOS = "SELECT COUNT(*) AS TOTAL_ALUNOS FROM cursoaluno WHERE ID_CURSO = ?";

        try (PreparedStatement stmtAprovados = conexao.prepareStatement(SQL_COUNT_APROVADOS);
                PreparedStatement stmtTotalAlunos = conexao.prepareStatement(SQL_COUNT_TODOS)) {

            stmtAprovados.setInt(1, curso.getId());
            stmtTotalAlunos.setInt(1, curso.getId());

            try (ResultSet resultSetAprovados = stmtAprovados.executeQuery();
                    ResultSet resultSetTotalAlunos = stmtTotalAlunos.executeQuery()) {

                double porcentagemAprovados = 0;
                if (resultSetAprovados.next() && resultSetTotalAlunos.next()) {
                    int aprovados = resultSetAprovados.getInt("APROVADOS");
                    int totalAlunos = resultSetTotalAlunos.getInt("TOTAL_ALUNOS");

                    // int reprovados = totalAlunos - aprovados;

                    porcentagemAprovados = (double) aprovados / totalAlunos * 100;
                    // double porcentagemReprovados = (double) reprovados / totalAlunos * 100;

                    // System.out.println("_____________________________________________\n");
                    // System.out.println(" Porcentagem de Alunos Aprovados: " +
                    // porcentagemAprovados + "%");
                    // System.out.println(" Porcentagem de Alunos Reprovados: " +
                    // porcentagemReprovados + "%");
                    // System.out.println("_____________________________________________\n");
                }

                return porcentagemAprovados;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Curso> exibirCursosConcluidosPorAluno(Aluno aluno) {
        List<Curso> cursosConcluidos = new ArrayList<>();
        String SQL_SELECT_CONCLUIDOS = "SELECT curso.* FROM cursoaluno "
                + "INNER JOIN curso ON cursoaluno.id_curso = curso.id "
                + "WHERE cursoaluno.id_aluno = ? AND cursoaluno.concluido = true";

        try (PreparedStatement stmt = conexao.prepareStatement(SQL_SELECT_CONCLUIDOS)) {
            stmt.setInt(1, aluno.getId());

            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    int idCurso = resultSet.getInt("id");
                    String nomeCurso = resultSet.getString("nome");
                    String statusStr = resultSet.getString("status");
                    StatusCurso status = StatusCurso.fromString(statusStr);
                    String cargaHoraria = resultSet.getString("cargahoraria");

                    Curso curso = new Curso(idCurso, nomeCurso, status, cargaHoraria);
                    cursosConcluidos.add(curso);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("_____________________________________________\n");
        System.out.println("   Cursos Concluídos pelo Aluno: " + aluno.getNome());
        for (Curso curso : cursosConcluidos) {
            System.out.println("   - " + curso.getNome());
        }
        System.out.println("_____________________________________________\n");

        return cursosConcluidos;
    }

    public void mostrarAproveitamento() {
        String select = "SELECT aluno.nome AS nome_aluno, curso.nome AS nome_curso, curso_aluno.aproveitamento " +
                        "FROM curso_aluno " +
                        "JOIN aluno ON curso_aluno.id_aluno = aluno.id " +
                        "JOIN curso ON curso_aluno.id_curso = curso.id " +
                        "WHERE aluno.id > 0";
        try {
            PreparedStatement stmt = conexao.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();
            System.out.printf("%-30s | %-30s | %-15s\n", "Nome do Aluno", "Nome do Curso", "Aproveitamento");
            while (rs.next()) {
                System.out.printf("%-30s | %-30s | %-15s\n", rs.getString("nome_aluno"), rs.getString("nome_curso"), rs.getString("aproveitamento"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Deu um erro!");
        }
    }

}
