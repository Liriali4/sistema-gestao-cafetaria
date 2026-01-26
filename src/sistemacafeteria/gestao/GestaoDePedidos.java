package sistemacafeteria.gestao;

import java.util.Date;
import java.util.Scanner;
import model.dao.ClienteDao;
import model.dao.DAO;
import model.dao.FuncionarioDao;
import model.dao.ItemPedidoDao;
import model.dao.PedidoDao;
import model.dao.ProdutoDao;
import model.entidades.Cliente;
import model.entidades.Funcionario;
import model.entidades.ItemPedido;
import model.entidades.Pedido;
import model.entidades.Produto;

public class GestaoDePedidos {

    public static void gestorDePedidos() {

        PedidoDao pedidoDao = DAO.criarPedidoDao();
        ItemPedidoDao itemPedidoDao = DAO.criarItemPedidoDao();
        ProdutoDao produtoDao = DAO.criarProdutoDao();
        FuncionarioDao funcionarioDao = DAO.criarFuncionarioDao();
        ClienteDao clienteDao = DAO.criarClienteDao();

        Scanner input = new Scanner(System.in);
        short op;

        do {
            System.out.println("1. Adicionar!");
            System.out.println("2. Editar!");
            System.out.println("3. Listar!");
            System.out.println("4. Buscar!");
            System.out.println("5. Remover!");
            System.out.println("0. Sair");
            System.out.print("R: ");
            op = input.nextShort();
            input.nextLine();
            System.out.println("");

            switch (op) {
                case 1:
                    // ----- FUNCIONÁRIO -----
                    System.out.print("Id do Funcionário: ");
                    int idFuncionario = input.nextInt();

                    Funcionario func = funcionarioDao.pesquisar(idFuncionario);
                    if (func == null) {
                        System.out.println("Funcionário não encontrado!");
                        break;
                    }

                    // ----- CLIENTE -----
                    System.out.print("Id do Cliente: ");
                    int idCliente = input.nextInt();
                    input.nextLine();

                    Cliente cliente = clienteDao.pesquisar(idCliente);
                    if (cliente == null) {
                        System.out.println("Cliente não encontrado!");
                        break;
                    }

                    // ----- PEDIDO -----
                    Pedido pedido = new Pedido();
                    pedido.setFuncionario(idFuncionario);
                    pedido.setCliente(idCliente);
                    pedido.setData(new Date());
                    pedido.setValorTotal(0f);

                    pedidoDao.inserir(pedido); // gera idPedido

                    double total = 0;
                    char opcao = 0;

                    // ----- ITENS -----
                    do {
                        System.out.print("Id do Produto: ");
                        int idProduto = input.nextInt();

                        Produto produto = produtoDao.pesquisar(idProduto);
                        if (produto == null) {
                            System.out.println("Produto não encontrado!");
                            continue;
                        }

                        System.out.print("Quantidade: ");
                        int quantidade = input.nextInt();
                        input.nextLine();

                        float subtotal = quantidade * produto.getPreco();
                        total += subtotal;

                        ItemPedido item = new ItemPedido();
                        item.setPedido(pedido.getIdPedido());
                        item.setProduto(produto.getIdProduto());
                        item.setQuantidade(quantidade);
                        item.setValor(subtotal);

                        itemPedidoDao.inserir(item); //Gaurdar item
                                                
                        System.out.print("Adicionar outro produto? (s/n): ");
                        opcao = input.next().charAt(0);
                        input.nextLine();

                    } while (opcao == 's' || opcao == 'S');

                    // ----- ATUALIZAR TOTAL -----
                    pedido.setValorTotal((float) total);
                    pedidoDao.actualizar(pedido);

                    System.out.println("Pedido registado com sucesso!");

                    break;

                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 0:
                    System.out.println("Saindo...\n");
                    break;
                default:
                    System.out.println("Escolha uma opção válida!!!");
            }
            System.out.println("");
        } while (op != 0);

    }
}
