public class problem43
{
  public static boolean g(String h,int y)
  {

    for(int k = 0;k!=y;)
    {
      if(h.charAt(k)!= h.charAt(y))
      {
        return false;
      }
      y--;
      k++;
    
      

      
    }
    return true;
  }

  public static void main(String[]args)
  {
    for(int i = 900;i<1000;i++)
    {
      for(int j =900;j<1000;j++)
      {
        
        long temp = i*j;
        String t = ""+temp; 
        String p = new StringBuilder(t).reverse().toString();

        if(t.equals(p))
        {
          System.out.println(t);

        }

       
        
      }
    }
  }
}