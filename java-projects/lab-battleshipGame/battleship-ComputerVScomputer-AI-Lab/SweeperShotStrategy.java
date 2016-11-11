package bships;

import java.util.Stack;

public class SweeperShotStrategy implements PredictShotStrategy {
	private SquareSpec lastShot;
	private Stack<SquareSpec> ships;
	private SquareContents[][] opponentBoard;
	
	public SweeperShotStrategy()
	{
		ships = new Stack<SquareSpec>();
		
		ships.add(new SquareSpec(0, 2));
		ships.add(new SquareSpec(0, 3));
		ships.add(new SquareSpec(0, 4));
		ships.add(new SquareSpec(1, 4));
		ships.add(new SquareSpec(2, 0));
		ships.add(new SquareSpec(2, 1));
		ships.add(new SquareSpec(2, 2));
		ships.add(new SquareSpec(2, 6));
		ships.add(new SquareSpec(2, 7));
		ships.add(new SquareSpec(2, 8));
		ships.add(new SquareSpec(4, 0));
		ships.add(new SquareSpec(4, 1));
		ships.add(new SquareSpec(4, 3));
		ships.add(new SquareSpec(4, 4));
		ships.add(new SquareSpec(4, 6));
		ships.add(new SquareSpec(4, 7));
		ships.add(new SquareSpec(6, 1));
		ships.add(new SquareSpec(6, 3));
		ships.add(new SquareSpec(6, 5));
		ships.add(new SquareSpec(6, 7));
	}
	@Override
	public SquareSpec predictNextShot(SquareContents[][] opponentBoard) {
		lastShot = ships.pop();
		return lastShot;
	}

	@Override
	public SquareSpec getLastShot() {
		// TODO Auto-generated method stub
		return lastShot;
	}

}
