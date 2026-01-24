package model.dao;

import java.util.List;
import model.entidades.Produto;

public interface ProdutoDao {

    void inserir(Produto p);

    void actualizar(Produto p);

    void remover(int id);

    Produto pesquisar(int id);

    List<Produto> listar();
}
