public class problem2
{
  public static long sumFigonashi()
  {
    int i =3;
    int j = 5;
    int c = 2;
    int tep = 0;
    
    while((i +j) < 100000)
    {
      tep = i+j;
    //  System.out.println(i+j);
      if((i+j)%2==0)
      {
        //System.out.println(i+j);
        c+=(i+j);
      }
      
      i = j;
      j = tep;
      
      
    }
    return c;
}
  public static void main(String[]args)
  {
  System.out.println(sumFigonashi());
  }
}