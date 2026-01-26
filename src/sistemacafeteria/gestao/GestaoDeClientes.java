package sistemacafeteria.gestao;

import java.util.List;
import java.util.Scanner;
import model.dao.DAO;
import model.dao.ClienteDao;
import model.entidades.Cliente;

public class GestaoDeClientes {

    public static void gestorDeClientes() {

        ClienteDao clienteDao = DAO.criarClienteDao();

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
                    System.out.print("Nome do Cliente: ");
                    String nome = input.nextLine();
                    Cliente c1 = new Cliente();
                    c1.setNome(nome);
                    clienteDao.inserir(c1);
                    break;
                case 2:
                    System.out.print("Id do Cliente: ");
                    int idEdit = input.nextInt();
                    input.nextLine();

                    System.out.print("Novo nome do Cliente: ");
                    String novoNome = input.nextLine();

                    Cliente c2 = new Cliente(idEdit, novoNome);
                    clienteDao.actualizar(c2);
                    break;
                case 3:
                    System.out.println("#Clientes registrados: ");
                    System.out.println("# Nome");
                    List<Cliente> lista = clienteDao.listar();
                    for (Cliente obj : lista) {
                        System.out.println(obj);
                    }

                    break;
                case 4:
                    System.out.print("Id do Cliente: ");
                    int id = input.nextInt();
                    input.nextLine();
                    
                    Cliente temp = clienteDao.pesquisar(id);
                    if (temp != null) {
                        System.out.println("#  Nome");
                        System.out.println(temp);
                    } else {
                        System.out.println("Cliente não encontrado!!");
                    }
                    break;
                case 5:
                    System.out.print("Id do Cliente: ");
                    int idRemover = input.nextInt();
                    input.nextLine();
                    clienteDao.remover(idRemover);
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
