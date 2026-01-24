package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBCreation {

    //--------------- Criar BD se não existir -------------------
    public static void createDatabaseIfNotExists() {

        Properties props = loadProperties();
        String baseUrl = props.getProperty("dburl");
        String dbName = props.getProperty("database");

        try (Connection serverConn = DriverManager.getConnection(baseUrl, props); Statement st = serverConn.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;
            st.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DbException("[Erro ao criar a base de dados]: " + e.getMessage());
        }
    }

    //--------------- Criar tabelas se não existirem -------------------
    public static void createTablesIfNotExists() {

        Properties props = loadProperties();
        String baseUrl = props.getProperty("dburl");
        String dbName = props.getProperty("database");

        String urlWithDb = baseUrl + dbName;

        try (Connection dbConn = DriverManager.getConnection(urlWithDb, props); Statement st = dbConn.createStatement()) {

            String sqlCliente = ("CREATE TABLE IF NOT EXISTS clientes ( "
                    + "idCliente INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nome VARCHAR(50) NOT NULL "
                    + ")");

            String sqlFuncionario = ("CREATE TABLE IF NOT EXISTS funcionarios ( "
                    + "idFuncionario INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nome VARCHAR(50) NOT NULL "
                    + ")");

            String sqlProduto = ("CREATE TABLE IF NOT EXISTS produtos ( "
                    + "idProduto INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nome VARCHAR(50) NOT NULL, "
                    + "preco DECIMAL(10,2) NOT NULL "
                    + ")");

            String sqlPedido = ("CREATE TABLE IF NOT EXISTS pedidos ( "
                    + "idPedido INT AUTO_INCREMENT PRIMARY KEY, "
                    + "data DATE NOT NULL, "
                    + "total DECIMAL(10,2) NOT NULL, "
                    + "idCliente INT NOT NULL, "
                    + "idFuncionario INT NOT NULL, "
                    + "FOREIGN KEY (idCliente) REFERENCES CLIENTES(idCliente), "
                    + "FOREIGN KEY (idFuncionario) REFERENCES FUNCIONARIOS(idFuncionario) "
                    + ")");

            String sqlItemPedido = ("CREATE TABLE IF NOT EXISTS itensPedido ("
                    + "idPedido INT NOT NULL, "
                    + "idProduto INT NOT NULL, "
                    + "quantidade  INT NOT NULL, "
                    + "valor DECIMAL(10,2) NOT NULL, "
                    + "PRIMARY KEY (idPedido, idProduto), "
                    + "FOREIGN KEY (idPedido) REFERENCES PEDIDOS(idPedido), "
                    + "FOREIGN KEY (idProduto) REFERENCES PRODUTOS(idProduto)"
                    + ")");

            st.executeUpdate(sqlCliente);
            st.executeUpdate(sqlFuncionario);
            st.executeUpdate(sqlProduto);
            st.executeUpdate(sqlPedido);
            st.executeUpdate(sqlItemPedido);

        } catch (SQLException e) {
            throw new DbException("[Erro ao criar as tabelas]: " + e.getMessage());
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

}
