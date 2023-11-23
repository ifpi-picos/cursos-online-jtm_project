package br.edu.ifpi.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConexao() {
        Connection conexcao = null;
        try {
            conexcao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JTM", "postgres",
                    "billiebossanova");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexcao;
    }

    public PreparedStatement prepareStatement(String sQL_INSERT) {
        return null;
    }

}