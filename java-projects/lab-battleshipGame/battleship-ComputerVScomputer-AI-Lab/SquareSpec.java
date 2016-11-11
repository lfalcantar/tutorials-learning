package bships;

/* this just encapsulates an x,y pair of coordinates as an object,
 * for passing to other objects, for putting on lists, and for printing
 */
  
 public class SquareSpec {
 	public int x;
	public int y;
	public SquareSpec(int sx, int sy) {
		this.x = sx; 
		this.y = sy;
	}
	public SquareSpec(String codeString) {
		this.x = Integer.parseInt(codeString.substring(1,2));
		this.y = Integer.parseInt(codeString.substring(3,4));
	}
	public String toString() {
		return("(" + x + "," + y + ")");
	}
	public boolean Equals(SquareSpec other) {
		return (other.x == this.x  && 
				other.y == this.y );
	}
	
}
