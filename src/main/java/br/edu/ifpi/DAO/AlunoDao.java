package br.edu.ifpi.DAO;

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
        String SQL_INSERT = "INSERT INTO ALUNO (NOME, EMAIL, CURSO) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(SQL_INSERT)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getCurso());

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
                String curso = resultSet.getString("CURSO");

                Aluno aluno = new Aluno(id, nome, email, curso);
                alunos.add(aluno);
            }

            for (Aluno p : alunos) {
                System.out.println(
                        "ID: " + p.getId() + "\tNome: " + p.getNome() + "\t" + p.getEmail() + "\t" + p.getCurso());
            }
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
        String SQL_UPDATE = "UPDATE ALUNO SET NOME=?, EMAIL=?, CURSO=? WHERE ID=?";

        try (PreparedStatement stmt = Conexao.getConexao().prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getCurso());
            stmt.setInt(4, aluno.getId());

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

            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
