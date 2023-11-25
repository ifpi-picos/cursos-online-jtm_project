package br.edu.ifpi.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.enums.StatusCurso;

public class CursoDao implements Dao<Curso> {
    private Conexao conexao;

    public CursoDao(Conexao conexao) {
        this.conexao = conexao;
    }

    @Override
    public int cadastrar(Curso curso) {
        String SQL_INSERT = "INSERT INTO Curso (NOME, STATUS, CARGAHORARIA) VALUES(?,?,?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(SQL_INSERT);
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getStatus().name());
            stmt.setString(3, curso.getCargaHoraria());

            int rowsAffected = stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    curso.setId(generatedKeys.getInt(1));
                }
            }

            return rowsAffected;

        } catch (SQLException e) {
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
                String statusStr = resultSet.getString("STATUS");

                StatusCurso status = StatusCurso.fromString(statusStr);

                String cargaHoraria = resultSet.getString("CARGAHORARIA");

                Curso curso = new Curso(id, nome, status, cargaHoraria);
                cursos.add(curso);
            }
            System.out.println("________L I S T A   D E   C U R S O S________");
            for (Curso curso : cursos) {
                System.out.println("id: " + curso.getId() + "\tNome: " + curso.getNome() + "\tStatus: "
                        + curso.getStatus() + "\t Carga Horária: " + curso.getCargaHoraria());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }

    @Override
    public int alterar(Curso curso) {
        String SQL_UPDATE = "UPDATE Curso SET NOME=?, STATUS=?, CARGAHORARIA=? WHERE ID=?";

        try (PreparedStatement stmt = conexao.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getStatus().name());
            stmt.setString(3, curso.getCargaHoraria());
            stmt.setInt(4, curso.getId());

            return stmt.executeUpdate();

        } catch (SQLException e) {
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
            e.printStackTrace();
        }
        return 0;
    }
}
