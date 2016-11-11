import java.util.*;

public class BTreeTest{

  public static void main(String[] args)   {
   Random generator = new Random();
  BTree T = new BTree(3);
//  for (int i=0;i<30;i++){
//   T.insert(generator.nextInt(100));
//  }
  
  //T.print();
  
  T.insert(23);
  T.insert(22);
  T.insert(21);
  T.insert(20);
  T.insert(19);
  T.insert(18);
  T.insert(17);
  T.insert(16);
  T.insert(15);
  T.insert(14);
  T.insert(13);
  T.insert(12);
  T.insert(11);
  T.insert(9);
  T.insert(8);
  T.insert(4);
  T.insert(23);
  T.insert(23);
  
  System.out.println("There are : "+T.size());
  System.out.print("The size of the tree(field) : ");
  T.printsize();
  System.out.println("There heigh is : "+T.heigh());
  System.out.print("There heigh is (field): ");
  T.printHeight();
  System.out.print("There Largest element : ");
  T.largestElement();
  System.out.println("There Smallest element : "+ T.smallerElement());
  System.out.print("Desending order : " );
  T.desending();
  System.out.println("");
  System.out.print("Find an element 19 ---->");
  System.out.println(T.findK(19));
  T.assending();
  System.out.println("");
  System.out.println("The numeber of nodes is : ->"+T.numberNodes());
  System.out.print("The numeber of nodes is(Field) : ->"); 
  T.printNodesField();
  System.out.println("are all nodes full : "+ T.allFull());
  System.out.println("thre are nodes with 4 elements : "+ T.nodesWithNElements(4));
  System.out.println("The number of leaves in the tree : "+ T.numberLeaves());
  System.out.println("The Nodes with the minimal number of keys : "+T.nodesWithMinimalNumeberOfElements());
  System.out.println("The Nodes with the Maximum number of keys : "+T.numberOfNodeWithFullElements());
  int n=2;
  System.out.println("The number of kesy in deep 1 is : "+T.nodesInDeepN(n));


 }
}
 
