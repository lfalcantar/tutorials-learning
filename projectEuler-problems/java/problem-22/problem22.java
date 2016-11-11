import java.io.*;
import java.util.*;

public class problem22
{
  public static ArrayList<KIP> capacity(String[]x)throws IOException
  {
    int c = 0;
    Scanner scan = new Scanner(new File("names.txt"));
    ArrayList<KIP> l = new ArrayList<KIP>();
    
    while(scan.hasNext())
    {
      String line = scan.nextLine();
      String[] spli = line.split(",");
      for(int i = 0;i<spli.length;i++){
        KIP k = new KIP(spli[i]);
        l.add(k);
      }
    }
    
    Collections.sort(l, new KIPComparator());
    
    c = l.size();
    System.out.println(c);

    
    return l;
  }
  

  
  
  public static void main(String[]args)throws IOException
  { 
    long time;
    time = System.currentTimeMillis();
    String[] x = new String[5163];
    ArrayList<KIP> n = new ArrayList<KIP>();
    n = capacity(x);
    
     long z = 0;
     long k = 0;
     
     
     
    for(int i = 0;i<n.size();i++){
      for(int j =0;j < n.get(i).getData().length();j++)
      {
       //System.out.println(n.get(i).getData());
        if(n.get(i).getData().charAt(j) == 'A')
        {
          z += 1;
        }
       else  if(n.get(i).getData().charAt(j) == 'B')
        {
          z += 2;
        }
      else   if(n.get(i).getData().charAt(j) == 'C')
        {
          z +=  3;
        }
      else   if(n.get(i).getData().charAt(j) == 'D')
        {
          z += 4;
        }
      else   if(n.get(i).getData().charAt(j) == 'E')
        {
          z += 5;
        }
       else  if(n.get(i).getData().charAt(j) == 'F')
        {
          z += 6;
        }
       else  if(n.get(i).getData().charAt(j) == 'G')
        {
          z += 7;
        }
    
       else  if(n.get(i).getData().charAt(j) == 'H')
        {
          z += 8;
        }
       else  if(n.get(i).getData().charAt(j) == 'I')
        {
          z += 9;
        }
       else  if(n.get(i).getData().charAt(j) == 'J')
        {
          z += 10;
        }
       else  if(n.get(i).getData().charAt(j) == 'K')
        {
          z +=11;
        }
       else  if(n.get(i).getData().charAt(j) == 'L')
        {
          z +=12;
        }
       else  if(n.get(i).getData().charAt(j) == 'M')
        {
          z +=13;
        }
       else  if(n.get(i).getData().charAt(j) == 'N')
        {
          z +=14;
        }
       else  if(n.get(i).getData().charAt(j) == 'O')
        {
          z +=15;
        }
       else  if(n.get(i).getData().charAt(j) == 'P')
        {
          z +=16;
        }
       else  if(n.get(i).getData().charAt(j) == 'Q')
        {
          z +=17;
        }
      else   if(n.get(i).getData().charAt(j) == 'R')
        {
          z +=18;
        }
        else if(n.get(i).getData().charAt(j) == 'S')
        {
          z +=19;
        }
        else if(n.get(i).getData().charAt(j) == 'T')
        {
          z +=20;
        }
       else  if(n.get(i).getData().charAt(j) == 'U')
        {
          z +=21;
        }
       else  if(n.get(i).getData().charAt(j)== 'V')
        {
          z +=22;
        }
        else if(n.get(i).getData().charAt(j) == 'W')
        {
          z +=23;
        }
        else if(n.get(i).getData().charAt(j) == 'X')
        {
          z+=24;
        }
        else if(n.get(i).getData().charAt(j) == 'Y')
        {
          z +=25;
        }
        else if(n.get(i).getData().charAt(j) == 'Z')
        {
          z +=26;
        }
     //  System.out.println(" z in his position"+ (i) +" is" + z+ " and j i" +j);
       // System.out.println(n.get(i).getData().length());
        //System.out.println(n.get(i).getData().charAt(j));

        
      }
      
      z *=( i+1) ; 
      k += z;
      z =0;
    }
    
    System.out.println("z= : "+z);
        System.out.println("k= : "+k);
    
    
    

    
    
    
    time = System.currentTimeMillis() - time;
    System.out.println(" The test took  " + time + " milliseconds");
    
    
  }
  
 

}