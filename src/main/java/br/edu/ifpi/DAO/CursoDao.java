package br.edu.ifpi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifpi.entidades.Curso;

class CursoDao implements Dao<Curso>{
    private Connection conexao;
    @Override
    public int cadastrar(Curso curso) {
          String SQL_INSERT = "INSERT INTO Curso (NOME, STATUS, CARGAHORARIA) VALUES(?,?,?)";

        try{
            PreparedStatement stmt =  conexao.prepareStatement(SQL_INSERT);
            stmt.setString(1, curso.getNome());
            stmt.setString(2, (String) curso.getStatus());
            stmt.setInt(3, curso.getCargaHoraria());
        }catch (SQLException e){
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;  
      }

    @Override
    public List<Curso> consultarTodos() {
        throw new UnsupportedOperationException("Unimplemented method 'consultarTodos'");
    }

    @Override
    public int alterar(Curso curso) {
        try {
            String sqlUpdate = "UPDATE cursos SET NOME=?, STATUS=?, CARGAHORARIA=? WHERE ID=?" + curso;
            PreparedStatement stmt = conexao.prepareStatement(sqlUpdate);
            stmt.setString(1, curso.getNome());
            stmt.setString(2, (String) curso.getStatus());
            stmt.setInt(3, curso.getCargaHoraria());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int remover(Curso entidade) {
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }
}
    
