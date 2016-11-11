public class Words
{
  //private int counter ;
  private String word;
  private String definition;
  
  public Words(String a )
  {
    word =a;
    definition = null;
  
  }  
  
  public Words()
  {
    
    word =null;
    definition = null;
    
  
  }
  

    public Words(String a , String b)
  {
    word =a;
    definition = b;
   // counter =x;
    
  }
    
    public void setWord(String a )
    {
      word = a;
    }
    
    public String getword()
    {
      return word;
    }
    
      public void setDefinition(String b )
    {
      definition = b;
    }
    
    public String getDefinition()
    {
      return definition;
    }
    
    public String toString()
    {
      return     " te word is : "+word+"  and the definition is:  "+definition;
    }
    


  
}