package br.edu.ifpi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifpi.entidades.Administrador;

class AdministradorDao implements Dao<Administrador>{
    private Connection conexao;
    @Override
    public int cadastrar(Administrador adiministrador) {
          String SQL_INSERT = "INSERT INTO Administrador (NOME, EMAIL) VALUES(?,?)";

        try{
            PreparedStatement stmt =  conexao.prepareStatement(SQL_INSERT);
            stmt.setString(1, adiministrador.getNome());
            stmt.setString(2, adiministrador.getEmail());
        }catch (SQLException e){
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;  
      }

    @Override
    public List<Administrador> consultarTodos() {
        throw new UnsupportedOperationException("Unimplemented method 'consultarTodos'");
    }

    @Override
    public int alterar(Administrador adiministrador) {
        try {
            String sqlUpdate = "UPDATE administrador SET NOME=?, EMAIL=? WHERE ID=?" + adiministrador;
            PreparedStatement stmt = conexao.prepareStatement(sqlUpdate);
            stmt.setString(1, adiministrador.getNome());
            stmt.setString(2, adiministrador.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int remover(Administrador entidade) {
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }
    