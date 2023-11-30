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
        // You may initialize the connection if needed
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

    public void cadastrarEmCurso(int idAluno, int idCurso) {
        String SQL_INSERT_MATRICULA = "INSERT INTO MATRICULA (ID_ALUNO, ID_CURSO) VALUES (?, ?)";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(SQL_INSERT_MATRICULA)) {
            stmt.setInt(1, idAluno);
            stmt.setInt(2, idCurso);

            System.out.println("_____________________________________________\n");
            System.out.println("  M A T R Í C U L A   R E A L I Z A D A   C O M \n              S U C E S S O !");
            System.out.println("_____________________________________________\n");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void desmatricularEmCurso(int idAluno, int idCurso) {
        String SQL_DELETE_MATRICULA = "DELETE FROM MATRICULA WHERE ID_ALUNO=? AND ID_CURSO=?";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(SQL_DELETE_MATRICULA)) {
            stmt.setInt(1, idAluno);
            stmt.setInt(2, idCurso);

            System.out.println("_____________________________________________\n");
            System.out.println("D E S M A T R Í C U L A   R E A L I Z A D A   C O M \n              S U C E S S O !");
            System.out.println("_____________________________________________\n");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        }
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

    public void exibirCursosMatriculados(int idAluno) {
        String SQL_SELECT_MATRICULAS = "SELECT C.ID, C.NOME FROM CURSO C " +
                                       "JOIN CURSOALUNO CA ON C.ID = CA.ID_CURSO " +
                                       "WHERE CA.ID_ALUNO = ?";
    
        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(SQL_SELECT_MATRICULAS)) {
            stmt.setInt(1, idAluno);
    
            try (ResultSet resultSet = stmt.executeQuery()) {
                System.out.println("\n______C U R S O S   M A T R I C U L A D O S______");
                while (resultSet.next()) {
                    int idCurso = resultSet.getInt("ID");
                    String nomeCurso = resultSet.getString("NOME");
    
                    System.out.println("ID: " + idCurso + "\t Nome do Curso: " + nomeCurso);
                }
                System.out.println("_____________________________________________\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
