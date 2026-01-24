package model.dao;

import java.util.List;
import model.entidades.Funcionario;

public interface FuncionarioDao {

    void inserir(Funcionario f);

    void actualizar(Funcionario f);

    void remover(int id);

    Funcionario pesquisar(int id);

    List<Funcionario> listar();
}
