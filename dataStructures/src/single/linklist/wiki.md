#LinkList Wiki

>By Luis Alcantar

>This implementation is a simple LinkList with one pointer to the head(first element) of the list.
At the end of this wiki, There are test that shows the performance in terms of speed and memory consumption.
You can see the other LinkList implementations and performance in the other packages such as: doublelinklist
and two.pointer.linklist

##Methods

-public boolean add(T) - BigO(N)

-public boolean addFirst(T)- BigO(1)

-public boolean addLast(T)- BigO(N)

-public void clear()- BigO(1)

-public void delete(T)- BigO(N)

-public LinkList<T> clone()- BigO(N)

-public Node<T> getFirstElement()- BigO(1)

-public int indexOf(T)- BigO(N)

-public boolean isEmpty()- BigO(1)

-public T peekFirst()- BigO(1)

-public T peekLast()- BigO(N)

-public int size()- BigO(1)

-public int sizeMethod()- BigO(N)

-public String toString()- BigO(N)


##Memory and speed tests
###class single.linklist.LinkList Test
	###Test 1 : Adding 100000 to lists
		OLD-SIZE :0, NEW_SIZE:100000
		Result for adding 100000, in Seconds: 11.943
		Used memory in bytes: 4485160
		Used memory in megabytes: 4
	###Test 2 : the removal of  100000 from lists
		OLD-SIZE :100000, NEW_SIZE:60594
		Result for Removing 100000, in Seconds: 5.668