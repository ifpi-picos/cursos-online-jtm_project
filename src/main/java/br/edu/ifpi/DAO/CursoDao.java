package br.edu.ifpi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entidades.Curso;

public class CursoDao implements Dao<Curso> {
    private Connection conexao;

    @Override
    public int cadastrar(Curso curso) {
        String SQL_INSERT = "INSERT INTO Curso (NOME, STATUS, CARGAHORARIA) VALUES(?,?,?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(SQL_INSERT);
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getStatus());
            stmt.setInt(3, curso.getCargaHoraria());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Curso> consultarTodos() {
        List<Curso> cursos = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT * FROM Curso";

        try (PreparedStatement stmt = conexao.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nome = resultSet.getString("NOME");
                String status = resultSet.getString("STATUS");
                int cargaHoraria = resultSet.getInt("CARGAHORARIA");

                Curso curso = new Curso(id, nome, status, cargaHoraria);
                cursos.add(curso);
            }

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }

        return cursos;
    }

    @Override
    public int alterar(Curso curso) {
        String SQL_UPDATE = "UPDATE Curso SET NOME=?, STATUS=?, CARGAHORARIA=? WHERE ID=?";

        try (PreparedStatement stmt = conexao.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getStatus());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setInt(4, curso.getId());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int remover(Curso curso) {
        String SQL_DELETE = "DELETE FROM Curso WHERE ID=?";

        try (PreparedStatement stmt = conexao.prepareStatement(SQL_DELETE)) {
            stmt.setInt(1, curso.getId());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
}
