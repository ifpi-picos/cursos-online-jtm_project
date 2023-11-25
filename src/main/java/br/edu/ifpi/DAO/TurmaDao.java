package br.edu.ifpi.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entidades.Turma;

public class TurmaDao implements Dao<Turma> {
    private Conexao conexao;

    public TurmaDao(Conexao conexao) {
        this.conexao = conexao;
    }

    @Override
    public int cadastrar(Turma turma) {
        String SQL_INSERT = "INSERT INTO TURMA (ID_CURSO, ID_ALUNO, NOTAS, SITUACAO) VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);

            preparedStatement.setInt(1, turma.getIdCurso());
            preparedStatement.setInt(2, turma.getIdAluno());
            preparedStatement.setFloat(3, turma.getNota());
            preparedStatement.setString(4, turma.getSituacao());

            int row = preparedStatement.executeUpdate();

            System.out.println(row);
            return row;

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Turma> consultarTodos() {
        List<Turma> turmas = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT TURMA.ID, TURMA.ID_CURSO, TURMA.ID_ALUNO, TURMA.NOTAS, TURMA.SITUACAO, ALUNO.NOME "
                + "FROM TURMA INNER JOIN ALUNO ON TURMA.ID_ALUNO = ALUNO.ID";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Turma turma = new Turma(resultSet.getInt("ID_CURSO"), resultSet.getInt("ID_ALUNO"),
                        resultSet.getFloat("NOTAS"), resultSet.getString("SITUACAO"));
                turma.setIdCurso(resultSet.getInt("ID"));
                turma.setIdAluno(resultSet.getString("NOME"));

                turmas.add(turma);
            }

            for (Turma t : turmas) {
                System.out.println("ID do Curso: " + t.getIdCurso() + "\t Nome do Aluno: " + t.getIdAluno() +
                        "\t Notas: " + t.getNota() + "\t Situação: " + t.getSituacao());
            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.format("Estado SQL %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }

        return turmas;
    }

    @Override
    public int alterar(Turma turma) {
        String SQL_UPDATE = "UPDATE TURMA SET ID_CURSO=?, ID_ALUNO=?, NOTAS=?, SITUACAO=? WHERE ID=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_UPDATE);

            preparedStatement.setInt(1, turma.getIdCurso());
            preparedStatement.setInt(2, turma.getIdAluno());
            preparedStatement.setFloat(3, turma.getNota());
            preparedStatement.setString(4, turma.getSituacao());
            preparedStatement.setInt(5, turma.getIdAluno());

            int row = preparedStatement.executeUpdate();

            System.out.println(row);
            return row;

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int remover(Turma turma) {
        String SQL_DELETE = "DELETE FROM TURMA WHERE ID=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_DELETE);

            preparedStatement.setInt(1, turma.getIdAluno());

            int row = preparedStatement.executeUpdate();

            System.out.println(row);
            return row;

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

    public void mostrarNotasPorAluno(int idAluno) {
        String SQL_SELECT_NOTAS_ALUNO = "SELECT ID_CURSO, NOTAS, SITUACAO FROM TURMA WHERE ID_ALUNO = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_SELECT_NOTAS_ALUNO);
            preparedStatement.setInt(1, idAluno);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idCurso = resultSet.getInt("ID_CURSO");
                float notas = resultSet.getFloat("NOTAS");
                String situacao = resultSet.getString("SITUACAO");

                System.out.println("ID do Curso: " + idCurso + "\t Notas: " + notas + "\t Situação: " + situacao);
            }

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.format("Estado SQL %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }
    }

    public void gerarEstatisticas(Turma turma) {
        String sqlSituacao = "UPDATE TURMA SET situacao = CASE WHEN nota >= 7.0 THEN 'Aprovado' ELSE 'Reprovado' END, nota = ? WHERE ID=?";

        try {
            PreparedStatement psmt = conexao.prepareStatement(sqlSituacao);
            psmt.setFloat(1, turma.getNota());
            psmt.setInt(2, turma.getIdAluno());
            psmt.executeUpdate();
            System.out.println("Situação criada com sucesso");
        } catch (SQLException e) {
            System.out.println("Algum erro ocorreu.");
            e.printStackTrace();
        }
    }
}
