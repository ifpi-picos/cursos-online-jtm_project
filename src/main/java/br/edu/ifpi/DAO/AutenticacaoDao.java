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

    public Aluno autenticarAluno(String email) throws SQLException {
        String sql = "SELECT id, nome, email FROM aluno WHERE email = ?";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, email);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");

                    System.out.println("Aluno autenticado com sucesso!");
                    return new Aluno(id, nome, email, null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao autenticar aluno: " + e.getMessage());
        }

        System.out.println("Aluno não encontrado!");
        return null;
    }

    public Professor autenticarProfessor(String email) throws SQLException {
        String sql = "SELECT id, nome, email FROM professor WHERE email = ?";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, email);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");

                    System.out.println("Professor autenticado com sucesso!");
                    return new Professor(id, nome, email);
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