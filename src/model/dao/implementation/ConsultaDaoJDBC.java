package model.dao.implementation;

import db.DB;
import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.dao.ConsultaDao;
import model.entidades.Cliente;
import model.entidades.Funcionario;
import model.entidades.Pedido;
import model.entidades.Produto;

public class ConsultaDaoJDBC implements ConsultaDao {

    private Connection conn = null;

    public ConsultaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    //---------------------------- CLIENTES ------------------------------------
    @Override
    public List<Cliente> pesquisarClientesPorNome(String nome) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT clientes.* FROM clientes WHERE clientes.nome LIKE ? "
            );
            st.setString(1, "%" + nome + "%");
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

    //---------------------------- FUNCIONARIOS ------------------------------------
    @Override
    public List<Funcionario> pesquisarFuncionariosPorNome(String nome) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT funcionarios.* FROM funcionarios WHERE funcionarios.nome LIKE ? "
            );
            st.setString(1, "%" + nome + "%");
            rs = st.executeQuery();
            List<Funcionario> list = new ArrayList<>();
            while (rs.next()) {
                Funcionario f = instantiateFuncionario(rs);
                list.add(f);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    //---------------------------- PRODUTOS ------------------------------------
    @Override
    public List<Produto> pesquisarProdutosPorNome(String nome) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT produtos.* FROM produtos WHERE produtos.nome LIKE ? "
            );
            st.setString(1, "%" + nome + "%");
            rs = st.executeQuery();
            List<Produto> list = new ArrayList<>();
            while (rs.next()) {
                Produto p = instantiateProduto(rs);
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

    @Override
    public List<Produto> pesquisarProdutosMaisCaro(Float preco) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT produtos.* FROM produtos WHERE produtos.preco >= ? "
            );
            st.setFloat(1, preco);
            rs = st.executeQuery();
            List<Produto> list = new ArrayList<>();
            while (rs.next()) {
                Produto p = instantiateProduto(rs);
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

    @Override
    public List<Produto> pesquisarProdutosMaisBarato(Float preco) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT produtos.* FROM produtos WHERE produtos.preco <= ? "
            );
            st.setFloat(1, preco);
            rs = st.executeQuery();
            List<Produto> list = new ArrayList<>();
            while (rs.next()) {
                Produto p = instantiateProduto(rs);
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

    //---------------------------- PEDIDOS ------------------------------------
    @Override
    public List<Pedido> pesquisarPedidosPorData(Date data) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT pedidos.* FROM pedidos WHERE pedidos.data = ? "
            );
            st.setDate(1, new java.sql.Date(data.getTime()));
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

    @Override
    public List<Pedido> pesquisarPedidosDoCliente(int idCliente) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT pedidos.* FROM pedidos WHERE pedidos.idCliente = ? "
            );
            st.setInt(1, idCliente);
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

    @Override
    public List<Pedido> pesquisarPedidosAtendidosPeloFuncionario(int idFuncionario) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT pedidos.* FROM pedidos WHERE pedidos.idFuncionario = ? "
            );
            st.setInt(1, idFuncionario);
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

    private Cliente instantiateCliente(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        c.setIdCliente(rs.getInt("idCliente"));
        c.setNome(rs.getString("nome"));
        return c;
    }

    private Funcionario instantiateFuncionario(ResultSet rs) throws SQLException {
        Funcionario f = new Funcionario();
        f.setIdFuncionario(rs.getInt("idFuncionario"));
        f.setNome(rs.getString("nome"));
        return f;
    }

    private Produto instantiateProduto(ResultSet rs) throws SQLException {
        Produto p = new Produto();
        p.setIdProduto(rs.getInt("idProduto"));
        p.setNome(rs.getString("nome"));
        p.setPreco(rs.getFloat("preco"));

        return p;
    }
}
