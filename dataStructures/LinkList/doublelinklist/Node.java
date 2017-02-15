package doublelinklist;

/**
 * Created by Luis Alcantar on 2/8/17.
 * This class will represent the nodes with two pinter for a double linklist, it's design so it can store any type.
 * Note a comparator for the data type will be need it.
 */
public class Node<T> {
    public Node next;
    public Node prev;
    private T   data;

    public Node(){
        this.data = null;
        this.next = this.prev = null;
    }

    public Node(T data){
        this.data = data;
        this.next = this.prev = null;
    }

    public Node(T data, Node next){
        this.data = data;
        this.next = next;
        this.prev = null;
    }

    public Node(T data, Node next, Node prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    public T getData() {
        return this.data;
    }
}
