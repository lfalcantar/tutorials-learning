public class BTree{
  private BTreeNode root;
  private int T; //2T is the maximum number of childen a node can have
  private int height;
  private int size; 
  private int nodes;
  
  public BTree(int t){
    root = new BTreeNode(t);
    T = t;
    height = 0;
    size=0;
    nodes++;
  }
/*
 * The method print size prints the field size
 */ 
  public void printsize(){
    System.out.println("Tree size is "+size);
  }
  /*
 * The method print height prints the field height
 */ 
  public void printHeight(){
    System.out.println("Tree height is "+height);
  }
  /*
 * The method print nodes fild prints the field nodes
 */ 
  public void printNodesField(){
    System.out.println("The number of nodes is "+nodes);
  }
  /*
 * The method insert check if the root is full if is ful it splits and after checking it calls the method inset of the root
 * The method BTreeNOde.insert it returns a integer,  ir the integet is 0 it means it perform an insertion 
 * if the method returns -1 it means that it find a duplicate
 * more than o is the times the roots were splited
 */ 
  public void insert(int newKey){
    if (root.isFull()){//Split root;
      split();
      height++;
      nodes+=2;
    }
    int x =root.insert(newKey);
    if(x>=0)
    {
      if(x==0)
      {
        size++;
      }
      else
      {
        size++;
        nodes+=x;
      }
    }

  }
  /*
 *  a public method  that call a private method with the root
 */ 
  public int numberLeaves()
  {
    if(root.n==0)
      System.out.println("the root is empty");
    return numberLeaves(root);
  }
  /*
 * The methodnumberLeaves  finds the number of leaves 
 * base case: if  is a leaf it returns one else
 *  it transverse the tree with recursion
 */ 
  private int numberLeaves(BTreeNode t)
  {
    if(t.isLeaf)
      return 1;
    else
    {
      int total=0;
      for(int i =0;i<=t.n;i++)
      {
        total+=numberLeaves(t.c[i]);
      }
      return total;
    }
    
  }
    /*
 * checks if the nodes are full
 */ 
  public boolean allFull()
  {
    if(root.n==0)
      return false;
    else
      return allFull(root);
  }
    /*
 *   we check if the number of nodes is  equal to t.n   and we return true or false
 * 
 */ 
  private boolean allFull(BTreeNode t)
  {
    if(t.isLeaf)
    {
      if(t.n==t.key.length)
        return true;
    }
    if(t.n!=t.key.length)
      return false;
    
    for(int i=0;i<=t.n;i++)
    {
      if(!(allFull(t.c[i])))
        return false;
    }
      return true;
  }
    /*
 *  to find the smaller element
 */ 
  public int smallerElement()
  {
    if(root.n ==0)
      return -1;
    return smallerElement(root);
  }
    /*
 *   it goes to the leave  in position t.c[0 ] when it find the leave it return the key (t.key[0])
 */ 
  private int smallerElement(BTreeNode t )
  {
    if(t.isLeaf)
      return t.key[0];
    return smallerElement(t.c[0]);
  }
  
  /*
   *  @param integer the integer  is used to compere the number of keys in each node
   */ 
  public int nodesWithNElements(int d)
  {
    if(d>=root.key.length||root.n==0)
      return -1;
   return  nodesWithNElements(root,d);
  }
  /*
   *  compares each node with the integer d,  and returns the number of elements that t.n ==d
   */ 
  private int nodesWithNElements(BTreeNode t,int d)
  {
    if(t.isLeaf)
    {
      if(t.n==d)
        return 1;
      else
      return 0;
    }
    if(t.n==d)
      return 1;
    
    int total=0;
    for(int i=0;i<=t.n;i++)
      total+=nodesWithNElements(t.c[i],d);
    return total;
    }
  
   /*
   * print the value
   */
  public void print(){
    // Wrapper for node print method
    root.print();
  }
   /*
   * 
   */ 
  private int size(BTreeNode t)
  {
    
    if(t.isLeaf)
      return t.n;
    else
    {
      int total =t.n;
      for(int i =0; i<=t.n;i++)
        total += size(t.c[i]);
      return total;
    }
  }
  
  public int size()
  {
    if(root.n==0)
      return -1; 
    else
      return size(root);
  }
   /*
   * 
   */ 
  public int numberNodes()
  {
    if(root.n==0)
      return -1;
    else
      return 1+ numberNodes(root);
  }
  
  private int numberNodes(BTreeNode t)
  {
    if(t.isLeaf)
      return 0;
    else
    {
      int total=0;
      for(int i = 0;i<=t.n;i++)
        total+= 1  + numberNodes(t.c[i]);
      return total;
    }
    
  }
   /*
   * find the key  in the tree retrn true or false it the tree was find 
   */ 
  private boolean findK(BTreeNode t,int k)
  {
    if(t.isLeaf)
    {
      for(int i =0;i<t.n;i++)
      {
        if(t.key[i]==k)
          return true;
      }
      
    }
    else{
      for(int i = 0;i<=t.n;)
      {
        if(i==t.n)
          return findK(t.c[t.n],k);
        if(t.key[i]==k)
          return true;
        if(t.key[i]<k)
          i++;
        else
          return findK(t.c[i],k);
      }
      
    }
    
    return false;
  }
  /*
   *  check taht the root i not empty an call the metho find it was not empty
   */ 
  public boolean findK(int k)
  {
    if(root.n==0)
      return false;
    else
      return  findK(root,k);
  }
   /*
   * prints the tree in assending order 
   */ 
  private void assending(BTreeNode t)
  {
    if(t.isLeaf)
    {
      for(int i =0;i<t.n;i++)
      {
        System.out.print(t.key[i]+ " ");
      }
    }
    else 
    {
      
      for(int i =0;i<t.n;i++){
        assending(t.c[i]);
        System.out.print(t.key[i] + " ");
      }
      assending(t.c[t.n]);
      
    }
  }
  /*
   * The method assending  that is public it checks if the tree is empty aftet that call the assendingwith the root as a parameter
   */ 
  public void assending()
  {
    if(root.n ==0)
      System.out.println("the tree is empty");
    assending(root);
  }
   /*
   * the height a method that returns the height of the tree 
   */ 
  private int heigh(BTreeNode t)
  {
    int x = t.isLeaf?0:1 +heigh(t.c[t.n]);
    return x;
  }
  public int heigh()
  {   
    if(root.n==0)
      return -1;
    return heigh(root);
  }
   /*
   * prints the tree in desending order  doing trought the loop
   */ 
  private void desending(BTreeNode t)
  {
    if(t.isLeaf)
    {
      for(int i=t.n-1;i>=0;i--)
        System.out.print(t.key[i]+" ");
    }
    else 
    {
      desending(t.c[t.n]);
      for(int i =t.n-1;i>=0;i--){
        System.out.print(t.key[i] + " ");
        desending(t.c[i]);
      }
    }
  }
  /*
   * it checks that the root is not empty
   */ 
  public void desending()
  {
    if(root.n==0)
      System.out.println("The tree is empty!");
    else
      desending(root);
  }
   /*
   * print lares element it goes to the end og the leaves recusively by t.c[0] and return the key at position key[t.n-1]
   */ 
  private void largestElement(BTreeNode t)
  {
    if(t.isLeaf)
      System.out.println(t.key[t.n-1]);
    
    else 
      largestElement(t.c[t.n]);
  }
  /*
   * it checks thata the tree is not empty
   */ 
  public void largestElement()
  {
    if(root.n==0)
      System.out.println("The tree is empty!");
    else
      largestElement(root);
  }
  /*
   * it checks thata the tree is not empty
   */ 
  public int nodesWithMinimalNumeberOfElements()
  {
     if(root.n==0)
      return -1;
    else
      return nodesWithMinimalNumeberOfElements(root);
  }
  /*
    * finds the  numbers of nodes with the minimal number of elements 
   */ 
  public int nodesWithMinimalNumeberOfElements(BTreeNode t)
  {
    if(t.isLeaf){
      if(t.n==(t.key.length/2))
        return 1;
      else 
        return 0;
    }
    if(t.n==(t.key.length/2))
      return 1;
 
    int total =0;
    for(int i=0;i<=t.n;i++)
      total+=nodesWithMinimalNumeberOfElements(t.c[i]);
    return total;
    
  }
  /*
   * it checks thata the tree is not empty
   */ 
   public int numberOfNodeWithFullElements()
  {
     if(root.n==0)
      return -1;
    else
      return numberOfNodeWithFullElements(root);
  }
   /*
    * finds the  numbers of nodes with the maximun number of elements 
   */ 
  public int numberOfNodeWithFullElements(BTreeNode t)
  {
    if(t.isLeaf){
      if(t.n==(t.key.length))
        return 1;
      else 
        return 0;
    }
    if(t.n==(t.key.length))
      return 1;
 
    int total =0;
    for(int i=0;i<=t.n;i++)
      total+=numberOfNodeWithFullElements(t.c[i]);
    return total;
    
  }
   public int nodesInDeepN(int n)
  {
     if(root.n==0)
      return -1;
     else if(n==1)
       return root.n;
    else
      return nodesInDeepN(root,n);
  }
  public int nodesInDeepN(BTreeNode t,int n)
  {
    if(n==1)
    {
      return t.n;
    }
    else
    {
      int total =0;
      for(int i=0;i<=t.n;i++)
        total+=nodesInDeepN(t.c[i],n-1);
      return total;
   
    }
   
    
    
  }
  
   /*
   * 
   */ 
  public void printNodes(){
    // Wrapper for node print method
    
    if(root.n==0)
      System.out.println("The tree is empty!");
    else
      root.printNodes();
  }
  
  public void split(){
    // Splits the root into three nodes.
    // The median element becomes the only element in the root
    // The left subtree contains the elements that are less than the median
    // The right subtree contains the elements that are larger than the median
    // The height of the tree is increased by one
    
    //System.out.println("Before splitting root");
    //root.printNodes(); // Code used for debugging
    BTreeNode leftChild = new BTreeNode(T);
    BTreeNode rightChild = new BTreeNode(T);
    leftChild.isLeaf = root.isLeaf;
    rightChild.isLeaf = root.isLeaf;
    leftChild.n = T-1;
    rightChild.n = T-1;
    int median = T-1;
    for (int i = 0;i<T-1;i++){
      leftChild.c[i] = root.c[i];
      leftChild.key[i] = root.key[i];
    }
    leftChild.c[median]= root.c[median];
    for (int i = median+1;i<root.n;i++){
      rightChild.c[i-median-1] = root.c[i];
      rightChild.key[i-median-1] = root.key[i];
    }
    rightChild.c[median]=root.c[root.n];
    root.key[0]=root.key[median];
    root.n = 1;
    root.c[0]=leftChild;
    root.c[1]=rightChild;
    root.isLeaf = false;
    //System.out.println("After splitting root");
    //root.printNodes();
  }
}
