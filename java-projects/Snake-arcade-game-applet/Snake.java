package snake.appet;
public class Snake implements Runnable {
	public Point[] coordinates;
	public int index;
	public boolean crash;
	public String level;
	public boolean R, L, T, B;
	public Point newPoint;
	public Point food;
	boolean checkFood;
	public Point[] points = { (new Point(100, 100)), (new Point(300, 300)),
			(new Point(400, 400)), (new Point(200, 250)) };
	int counter;
	int delay =150;
	public boolean eatSoundb;


	public Snake() {
		this.level = " ";
		food = points[0];
		this.coordinates = new Point[100];
		this.index = 3;
		this.crash = false;
		coordinates[2] = new Point(150, 150);
		coordinates[1] = new Point(160, 150);
		coordinates[0] = new Point(170, 150);
		R = L = T = B = eatSoundb = false;
		checkFood = true;
		counter = 0;
		
	}
	 
	/*Check if the new position is inside the snakeArray*/
	public boolean contains(Point p)
	{
		for(int i=0;i<index;i++)
		{
			if(coordinates[i].equals(p))
			{return true;}
		}
			return false;
	}
	/*it will keep updateting the snake to the correct direction*/
	public void update() {
		Point p = null;
		// boolean grow = false;
		if (B) {
			p = new Point(coordinates[0].x, coordinates[0].y + 10);
		}
		if (T) {
			p = new Point(coordinates[0].x, coordinates[0].y - 10);
		}
		if (R) {
			p = new Point(coordinates[0].x + 10, coordinates[0].y);
		}
		if (L) {
			p = new Point(coordinates[0].x - 10, coordinates[0].y);
		}
		/*check if is in the array*/
		if(p != null && contains(p))
		{
			this.crash = true;
		}
		
		if (p != null) {
			/*Make the move*/
			moveSnake(p, false);
		}

	}
	/*select level compare the coordinates given by the mouse with the coordinates of the image*/
	public boolean selecLevel(Point p)
	{
		//System.out.println("Comparing"+ p);
		if((p.x >= 116 && p.x <= 149) && (p.y >= 267 && p.y <= 278))
		{
			delay =80;
			this.level = "SLUG";
			return true;
		}
		else if((p.x >= 220 && p.x <= 263) && (p.y >=264 && p.y <= 277))
		{
			delay =50;
			this.level = "WORM";
			return true;

		}
		else if((p.x >= 318 && p.x <= 372) && (p.y >=270 && p.y <= 279))
		{
			delay =30;
			this.level = "PHYTON";
			return true;
		}
		else
		{
			return false;
		} 
	}
	
	/*Makes move use a simple algorithm to move the snake to the next position*/
	public void moveSnake(Point p, Boolean flag) {
		if ((p.x >= 0 && p.x <= 500) && (p.y >= 0 && p.y <= 449)) {
				if(p.equals(food))
				{
					this.eatSoundb = true;
					this.index ++;
					if(counter != 4)
						this.food = points[counter++];
					else
						counter = 0;
				}
				Point tmp = null;
				Point tmp2 = null;
				for (int i = 0; i < this.index; i++) {
					tmp2 = coordinates[i];
					coordinates[i] = (i == 0) ? p : tmp;
					tmp = tmp2;
				}
			
		} else {
			crash = true;
		}
	}
	
	/*all the moves make everithing false except the direcction where is going*/
	public void moveR() {
		B = T = L = false;
		R = true;

	}

	public void moveL() {
		B = T = R = false;
		L = true;
	}

	public void moveT() {
		B = R = L = false;
		T = true;
	}

	public void moveB() {
		R = T = L = false;
		B = true;
	}

	public void loose() {
		R = T = B = L = false;
	}
	

	@Override
	public void run() {
		while (true) {
			update();
			try {
				/*The delay is determinated by the level */
				Thread.sleep(this.delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
