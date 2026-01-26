package model.dao;

import db.DB;
import model.dao.implementation.ClienteDaoJDBC;
import model.dao.implementation.ConsultaDaoJDBC;
import model.dao.implementation.FuncionarioDaoJDBC;
import model.dao.implementation.ItemPedidoDaoJDBC;
import model.dao.implementation.PedidoDaoJDBC;
import model.dao.implementation.ProdutoDaoJDBC;

public class DAO {

    public static FuncionarioDao criarFuncionarioDao() {
        return new FuncionarioDaoJDBC(DB.getConnection());
    }

    public static ClienteDao criarClienteDao() {
        return new ClienteDaoJDBC(DB.getConnection());
    }

    public static ProdutoDao criarProdutoDao() {
        return new ProdutoDaoJDBC(DB.getConnection());
    }

    public static PedidoDao criarPedidoDao() {
        return new PedidoDaoJDBC(DB.getConnection());
    }

    public static ItemPedidoDao criarItemPedidoDao() {
        return new ItemPedidoDaoJDBC(DB.getConnection());
    }
    
    public static ConsultaDao criarConsultaDao(){
        return new ConsultaDaoJDBC(DB.getConnection());
    }
}
