import java.text.*;
public class Stock
{
  public String name;
  public double stockValue;
  public int numberTransaction;
  
  //customize countroctor
  public Stock(String name){
    this.name = name;
    this.stockValue=0;
    this.numberTransaction=0;
  }
  public Stock(String name,double x){
    this.name = name;
    this.stockValue=x;
    this.numberTransaction=0;
  }
  public Stock(String name,double sV,double start){
    this.name = name;
    this.stockValue=start+sV;
    this.numberTransaction=0;
  }
  
  public void incremnetNumberTransaction()
  {
     numberTransaction+=1;
  }
   public int getNumberTransaction()
  {
    return numberTransaction;
  }
   
   public void modifyStockvalue(double x)
   {
     stockValue +=x;
   }
  
  public Stock(String name, int newValue,double v){
    this.name = name;
    this.numberTransaction=0;
    this.stockValue = v+newValue;
  }
  
  /*method compareTo
   * to compare two stock objects
   */ 
   public int compareTo(Stock o)
   {
     if(this.name.compareTo(o.name)<0)
       return -1;
     else if(this.name.compareTo(o.name)>0)
       return 1;
     else 
       return 0;
   }
   
   
   
   //to String to print the stock Objec
   public String toString()
   {
     DecimalFormat df = new DecimalFormat("0.00");
     return  "The Stock " +name + " has a value of " +df.format(stockValue) + "  and the number of trasactions are : " + numberTransaction; 
   }
  
}
