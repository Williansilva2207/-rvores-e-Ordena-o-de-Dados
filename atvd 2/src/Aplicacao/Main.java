package Aplicacao;

import java.util.Scanner;
import Dados.Tarefas;
import LDE.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LDEDeTarefas lista = new LDEDeTarefas();

        System.out.println("Seja bem-vindo ao programa!\n");
        int op;
        while (true) {
            System.out.println("Escolha uma opção:\n" +
                    "1 - Inserir tarefa\n" +
                    "2 - Executar tarefa\n" +
                    "3 - Excluir tarefa\n" +
                    "4 - Exibir tarefas\n" +
                    "5 - Mudar prioridade\n" +
                    "0 - Sair");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    Tarefas tarefa = info(sc);
                    lista.adicionar(tarefa);
                    break;
                case 2:
                    lista.executar();
                    break;
                case 3:
                    Tarefas tarefas = pesquisar(sc);
                    lista.deletar(tarefas);
                    break;
                case 4:
                    lista.exibir();
                    break;
                case 5:
                    Tarefas tarefa2 = info(sc);
                    lista.mudarPrioridade(tarefa2, tarefa2.getNivel());
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public static Tarefas info(Scanner sc) {
        System.out.println("Digite a descrição da tarefa: ");
        String desc = sc.nextLine();

        System.out.println("Digite o nível da tarefa: ");
        int nivel = sc.nextInt();
        sc.nextLine();

        return new Tarefas(desc, nivel);
    }
    public static Tarefas pesquisar(Scanner sc) {
        System.out.println("Digite a descrição da tarefa: ");
        String desc = sc.nextLine();
        return new Tarefas(desc);
    }
}
