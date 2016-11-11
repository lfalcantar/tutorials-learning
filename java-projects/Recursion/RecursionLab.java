public class RecursionLab
{
  //*************Problem1**************************
  //************------------------***********************************
  /*Write a recursive method that takes as a parameters a non-negative integer and generates
   the following pattern of stars. If the non-negative integer is 4, then the pattern generated is:
   */ 
  public static void starsRecurcion(int n)
  {
    if(n==0){
      System.out.print("");
    }
    else 
    {
      starsRecurcion2(n);
      starsRecurcion(n-1);
      starsRecurcion2(n);
    } 
  }
  //**************helper***************************
  public static void starsRecurcion2(int n)
  {
    if(n==0)
    {
      System.out.println();
    }
    else 
    {
      System.out.print("x");
      starsRecurcion2(n-1);
    }
  }
  
  //*************Problem2**************************
  //************------------------***********************************
  /*Write a recursive method named vowels, that returns the number of vowels in a string.
   Also write a program to test your program.
   */ 
  
  public static int volwsRecurcion2(String s,int i)
  {
    if(i==s.length())
    {
      return 0;
    }
    
    else if(s.charAt(i)=='a'||s.charAt(i)=='e'||s.charAt(i)=='i'||s.charAt(i)=='o'||s.charAt(i)=='u')
    {
      
      return 1 + volwsRecurcion2(s,i+1);
      
    }
    
    else
    {
      
      return   volwsRecurcion2(s,i+1);
      
    }
  }
  //**************helper***************************
  public static int volwsRecurcion(String s)
  {
    return volwsRecurcion2(s,0);
  }
  
  //*************Problem3**************************
  //************------------------***********************************
  /*Write a recursive method named sum that Þnds and returns the sum of the elements of an
   int array. Also, write a program to test your method
   */ 
  public static int sumArrayRecurcion(int [] array,int i)
  {
    if(i==array.length)
    {
      return 0;
    }
    else
    {
      return array[i] += sumArrayRecurcion(array,i+1);
    }
  }
  //**************helper***************************
  public static int sumArrayRecurcion(int [] array)
  {
    
    return sumArrayRecurcion(array,0);
  }
  //*************Problem4**************************
  //************------------------***********************************
  /* Write a program that uses a recursive method to print a String backward. Use appropriate
   parameters in your method.
   */
  public static void backward(String x,int i)
  {
    
    if(i==x.length())
    {
      System.out.print("");
    }
    else
    {
      
      backward(x,i+1); 
      System.out.print(x.charAt(i));
      
    }
    
  }
  //**************helper***************************
  public static void backward(String x)
  {
    backward(x,0);
  }
  //*************Problem5**************************
  //************------------------***********************************
  /* Write a recursive method, power, that takes as parameters two integers x and y such that
   x is nonzero and returns xy . You can use the following recursive deÞnition to calculate xy .
   If y ³ 0,.
   */
  public static int power(int x ,int y)
  {
    if (y==0)
    {
      return  1;
    }
    else if(y==1)
    {
      return  x;
    }
    else
    {
      
      return x*power(x,y-1);
      
    }
    
  }
  //*************Problem6**************************
  //************------------------***********************************
  /* 6. Write a program that uses a stack to check whether a given string is a palindrome Ð i.e.,
   whether it reads the same from the beginning and from the end. For example, ÒMadam, IÕm
   Adam.Ó is a palindrome. According to the general rule of the palindrome, when we check
   whether a given String is a palindrome, we:
   */
  public static boolean palindrome(int i,String s,int k)
  {
    if(i==k)
    {
      return true;
    }
    else if(Character.toLowerCase(s.charAt(i))!=Character.toLowerCase((s.charAt(k))))
    {
      return false;
    }
    else
    {
      palindrome(i+1,s,k-1);
    }
    return true;
  }
  //*************helper **************************
  public static boolean palindrome(String s)
  {
    s.replace("[!-@-#-$-%-^-&-*-(-)-?-,-.-:-_]"," ");
    s.replace(" ", "");
    return palindrome(0,s,s.length()-1);
    
  }
  //*************Problem7**************************
  //************------------------***********************************
  /* 6. Write a recursive method named maxElement, which returns the largest value in an array
   of ints that is passed as an argument. The method should use recursion to Þnd the largest
   element.
   */
  public static int maxValue(int[] x,int i)
  { 

    if(1+i==x.length)
    {
      return x[0] ;
    }
    
    else if(x[1+i]>x[0])
    {
      x[0] = x[1+i];
      maxValue(x,i+1);
    }
    
    else
    {
      maxValue(x,i+1);
    }

    return x[0];
  }
  
  
  private static int maxValue(int[] x)
  {
    return maxValue(x,0);
  }
  //*************Problem8**************************
  //************------------------***********************************
  /*Write a recursive function that accepts two arguments into the parameters x and y. The
   function should return the value of x times y. Remember, multiplication can be per-
   formed as repeated addition as follows:
   */
  public static int multiplication(int x,int y)
  {
    if(y==0)
    {
      return 0;
    }
    else
    { 
      return x+multiplication(x,y-1);
    }
  }
  //*************Problem-9**************************
  //************------------------***********************************
  /*Write a recursive method named alphabeticalJoint that will combine two Strings in
   alphabetically order. For example:
   */
  public static void alphabeticalJoint(char []x,char[]y,int i,int k)
  {
    if((i+k)==x.length+y.length)
    {
      System.out.print("");
    }
    else if(i==x.length)
    {
      System.out.print(y[k]);
      alphabeticalJoint(x,y,i,k+1);
    }
    
    else if(k==y.length)
    {
      System.out.print(x[i]);
      alphabeticalJoint(x,y,i+1,k);
    }
    
    else
    {
      if(x[i]>y[k])
      {
        
        System.out.print(y[k]);
        alphabeticalJoint(x,y, i,k+1);
      }
      else
      {
        
        System.out.print(x[i]);
        alphabeticalJoint(x,y,i+1,k);
        
      }
      
    }
    
  }
  
  public static void alphabeticalJoint(String g,String s)
  {
    char[] y= g.toCharArray();
    char[] x= s.toCharArray();
    int i=0;
    int k=0;
    
    alphabeticalJoint(x,y,i,k);
    
  }
  //*************Problem-10**************************
  //************------------------***********************************
  /*Write print out the binary representation of a given number: Note that the binary represen-
   tation of 0 should be 0. e.g., 32 in binary is 100000 since,:
   */
  
  public static void binary(int x)
  {
    if(x==1) 
    {
      System.out.print("1");
      
    }
    else
    {
      if(x%2==0){
        binary(x/2);
        System.out.print("0");
        
      }
      else{
        binary(x/2);
        System.out.print("1");
        
      }
    }
  }
  
  
  
  public static void main(String[]args)
  {
    System.out.println("**********Problem number 1*****"); 
    starsRecurcion(5);
    
    System.out.println("**********Problem number 2*****"); 
    System.out.println(volwsRecurcion("faseeidoauru"));
    
    System.out.println("**********Problem number 3*****"); 
    int [] array={4,4,5,7,10,40,99};
    System.out.println(sumArrayRecurcion(array));
    
    System.out.println("**********Problem number 4*****"); 
    backward("123456789");
    System.out.println();
    
    System.out.println("**********Problem number 5*****");
    System.out.println(power(2,3));
    
    System.out.println("**********Problem number 6*****"); 
    System.out.println(palindrome("R.a,c'e_ca;r"));
    
    System.out.println("**********Problem number 7*****"); 
    int [] array2={1001,2,565788,7,10,4050,100,34,23,18000,34};
    System.out.println(maxValue(array2));
    
    System.out.println("**********Problem number 8*****"); 
    System.out.println(multiplication(4,3));
    
    System.out.println("**********Problem number 9*****"); 
    alphabeticalJoint("apple","apple"); 
    System.out.println();
    
    System.out.println("**********Problem number 10*****"); 
    binary(4569);
  }
}
