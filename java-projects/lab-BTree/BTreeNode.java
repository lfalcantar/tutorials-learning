public class BTreeNode{
  public int[] key;//array  int 
  public BTreeNode[] c; //un  array the nodetree 
  boolean isLeaf;
  public int n;
  private int T; //Each node has at least T-1 and at most 2T-1 keys
  
  public  BTreeNode(int t){
    T = t;
    isLeaf = true;
    key = new int[2*T-1];
    c = new BTreeNode[2*T];
    n=0; 
  }
  
  public boolean isFull(){
    return n==(2*T-1);
  }
  
  public int insert(int newKey){
    // Instert new key to current node
    // We make sure that the current node is not full by checking and
    // splitting if necessary before descending to node
    
    //System.out.println("inserting " + newKey); // Debugging code
    int i=n-1;
    int x =0;
    if (isLeaf){
      while ((i>=0)&& (newKey<=key[i])) {
        if(newKey==key[i]) 
          return -1;//duplicate return withoiut doinganything
        key[i+1] = key[i];
        i--;
        
      }
      if(x>=0){
        n++;
        key[i+1]=newKey;
      }
    }
    else{
      while ((i>=0)&& (newKey<=key[i])) {
        if(newKey==key[i])
          return -1;// find duplictte return without doing anithing 
          
        else
          i--;
      }
      if(x>=0){
        
        int insertChild = i+1;  // Subtree where new key must be inserted
        if (c[insertChild].isFull()){
          x++;// one more node.
          // The root of the subtree where new key will be inserted has to be split
          // We promote the mediand of that root to the current node and
          // update keys and references accordingly
          
          //System.out.println("This is the full node we're going to break ");// Debugging code
          //c[insertChild].printNodes();
          //System.out.println("going to promote " + c[insertChild].key[T-1]);
          n++;
          c[n]=c[n-1];
          for(int j = n-1;j>insertChild;j--){
            c[j] =c[j-1];
            key[j] = key[j-1];
          }
          key[insertChild]= c[insertChild].key[T-1];
          c[insertChild].n = T-1;
          
          BTreeNode newNode = new BTreeNode(T);
          for(int k=0;k<T-1;k++){
            newNode.c[k] = c[insertChild].c[k+T];
            newNode.key[k] = c[insertChild].key[k+T];
          }
          
          newNode.c[T-1] = c[insertChild].c[2*T-1];
          newNode.n=T-1;
          newNode.isLeaf = c[insertChild].isLeaf;
          c[insertChild+1]=newNode;
          
          //System.out.println("This is the left side ");
          //c[insertChild].printNodes(); 
          //System.out.println("This is the right side ");
          //c[insertChild+1].printNodes();
          //c[insertChild+1].printNodes();
          
          if (newKey <key[insertChild]){
            c[insertChild].insert(newKey);     }
          else{
            c[insertChild+1].insert(newKey);    }
        }
        else
          c[insertChild].insert(newKey);
      }
      
      
    }
    return x ;
  }
  
  
  public void print(){
    //Prints all keys in the tree in ascending order
    if (isLeaf){
      for(int i =0; i<n;i++)
        System.out.print(key[i]+" ");
      System.out.println();
    }
    else{
      for(int i =0; i<n;i++){
        c[i].print();
        System.out.print(key[i]+" ");
      }
      c[n].print();
    }
  }
  
  public void printNodes(){
    //Prints all keys in the tree, node by node, using preorder
    //It also prints the indicator of whether a node is a leaf
    //Used mostly for debugging purposes
    for(int i =0; i<n;i++)
      System.out.print(key[i]+" ");
    System.out.println(isLeaf);
    if (!isLeaf){
      for(int i =0; i<=n;i++){
        c[i].printNodes();
      }
    }
  }
}
