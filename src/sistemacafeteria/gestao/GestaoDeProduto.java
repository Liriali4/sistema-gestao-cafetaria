package sistemacafeteria.gestao;

import java.util.List;
import java.util.Scanner;
import model.dao.DAO;
import model.dao.ProdutoDao;
import model.entidades.Produto;

public class GestaoDeProduto {

    public static void gestorDeProdutos() {

        ProdutoDao produtoDao = DAO.criarProdutoDao();

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
                    System.out.print("Nome do Produto: ");
                    String nome = input.nextLine();
                    Produto p1 = new Produto();
                    p1.setNome(nome);
                    
                    System.out.print("Preço do Produto: ");
                    float preco = input.nextFloat();
                    input.nextLine();
                    p1.setPreco(preco);
                    
                    produtoDao.inserir(p1);
                    break;
                case 2:
                    System.out.print("Id do Produto: ");
                    int idEdit = input.nextInt();
                    input.nextLine();

                    System.out.print("Novo nome do Produto: ");
                    String novoNome = input.nextLine();

                    System.out.print("Novo preço do Produto: ");
                    float precoEdit = input.nextFloat();
                    input.nextLine();

                    Produto p2 = new Produto(idEdit, novoNome, precoEdit);
                    produtoDao.actualizar(p2);
                    break;
                case 3:
                    System.out.println("#Produtos registrados: ");
                    System.out.println("# Nome");
                    List<Produto> lista = produtoDao.listar();
                    for (Produto obj : lista) {
                        System.out.println(obj);
                    }

                    break;
                case 4:
                    System.out.print("Id do Produto: ");
                    int id = input.nextInt();
                    input.nextLine();

                    Produto temp = produtoDao.pesquisar(id);
                    if (temp != null) {
                        System.out.println("#  Nome");
                        System.out.println(temp);
                    } else {
                        System.out.println("Produto não encontrado!!");
                    }
                    break;
                case 5:
                    System.out.print("Id do Produto: ");
                    int idRemover = input.nextInt();
                    input.nextLine();
                    produtoDao.remover(idRemover);
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
