package bships;

import java.awt.*;

public class Sweep extends Player {
	private int x;
	private int y; 
	public SquareContents[][] contents;
	boolean shotAt[][];

	
	public Sweep() {
		name = "sweep";
		x = 0;
		y = 0;
		contents = new SquareContents[BoardPlus.boardSize][BoardPlus.boardSize];
		shotAt = new boolean[BoardPlus.boardSize][BoardPlus.boardSize];
	}
	
	
	void learnOpponentName(String opponentName) {}
	void learnShotResult(SquareContents result) {
		contents[x][y] = result;
	}

	public SquareSpec makeShot() {
		int counter = 0;
		do
		{
			this.x = (int)(Math.random()*9);
			this.y = (int)(Math.random()*9);
			if(counter++ == 91)
				throw new IndexOutOfBoundsException();
		}while(shotAt[x][y]);
		shotAt[x][y] = true;
		
		return new SquareSpec(x,y);
	}
	
	void learnGameOutcome(Boolean winner) {	}
	
	public BoardPlus getDeployment() {
		BoardPlus defensiveBoard = new BoardPlus();
		// put ships on the board
		SquareContents[][] shipArray = defensiveBoard.getContents(); 
		RandomPlayer.placeValidlyForRandom(shipArray);
		defensiveBoard.setDeployment(shipArray);
		return defensiveBoard; 
	}
}

