package sistemacafeteria.gestao;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import model.dao.ConsultaDao;
import model.dao.DAO;
import model.entidades.Cliente;
import model.entidades.Funcionario;
import model.entidades.Pedido;
import model.entidades.Produto;

public class Consultas {

    public static void consultas() {

        ConsultaDao consultaDao = DAO.criarConsultaDao();
        Scanner input = new Scanner(System.in);
        short op;

        do {
            System.out.println("===== CONSULTAS =====");
            System.out.println("1. Consultar Clientes");
            System.out.println("2. Consultar Funcionários");
            System.out.println("3. Consultar Produtos");
            System.out.println("4. Consultar Pedidos");
            System.out.println("0. Voltar");
            System.out.print("R: ");
            op = input.nextShort();
            input.nextLine();
            System.out.println("");

            switch (op) {
                case 1:
                    consultarClientes(input, consultaDao);
                    break;

                case 2:
                    consultarFuncionarios(input, consultaDao);
                    break;

                case 3:
                    consultarProdutos(input, consultaDao);
                    break;

                case 4:
                    consultarPedidos(input, consultaDao);
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Escolha uma opção válida!!!");
            }
            System.out.println("");
        } while (op != 0);
    }

    // ================== CLIENTES ==================
    private static void consultarClientes(Scanner input, ConsultaDao dao) {
        System.out.print("Nome do cliente: ");
        String nome = input.nextLine();

        List<Cliente> lista = dao.pesquisarClientesPorNome(nome);

        if (lista.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    // ================== FUNCIONÁRIOS ==================
    private static void consultarFuncionarios(Scanner input, ConsultaDao dao) {
        System.out.print("Nome do funcionário: ");
        String nome = input.nextLine();

        List<Funcionario> lista = dao.pesquisarFuncionariosPorNome(nome);

        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionário encontrado.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    // ================== PRODUTOS ==================
    private static void consultarProdutos(Scanner input, ConsultaDao dao) {

        System.out.println("1. Por nome");
        System.out.println("2. Mais caros que");
        System.out.println("3. Mais baratos que");
        System.out.print("R: ");
        short op = input.nextShort();
        input.nextLine();

        List<Produto> lista;

        switch (op) {
            case 1:
                System.out.print("Nome do produto: ");
                String nome = input.nextLine();
                lista = dao.pesquisarProdutosPorNome(nome);
                break;

            case 2:
                System.out.print("Preço mínimo: ");
                float min = input.nextFloat();
                lista = dao.pesquisarProdutosMaisCaro(min);
                break;

            case 3:
                System.out.print("Preço máximo: ");
                float max = input.nextFloat();
                lista = dao.pesquisarProdutosMaisBarato(max);
                break;

            default:
                System.out.println("Opção inválida!");
                return;
        }

        if (lista.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    // ================== PEDIDOS ==================
    private static void consultarPedidos(Scanner input, ConsultaDao dao) {

        System.out.println("1. Pedidos por data");
        System.out.println("2. Pedidos de um cliente");
        System.out.println("3. Pedidos atendidos por funcionário");
        System.out.print("R: ");
        short op = input.nextShort();
        input.nextLine();

        List<Pedido> lista;

        switch (op) {
            case 1:
                System.out.print("Data (yyyy-mm-dd): ");
                Date data = java.sql.Date.valueOf(input.nextLine());
                lista = dao.pesquisarPedidosPorData(data);
                break;

            case 2:
                System.out.print("Id do Cliente: ");
                int idCliente = input.nextInt();
                lista = dao.pesquisarPedidosDoCliente(idCliente);
                break;

            case 3:
                System.out.print("Id do Funcionário: ");
                int idFunc = input.nextInt();
                lista = dao.pesquisarPedidosAtendidosPeloFuncionario(idFunc);
                break;

            default:
                System.out.println("Opção inválida!");
                return;
        }

        if (lista.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
        } else {
            lista.forEach(System.out::println);
        }
    }
}
