package model.dao;

import java.util.Date;
import java.util.List;
import model.entidades.Cliente;
import model.entidades.Funcionario;
import model.entidades.Pedido;
import model.entidades.Produto;

public interface ConsultaDao {

    List<Cliente> pesquisarClientesPorNome(String nome);

    List<Funcionario> pesquisarFuncionariosPorNome(String nome);

    List<Produto> pesquisarProdutosPorNome(String nome);

    List<Produto> pesquisarProdutosMaisCaro(Float preco);

    List<Produto> pesquisarProdutosMaisBarato(Float preco);

    List<Pedido> pesquisarPedidosPorData(Date data);

    List<Pedido> pesquisarPedidosDoCliente(int idCliente);

    List<Pedido> pesquisarPedidosAtendidosPeloFuncionario(int idFuncionario);

}
