package ABB;

public class ABB<T extends Comparable<T>> {
    private ABBNode <T> root;
    public ABB(ABBNode<T> al){
        this.root = al;
    }
    public boolean isEmpty(){
        return this.root == null;
    }

    public void insert(ABBNode<T> node, T valor){
        if(isEmpty()){
            this.root = new ABBNode<T>(valor);
        }else{
            inserir(this.root, valor);
        }
    }

    private void inserir(ABBNode<T> node, T valor){
        //node já é uma cópia do this.root;
        //node não usa o endereço do this.root;
        ABBNode<T> aux = search(this.root, valor);
        if(aux == null){
            while(true){
                ABBNode<T> novo = new ABBNode<T>(valor);
                int retorno;
                retorno = valor.compareTo(node.getInfo());
                if(retorno > 0){
                    if(node.getRight() == null){
                        node.setRight(novo);
                        return;
                    }else{
                        node = node.getRight();
                    }
                }else{
                    if(node.getLeft() == null){
                        node.setLeft(novo);
                        return;
                    }else{
                        node = node.getLeft();
                    }
                }
            }
        }else{
            System.out.println("Ja existe fiao!");
        }
    }
    private ABBNode<T> search(ABBNode<T> node, T valor){
        for(int i = 0; node != null; i++){
            int retorno = valor.compareTo(node.getInfo());
            if(retorno == 0){
                return node;
            }else if(retorno < 0){
                node = node.getLeft();
            }else{
                node = node.getRight();
            }
        }
        return null;
    }
}


