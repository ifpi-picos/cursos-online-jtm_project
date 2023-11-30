package br.edu.ifpi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entidades.Aluno;

public class AlunoDao implements Dao<Aluno> {
    public AlunoDao(Conexao conexao) {
    }

    @Override
    public int cadastrar(Aluno aluno) {
        String SQL_INSERT = "INSERT INTO ALUNO (NOME, EMAIL) VALUES (?, ?)";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(SQL_INSERT)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());

            System.out.println("_____________________________________________\n");
            System.out.println("  C A D A S T R O  R E A L I Z A D O  C O M \n              S U C E S S O !");
            System.out.println("_____________________________________________\n");
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        }
        return 0;
    }

    @Override
    public List<Aluno> consultarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT * FROM ALUNO";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(SQL_SELECT_ALL);
                ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nome = resultSet.getString("NOME");
                String email = resultSet.getString("EMAIL");

                Aluno aluno = new Aluno(id, nome, email);
                alunos.add(aluno);
            }
            System.out.println("________L I S T A   D E   A L U N O S________");
            for (Aluno p : alunos) {
                System.out.println(
                        "ID: " + p.getId() + "\t Nome: " + p.getNome() + "\t Email:" + p.getEmail());
            }
            System.out.println("_____________________________________________\n");
            resultSet.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return alunos;
    }

    @Override
    public int alterar(Aluno aluno) {
        String SQL_UPDATE = "UPDATE ALUNO SET NOME=?, EMAIL=? WHERE ID=?";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setInt(3, aluno.getId());

            System.out.println("_____________________________________________\n");
            System.out.println("    D A D O S   A L T E R A D O S   C O M\n              S U C E S S O !");
            System.out.println("_____________________________________________\n");
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
        String SQL_DELETE = "DELETE FROM ALUNO WHERE ID=?";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(SQL_DELETE)) {
            stmt.setInt(1, aluno.getId());

            System.out.println("_____________________________________________\n");
            System.out.println("     A L U N O   E X C L U Í D O   C O M\n              S U C E S S O !");
            System.out.println("_____________________________________________\n");
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void vizualizarPerfilAluno(String email) {
        String sql = "SELECT id, nome, email FROM aluno WHERE email = ?";

        try (Connection connection = Conexao.getConexao();
                PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, email);
            try (ResultSet resultSet = stm.executeQuery()) {
                System.out.println("\n________P E R F I L   D O   A L U N O________");
                while (resultSet.next()) {
                    int idAluno = resultSet.getInt("id");
                    String nomeAluno = resultSet.getString("nome");
                    String emailAluno = resultSet.getString("email");

                    System.out.println("\nId: " + idAluno + "\nNome: " + nomeAluno + "\nEmail: " + emailAluno);
                }
                System.out.println("_____________________________________________\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) {
    }
}
