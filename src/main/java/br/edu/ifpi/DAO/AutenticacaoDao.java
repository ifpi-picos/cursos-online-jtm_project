package br.edu.ifpi.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Professor;

public class AutenticacaoDao {
    private Conexao conexao;

    public AutenticacaoDao(Conexao conexao) {
        this.conexao = conexao;
    }

    public Aluno autenticarAluno(String email, int id) throws SQLException {
        String sql = "SELECT id, nome, email FROM aluno WHERE email = ? AND id = ?";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, email);
            stm.setInt(2, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    resultSet.getString("nome");

                    System.out.println("Aluno autenticado com sucesso!");
                    return new Aluno(email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao autenticar aluno: " + e.getMessage());
        }

        System.out.println("Aluno não encontrado!");
        return null;
    }

    public Professor autenticarProfessor(String email, int id) throws SQLException {
        String sql = "SELECT id, nome, email FROM professor WHERE email = ? AND id = ?";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, email);
            stm.setInt(2, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    int professorId = resultSet.getInt("id");
                    String professorNome = resultSet.getString("nome");

                    System.out.println("Professor autenticado com sucesso!");
                    return new Professor(professorId, professorNome, email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao autenticar professor: " + e.getMessage());
        }

        System.out.println("Professor não encontrado!");
        return null;
    }
}
