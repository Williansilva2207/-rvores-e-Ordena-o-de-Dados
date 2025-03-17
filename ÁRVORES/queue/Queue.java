package queue;
public class Queue<T> {
    QueueNode<T> beggin;
    QueueNode<T> end;

    public boolean isEmpty() {
        return beggin == null;
    }

    public void enQueue(T data) {
        if (isEmpty()) {
            QueueNode<T> newNode = new QueueNode<>(data);
            beggin = newNode;
            end = newNode;
        }else{
            QueueNode<T> newNode = new QueueNode<>(data);
            end.setNext(newNode);
            end = newNode;
        }
    }
    public T deQueue() {
        QueueNode<T> aux = beggin;
        if (isEmpty()) {
            return null;
        }else if(beggin != end){

            beggin = beggin.getNext();
            return aux.getInfo();
        }else{
            beggin = null;
            end = null;
            return aux.getInfo();
        }
    }
}
