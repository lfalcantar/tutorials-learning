import java.util.*; // Random class & Sort 

public class Lab1
{
  //*******************************"1"************************************************
  //constant iteratibe "BigO(1)"
  public static double Iconstant()
  {
    int sum=0;
    for(int i =1;i<=10;i++)
    {
      sum+=i;
    }
    return sum;
    
  }
  //recursive"BigO(1)"
  public static double Rconstant(int n)
  {
    if(n==2100)
      return 0;
    else
      return  n*n + Rconstant(n+1);
    
    
  }
  //********************************N************************************************
  //Linear Iterative
  public static double Ilinear(int n)
  {
    double sum =0;
    for(int i =n; i>0; i--)
    {
      sum+=i/2 +i;
    }
    return sum;
    
    
  }
  //Linear recursive
  public static double Rlinear(int n)
  {
    if(n == 1)
    {
      return 1;
    }
    else
    {
      return (n/2+n) + Rlinear(n -1);
    }
  }
  //***********************************N^2*********************************************
  //N^2 Iterative
  public static int Isquare(int[][] x)
  {
    int sum = 0;
    for(int i =0;i< x.length;i++)
    {
      for(int j =0;j< x[i].length;j++)
      {
        sum+=x[i][j];
      }
    }
    return sum;
  }
  
  //N^2 Recursive
  public static int Rsquare(int[][] x, int i ,int j)
  {
    if(i == x.length)
      return 0;
    if(j == x[i].length)
      return Rsquare(x,i+1,j=0);
    else 
      return x[i][j] + Rsquare(x,i,j+1);
    
  }
  
  //******************************N^3**************************************************
  //N^3 Iterative
  public static int[][] IMatrixmultiplication(int[][] x,int[][] y)
  {
    
    int[][] c = new int[x.length][y[0].length];
    
    for(int i = 0;i< x.length;i++)
    {
      for(int j =0;j <y[0].length;j++)
      {
        for (int k =0;k<x[0].length;k++)
        {
          c[i][j] += x[i][k] * y[k][j];
        }
      }
    }
    return c;
  }
  
  //N^3 REcursive  "T(N) = 3T(n+1) + 1
  public static int[][] RMatrixmultiplication(int [][]x,int[][]y,int[][] c,int i,int j, int k)
  {
    if( i== x.length)
      return c;
    if(j==y[0].length)
      return   RMatrixmultiplication(x,y,c,i+1,j=0,k);
    if(k==x[0].length)
      return  RMatrixmultiplication(x,y,c,i,j+1,k=0);
    else {
      c[i][j] +=x[i][k]*y[k][j];
      return  RMatrixmultiplication(x,y,c,i,j,k+1);
    }
  }
  //herlper
  public static int[][] RMatrixmultiplication(int [][] x ,int [][] y)
  {
    int[][] c = new int[x.length][y[0].length];
    return RMatrixmultiplication(x,y,c,0,0,0);
    
  }
  //***********************************logN*********************************************
  //logN iterative
  public static int ILog(int n)
  {
    int sum =0;
    for(int i =1;i<n;i+=i*i)
    {
      sum+=i;
    }
    return sum;
  }
  //logN Recursive "T(N) = T(N*N) +1
  public static int RLog(int n , int i)
  {
    if(i>=n)
      return 0;
    else
      return i + RLog(n,i+(i*i));
  }
  //helper
  public static int RLog(int n)
  {
    return RLog(n,1);
  }
  //*************************************NLogN *******************************************
  //NLogN Iterative
  public static void  INlogn(int[] x)
  {
    Arrays.sort(x);//sort uses quick sort BigO(nlogn)
  }
  
  //NLogN Recursive "T(N) = 2T(n/2) + n"
  public static double RNlogn(int n)
  {
    if(n==0)
      return Ilinear(n);
    
    else
    {
      return 1 + RNlogn(n/2) + RNlogn(n/2) ;
    } 
  }
  //***********************************N^2LogN*********************************************
  //N^2LogN Iterative
  
  //N^2LogN Recursive "T(N) = 4T(n/2) + n^2"
  public static void RN2logn(int n)
  {
    
    if(n>0)
    {
      for(int i =0;i<4;i++)
      {
        RN2logn(n/2);
        //System.out.println(n);
      }
    }
    for(int i =0; i<n;i++)
    {
      
      for(int j =0;j<n;)
      {
        j++;
      }
    }    
  }
  //************************************N^3LogN ********************************************
  //N^3LogN Iterative
  
  //N^3LogN Recursive "T(N) = 8T(n/2) + n^3"
  public static void RN3logn(int n)
  {
    
    if(n>0)
      for(int i =0;i<8;i++)
    {
      RN2logn(n/2);
      // System.out.println(n);
    }
    for(int i =0; i<n;i++)
    {
      for(int j =0;j<n;j++)
      {
        for(int k=0;k<n;)
        {
          k++;
        }
      }
    }
    
    
  }
  //*************************************2^n*******************************************
  //2^n iterative 
  public static void I2n(int n)
  {
    for(int i =0; i< Math.pow(2,n);i++)
    {
      //System.out.println("2^n");
    }
  }
  
  
  //2^n recursive 
  public static int R2n(int n)
{
    if(n == 1 || n == 2)
    { 
      return 1;
    }
    else 
    {
      return R2n(n-2) + R2n(n-1);
    }
  }
  //********************************************************************************
  
  /*
   *help to print the 2 dimencional arrays , to proof they are correct. 
   */ 
    
  public static void print(int[][] x)
  {
    for(int i =0;i<x.length;i++)
    {
      for(int j=0;j<x[i].length;j++)
      {
        System.out.print(x[i][j]+ "\t");  
      }
      System.out.println("");
    }
  }

  /*TimeAverage  is used for testing  it  computers a method 10 times and add the time for each of the iteration
   * @return sum/10 sum is the addition of the iteration and 10 the ten times , returns teh average time .
   * 
   */ 
  public static double TimeAverage()
  {
    double sum =0;
    long time;
    int[][] matrix = {{15,59,6,4,5,6,7,8},{9,32,43,3,87,65,32,23},{15,59,6,4,5,6,7,8},{9,32,43,3,87,65,32,23},{15,59,6,4,5,6,7,8},{9,32,43,3,87,65,32,23},{15,59,6,4,5,6,7,8},{9,32,43,3,87,65,32,23}};
    int[][] a =new int[6][4];
    int[][] b =new int[6][6];
    
    a[0][0]=1;
    a[1][0]=4;
    a[2][0]=6;
    a[0][1]=1;
    a[1][1]=4;
    a[2][1]=6;
    a[0][2]=1;
    a[1][2]=4;
    a[2][2]=6;
    a[0][3]=1;
    a[1][3]=4;
    a[2][3]=6;
    //
    a[3][0]=1;
    a[4][0]=4;
    a[5][0]=6;
    a[3][1]=1;
    a[4][1]=4;
    a[5][1]=6;    
    a[3][2]=1;
    a[4][2]=4;
    a[5][2]=6;
    a[3][3]=1;
    a[4][3]=4;
    a[5][3]=6;   
    
    b[0][0]=2;
    b[0][1]=5;
    b[0][2]=7;
    b[1][0]=3;
    b[1][1]=8;
    b[1][2]=9;
    b[2][0]=3;
    b[2][1]=8;
    b[2][2]=9;
    b[0][3]=2;
    b[0][4]=5;
    b[0][5]=7;
    b[1][3]=3;
    b[1][4]=8;
    b[1][5]=9;
    b[2][3]=3;
    b[2][4]=8;
    b[2][5]=9;
    b[3][0]=2;
    b[3][1]=5;
    b[3][2]=7;
    b[3][3]=3;
    b[3][4]=8;
    b[3][5]=9;
    b[4][0]=2;
    b[4][1]=5;
    b[4][2]=7;
    b[4][3]=3;
    b[4][4]=8;
    b[4][5]=9;
    b[5][0]=2;
    b[5][1]=5;
    b[5][2]=7;
    b[5][3]=3;
    b[5][4]=8;
    b[5][5]=9;
    
    int[] nln =new int[10000];
    nln = fill(nln);
    
    for(int i =0;i<10;i++)
    {
      time =System.nanoTime();
      
      R2n(50);
      
      sum += System.nanoTime()-time;
    }
    return sum/10;
  }
  
  public static int[] fill(int [] x)
  {
    Random r =new Random();
    for(int i=0;i<x.length;i++)
    {
      x[i]= r.nextInt(100);
      
    }
    return x;
  }
  public static void main(String[]args)
  {
    long time;
    
    System.out.println("******************Constant**********************");
    System.out.println("");
    time = System.nanoTime();
    //System.out.print(Iconstant());//"20"time average  1000.0M-Time"200" time average 4300.0M-Time 2000 time average 55400.0.0M
    System.out.println(" -> The time for constant  iterative is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    time = System.nanoTime();
    //System.out.print(Rconstant(1));//"20"time average  3400.0M-Time"200" time average 27600.0M-Time 2000 time average 211900.0M
    System.out.println(" -> The time for constant  Recursive is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    
    System.out.println("******************Linear**********************");
    time = System.nanoTime();
    //System.out.print(Ilinear(5000));//10-time 700.0--100-time 2300--1000-time 24700--10000- time 314100
    System.out.println(" ->  The time for Linear   iterative is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    time = System.nanoTime();
    //System.out.print(Rlinear(5000));//10-time 1200--100-time 12200--1000-time 88200--10000 N?A
    System.out.println(" ->  The time for Linear   Recursive is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    
    System.out.println("******************square**********************");
    time = System.nanoTime();
    int[][] matrix = {{15,59,6},{9,32,43,3,87,65,32,23}};
    //System.out.print(Isquare(matrix));//2*8 time 1200 - 4*8 time 1300 - 8*8 time 2600 
    System.out.println(" ->  The time for Square   iterative is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    time = System.nanoTime();
    //System.out.print(Rsquare(matrix,0,0));//2*8 time 3200 - 4*8 3800 - 8*8  time 9300
    System.out.println(" ->  The time for Square   Recursive is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    
    System.out.println("******************Cubic**********************");
    
    int[][] a =new int[3][2];
    int[][] b =new int[3][3];
    
    a[0][0]=1;
    a[1][0]=4;
    a[2][0]=6;
    a[0][1]=1;
    a[1][1]=4;
    a[2][1]=6;
    
    
    b[0][0]=2;
    b[0][1]=5;
    b[0][2]=7;
    b[1][0]=3;
    b[1][1]=8;
    b[1][2]=9;
    b[2][0]=3;
    b[2][1]=8;
    b[2][2]=9;
    time = System.nanoTime();
    //int[][] l=  IMatrixmultiplication(b,a);//[3*2-3*3] time 1900 -6*4-6*6]  time 9500
    System.out.println(" ->  The time for Cubic   iterative is   "  +  (System.nanoTime() - time )+"  Miliseconds");
    time = System.nanoTime();
    //int[][] k = RMatrixmultiplication(b,a);//[3*2-3*3] time 6800 -[6*4-6*6] time 19100
    System.out.println(" ->  The time for Cubic   Recursive is   "  +  (System.nanoTime() - time )+"  Miliseconds");
    //print(l);//testing
    //print(k);//testing
    
    System.out.println("******************LogN**********************");
    time = System.nanoTime();
    //System.out.print(ILog(100));//10 time 200.0 -100 time 400 -1000 time 588-10000 time 800
    System.out.println(" ->  The time for Log   iterative is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    time = System.nanoTime();
    //System.out.print(RLog(100));//10 time 700.0 -100 time 1200 -1000 time 800-10000 time 1200
    System.out.println(" ->  The time for Log   Recursive is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    
    System.out.println("******************NLogN**********************");
    time = System.nanoTime();
    // System.out.print(INlogn(1000));10 time 9200-100 time 41600 -1000 645100 -10000 time 1435400
    System.out.println(" ->  The time for NLogN   iterative is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    time = System.nanoTime();
    //System.out.print(RNlogn(1000));10 time 5900-100 time 32900 -1000 365200 -10000 time 357800
    System.out.println(" ->  The time for NLogN   Recursive is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    
    System.out.println("******************N^2LogN**********************");
    time = System.nanoTime();
    
    System.out.println(" ->  The time for N^2LogN   iterative is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    time = System.nanoTime();
    //RN2logn(1000);10 time 50600-100 time 301600 -1000 2566500 -10000 time 4.273367E8
    System.out.println(" ->  The time for N^2LogN   Recursive is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    
    System.out.println("******************N^3LogN**********************");
    time = System.nanoTime();
    
    System.out.println(" ->  The time for N^3LogN   iterative is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    time = System.nanoTime();
    //RN3logn(1000);10 time 295200-100 time 609400 -1000 7405300 -10000 time 4.273367E8
    System.out.println(" ->  The time for N^3LogN   Recursive is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    
    System.out.println(TimeAverage());
    
     System.out.println("******************2^N**********************");
    time = System.nanoTime();
    //I2n();//10 time 9200 -24 time 650100-35 time 1.57162E7-50 time 
    System.out.println(" ->  The time for 2^N  iterative is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    time = System.nanoTime();
    //R2n();
    System.out.println(" ->  The time for 2^N   Recursive is   "  +  (System.nanoTime() - time )+"    Miliseconds");
    
  }
}