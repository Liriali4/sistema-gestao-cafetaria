package sistemacafeteria.gestao;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
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
                    System.out.print("Id do Pedido: ");
                    int idPedidoEdit = input.nextInt();
                    input.nextLine();

                    Pedido pedidoEdit = pedidoDao.pesquisar(idPedidoEdit);
                    if (pedidoEdit == null) {
                        System.out.println("Pedido não encontrado!");
                        break;
                    }

                    System.out.print("Id do Produto: ");
                    int idProdutoEdit = input.nextInt();
                    input.nextLine();

                    ItemPedido itemEdit = itemPedidoDao.pesquisar(idPedidoEdit, idProdutoEdit);
                    if (itemEdit == null) {
                        System.out.println("Item não encontrado no pedido!");
                        break;
                    }

                    System.out.print("Nova quantidade: ");
                    int novaQtd = input.nextInt();
                    input.nextLine();

                    Produto prod = produtoDao.pesquisar(idProdutoEdit);
                    float novoSubtotal = novaQtd * prod.getPreco();

                    itemEdit.setQuantidade(novaQtd);
                    itemEdit.setValor(novoSubtotal);
                    itemPedidoDao.actualizar(itemEdit);

                    // 🔁 Recalcular total
                    List<ItemPedido> itensPedido = itemPedidoDao.listar().stream()
                            .filter(ip -> ip.getPedido() == idPedidoEdit)
                            .collect(Collectors.toList());

                    double novoTotal = itensPedido.stream()
                            .mapToDouble(ItemPedido::getValor)
                            .sum();

                    pedidoEdit.setValorTotal((float) novoTotal);
                    pedidoDao.actualizar(pedidoEdit);

                    System.out.println("Pedido atualizado com sucesso!");
                    break;

                case 3:
                    List<Pedido> listaPedidos = pedidoDao.listar();
                    List<ItemPedido> listaItenDoPedidos = itemPedidoDao.listar();

                    for (Pedido obj : listaPedidos) {
                        System.out.println(obj);

                        List<ItemPedido> listaItenDoPedido = listaItenDoPedidos.stream()
                                .filter(ip -> ip.getPedido() == obj.getIdPedido())
                                .collect(Collectors.toList());

                        for (ItemPedido i : listaItenDoPedido) {
                            System.out.println(i);
                        }
                    }

                    break;
                case 4:
                    System.out.print("Id do Pedido: ");
                    int idBusca = input.nextInt();
                    input.nextLine();

                    Pedido pedidoBuscado = pedidoDao.pesquisar(idBusca);
                    if (pedidoBuscado == null) {
                        System.out.println("Pedido não encontrado!");
                        break;
                    }

                    System.out.println(pedidoBuscado);

                    List<ItemPedido> itens = itemPedidoDao.listar().stream()
                            .filter(ip -> ip.getPedido() == idBusca)
                            .collect(Collectors.toList());

                    for (ItemPedido i : itens) {
                        System.out.println(i);
                    }
                    break;

                case 5:
                    System.out.print("Id do Pedido: ");
                    int idRemover = input.nextInt();
                    input.nextLine();

                    Pedido pedidoRemover = pedidoDao.pesquisar(idRemover);
                    if (pedidoRemover == null) {
                        System.out.println("Pedido não encontrado!");
                        break;
                    }

                    List<ItemPedido> itensRemover = itemPedidoDao.listar().stream()
                            .filter(ip -> ip.getPedido() == idRemover)
                            .collect(Collectors.toList());

                    for (ItemPedido ip : itensRemover) {
                        itemPedidoDao.remover(ip.getPedido(), ip.getProduto());
                    }

                    pedidoDao.remover(idRemover);

                    System.out.println("Pedido removido com sucesso!");
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
