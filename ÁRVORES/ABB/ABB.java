package ABB;
import queue.*;
import java.util.Stack;
public class ABB <T extends Comparable<T>> {
    private ABBNode<T> root;
    private int qtd;

    public boolean isEmpty() {
        if (this.root == null) {
            return true;
        } else {
            return false;
        }
    }

    public void insert(T valor) {
        if (this.isEmpty() == true) {
            this.root = new ABBNode<T>(valor);
        } else {
            inserir(this.root, valor);
        }
    }

    private void inserir(ABBNode<T> node, T valor) { // versão recursiva
        int retorno;
        if (node != null) {
            retorno = valor.compareTo(node.getInfo());
            if (retorno == 0) {
                System.out.println("Valor repetido!\n");
                return;
            } else if (retorno < 0) {
                if (node.getLeft() != null) {
                    inserir(node.getLeft(), valor);
                } else {
                    ABBNode<T> novo = new ABBNode<T>(valor);
                    node.setLeft(novo);
                }
            } else {
                if (node.getRight() != null) {
                    inserir(node.getRight(), valor);
                } else {
                    ABBNode<T> novo = new ABBNode<T>(valor);
                    node.setRight(novo);
                }
            }
        }
    }

    public T find(T value) {
        if (this.isEmpty() == true) {
            return null;
        } else {
            return findNode(this.root, value);
        }
    }

    private T findNode(ABBNode<T> r, T value) {
        int result = value.compareTo(r.getInfo());
        if (result == 0) {
            return r.getInfo();
        } else if (result < 0) {
            if (r.getLeft() == null) {
                return null;
            } else {
                return findNode(r.getLeft(), value);
            }
        } else {
            if (r.getRight() == null) {
                return null;
            } else {
                return findNode(r.getRight(), value);
            }
        }
    }

    public T searchSmall() {
        if (this.isEmpty() == true) {
            return null;
        } else {
            return lowestValue(this.root);
        }
    }

    private T lowestValue(ABBNode<T> r) {
        if (r.getLeft() != null) {
            lowestValue(r.getLeft());
        } else {
            return r.getInfo();
        }
    }

    public T searchBig() {
        if (this.isEmpty() == true) {
            return null;
        } else {
            return highestValue(this.root);
        }
    }

    public T highestValue(ABBNode<T> r) {
        if (r.getRight() != null) {
            highestValue(r.getRight());
        } else {
            return r.getInfo();
        }
    }

    public void passeioPorNivel() {
        Queue<ABBNode<T>> fila;
        ABBNode<T> aux;
        if (this.isEmpty() == false) {
            fila = new Queue();
            fila.enQueue(this.root);
            while (fila.isEmpty() == false) {
                aux = fila.deQueue();
                if (aux.getLeft() != null) {
                    fila.enQueue(aux.getLeft());
                }
                if (aux.getRight() != null) {
                    fila.enQueue(aux.getRight());
                }
                System.out.println(aux.getInfo());
            }
        } else {
            System.out.println("Árvore vazia");
        }
    }



    public void passeioEmOrdem() {
        if (this.isEmpty()) {
            System.out.println("Árvore vazia");
            return;
        }

        Stack<ABBNode<T>> stack = new Stack<>();
        ABBNode<T> atual = this.root;

        while (atual != null || !stack.isEmpty()) {

            while (atual != null) {
                stack.push(atual);
                atual = atual.getLeft();
            }


            atual = stack.pop();
            System.out.println(atual.getInfo());


            atual = atual.getRight();
        }
    }

    public int contagem(ABBNode<T> r){
        this.qtd++;
        if (r != null) {
            contagem(r.getLeft());
            contagem(r.getRight());
        }
    }

}
