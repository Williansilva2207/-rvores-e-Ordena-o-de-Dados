package AVL;
import queue.*;

public class AVLTree<T extends Comparable<T>> {
    private AVLNode<T> root;
    private boolean status;

    public boolean isEmpty(){
        return this.root == null;
    }

    public void insert(T value){
        if(isEmpty()){
            this.root = new AVLNode<T>(value);
        }else{
            this.root = insertNode(this.root, value);
            this.status = false;
        }
    }

    private AVLNode<T> insertNode (AVLNode<T> r, T value)
    {
        if (r == null) {
            r = new AVLNode<T> (value);
            this.status = true;
        }
        else if (r.getInfo().compareTo(value) > 0) {
            r.setLeft(insertNode (r.getLeft(),value));
            if (this.status == true) {
                switch (r.getFatBal()) {
                    case 1: r.setFatBal(0);
                        this.status = false;
                        break;
                    case 0: r.setFatBal(-1);
                        break;
                    case -1 : r = this.rotateRight(r);
                        break;
                } // fim switch
            } // fim if
        } // fim if
        else {
            r.setRight(insertNode (r.getRight(),value));
            if (this.status == true) {
                switch (r.getFatBal()) {
                    case -1: r.setFatBal(0);
                        this.status = false;
                        break;
                    case 0: r.setFatBal(1);
                        break;
                    case 1 : r = this.rotateLeft(r);
                        break;
                } // fim switch
            } // fim if
        } // fim else
        return r;
    } // fim insertNode
    private AVLNode<T> rotateRight (AVLNode<T> a) {
        AVLNode<T> b, c;
        b = a.getLeft();
        if (b.getFatBal() == -1) { // rot. simples
            a.setLeft(b.getRight());
            b.setRight(a);
            a.setFatBal(0);
            a = b;
        }else { // rotação dupla
            c = b.getRight();
            b.setRight(c.getLeft());
            c.setLeft(b);
            a.setLeft(c.getRight());
            c.setRight(a);
            if (c.getFatBal() == -1) {
                a.setFatBal(1);
            } else {
                a.setFatBal(0);
            }
            if (c.getFatBal() == 1) {
                b.setFatBal(-1);
            } else {
                b.setFatBal(0);
            }
            a = c;
        }
        a.setFatBal(0);
        this.status = false;
        return a;
    }
    private AVLNode<T> rotateLeft (AVLNode<T> a) {
        AVLNode<T> b, c;
        b = a.getRight();
        if (b.getFatBal() == 1) { // rotação simples
            a.setRight(b.getLeft());
            b.setLeft(a);
            a.setFatBal(0);
            a = b;
        }else { // rotação dupla
            c = b.getLeft();
            b.setLeft(c.getRight());
            c.setRight(b);
            a.setRight(c.getLeft());
            c.setLeft(a);
            if (c.getFatBal() == 1) {
                a.setFatBal(-1);
            } else {
                a.setFatBal(0);
            }
            if (c.getFatBal() == -1) {
                b.setFatBal(1);
            } else {
                b.setFatBal(0);
            }
            a = c;
        }
        a.setFatBal(0);
        this.status = false;
        return a;
    }

    public void passeioOrdenado(){
        if(isEmpty()){
            System.out.println("Vazio");
        }else{
            passeioEmOdem(this.root);
        }
    }
    private void passeioEmOdem(AVLNode<T> r){
        if(r!=null){
            passeioEmOdem(r.getLeft());
            System.out.println(r.getInfo());
            passeioEmOdem(r.getRight());
        }
    }
    public void passeioNivelado(){
        if(isEmpty()){
            System.out.println("Vazio");
        }else{
            passeioPorNivel(this.root);
        }
    }
    private void passeioPorNivel(AVLNode<T> r){
        Queue<AVLNode<T>> q = new Queue<>();
        q.enQueue(r);

        while(!q.isEmpty()){
            int ctd = q.size();

            for(int i=0;i<ctd;i++){
                r = q.deQueue();
                if(r!=null){
                    System.out.print(r.getInfo() + ", ");

                }else{
                    System.out.print("null, ");
                   continue;
                }
                q.enQueue(r.getLeft());
                q.enQueue(r.getRight());

            }


            System.out.println();

        }

    }

}
