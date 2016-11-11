public class Node {
	public int item;
	public Node next;

	public Node() {
		item = 0;
		next = null;

	}

	public Node(int i, Node n) {
		next = n;
		item = i;

	}

	public Node(int i) {
		next = null;
		item = i;

	}

	public String toString() {
		return item + " ";
	}

}
