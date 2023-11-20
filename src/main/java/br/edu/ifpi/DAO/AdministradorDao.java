package br.edu.ifpi.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifpi.entidades.Administrador;

public class AdministradorDao implements Dao<Administrador> {
    private Conexao conexao;

    public AdministradorDao(Conexao conexao) {
        this.conexao = conexao;
    }

    @Override
    public int cadastrar(Administrador administrador) {
        String SQL_INSERT = "INSERT INTO Administrador (NOME, EMAIL) VALUES(?,?)";

        try (PreparedStatement stmt = conexao.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, administrador.getNome());
            stmt.setString(2, administrador.getEmail());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Administrador> consultarTodos() {
        List<Administrador> administradores = new ArrayList<>();
        String SQL_SELECT_ALL = "SELECT * FROM Administrador";

        try (PreparedStatement stmt = conexao.prepareStatement(SQL_SELECT_ALL);
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String nome = resultSet.getString("NOME");
                String email = resultSet.getString("EMAIL");

                Administrador administrador = new Administrador(nome, id, email);
                administradores.add(administrador);
            }

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }

        return administradores;
    }

    @Override
    public int alterar(Administrador administrador) {
        String SQL_UPDATE = "UPDATE Administrador SET NOME=?, EMAIL=? WHERE ID=?";

        try (PreparedStatement stmt = conexao.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, administrador.getNome());
            stmt.setString(2, administrador.getEmail());
            stmt.setInt(3, administrador.getId());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int remover(Administrador administrador) {
        String SQL_DELETE = "DELETE FROM Administrador WHERE ID=?";

        try (PreparedStatement stmt = conexao.prepareStatement(SQL_DELETE)) {
            stmt.setInt(1, administrador.getId());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.format("SQL State %s\n%s", e.getSQLState(), e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
}
