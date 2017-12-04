#LinkList Wiki
>By Luis Alcantar

>This implementation is a  simple LinkList with two pointer to the list, one in the front and one in the end of the list.
At the end of this wiki, There are test that shows the performance in terms of speed and memory consumption.
You can see the other LinkList implementations and performance in the other packages such as: sigle.liklist
and doublelinklist

##Methods
-public boolean add(T)- BigO(1)

-public boolean addFirst(T)- BigO(1)

-public boolean addLast(T)- BigO(1)

-public void clear()- BigO(1)

-public LinkList<T> clone()- BigO(N)

-public Node<T> getFirstElement()- BigO(1)

-public int indexOf(T)- BigO(N)

-public boolean isEmpty()- BigO(1)

-public T peekFirst()- BigO(1)

-public T getFirstElement()- BigO(1)

-public T peekLast()- BigO(1)

-public T getLast()- BigO(1)

-public int size()- BigO(1)

-public String toString()- BigO(n)

##Memory and speed tests
###class two.pointers.linklist.TwoPointersLinkList Test
Test 1 : Adding 100000 to lists
	OLD-SIZE :0, NEW_SIZE:100000
	Result for adding 100000, in Seconds: 0.008
	Used memory in bytes: 4486040
	Used memory in megabytes: 4
Test 2 : the removal of  100000 from lists
	OLD-SIZE :100000, NEW_SIZE:60661
	Result for Removing 100000, in Seconds: 5.199
	Used memory in bytes: 2895472
	Used memory in megabytes: 2
Test 3 : removing all elements using getLast()
	OLD-SIZE :60661, NEW_SIZE:0
	Result removing all element using getLast(), in Seconds: 3.643
	Used memory in bytes: 466912
	Used memory in megabytes: 0

Process finished with exit code 0
