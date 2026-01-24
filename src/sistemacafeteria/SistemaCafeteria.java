package sistemacafeteria;

import java.util.Scanner;

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

        Scanner input = new Scanner(System.in);
        short op;

        do {
            System.out.println("1. Gestão de funcionários");
            System.out.println("2. Gestão de clientes");
            System.out.println("3. Gestão de Pedidos");
            System.out.println("0. Sair");
            System.out.print("R: ");
            op = input.nextShort();
            input.nextLine();

            switch (op) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
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
