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
            System.out.println("_____________________________________________\n");
            System.out.println("  C A D A S T R O  R E A L I Z A D O  C O M \n              S U C E S S O !");
            System.out.println("_____________________________________________\n");
            return row;

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
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
                Professor professor = new Professor(SQL_SELECT_ALL, 0, SQL_SELECT_ALL, 0);
                professor.setId(resultSet.getInt("ID"));
                professor.setNome(resultSet.getString("NOME"));
                professor.setEmail(resultSet.getString("EMAIL"));
                professor.setId_curso(resultSet.getInt("ID_CURSO"));
                

                professores.add(professor);
            }
            System.out.println("___L I S T A   D E   P R O F E S S O R E S___");
            for (Professor p : professores) {
                System.out.println("id : " + p.getId() + "\t Nome  :" + p.getNome() + "\t Email:" + p.getEmail()
                        + "\t Curso: " + (p.getId_curso() != 0 ? p.getId_curso() : ""));
            }
            resultSet.close();
            preparedStatement.close();

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

            preparedStatement.setInt(1, professor.getId());

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

    public int AssociarProfessorCurso(int idProfessor, int idCurso) {
        String SQL_QUERY_PROFESSOR = "SELECT * FROM professor WHERE id = ?";
        String SQL_QUERY_CURSO = "SELECT * FROM curso WHERE id = ?";
        String SQL_UPDATE = "UPDATE professor SET id_curso=? WHERE id=?";

        try (
                PreparedStatement professorStatement = conexao.prepareStatement(SQL_QUERY_PROFESSOR);
                PreparedStatement cursoStatement = conexao.prepareStatement(SQL_QUERY_CURSO);
                PreparedStatement updateStatement = conexao.prepareStatement(SQL_UPDATE)) {
            professorStatement.setInt(1, idProfessor);
            ResultSet professorResult = professorStatement.executeQuery();

            cursoStatement.setInt(1, idCurso);
            ResultSet cursoResult = cursoStatement.executeQuery();

            if (professorResult.next() && cursoResult.next()) {
                updateStatement.setInt(1, idCurso);
                updateStatement.setInt(2, idProfessor);

                int rowsUpdated = updateStatement.executeUpdate();
                System.out.println("Linhas atualizadas: " + rowsUpdated);

                return rowsUpdated;
            } else {
                System.out.println("Registro de professor ou curso não encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void vizualizarPerfilProfessor(String email) {
        String sql = "SELECT id, nome, email, id_curso FROM professor WHERE email = ?";

        try (Connection connection = Conexao.getConexao();
                PreparedStatement stm = connection.prepareStatement(sql)) {

            stm.setString(1, email);

            try (ResultSet resultSet = stm.executeQuery()) {
                System.out.println("\n____P E R F I L   D O   P R O F E S S O R____");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nome = resultSet.getString("nome");
                    int idCurso = resultSet.getInt("id_curso");

                    System.out.println("\nId: " + id + "\nNome: " + nome + "\nEmail: " + email + "\nID do Curso: "
                            + idCurso);
                }
                System.out.println("_____________________________________________\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}