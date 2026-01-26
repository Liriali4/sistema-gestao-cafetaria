package sistemacafeteria.gestao;

import java.util.List;
import java.util.Scanner;
import model.dao.DAO;
import model.dao.FuncionarioDao;
import model.entidades.Funcionario;

public class GestaoDeFuncionarios {

    public static void gestorDeFuncionarios() {

        FuncionarioDao funcionarioDao = DAO.criarFuncionarioDao();

        Scanner input = new Scanner(System.in);
        short op;

        do {
            System.out.println("1. Adicionar!");
            System.out.println("2. Editar!");
            System.out.println("3. Listar!");
            System.out.println("4. Buscar!");
            System.out.println("5. Remover!");
            System.out.println("0. Voltar");
            System.out.print("R: ");
            op = input.nextShort();
            input.nextLine();
            System.out.println("");

            switch (op) {
                case 1:
                    System.out.print("Nome do Funcionário: ");
                    String nome = input.nextLine();
                    Funcionario f1 = new Funcionario();
                    f1.setNome(nome);
                    funcionarioDao.inserir(f1);
                    break;
                case 2:
                    System.out.print("Id do Funcionário: ");
                    int idEdit = input.nextInt();
                    input.nextLine();

                    System.out.print("Novo nome do Funcionário: ");
                    String novoNome = input.nextLine();

                    Funcionario f2 = new Funcionario(idEdit, novoNome);
                    funcionarioDao.actualizar(f2);
                    break;
                case 3:
                    System.out.println("#Funcionários registrados: ");
                    System.out.println("# Nome");
                    List<Funcionario> lista = funcionarioDao.listar();
                    for (Funcionario obj : lista) {
                        System.out.println(obj);
                    }

                    break;
                case 4:
                    System.out.print("Id do Funcionário: ");
                    int id = input.nextInt();
                    input.nextLine();
                    
                    Funcionario temp = funcionarioDao.pesquisar(id);
                    if (temp != null) {
                        System.out.println("#  Nome");
                        System.out.println(temp);
                    } else {
                        System.out.println("Funcionário não encontrado!!");
                    }
                    break;
                case 5:
                    System.out.print("Id do Funcionário: ");
                    int idRemover = input.nextInt();
                    input.nextLine();
                    funcionarioDao.remover(idRemover);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Escolha uma opção válida!!!");
            }
            System.out.println("");
        } while (op != 0);

    }
}
