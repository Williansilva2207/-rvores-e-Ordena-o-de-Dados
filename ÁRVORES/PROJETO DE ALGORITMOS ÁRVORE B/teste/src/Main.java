import AVB.AVB;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        AVB arvore = new AVB();
        Scanner sc = new Scanner(System.in);

        int opc = -1;

        while (opc != 0) {
            System.out.println("\nEscolha uma opcao: ");
            System.out.println("| 1. Adicionar Valor | 2. Exibir por nivel | 3. Exibir em ordem | 4. Remover | 0. Sair |");
            opc = sc.nextInt();
             switch (opc) {
                 case 1:
                     System.out.print("Digite o valor a ser inserido: ");
                     int valorInserir = sc.nextInt();
                     arvore.inserirValor(valorInserir);
                     break;
                 case 2:
                     arvore.imprimirPorNivel();
                     break;
                 case 3:
                     arvore.imprimirOrdenado();
                     break;
                 case 4:
                     System.out.print("Digite o valor a ser deletado: ");
                     int valorDeletar = sc.nextInt();
                     break;
                 case 0:
                     System.out.println("Encerrando...");
                     break;
                 default:
                     System.out.println("Opção inválida. Tente novamente.");
                     break;
                }
            }

            sc.close();
        }


}

