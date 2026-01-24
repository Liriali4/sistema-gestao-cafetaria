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
import model.dao.ItemPedidoDao;
import model.entidades.ItemPedido;

public class ItemPedidoDaoJDBC implements ItemPedidoDao {

    private Connection conn = null;

    public ItemPedidoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(ItemPedido ip) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO itensPedido (idPedido, idProduto, quantidade, valor) "
                    + "VALUES (?, ?, ?, ?)"
            );
            st.setInt(1, ip.getPedido());
            st.setInt(2, ip.getProduto());
            st.setInt(3, ip.getQuantidade());
            st.setFloat(4, ip.getValor());
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void actualizar(ItemPedido ip) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE itensPedido SET quantidade = ? , valor = ?  "
                    + "WHERE idPedido = ? AND idProduto = ? "
            );
            st.setInt(1, ip.getQuantidade());
            st.setFloat(2, ip.getValor());
            st.setInt(3, ip.getPedido());
            st.setInt(4, ip.getProduto());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void remover(int idPedido, int idProduto) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM itensPedido "
                    + "WHERE idPedido = ? AND idProduto = ? ");
            st.setInt(1, idPedido);
            st.setInt(2, idProduto);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public ItemPedido pesquisar(int idPedido, int idProduto) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT itensPedido.* FROM itensPedido "
                    + "WHERE idPedido = ? AND idProduto = ? "
            );
            st.setInt(1, idPedido);
            st.setInt(2, idProduto);
            rs = st.executeQuery();
            if (rs.next()) {
                return instantiateItemPedido(rs);
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
    public List<ItemPedido> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT itensPedido.* FROM itensPedido"
            );

            rs = st.executeQuery();
            List<ItemPedido> list = new ArrayList<>();
            while (rs.next()) {
                ItemPedido ip = instantiateItemPedido(rs);
                list.add(ip);
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
    private ItemPedido instantiateItemPedido(ResultSet rs) throws SQLException {
        ItemPedido f = new ItemPedido();
        f.setPedido(rs.getInt("idPedido"));
        f.setProduto(rs.getInt("idProduto"));
        f.setQuantidade(rs.getInt("quantidade"));
        f.setValor(rs.getFloat("valor"));
        return f;
    }
}
