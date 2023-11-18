package br.edu.ifpi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifpi.entidades.Aluno;

public class AlunoDao implements Dao<Aluno> {
    private Conexao conexao;

    public AlunoDao(Conexao conexao){
        this.conexao = conexao;
    }

    @Override
    public int cadastrar(Aluno aluno) {
        String SQL_INSERT = "INSERT INTO ALUNOS (NOME, EMAIL) VALUES(?,?)";

        try{
            PreparedStatement stmt =  conexao.prepareStatement(SQL_INSERT);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
        }catch (SQLException e){
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Aluno> consultarTodos() {
        throw new UnsupportedOperationException("Unimplemented method 'consultarTodos'");
    }

    @Override
    public int alterar(Aluno entidade) {
        throw new UnsupportedOperationException("Unimplemented method 'alterar'");
    }

    @Override
    public int remover(Aluno entidade) {
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }
    
}