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
import model.dao.FuncionarioDao;
import model.entidades.Funcionario;

public class FuncionarioDaoJDBC implements FuncionarioDao {

    private Connection conn = null;

    public FuncionarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Funcionario f) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO funcionarios (NOME) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(1, f.getNome());
            int linhasAfectadas = st.executeUpdate();

            if (linhasAfectadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    f.setIdFuncionario(id);
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
    public void actualizar(Funcionario f) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE funcionarios SET nome = ? WHERE idFuncionario =?"
            );
            st.setString(1, f.getNome());
            st.setInt(2, f.getIdFuncionario());
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
            st = conn.prepareStatement("DELETE FROM funcionarios WHERE idFuncionario = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Funcionario pesquisar(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT funcionarios.* FROM funcionarios WHERE funcionarios.idFuncionario = ? "
            );
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                return instantiateFuncionario(rs);
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
    public List<Funcionario> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT funcionarios.* FROM funcionarios"
            );

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

    //------------ Instanciar a classe -------------------
    private Funcionario instantiateFuncionario(ResultSet rs) throws SQLException {
        Funcionario f = new Funcionario();
        f.setIdFuncionario(rs.getInt("idFuncionario"));
        f.setNome(rs.getString("nome"));
        return f;
    }
}
