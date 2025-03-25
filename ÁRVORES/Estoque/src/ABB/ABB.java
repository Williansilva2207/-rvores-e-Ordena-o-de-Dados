package ABB;
import Produto.*;
public class ABB<T extends Comparable<T>>{
    private ABBNode<T> root;

    boolean isEmpty(){
        return this.root == null;
    }

    public void insert(T value){
        if(isEmpty()){
            this.root.setData(value);
        }else{
            this.root = insert2(this.root, value);
        }
    }

    private ABBNode<T> insert2(ABBNode<T> r, T value){
        int regress = value.compareTo(r.getData());
        if(regress == 0){
            return null;
        }else if(regress < 0){
            if(r.getLeft() != null){
                insert2(r.getLeft(), value);
            }else{
                ABBNode<T> temp = new ABBNode<>(value);
                r.setLeft(temp);
            }
        }else{
            if(r.getRight() != null){
                insert2(r.getRight(), value);
            }else{
                ABBNode<T> temp = new ABBNode<>(value);
                r.setRight(temp);
            }
        }
        return r;
    }
    public ABBNode<T> busca1(T value){
        if(isEmpty()){
            return null;
        }else{
            return busca2(this.root,value);

        }
    }
    private ABBNode<T> busca2(ABBNode <T> r, T value){
        int regress = value.compareTo(r.getData());
        if(r == null){
            System.out.println("Valor nao existente");
            return null;
        }else if(regress == 0){
            return r;
        }else if(regress < 0){
            return busca2(r.getLeft(), value);
        }else{
            return busca2(r.getRight(), value);
        }
    }
    public void emOrdem(){
        if(isEmpty()){
            System.out.println("Vazio");
        }else{
            passeioEmOrdem(this.root);
        }
    }

    private void passeioEmOrdem(ABBNode<T> r){
        if(r!=null){
            passeioEmOrdem(r.getLeft());
            System.out.println(r.toString());
            passeioEmOrdem(r.getRight());
        }
    }
}
