public class problem9
{
  public static void main(String[]args)
  {
    for(int  i =0;i < 100;i++)
    {
      for(int j = 0;i<100;j++)
      {
        for(int k =0;i<100;k++)
        {
          
          if(i+k+j == 1000 && Math.pow(i,2) + Math.pow(j,2) == Math.pow(k,2))
          {
            System.out.println("i : "+i+ "j : "+j+ "k : "+ k);
          }
        }
      
      }
    }
  }

}