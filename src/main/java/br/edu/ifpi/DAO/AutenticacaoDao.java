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

                    System.out.println("_____________________________________________\n");
                    System.out.println("  A L U N O   A U T E N T I C A D O   C O M\n                S U C E S S O !");
                    System.out.println("_____________________________________________\n");
                    return new Aluno(id, email, null);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao autenticar aluno: " + e.getMessage());
        }
        return null;
    }

    public Professor autenticarProfessor(String email, int id) throws SQLException {
        String sql = "SELECT * FROM professor WHERE email = ? AND id = ?";

        try (PreparedStatement stm = conexao.prepareStatement(sql)) {
            stm.setString(1, email);
            stm.setInt(2, id);
            try (ResultSet resultSet = stm.executeQuery()) {
                if (resultSet.next()) {
                    int professorId = resultSet.getInt("id");
                    String professorNome = resultSet.getString("nome");
                    int cursoID = resultSet.getInt("id_curso");

                    System.out.println("_____________________________________________\n");
                    System.out.println("  P R O F E S S O R   A U T E N T I C A D O\n           C O M   S U C E S S O !");
                    System.out.println("_____________________________________________\n");
                    return new Professor(professorNome, professorId, email, cursoID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao autenticar professor: " + e.getMessage());
        }
        return null;
    }
}
