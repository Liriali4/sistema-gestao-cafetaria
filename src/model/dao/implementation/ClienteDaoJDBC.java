package model.dao.implementation;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.dao.ClienteDao;
import model.entidades.Cliente;

public class ClienteDaoJDBC implements ClienteDao {

    private Connection conn = null;

    public ClienteDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Cliente c) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO clientes (NOME) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, c.getNome());
            int linhasAfectadas = st.executeUpdate();

            if (linhasAfectadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    c.setIdCliente(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void actualizar(Cliente c) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE clientes SET nome = ? WHERE idCliente =?"
            );
            st.setString(1, c.getNome());
            st.setInt(2, c.getIdCliente());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void remover(int id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM clientes WHERE idCliente = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Cliente pesquisar(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT clientes.* FROM clientes WHERE Clientes.idCliente = ? "
            );
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                return instantiateCliente(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Cliente> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT clientes.* FROM clientes"
            );

            rs = st.executeQuery();
            List<Cliente> list = new ArrayList<>();
            while (rs.next()) {
                Cliente c = instantiateCliente(rs);
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    //------------ Instanciar a classe -------------------
    private Cliente instantiateCliente(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        c.setIdCliente(rs.getInt("idCliente"));
        c.setNome(rs.getString("nome"));
        return c;
    }
}
