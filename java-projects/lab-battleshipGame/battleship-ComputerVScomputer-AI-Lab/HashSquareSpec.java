package bships;

public class HashSquareSpec extends SquareSpec 
{
	
	public HashSquareSpec(int sx, int sy) 
	{
		super(sx,sy);
	}
	public HashSquareSpec(String codeString) 
	{
		super(codeString);
	}
      
    @Override  
    public int hashCode() 
    {  
        return this.toString().hashCode();  
    }  
	
    public int getX()
    {
    	return x;
    }
    
    public int getY()
    {
    	return y;
    }
    
    @Override
    public boolean equals(Object other) 
    {
      if(this.toString().equals(other.toString()))
        return true;
      else
        return false;
    }
	
}