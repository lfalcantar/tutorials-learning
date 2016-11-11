/**
 The LinkedList1 class implements a Linked list.
 */
 import java.text.*;
class LinkedList
{
  
  public Node first;  // list head
  public Node last;   // last element in list
  
  /**
   Constructor.
   */
  
  public LinkedList()
  {
    first = null;
    last = null;        
  }
  
  /**
   The isEmpty method checks to see 
   if the list is empty.
   @return true if list is empty, 
   false otherwise.
   */
  
  public boolean isEmpty()
  {        
    return first == null;       
  }
  
  /**
   * 
   * find
   * 
   **/
  public boolean find(Stock s,double x){
    if(isEmpty())
      return false;
    Node tmp = first;
    while(tmp!= null){// &&!tmp.value.equals(s)){
      if(tmp.stock.compareTo(s)==0)
      {
        tmp.stock.modifyStockvalue(x);
        tmp.stock.incremnetNumberTransaction();
        return true;
      }

      tmp = tmp.next;
    }
    return false;
  }
  
  /**
   The size method returns the length of the list.
   @return The number of elements in the list.
   */
  
  public int size()
  {
    int count = 0;
    Node p = first;     
    while (p != null)
    {
      // There is an element at p
      count ++;
      p = p.next;
    }
    return count;
  }
  
  /**
   The add method adds an element to
   the end of the list.
   @param e The data to add to the
   end of the list.       
   */
  
  public void add(Stock e)
  {
    if (isEmpty()) 
    {
      first = new Node(e);
      last = first;
    }
    else
    {
      // Add to end of existing list
      last.next = new Node(e);
      last = last.next;
    }      
  }
  
  /**
   The add method adds an element at a position.
   @param e The element to add to the list.
   @param index The position at which to add 
   the element.
   @exception IndexOutOfBoundsException When 
   index is out of bounds.  
   */
  
  public void add(int index, Stock e)
  {
    if (index < 0  || index > size()) 
    {
      String message = String.valueOf(index);
      throw new IndexOutOfBoundsException(message);
    }
    
    // Index is at least 0
    if (index == 0)
    {
      // New element goes at beginning
      first = new Node(e, first);
      if (last == null)
        last = first;
      return;
    }
    
    // Set a reference pred to point to the node that
    // will be the predecessor of the new node
    Node pred = first;        
    for (int k = 1; k <= index - 1; k++)        
    {
      pred = pred.next;           
    }
    
    // Splice in a node containing the new element
    pred.next = new Node(e, pred.next);  
    
    // Is there a new last element ?
    if (pred.next.next == null)
      last = pred.next;         
  }
  
  /**
   The toString method computes the string
   representation of the list.
   @return The string form of the list.
   */
  
  public String toString()
  {
    DecimalFormat dF = new DecimalFormat("0.00");
//    StringBuilder strBuilder = new StringBuilder();
    String strBuilder = "";
    // Use p to walk down the linked list
    Node p = first;
    while (p != null)
    {
//      strBuilder.append(p.value + "\n"); 
      strBuilder+=""+p.stock.name +" : "+dF.format(p.stock.stockValue)+"->"; 
      p = p.next;
    }      
    return strBuilder.toString(); 
  }
  
  /**
   The remove method removes the element at an index.
   @param index The index of the element to remove. 
   @return The element removed.  
   @exception IndexOutOfBoundsException When index is 
   out of bounds.     
   */
  
  public Stock remove(int index)
  {
    if (index < 0 || index >= size())
    {  
      String message = String.valueOf(index);
      throw new IndexOutOfBoundsException(message);
    }
    
    Stock element;  // The element to return     
    if (index == 0)
    {
      // Removal of first item in the list
      element = first.stock;    
      first = first.next;
      if (first == null)
        last = null;             
    }
    else
    {
      // To remove an element other than the first,
      // find the predecessor of the element to
      // be removed.
      Node pred = first;
      
      // Move pred forward index - 1 times
      for (int k = 1; k <= index -1; k++)
        pred = pred.next;
      
      // Store the data to return
      element = pred.next.stock;
      
      // Route link around the node to be removed
      pred.next = pred.next.next;  
      
      // Check if pred is now last
      if (pred.next == null)
        last = pred;              
    }
    return element;        
  }  
  
  /**
   The remove method removes an element.
   @param element The element to remove.
   @return true if the remove succeeded, 
   false otherwise.
   */
  
  public boolean remove(Stock element)
  {
    if (isEmpty()) 
      return false;      
    
    if (element.compareTo(first.stock)==0)
    {
      // Removal of first item in the list
      first = first.next;
      if (first == null)
        last = null;       
      return true;
    }
    
    // Find the predecessor of the element to remove
    Node pred = first;
    while (pred.next != null && !(pred.next.stock.compareTo(element)==0))
    {
      pred = pred.next;
    }
    
    // pred.next == null OR pred.next.value is element
    if (pred.next == null)
      return false;
    
    // pred.next.value  is element
    pred.next = pred.next.next;    
    
    // Check if pred is now last
    if (pred.next == null)
      last = pred;
    
    return true;       
  }
  
  public static void main(String [] args)
  {
    
  }
}