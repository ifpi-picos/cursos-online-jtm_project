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
        String SQL_INSERT = "INSERT INTO TURMA (ID_PROFESSOR, ID_ALUNO, NOTAS, SITUACAO) VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);

            preparedStatement.setInt(1, turma.getIdProfessor());
            preparedStatement.setInt(2, turma.getIdAluno());
            preparedStatement.setString(3, turma.getNotas());
            preparedStatement.setString(4, turma.getSituacao());

            int row = preparedStatement.executeUpdate();

            System.out.println(row);
            return row;

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<Turma> consultarTodos() {
        List<Turma> turmas = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT * FROM TURMA";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Turma turma = new Turma(0, 0, SQL_SELECT_ALL, SQL_SELECT_ALL);
                turma.setId(resultSet.getInt("ID"));
                turma.setIdProfessor(resultSet.getInt("ID_PROFESSOR"));
                turma.setIdAluno(resultSet.getInt("ID_ALUNO"));
                turma.setNotas(resultSet.getString("NOTAS"));
                turma.setSituacao(resultSet.getString("SITUACAO"));

                turmas.add(turma);
            }

            for (Turma t : turmas) {
                System.out.println("id : " + t.getId() + "\t Professor ID: " + t.getIdProfessor() +
                        "\t Aluno ID: " + t.getIdAluno() + "\t Notas: " + t.getNotas() + "\t Situação: "
                        + t.getSituacao());
            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return turmas;
    }

    @Override
    public int alterar(Turma turma) {
        String SQL_UPDATE = "UPDATE TURMA SET ID_PROFESSOR=?, ID_ALUNO=?, NOTAS=?, SITUACAO=? WHERE ID=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_UPDATE);

            preparedStatement.setInt(1, turma.getIdProfessor());
            preparedStatement.setInt(2, turma.getIdAluno());
            preparedStatement.setString(3, turma.getNotas());
            preparedStatement.setString(4, turma.getSituacao());
            preparedStatement.setInt(5, turma.getId());

            int row = preparedStatement.executeUpdate();

            System.out.println(row);
            return row;

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int remover(Turma turma) {
        String SQL_DELETE = "DELETE FROM TURMA WHERE ID=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_DELETE);

            preparedStatement.setInt(1, turma.getId());

            int row = preparedStatement.executeUpdate();

            System.out.println(row);
            return row;

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public void gerarEstaticas() {
        String sqlSituacao = "UPDATE turma SET situacao = CASE WHEN nota >= 7.0 THEN 'Aprovado' ELSE 'Reprovado' END ";
        try {
            PreparedStatement psmt = conexao.prepareStatement(sqlSituacao);
            psmt.executeUpdate();
            System.out.println("situacao criada com sucesso");
        } catch (Exception e) {
            System.out.println("Algum erro ocorreu.");
            e.printStackTrace();
        }
    }
}