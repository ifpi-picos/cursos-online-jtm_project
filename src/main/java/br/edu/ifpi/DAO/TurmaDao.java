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
        String SQL_INSERT = "INSERT INTO TURMA (ID_CURSO, ID_ALUNO, NOTAS) VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);

            preparedStatement.setInt(1, turma.getIdCurso());
            preparedStatement.setInt(2, turma.getIdAluno());
            preparedStatement.setFloat(3, turma.getNota());

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
    public List<Turma> consultarTodos() {
        List<Turma> turmas = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT TURMA.ID, TURMA.ID_CURSO, TURMA.ID_ALUNO, TURMA.NOTAS, TURMA.SITUACAO, ALUNO.NOME "
                + "FROM TURMA INNER JOIN ALUNO ON TURMA.ID_ALUNO = ALUNO.ID";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Turma turma = new Turma(resultSet.getInt("ID_CURSO"), resultSet.getInt("ID_ALUNO"),
                        resultSet.getFloat("NOTAS"));
                turma.setIdAluno(resultSet.getInt("ID"));
                turma.setIdAluno(resultSet.getString("NOME"));

                turmas.add(turma);
            }

            for (Turma t : turmas) {
                System.out.println("ID do Curso: " + t.getIdCurso() + "\t Nome do Aluno: " + t.getIdAluno() +
                        "\t Notas: " + (t.getNota() != 0 ? t.getNota() : " "));
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
            preparedStatement.setInt(4, turma.getIdAluno());

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
    public int remover(Turma turma) {
        String SQL_DELETE = "DELETE FROM TURMA WHERE ID=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_DELETE);

            preparedStatement.setInt(1, turma.getIdAluno());

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
        String SQL_SELECT_NOTAS_ALUNO = "SELECT ID_CURSO, NOTAS FROM TURMA WHERE ID_ALUNO = ?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_SELECT_NOTAS_ALUNO);
            preparedStatement.setInt(1, idAluno);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("\n________________B O L E T I M________________");
            while (resultSet.next()) {
                int idCurso = resultSet.getInt("ID_CURSO");
                float notas = resultSet.getFloat("NOTAS");

                System.out.println("ID do Curso: " + idCurso + "\t Notas: " + notas);
            }
            System.out.println("\n_____________________________________________\n");
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.err.format("Estado SQL %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }
    }
}
