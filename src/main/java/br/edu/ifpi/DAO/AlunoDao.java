package br.edu.ifpi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entidades.Aluno;

public class AlunoDao implements Dao<Aluno> {
    private Conexao conexao;

    public AlunoDao(Conexao conexao) {
        this.conexao = conexao;
    }

    @Override
    public int cadastrar(Aluno aluno) {
        String SQL_INSERT = "INSERT INTO ALUNOS (NOME, EMAIL) VALUES(?,?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(SQL_INSERT);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Aluno> consultarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT * FROM ALUNOS";

        try {
            PreparedStatement stmt = conexao.prepareStatement(SQL_SELECT_ALL);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                String nome = resultSet.getString("NOME");
                String email = resultSet.getString("EMAIL");

                Aluno aluno = new Aluno(nome, email);
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return alunos;
    }

    @Override
    public int alterar(Aluno aluno) {
        String SQL_UPDATE = "UPDATE ALUNOS SET NOME=?, EMAIL=? WHERE ID=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(SQL_UPDATE);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setInt(3, aluno.getId());

            // Execute the update statement
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int remover(Aluno aluno) {
        String SQL_DELETE = "DELETE FROM ALUNOS WHERE ID=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(SQL_DELETE);
            stmt.setInt(1, aluno.getId());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
