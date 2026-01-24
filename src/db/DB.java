package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

    //--------------- Conexão ------------------------------------
    private static Connection conn = null;

    private static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                String databaseName = props.getProperty("database");

                createDatabaseIfNotExists(url, databaseName, props);
                
                String urlWithDb = url + databaseName;
                conn = DriverManager.getConnection(urlWithDb, props);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    //--------------- Criar BD se não existir -------------------
    private static void createDatabaseIfNotExists(String baseUrl, String dbName, Properties props) {
        try (Connection serverConn = DriverManager.getConnection(baseUrl, props); Statement st = serverConn.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DbException("Erro ao criar a base de dados: " + e.getMessage());
        }
    }

    //--------------- Carregar as propriedades -------------------
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    //--------------- Terminar a conexão com a bd ----------------
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

}
