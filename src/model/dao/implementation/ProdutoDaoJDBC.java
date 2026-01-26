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
import model.dao.ProdutoDao;
import model.entidades.Produto;

public class ProdutoDaoJDBC implements ProdutoDao {

    private Connection conn = null;

    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Produto p) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO produtos (NOME, PRECO) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, p.getNome());
            st.setFloat(2, p.getPreco());

            int linhasAfectadas = st.executeUpdate();

            if (linhasAfectadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    p.setIdProduto(id);
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
    public void actualizar(Produto p) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE produtos SET nome = ?, preco = ? WHERE idProduto =?"
            );
            st.setString(1, p.getNome());
            st.setFloat(2, p.getPreco());
            st.setInt(3, p.getIdProduto());

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
            st = conn.prepareStatement("DELETE FROM produtos WHERE idProduto = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Produto pesquisar(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT produtos.* FROM produtos WHERE produtos.idProduto = ? "
            );
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                return instantiateProduto(rs);
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
    public List<Produto> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT produtos.* FROM produtos"
            );

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

    //------------ Instanciar a classe -------------------
    private Produto instantiateProduto(ResultSet rs) throws SQLException {
        Produto p = new Produto();
        p.setIdProduto(rs.getInt("idProduto"));
        p.setNome(rs.getString("nome"));
        p.setPreco(rs.getFloat("preco"));

        return p;
    }
}
