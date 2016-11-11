import java.util.Random;
import java.util.Scanner;

public class lab_2 {
	/*
	 * Print Method is just simply Print all elements with the given reference
	 * 
	 * @param is a Node the head of the list
	 * 
	 * @return void
	 */
	public static void print(Node n) {
		if (n == null)
			System.out.println();
		else {
			System.out.print(n.item + " ");
			print(n.next);
		}
	}

	/*
	 * Add method it add an element
	 * 
	 * @param the Node the head of the list
	 * 
	 * @param int x the value that the new Node will Contain
	 * 
	 * @return the Node that is the reference of the head in the new list
	 */
	public static Node add(Node head, int x) {
		if (head == null) {
			head = new Node(x, head);
		}
		Node temp = new Node();
		temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		temp.next = new Node(x, null);
		return head;
	}

	/*
	 * Size method it determinate the size of the list
	 * 
	 * @param the Head of the list to transverse
	 * 
	 * @return The sixe of the list Int
	 */
	public static int size(Node n) {
		if (n == null)
			return 0;
		return 1 + size(n.next);
	}

	/*
	 * AddNumber will go to each Node and add the argument int i to the value of
	 * each node
	 * 
	 * @param Node n the head of the list
	 * 
	 * @param int i the value to be added it to the positions
	 * 
	 * @return void
	 */
	public static void addANumber(Node n, int i) {
		if (n.next == null)
			n.item += i;
		else {
			n.item += i;
			addANumber(n.next, i);
		}
	}

	/*
	 * ReferenceToNthElement it will take a number indicating the position of
	 * the desire Node
	 * 
	 * @param Node h the head of the list
	 * 
	 * @param int n the position desire
	 * 
	 * @return the smaller value int he list (int)
	 */
	public static int ReferenceToNthElement(Node h, int n) {
		if (n == 1)
			return h.item;

		return ReferenceToNthElement(h.next, n - 1);

	}

	/*
	 * This is the Helper method for ReferenceToNthElement in this method we
	 * check if the size is not bigger than the desire position if the statement
	 * is true we return -1 but if is false we execute ReferenceToNthElement
	 * 
	 * @param Node h the head of the list
	 * 
	 * @param int n the position desire
	 * 
	 * @return The value for the node that was in the desire position (int)
	 */
	public static int ReferenceToNthElementH(Node h, int n) {
		if (n > size(h))
			return -1;

		return ReferenceToNthElement(h, n);

	}

	/*
	 * ReferenceLargeElement is a method that looks for the large value in the
	 * nodes it gets the head of a list and transversing the list it perform
	 * comparations with a temporary node.
	 * 
	 * @param The head of the list Node h
	 * 
	 * @return a new Node that contains the modify list.
	 */
	public static Node ReferenceLargeElement(Node h) {
		Node referenceMax = new Node();
		referenceMax.item = -1;

		while (h != null) {
			if (h.item > referenceMax.item) {
				referenceMax = h;
			} else {
				h = h.next;
			}
		}
		return referenceMax;
	}

	/*
	 * ReferenceSmallElement is a method that looks for the smaller element in
	 * the in a given list
	 * 
	 * @param the list Node h
	 * 
	 * @return a new Node that contains the modify list.
	 */
	public static Node ReferenceSmallElement(Node h) {
		Node referenceMin = new Node();
		referenceMin.item = h.item;

		while (h != null) {
			if (h.item < referenceMin.item) {
				referenceMin = h;
			} else {
				h = h.next;
			}
		}
		return referenceMin;
	}

	/*
	 * Rotation moves the Node that is at the position 1 to the last positions
	 * 
	 * @param Node n the list were we need to perform the rotation.
	 * 
	 * @return a new Node that contains the modify list.
	 */
	public static Node Rotation(Node n) {
		Node x = new Node();
		Node y = new Node();
		y = x = n.next;
		n.next = null;
		while (x.next != null) {
			x = x.next;
		}
		x.next = n;
		return y;
	}

	/*
	 * listWithOddPositions Will transverse the list and find out which nodes
	 * are in add postions And it will display the values of them.
	 * 
	 * @param Node n the with a reference to the head of the list
	 */
	public static Node listWithOddPositions(Node n) {
		Node odd = new Node();
		Node ans = new Node();
		ans = null;
		odd = null;
		int i = 1;

		while (n != null) {
			if (i % 2 != 0) {
				odd = new Node(n.item, odd);
			}
			n = n.next;
			i++;
		}
		while (odd != null) {
			ans = new Node(odd.item, ans);
			odd = odd.next;
		}

		return ans;
	}

	/*
	 * This method is the same as listWithOddPositions but in this method we
	 * look for even postions
	 * 
	 * @param Node n the with a reference to the head of the list
	 */
	public static Node listWithEvenPositions(Node n) {
		Node odd = new Node();
		Node ans = new Node();
		ans = null;
		odd = null;
		int i = 1;

		while (n.next != null) {
			if (i % 2 == 0) {
				odd = new Node(n.item, odd);
			}
			n = n.next;
			i++;
		}
		while (odd != null) {
			ans = new Node(odd.item, ans);
			odd = odd.next;
		}

		return ans;
	}
	//Find the Nth element  in a sorting list
	//assuming the list is in assendig order
	public static int SmallElementSortList(Node n,int i)
	{
		Node x =new Node();
		x=n;
		int position=0;
		
		while(position!=i)
		{
			if(x==null)
				return -1;
			if(x.item==x.next.item){
			x=x.next.next;
			position++;
			}
			else{
				x=x.next;
				position++;
			}
		}
		return x.item;
		
	}

	/*
	 * appendix
	 * 
	 * @param Node h a reference to a list.
	 * 
	 * @param Node i a reference to a list. --> the method Receive a reference
	 * to the head of the list h and a reference to the head of the list i and
	 * appends i to the end of h. So if h is 1 2 3 and i is 4 5 6, after
	 * executing your method, h should be 1 2 3 4 5 6.
	 * 
	 * @return void.
	 */
	public static Node appendix(Node h, Node i) {
		Node appendix = new Node();
		appendix = h;
		System.out.println("List 1 : ");
		print(h);
		System.out.println("List 2 : ");
		print(i);
		while (h.next != null) {
			h = h.next;
		}
		h.next = i;
		System.out.println("new list : ");
		return appendix;
	}

	public static Node concatinate(Node x, Node y) {
		while (y != null) {
			x = new Node(y.item, x);
			y = y.next;
		}
		return x;
	}

	/*
	   * 
	   */
	public static Node quick(Node L, Node L1, Node L2, Node D) {
		if (L == null)
			return L;
		Node pivot = new Node();
		pivot.item = L.item;
		D = L.next;
		if (D.item <= pivot.item)
			L1 = add(L1, D.item);
		else {
			L2 = add(L2, D.item);
		}
		L1 = quick(L, L1, L2, D);
		L2 = quick(L, L1, L2, D);
		L.next = L2;
		L = concatinate(L1, L);

		return L;
	}

	/*
	   * 
	   */
	public static Node quick(Node N) {
		Node L = new Node();
		L = null;
		Node R = new Node();
		R = null;
		Node D = new Node();
		D = null;

		return quick(N, L, R, D);
	}

	public static int findnthSmallestElement(Node n, int i) {
		int p = 0;
		boolean[] map = new boolean[ReferenceLargeElement(n).item+1];
		Node x = new Node();
		x=n;
		while(x!=null)
		{
			map[x.item]=true;
			x=x.next;
		}
		int position=0;
		
		for(int y=0;position!=i;y++)
		{
			if(map[y]==true){
				position++;
				p=y;
			}
			
				
		}
		

		return p;
	}

	public static Node quickModif(Node y,Node x, int i)
	{
		if(y==x)
			return x;
		else
		{
			if(x.item<x.item)
				y=add(y,x.item);
			return quickModif(y,x.next,i);
		}


	}
	
	public static Node quickModif(Node n,int i)
	{
		Node x =new Node();
		Node y =new Node();
		x=n;
		
		return quickModif(y,x,i);
	}
	

	// main
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		char responce = ' ';
		Random r = new Random();
		Node n = new Node();
		Node largeElement = new Node();
		Node smallElement = new Node();
		Node listH = new Node();
		listH = null;
		Node listI = new Node();
		listI = null;
		Node L1 = new Node();
		L1 = null;
		Node L2 = new Node();
		L2 = null;
		int position = 0;

		listH = new Node(10, listH);
		listH = new Node(9, listH);
		listH = new Node(8, listH);
		listH = new Node(7, listH);
		listI = new Node(14, listI);
		listI = new Node(13, listI);
		listI = new Node(12, listI);
		listI = new Node(11, listI);

		L1 = new Node(10, L1);
		L1 = new Node(4, L1);
		L1 = new Node(8, L1);
		L1 = new Node(12, L1);
		L1 = new Node(14, L1);
		L1 = new Node(16, L1);
		L1 = new Node(6, L1);
		L1 = new Node(2, L1);

		n = new Node(1, n);
		n = new Node(2, n);
		n = new Node(3, n);
		n = new Node(4, n);
		n = new Node(5, n);
		n = new Node(6, n);
		n = new Node(7, n);
		n = new Node(8, n);

		while (responce != 'N') {
			System.out.println(" ");

			System.out.println("WELCOME TO LAB 2 PLASE CHOOSE AN OPTION");

			System.out
					.println("***********************************OPTIONS****************************************");
			System.out
					.println("a) Add a vale to  all the position \nb) to get an element at sertain position \nc) Reference to the largest element "
							+ " \nd) Reference to the smallest element \ne) rotating one unit.(123-->231) \nf) Eliminate Ofdd positions \ng) Eliminate Even positions"
							+ " \nh) appendix lists  \nq) Quick sort(sort elements) \np) Print \nv) Nvth smallest element ");

			System.out
					.println("******************************OR SELECT TYPE N TO EXIT*********************************************");

			responce = scan.nextLine().charAt(0);

			if (responce == 'a' || responce == 'A') {
				System.out
						.println("The value to add to each one of the positions?");
				int addnumber = Integer.parseInt(scan.nextLine());
				addANumber(n, addnumber);
			}
			if (responce == 'Z' || responce == 'z') {
				// clear(n);
			}
			if (responce == 'B' || responce == 'b') {

				System.out.println("which position you whant to reach?");
				position = Integer.parseInt(scan.nextLine());
				System.out.println(ReferenceToNthElementH(n, position));
			}
			if (responce == 'C' || responce == 'c') {
				largeElement = ReferenceLargeElement(n);
				System.out.println("The larger element is : " + largeElement);
			}
			if (responce == 'D' || responce == 'd') {
				smallElement = ReferenceSmallElement(n);
				System.out.println("The smaller element is : " + smallElement);
			}
			if (responce == 'E' || responce == 'e') {
				n = Rotation(n);
			}
			if (responce == 'f' || responce == 'F') {
				print(listWithOddPositions(n));
			}
			if (responce == 'G' || responce == 'g') {
				print(listWithEvenPositions(n));
			}
			if (responce == 'h' || responce == 'H') {
				print(appendix(listH, listI));
			}
			if (responce == 'q' || responce == 'Q') {
				print(quick(L1));
			}
			if (responce == 'j' || responce == 'J') {
				print(concatinate(listH, listI));
			}
			if (responce == 'M' || responce == 'm') {
				System.out.println(quickModif(n,2));
			}
			if (responce == 'v' || responce == 'V') {
				System.out
						.println("give me the number fot the nth smaller postion ?");
				position = Integer.parseInt(scan.nextLine());
				System.out.println("The " + position + "TH is : "
						+ findnthSmallestElement(n, position));

			}
			if (responce == 'P' || responce == 'p') {
				print(n);
			}

		}
	}

}
