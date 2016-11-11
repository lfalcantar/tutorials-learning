package snake.appet;

public class Point {
	public int x;
	public int y;
	
	public Point(int x,int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Point p)
	{
		if(this.x == p.x && this.y == p.y)
			return true;
		return false;
	}
	
	public String toString()
	{
		return x +" , " + y;
	}
}
