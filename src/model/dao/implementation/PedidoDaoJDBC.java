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
import model.dao.PedidoDao;
import model.entidades.Pedido;

public class PedidoDaoJDBC implements PedidoDao {

    private Connection conn = null;

    public PedidoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Pedido p) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO pedidos (data, total, idCliente, idFuncionario) "
                    + "VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setDate(1, new java.sql.Date(p.getData().getTime()));
            st.setFloat(2, p.getValorTotal());
            st.setInt(3, p.getCliente());
            st.setInt(4, p.getFuncionario());
            int linhasAfectadas = st.executeUpdate();

            if (linhasAfectadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    p.setIdPedido(id);
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
    public void actualizar(Pedido p) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE pedidos SET data = ?, total = ? WHERE idPedido = ?"
            );
            st.setDate(1, new java.sql.Date(p.getData().getTime()));
            st.setFloat(2, p.getValorTotal());
            st.setInt(3, p.getIdPedido());
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
            st = conn.prepareStatement("DELETE FROM pedidos WHERE idPedido = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Pedido pesquisar(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT pedidos.* FROM pedidos WHERE pedidos.idPedido = ? "
            );
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                return instantiatePedido(rs);
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
    public List<Pedido> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT pedidos.* FROM pedidos"
            );

            rs = st.executeQuery();
            List<Pedido> list = new ArrayList<>();
            while (rs.next()) {
                Pedido p = instantiatePedido(rs);
                list.add(p);
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
    private Pedido instantiatePedido(ResultSet rs) throws SQLException {
        Pedido p = new Pedido();
        p.setIdPedido(rs.getInt("idPedido"));
        p.setData(rs.getDate("data"));
        p.setValorTotal(rs.getFloat("total"));
        p.setCliente(rs.getInt("idCliente"));
        p.setFuncionario(rs.getInt("idFuncionario"));
        return p;
    }
}
