import AVB.*;

public class Main {
    public static void main(String[] args) {
        AVB arvore = new AVB();

        // Teste 1: Inserção básica
        for(int i = 1; i <= 10; i++) {
            arvore.inserirValor(i);
        }
        arvore.imprimir();

        // Teste 2: Inserção com valores aleatórios
        arvore = new AVB();
        int[] valores = {5, 3, 7, 1, 4, 6, 8, 2, 9, 0};
        for(int val : valores) {
            arvore.inserirValor(val);
        }
        arvore.imprimir();
    }
}
