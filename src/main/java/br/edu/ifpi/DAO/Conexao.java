package br.edu.ifpi.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexacao {

    public static Connection getConexao() {
        Connection conexacao = null;
        try {
            conexacao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/JTM", "postgres",
                    "billiebossanova");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexacao;
    }
}