/**
 * Created by Luis Alcantar on 2/8/17.
 */
package two.pointers.linklist;

import java.util.ArrayList;

public class TwoPointersLinkList<T> {
    public Node<T> front;
    public Node<T> back;
    private int count;

    public TwoPointersLinkList() {
        this.front = null;
        this.back = null;
        this.count = 0;
    }

    /**
     * This method will add new Node<T> to the array using the data,
     * in the parameters
     *
     * @param data : data to be added to the list
     */
    public void add(T data) {
        Node<T> newNode = new Node<T>(data);
        if (this.front == null) {
            this.front = newNode;
            this.back = this.front;
        } else {
            this.back.next = newNode;
            this.back = newNode;
        }
        this.count++;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     *
     * @param :index the desire position starting from 0 then 2,3,4,5
     * @param :data  the content of the new node
     * @return Boolean: if the insertion was successfully return  true otherwise false
     */
    public boolean add(int index, T data) {
        /*Add to the end of the list*/
        if (index == size()) {
            add(data);
            return true;
        }
        /*Empty list and index bigger than 0*/
        else if (this.front == null && index != 0) {
            return false;
        } else {
            Node<T> tempNode = this.front;

            while (tempNode != null && tempNode.next != null && --index != 0) {
                tempNode = tempNode.next;
            }

            if (index != 0) {
                return false;
            } else {
                Node<T> newNode = new Node<T>(data);

                /*Empty list,and index is 0*/
                if (tempNode == null) {
                    add(data);
                } else {
                    newNode.next = tempNode.next;
                    tempNode.next = newNode;
                }
            }
        }
        this.count++;
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
        this.front = null;
    }


    /**
     * Delete a node from the list if param Data match a value inside the list.
     * because of the nature of the list, the first element that match the data
     * will be delete.
     * @param data : element that need to be delete,
     * @return
     */
    public boolean delete(T data) {
        /*Add to the end of the list*/
        if (isEmpty()) {
            return false;
        }
        /*first element*/
        if(this.front.getData().equals(data)){
            this.front = this.front.next;
            return true;
        }

        Node<T> temp = this.front;
        Node<T> previous = null;
        while(temp != null){
            if(temp.getData().equals(data)){
                previous.next = temp.next;
                temp.next = null;
                this.count--;
                return true;
            }
            previous = temp;
            temp = temp.next;
        }
        return false;
    }


    public void bigON(String x){
        int sum = 0;
        for (int i = 0; i < x.length(); i++){

        }
    }
        /**
         * Returns the index of the first occurrence of the specified element in this list,
         * or -1 if this list does not contain the element.
         *
         * @param data
         * @return
         */
    public int indexOf(T data) {
        Node<T> temp = this.front;
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
        return this.front == null;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @param : element points to the head
     * @see : sizeHelper , size
     */
    public int size() { return this.count; }

    /**
     * Return and remove from the list the first element of the list
     *
     * @return
     */
    public Node<T> getFirstElement() {
        Node<T> oldHead = this.front;
        this.front = this.front.next;
        return oldHead;
    }

    /**
     * Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.
     *
     * @return the value of the first element in the list
     */
    public T peekFirst() {
        return this.front != null ? this.front.getData() : null;
    }

    /**
     * Retrieves, but does not remove, the last element of this list, or returns null if this list is empty.
     *
     * @return
     */
    public T peekLast() { return this.back.getData(); }

    /**
     * Get the last element of the list, and the last element will know be the
     * @return
     */
    public Node<T> getLast() {
        Node<T> last = this.front;
        Node<T> beforeLast = null;
        if(isEmpty()){ return beforeLast; }

        while (last.next != null) {
            beforeLast = last;
            last = last.next;
        }

        /*There is only on element the head,remove the link of head*/
        if(beforeLast == null){
            this.front = null;
            this.back = null;
        }else{
            beforeLast.next = null;
        }

        this.count--;
        return last;
    }

    /**
     * This toString requires the Generic Type T to have a toString method.
     */
    public String toString() {
        if (isEmpty()) {
            return "Empty List";
        } else {
            StringBuilder str = new StringBuilder();

            Node temp = this.front;
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
        Node<T> temp = this.front;

        while (temp != null) {
            cloneList.add(temp.getData());
            temp = temp.next;
        }
        return cloneList;
    }
}