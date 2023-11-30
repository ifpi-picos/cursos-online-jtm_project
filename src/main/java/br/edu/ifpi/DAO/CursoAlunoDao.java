package br.edu.ifpi.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.CursoAluno;

public class CursoAlunoDao implements Dao<CursoAluno> {
    private Conexao conexao;

    public CursoAlunoDao(Conexao conexao) {
        this.conexao = conexao;
    }

    @Override
    public int cadastrar(CursoAluno cursoAluno) {
        String SQL_INSERT = "INSERT INTO CURSO_ALUNO (ID_CURSO, ID_ALUNO, NOTAS, SITUACAO) VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);

            preparedStatement.setInt(1, cursoAluno.getIdCurso());
            preparedStatement.setInt(2, cursoAluno.getIdAluno());
            preparedStatement.setFloat(3, cursoAluno.getNota());
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
        String SQL_SELECT_ALL = "SELECT CURSO_ALUNO.ID, CURSO_ALUNO.ID_CURSO, CURSO_ALUNO.ID_ALUNO, CURSO_ALUNO.NOTAS, CURSO_ALUNO.SITUACAO, ALUNO.NOME "
                + "FROM CURSO_ALUNO INNER JOIN ALUNO ON CURSO_ALUNO.ID_ALUNO = ALUNO.ID";

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
                        "\t Notas: " + (ca.getNota() != 0 ? ca.getNota() : " ") +
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
        String SQL_UPDATE = "UPDATE CURSO_ALUNO SET ID_CURSO=?, ID_ALUNO=?, NOTAS=?, SITUACAO=? WHERE ID=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_UPDATE);

            preparedStatement.setInt(1, cursoAluno.getIdCurso());
            preparedStatement.setInt(2, cursoAluno.getIdAluno());
            preparedStatement.setFloat(3, cursoAluno.getNota());
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
        String SQL_DELETE = "DELETE FROM CURSO_ALUNO WHERE ID=?";

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

    public void mostrarBoletim(int idAluno) {
        String SQL_SELECT_NOTAS_ALUNO = "SELECT ID_CURSO, NOTAS, SITUACAO FROM CURSO_ALUNO WHERE ID_ALUNO = ?";

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

    public void remover(int id) {
    }

    public double exibirNotaMediaGeralAlunos(Curso curso) {
        String SQL_SELECT_AVG_GRADE = "SELECT AVG(nota) AS MEDIA_GERAL FROM cursoaluno WHERE ID_CURSO = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(SQL_SELECT_AVG_GRADE)) {
            stmt.setInt(1, curso.getId());

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    double mediaGeral = resultSet.getDouble("MEDIA_GERAL");
                    System.out.println("_____________________________________________\n");
                    System.out.println("   Nota Média Geral dos Alunos no Curso: " + mediaGeral);
                    System.out.println("_____________________________________________\n");
                    return mediaGeral;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

}
