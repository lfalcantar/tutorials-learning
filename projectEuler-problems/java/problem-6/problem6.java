import java.util.*;
public class problem6
{
  public static void main(String[]args)
  { 
    long square =0;
    long sum = 0;
    for(int i = 1;i<=100;i++)
    {
      square +=Math.pow(i,2);
    }
    
    for(int j = 1;j<=100;j++)
    {
      sum += j;
    }
    

    System.out.println(Math.pow(sum,2) - square);
  
  }
}