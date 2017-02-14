/**
 * Created by Luis Alcantar on 2/8/17.
 */
package two.pointers.linklist;

public class TwoPointersLinkList<T>{
    public Node<T> head;

    public TwoPointersLinkList() {
        this.head = null;
    }

    /**
     * This method will add new Node<T> to the array using the data,
     * in the parameters
     *
     * @param data : data to be added to the list
     */
    public void add(T data) {
        if (this.head == null) {
            this.head = new Node<T>(data);
        } else {
            Node temp = this.head;
            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = new Node<T>(data);
        }
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param :index the desire position starting from 0 or 1 then 2,3,4,5
     * @param :data  the content of the new node
     * @return Boolean: if the insertion was successfully true otherwise false
     */
    public boolean add(int index, T data) {
        /*Add to the end of the list*/
        if (index == size()) {
            add(data);
            return true;
        }
        /*Empty list and index bigger than 0*/
        else if (this.head == null && index != 0) {
            return false;
        } else {
            Node<T> tempNode = this.head;

            while (tempNode != null && tempNode.next != null && --index != 0) {
                tempNode = tempNode.next;
            }

            if (index != 0) {
                return false;
            } else {
                Node<T> newNode = new Node<T>(data);

                /*Empty list,and index is 0*/
                if (tempNode == null) {
                    this.head = newNode;
                } else {
                    newNode.next = tempNode.next;
                    tempNode.next = newNode;
                }
            }
        }
        return true;
    }

    /**
     * This method will add T data to the front of the linklist
     *
     * @param data
     * @return
     */
    public boolean addFirst(T data) {
        return add(0, data);
    }

    /**
     * This method will add data to the end of the list
     *
     * @param data
     * @return true if successfull, false otherwise
     */
    public boolean addLast(T data) {
        return add(size(), data);
    }

    /**
     * Removes all of the elements from this list.
     */
    public void clear() {
        this.head = null;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     *
     * @param data
     * @return
     */
    public int indexOf(T data) {
        Node<T> temp = this.head;
        int index = 0;
        while (temp != null) {
            if (temp.getData().equals(data)) {
                return index;
            }
            index++;
            temp = temp.next;
        }
        return -1;
    }

    /**
     * Check if the list if empty
     *
     * @return true if the list contains 1 or more elements, false otherwise
     */
    public boolean isEmpty() {
        return this.head == null;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @param : element points to the head
     * @see : sizeHelper , size
     */
    public int size() {
        Node<T> temp = this.head;
        int count = 0;

        while (temp != null) {
            temp = temp.next;
            count += 1;
        }
        return count;
    }

    /**
     * Return and remove from the list the first element of the list
     *
     * @return
     */
    public Node<T> getFirstElement() {
        Node<T> oldHead = this.head;
        this.head = this.head.next;
        return oldHead;
    }

    /**
     * Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
     *
     * @return the value of the first element in the list
     */
    public T peekFirst() {
        return this.head != null ? this.head.getData() : null;
    }

    /**
     * Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.
     *
     * @return
     */
    public T peekLast() {
        Node<T> temp = this.head;
        if (temp == null) {
            return null;
        }
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp.getData();
    }

    /**
     * This toString requires the Generic Type <T> to have a toString method.
     */
    public String toString() {
        if (isEmpty()) {
            return "Empty List";
        } else {
            StringBuilder str = new StringBuilder();

            Node temp = this.head;
            while (temp != null) {
                str.append(" [ " + temp.getData().toString() + " ] " + (temp.next == null ? "" : "->"));
                temp = temp.next;
            }
            return str.toString();
        }
    }

    /**
     * Returns a shallow copy of this LinkedList.
     *
     * @return The new clone list.
     */
    public TwoPointersLinkList<T> clone() {
        TwoPointersLinkList<T> cloneList = new TwoPointersLinkList<T>();
        Node<T> temp = this.head;

        while (temp != null) {
            cloneList.add(temp.getData());
            temp = temp.next;
        }
        return cloneList;
    }
}