package br.edu.ifpi.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConexao() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(
                    "jdbc:postgresql://db.vgxfsesnppqjzdwgolcl.supabase.co:5432/postgres", "postgres",
                    "billiebossanova");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexao;
    }

    public PreparedStatement prepareStatement(String sQL_INSERT) {
        try {
            return getConexao().prepareStatement(sQL_INSERT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
