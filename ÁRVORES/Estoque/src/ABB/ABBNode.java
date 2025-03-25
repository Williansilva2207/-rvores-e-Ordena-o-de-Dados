package ABB;

public class ABBNode <T extends Comparable<T>> {
    private ABBNode<T> left;
    private ABBNode<T> right;
    private T data;
   public ABBNode(T data){
       this.data = data;
   }

    public ABBNode<T> getLeft() {
        return left;
    }

    public void setLeft(ABBNode<T> left) {
        this.left = left;
    }

    public ABBNode<T> getRight() {
        return right;
    }

    public void setRight(ABBNode<T> right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
