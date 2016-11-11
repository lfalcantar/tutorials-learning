public class problem1
{
  public static int natural(int x)
  {
    if(x ==1000)
    {
      return 0;
    }
    else if(x % 3== 0 || x % 5 == 0)
    {
      System.out.println(x);
      return   x + natural(x+1); 
    }
    
    else
    {
      
      return natural(x+1); 
    }
    
    
  }
  public static void main (String[]args)
  {
    int x=1;
    
   System.out.println( natural(x) ); 
    
  
  
  }
}