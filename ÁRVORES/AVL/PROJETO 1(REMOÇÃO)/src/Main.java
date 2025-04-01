import AVL.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("==== MENU ====");
            System.out.println("1 - Inserir elemento");
            System.out.println("2 - Exibir em ordem");
            System.out.println("3 - Exibir por nível");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite um número para inserir: ");
                    int valor = sc.nextInt();
                    avl.insert(valor);
                    System.out.println("Número inserido com sucesso!\n");
                    break;
                case 2:
                    System.out.println("Elementos em ordem:");
                    avl.passeioOrdenado();
                    System.out.println();
                    break;
                case 3:
                    System.out.println("Elementos por nível:");
                    avl.passeioNivelado();
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida!\n");
            }
        }
    }
}
