//luis alcantar
import java.util.*;
import java.io.*;
import java.lang.*;

public class SortingAlgorithms
  
{
      //***************************************************************************************
  /*
   * quick sort
   */ 
  public static void quicksort (Words[] array, int min, int max)
    
  {
    Words temp = new Words();
    
    int i=min, j=max;
    
    Words half = new Words();
     half=array[(min+max)/2];
    
    
    
    do
      
    {   
      
      while (array[i].getword().compareTo(half.getword()) < 0)
        
        i++;
      
      while (array[j].getword().compareTo(half.getword()) > 0)
        
        j--;
      
      if (i<=j)
        
      {
        
        temp=array[i];
        
        array[i]=array[j];
        
        array[j]=temp;
        
        i++;
        
        j--;
        
      }
      
    } while (i<=j);
    
    if (min<j)
      
      quicksort(array, min, j);
    
    if (i<max)
      
      quicksort(array, i, max);
    
  }
      //***************************************************************************************
  
  public static void print(Words[] x)
  {
    for(Words value:x)
      System.out.println(value);
    
  }
      //***************************************************************************************
  /*
   * 
   */
  public static Words[] fill(Words[] a )throws IOException
  {
    Scanner scan2 = new  Scanner(new File("words2.txt"));
    int i=0;
    while(scan2.hasNext())
    {
      String line = scan2.nextLine();
      String[] y = line.split(": ");
      Words wi = new Words(y[0],y[1]);
      // System.out.println(y[0]);
      //System.out.println(y[1]);
      // System.out.println(y[2]);
      // System.out.println("word 1 palabra  :"+wi.getword()+"la definition  :"+wi.getDefinition());
      a[i]=wi;
      i++;
      
      
    }
    //print(a);
    return a;
  }
      //***************************************************************************************
  /*
   * 
   */
  
  public static int sizearray(String file)throws IOException
  {
    Scanner scan = new Scanner(new File(file));
    int counter =0;
    while(scan.hasNext())
    {
      String line = scan.nextLine();
      counter= counter +1;
    }
    return counter;
  }
      //***************************************************************************************
  /*
   * 
   */ 
  public static Words[] bubblesort( Words[] a )throws IOException{
    int n =sizearray("words2.txt");
    // the first loop will travese all the array
    for(int i = 0; i < n; i++){
      // will control the "already ordered" positions
      for(int j = 1; j < (n-i); j++){
        // swap: in case you need to exchange
        //       a value from the array
        if(a[j-1].getword().compareTo( a[j].getword()) > 0){
          Words temp = new Words(a[j-1].getword(),a[j-1].getDefinition()); // save whatever is in a[j-1] into t
          a[j-1]=a[j];    // overrite what is in a[j-1] with a[j]
          a[j]=temp;         // write in a[j] what is saved in t
        }
        //print(a);
      }
      //System.out.println("========= Next iter n = "+(n-i)+"==========");
    }
    // print(a);
    return a;
  }
      //***************************************************************************************
  /*
   * selectionsort sort
   */
  public static Words[] selectionsort(Words[] array) {
    for ( int i = 0; i < array.length; i++ ) {
      
      //find minimum, starting from index i
      int minIndex = i;
      Words min = array[i];
      for ( int j = i + 1; j < array.length; j++ ) {
        if ( array[ j ].getword().compareTo(min.getword()) > 0) {
          minIndex = j;
          min = array[j];
        }
      }
      
      // now move the smallest element to the front, and the element at index i to the index of the minimal element
      Words temp = array[ i ];
      array[ i ] = min;
      array[ minIndex ] = temp;
    }
    return array;
  }
      //***************************************************************************************
  /*
   * insertionsort
   */ 
  
  public static Words[] insertionsort(Words[] a){
    int n = a.length;
    for (int i = 1; i < n; i++){
      int j = i;
      Words z = new Words();
      z = a[i];
      // in the applet is the "green" box
      // and it must hold the conditions in order to
      // store the value
      while ((j > 0) && (a[j-1].getword().compareTo(z.getword()) > 0)){
        // j > 0 means that you need to go back to pos 0
        // in case (a[j-1] > b) is not true
        a[j] = a[j-1];
        j--;
      }
      //System.out.println("========= Next iter n = "+(n-i)+"==========");
      a[j] = z;
      //print(a);
    }
    return a;
  }
      //***************************************************************************************
  /*
   * mergeSort_srt
   */ 
  
  public static Words[] mergeSort_srt(Words[] array,int lo, int n){
    int low = lo;
    int high = n;
    if (low >= high) {
      return array;
    }
    
    int middle = (low + high) / 2;
    mergeSort_srt(array, low, middle);
    mergeSort_srt(array, middle + 1, high);
    int end_low = middle;
    int start_high = middle + 1;
    while ((lo <= end_low) && (start_high <= high)) {
      if (array[low].getword().compareTo(array[start_high].getword()) > 0) {
        low++;
      } else {
        Words Temp = new Words();
        Temp = array[start_high];
        for (int k = start_high- 1; k >= low; k--) {
          array[k+1] = array[k];
        }
        array[low] = Temp;
        low++;
        end_low++;
        start_high++;
      }
    }
    return array;
  }
  
  
  public static void main(String[]args)throws IOException
  {
    
    
    Words[] d = new Words[sizearray("words2.txt")];
        Words[] y = new Words[sizearray("words2.txt")];
    Words[] x = new Words[sizearray("words2.txt")];
   //x = fill(d);
   x = insertionsort(fill(d));
   //print(x);
    
    
    
    
    // Words[] y = new Words[sizearray("words2.txt")];
    
    
   //***************************************************************************************    
    long time;
    System.out.println("Starting test insertion");
    time = System.currentTimeMillis();
    
    
    
    //bien insertionsort
    insertionsort(x);
    // y = insertionsort(fill(x));
    // print(y);
    time = System.currentTimeMillis() - time;
    System.out.println(" The test insetion took " + time + " milliseconds");
    
    //***************************************************************************************   
    System.out.println("Starting test selection ");
    time = System.currentTimeMillis();
    
    
    //bien selectionsort
    selectionsort(x);
    //y = selectionsort(fill(x));
    // print(y);
    
    time = System.currentTimeMillis() - time;
    System.out.println(" The test selection took " + time + " milliseconds");
    
//***************************************************************************************
    System.out.println("Starting test bubble");
    time = System.currentTimeMillis();
    
    
    //bien buuble
    bubblesort(x);
    //y = bubblesort(fill(x));
    //print(y);
    time = System.currentTimeMillis() - time;
    System.out.println(" The test bubble took " + time + " milliseconds");
    
//***************************************************************************************
    System.out.println("Starting test merge ");
    time = System.currentTimeMillis();
    
    
    //bien merge
   mergeSort_srt(x,0,sizearray("words2.txt")-1);
    //y =mergeSort_srt(x,0,sizearray("words2.txt")-1);
   // print(y);
    
    
    
    time = System.currentTimeMillis() - time;
    System.out.println(" The test merge took " + time + " milliseconds");
    
    //***************************************************************************************
      System.out.println("Starting test quick ");
    time = System.currentTimeMillis();
    
    
    //bien merge
    quicksort (x, 0,sizearray("words2.txt")-1 );
    
    
    time = System.currentTimeMillis() - time;
    System.out.println(" The test quic took " + time + " milliseconds");
  }
}
