public class Node{
  public Stock stock;
  public Node next;
  
  public Node(Stock s, Node next){
    this.stock = s;
    this.next  = next;
   
  }
  
  public Node(Stock s){
    this.stock=s;
    this.next  = null;
  }
  
  public String toString(){
    return stock.name+"->"+next;
  }
  
 
  
  public static void main(String[] args){
    
    
  }
  
}