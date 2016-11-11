import java.util.*;
import java.text.*;
public class Lab4
{
  static int table_Size=0;
  static int timesRehash =0;
  static int size =0;
  static double startValueStock=0;
  static Random r = new Random();  
  static Scanner s= new Scanner(System.in);
  static DecimalFormat dF = new DecimalFormat("0.00");
  static LinkedList[] hash_Table ;
  static LinkedList[] rehash ;
  static Stock maxValue;
  static Stock minValue;
  static Stock moreTran;
  
  
  
  
  public static void simulation(int x)
  {
    String S="";  
    double v=0; 
    for(int i=0;i<x;i++){
      S = "";  
      for(int k=0; k<=r.nextInt(4);k++)  
        S+=(char)(65 + r.nextInt(26));        
      v=r.nextGaussian()*3.+.1;
      
      double k = Double.parseDouble(dF.format(v));
      Stock temp = new Stock(S);
      if(!find(temp,k)){
        
        size++;
        Stock nsize = new Stock(S,k,startValueStock);
        add(nsize);
        
      }
      
    }
    
  }
  
  public static void initializeHashTable(LinkedList[] x)
  {
    for(int i =0; i < x.length; i++){
      x[i] = new LinkedList();
    }
    
  }
  
  public static int hashFunction(Stock s)
  {
    

    int name = s.name.length();
    int hash=7;
    for (int i=0; i < name; i++) {
      hash = hash*31+s.name.charAt(i);
    }
    
    return hash%table_Size;
  }
  
  public static void rehashMethod()
  {
    
    // to copy elements in secodary list declare new hastable
    rehash = new LinkedList[table_Size];
    
    //initialize the secondaty hastable
    initializeHashTable(rehash);
    
    // copy elements from has_table to rehash
    for(int i =0; i < hash_Table.length; i++){
      
      for(Node t=hash_Table[i].first;t!=null;t=t.next)
      {
        rehash[hashFunction(t.stock)].add(t.stock);
      }
      
    }
    // next prime in the to rehash
    table_Size=table_Size*2-1;
    // overwrite the hash_Table variable to with other size
    hash_Table = new LinkedList[table_Size];
    
    
    //initialize th linklist in every  position
    initializeHashTable(hash_Table);
    
    //re enter values to the  original table
    for(int i =0; i < rehash.length; i++){
      
      for(Node t=rehash[i].first;t!=null;t=t.next)
      {
        hash_Table[hashFunction(t.stock)].add(t.stock);
      }
      
    }
    
    
    timesRehash+=1;
  }
  
  
  public static void add(Stock s)
  {
    if(loadFactor()>=1)
      rehashMethod();
    
    hash_Table[hashFunction(s)].add(s); 
  }
  
  
  
  public static boolean find(Stock s, double z)
  {
    
    
    return hash_Table[hashFunction(s) ].find(s,z);
    
    
  }
  
  public static double loadFactor()
  {
    return (double)(size/table_Size);
  }
  
  public static int emptyRows()
  {
    int x=0;
    for(int i =0 ;i<hash_Table.length;i++)
    {
      x += hash_Table[i].first==null ? 1:0;
    }
    return x;
  }
  
  
  public static int largerRow()
  {
    int x=-1;
    int y=0;
    for(int i =0 ;i<hash_Table.length;i++)
    {
      y = hash_Table[i].size();
      if(y>x)
        x=y;
    }
    return x;
  }
  public static void findall()
  {
    maxValue=new Stock("max",0);
     minValue=new Stock("max",startValueStock);
    moreTran=new Stock("more",0);
    
    
    for(int i =0;i<hash_Table.length;i++)
    {
      for(Node t =hash_Table[i].first;t!=null;t=t.next)
      {
        if(t.stock.stockValue>=maxValue.stockValue)
          maxValue=t.stock;
        
        if(t.stock.stockValue<=minValue.stockValue)
          minValue=t.stock;
        
        if(t.stock.numberTransaction>maxValue.numberTransaction)
          moreTran=t.stock;
      }
      
    }
    
    
  }
  
  public static void main(String[]args)
  {
    table_Size=11;
    
    hash_Table= new LinkedList[table_Size];
    
    
    //initialize every position in the array
    initializeHashTable(hash_Table);
    
    String ans= "y";
    String ans2= "";
    //how many elements to simulate
    System.out.println("Enter the number of transactions: ");
    int x =Integer.parseInt(s.nextLine());
    System.out.println("Initial value of all stocks:");
    int y =Integer.parseInt(s.nextLine());
    startValueStock =y;
    System.out.println("Display transactions (Y/N)? Y  ");
    ans2 =s.nextLine();
    System.out.println(x +" transactions will be processed: \n ");
    
    while(ans.equalsIgnoreCase("y")){
      
      simulation(x);
      
      if(ans2.equalsIgnoreCase("y"))
      {
        for(int i =0;i<hash_Table.length;i++)
        {
          
          System.out.println(hash_Table[i]);
        }
      }
      
      findall();
        
      System.out.println("The stock with the highest value is "+maxValue.name+", with a value of $"+dF.format(maxValue.stockValue)); 
        
      System.out.println("The stock with the lowest value is "+minValue.name+", with a value of $"+dF.format(minValue.stockValue));
        
      System.out.println("The stock with the most transactions is "+moreTran.name+", with "+moreTran.numberTransaction); 
      System.out.println("");
      System.out.println("");
        
      System.out.println("Data stored in a hash table of size "+table_Size+" . There are "+size+" items in the " 
                           +" table, the longest list has length "+largerRow()+" , load factor is "+dF.format(loadFactor()) +", and  "
                           +" there are "+emptyRows()+" empty slots in the table. The table size was doubled "+timesRehash+" times. ");
      
      
      
      
      
      System.out.println("Do you want to run the simulation again (Y/N)? N");
      ans =s.nextLine();
    }
    
  }
}
