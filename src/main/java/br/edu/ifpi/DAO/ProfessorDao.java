package br.edu.ifpi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entidades.Professor;

public class ProfessorDao implements Dao<Professor> {
    private Conexao conexao;

    public ProfessorDao(Conexao conexao) {
        this.conexao = conexao;
    }

    @Override
    public int cadastrar(Professor professor) {
        String SQL_INSERT = "INSERT INTO PROFESSOR (NOME, EMAIL) VALUES(?,?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_INSERT);

            preparedStatement.setString(1, professor.getNome());
            preparedStatement.setString(2, professor.getEmail());

            int row = preparedStatement.executeUpdate();

            System.out.println(row);
            return row;

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    @Override
    public List<Professor> consultarTodos() {
        List<Professor> professores = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT * FROM PROFESSOR";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Professor professor = new Professor(SQL_SELECT_ALL, 0, SQL_SELECT_ALL, null);
                professor.setId(resultSet.getInt("ID")); // Assuming there is an ID column
                professor.setNome(resultSet.getString("NOME"));
                professor.setEmail(resultSet.getString("EMAIL"));

                professores.add(professor);
            }

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return professores;
    }

    @Override
    public int alterar(Professor professor) {
        String SQL_UPDATE = "UPDATE PROFESSOR SET NOME=?, EMAIL=? WHERE ID=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, professor.getNome());
            preparedStatement.setString(2, professor.getEmail());
            preparedStatement.setInt(3, professor.getId()); // Assuming there is an ID column

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
    public int remover(Professor professor) {
        String SQL_DELETE = "DELETE FROM PROFESSOR WHERE ID=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(SQL_DELETE);

            preparedStatement.setInt(1, professor.getId()); // Assuming there is an ID column

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
}
