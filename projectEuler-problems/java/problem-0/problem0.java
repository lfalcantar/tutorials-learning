public class problem0
{
  public static void ing(int x, long y)
  {
    if(x >1000000000)
    {
      System.out.println("ya");
      
    }
    else
    {
      if(y%x == 0){
        System.out.println(x);
        ing(x+1,y);
      }
      else 
        ing(x+1,y);
        
    }
  }
  public static void main (String[]args)
  {
    long p =600851475143l;
    ing(1,p);
  
  }
}