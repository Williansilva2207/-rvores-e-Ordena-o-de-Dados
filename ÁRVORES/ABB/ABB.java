package ABB;

public class ABB <T extends Comparable<T>> {
    private ABBNode<T> root;

    public boolean isEmpty () {
        if (this.root == null) {
            return true;
        }
        else {
            return false;
        }
    }

    public void insert (T valor) {
        if (this.isEmpty() == true) {
            this.root = new ABBNode<T>(valor);
        }
        else {
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
            }
            else if (retorno < 0) {
                if (node.getLeft() != null) {
                    inserir(node.getLeft(), valor);
                } else {
                    ABBNode<T> novo = new ABBNode<T>(valor);
                    node.setLeft(novo);
                }
            }
            else {
                if (node.getRight() != null) {
                    inserir(node.getRight(), valor);
                } else {
                    ABBNode<T> novo = new ABBNode<T>(valor);
                    node.setRight(novo);
                }
            }
        }
    }

    public T find (T value) {
        if (this.isEmpty() == true) {
            return null;
        }
        else {
            return findNode (this.root, value);
        }
    }

    private T findNode (ABBNode<T> r, T value) {
        int result = value.compareTo(r.getInfo());
        if (result == 0) {
            return r.getInfo();
        }
        else if (result < 0) {
            if (r.getLeft() == null) {
                return null;
            }
            else {
                return findNode (r.getLeft(),value);
            }
        }
        else {
            if (r.getRight() == null) {
                return null;
             }
            else {
                return findNode (r.getRight(),value);
            }
        }
    }
    public searchSmall(){
        if(this.isEmpty==true){
            return null;
        }else{
            return lowestValue(this.root);
        }
    }
    private T lowestValue(ABBNode <T> r){
        if(r.getLeft != null){
            lowestValue(r.getLeft);
        }else{
            return r.getInfo;
        }
    }

    public searchBig(){
        if(this.isEmpty==true){
            return null;
        }else{
            return highestValue(this.root);
        }
    }
    private T highestValue(ABBNode <T> r){
        if(r.getRight != null){
            highestValue(r.getRight);
        }else{
            return r.getInfo;
        }
    }
}