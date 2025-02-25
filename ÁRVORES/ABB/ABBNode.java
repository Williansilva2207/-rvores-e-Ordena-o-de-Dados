package ABB;
class ABBNode<T extends Comparable <T>>{
    private ABBNode <T> left;
    T info;
    private ABBNode<T> right;

    ABBNode(T info){
        this.info = info;
    }

    public ABBNode<T> getLeft() {
        return left;
    }

    public void setLeft(ABBNode<T> left) {
        this.left = left;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public ABBNode<T> getRight() {
        return right;
    }

    public void setRight(ABBNode<T> right) {
        this.right = right;
    }

    

}