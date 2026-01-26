package sistemacafeteria;

//import db.DBCreation;
import java.util.Scanner;
import sistemacafeteria.gestao.GestaoDeClientes;
import sistemacafeteria.gestao.GestaoDeFuncionarios;
import sistemacafeteria.gestao.GestaoDePedidos;
import sistemacafeteria.gestao.GestaoDeProduto;

/**
 *
 * @author LiriaLi4
 */
public class SistemaCafeteria {

    public static void main(String[] args) {
        System.out.println("--------------------------------------------------------");
        System.out.println("|                    COFFE SHOP                        |");
        System.out.println("--------------------------------------------------------");

        System.out.println("\n Bem-vindo(a) ao seu novo refúgio do café!!!\n\n");

        /*  Inicializar se não houver bd
        DBCreation.createDatabaseIfNotExists();
        DBCreation.createTablesIfNotExists();
         */
        Scanner input = new Scanner(System.in);
        short op;

        do {
            System.out.println("1. Gestão de funcionários");
            System.out.println("2. Gestão de clientes");
            System.out.println("3. Gestão de Produtos");
            System.out.println("4. Gestão de Pedidos");
            System.out.println("5. Consultas");
            System.out.println("0. Sair");
            System.out.print("R: ");
            op = input.nextShort();
            input.nextLine();
            System.out.println("");

            switch (op) {
                case 1:
                    GestaoDeFuncionarios.gestorDeFuncionarios();
                    break;
                case 2:
                    GestaoDeClientes.gestorDeClientes();
                    break;
                case 3:
                    GestaoDeProduto.gestorDeProdutos();
                    break;
                case 4:
                    GestaoDePedidos.gestorDePedidos();
                    break;
                case 0:
                    System.out.println("Saindo...\n");
                    break;
                default:
                    System.out.println("Escolha uma opção válida!!!");
            }

        } while (op != 0);
    }

}
