package doublelinklist;

/**
 * Created by Luis Alcantar on 2/8/17.
 * This class will represent the nodes, it's design so it can store any type.
 * Note a comparator for the data type will be need it.
 */
public class Node<T> {
    public Node next;
    private T   data;

    public Node(){
        this.data = null;
        this.next = null;
    }

    public Node(T data){
        this.data = data;
        this.next = null;
    }

    public Node(T data, Node next){
        this.data = data;
        this.next = next;
    }

    public T getData() {
        return this.data;
    }
}
