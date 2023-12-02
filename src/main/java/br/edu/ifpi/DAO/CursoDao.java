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
            System.out.println("_____________________________________________\n");
            System.out.println("  C A D A S T R O  R E A L I Z A D O  C O M \n              S U C E S S O !");
            System.out.println("_____________________________________________\n");
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

                // Aqui, passamos apenas o ID do curso para o método
                // exibirQuantidadeAlunosMatriculados
                int quantidadeAlunos = exibirQuantidadeAlunosMatriculados(id);

                System.out.println("id: " + curso.getId() + "\tNome: " + curso.getNome() + "\tStatus: "
                        + curso.getStatus() + "\tCarga Horária: " + curso.getCargaHoraria() + "\tAlunos cursando: "
                        + quantidadeAlunos);
            }

            System.out.println("________L I S T A   D E   C U R S O S________");
            System.out.println("_____________________________________________\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }

    public int exibirQuantidadeAlunosMatriculados(int idCurso) {
        String SQL_COUNT_ENROLLMENTS = "SELECT COUNT(*) AS QUANTIDADE_ALUNOS FROM cursoaluno WHERE id_curso = ?";
        String SQL_GET_CURSO_NAME = "SELECT NOME FROM Curso WHERE ID = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(SQL_COUNT_ENROLLMENTS)) {
            stmt.setInt(1, idCurso);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    int quantidadeAlunos = resultSet.getInt("QUANTIDADE_ALUNOS");

                    try (PreparedStatement stmtCurso = conexao.prepareStatement(SQL_GET_CURSO_NAME)) {
                        stmtCurso.setInt(1, idCurso);

                        try (ResultSet resultSetCurso = stmtCurso.executeQuery()) {
                            if (resultSetCurso.next()) {
                                String nomeCurso = resultSetCurso.getString("NOME");
                                System.out.println("_____________________________________________\n");
                                System.out.println("   Número de Alunos Matriculados no Curso '" + nomeCurso + "': "
                                        + quantidadeAlunos);
                                System.out.println("_____________________________________________\n");
                                return quantidadeAlunos;
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int alterar(Curso curso) {
        String SQL_UPDATE = "UPDATE Curso SET NOME=?, STATUS=?, CARGAHORARIA=? WHERE ID=?";

        try (PreparedStatement stmt = conexao.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getStatus().name());
            stmt.setString(3, curso.getCargaHoraria());
            stmt.setInt(4, curso.getId());

            System.out.println("_____________________________________________\n");
            System.out.println("    D A D O S   A L T E R A D O S   C O M\n              S U C E S S O !");
            System.out.println("_____________________________________________\n");
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Curso> consultarDesempenhoCursos() {
        List<Curso> cursos = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT * FROM Curso";
        CursoAlunoDao cursoalunoDao = new CursoAlunoDao(conexao);

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

            System.out.println("__D E S E M P E N H O   D O S   C U R S O S__");
            for (Curso curso : cursos) {
                System.out.println("Id do Curso: " + curso.getId() + "\tMédia Geral: "
                        + cursoalunoDao.exibirNotaMediaGeralAlunos(curso) + "\tPorcentagem de Aprovações: "
                        + cursoalunoDao.exibirPorcentagemAprovados(curso) + "\tPorcentagem de Reprovações: " + cursoalunoDao.calcularPorcentagemReprovados(curso));
            }
            System.out.println("_____________________________________________\n");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }

    @Override
    public int remover(Curso curso) {
        String SQL_DELETE = "DELETE FROM Curso WHERE ID=?";

        try (PreparedStatement stmt = conexao.prepareStatement(SQL_DELETE)) {
            stmt.setInt(1, curso.getId());
            System.out.println("_____________________________________________\n");
            System.out.println("     C U R S O   E X C L U Í D O   C O M\n              S U C E S S O !");
            System.out.println("_____________________________________________\n");
            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
