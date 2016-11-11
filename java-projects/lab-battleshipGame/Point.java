/**
 * <p>Class point is  a Class that helps to encapsulate two point in one object.
 * @see   CheatCode() method
 * @author Luis Alcantar
 *
 */
package battleship.object.oriented;

public class Point {
	
	private int x;
	private int y;
	/**
	 * Default Constructor
	 */
	public Point()
	{
		this.x = 0;
		this.y = 0;
		
	}
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * Getter for the field X
	 */
	public int getX() {
		return x;
	}
	/**
	 * Setter for the field X
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Getter for the field Y
	 */
	public int getY() {
		return y;
	}
	/**
	 * etter for the field Y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * To String 
	 * @return String : the point print format
	 */
	public String toString()
	{
		return  x + " "+ y;
		
	}
	
	
}
