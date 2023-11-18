package br.edu.ifpi.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.edu.ifpi.entidades.Administrador;

class AdministradorDao implements Dao<Administrador> {
    private Connection conexao;

    public AdministradorDao(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public int cadastrar(Administrador administrador) {
        String SQL_INSERT = "INSERT INTO Administrador (NOME, EMAIL) VALUES(?,?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(SQL_INSERT);
            stmt.setString(1, administrador.getNome());
            stmt.setString(2, administrador.getEmail());

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Administrador> consultarTodos() {
        throw new UnsupportedOperationException("Método 'consultarTodos' não implementado");
    }

    @Override
    public int alterar(Administrador administrador) {
        try {

            String sqlUpdate = "UPDATE Administrador SET NOME=?, EMAIL=? WHERE ID=?";
            PreparedStatement stmt = conexao.prepareStatement(sqlUpdate);
            stmt.setString(1, administrador.getNome());
            stmt.setString(2, administrador.getEmail());

            stmt.executeUpdate();

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int remover(Administrador entidade) {
        throw new UnsupportedOperationException("Método 'remover' não implementado");
    }
}
