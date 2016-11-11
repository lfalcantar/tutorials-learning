package bships;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Utilizes the Strategy Pattern to implement different algorithms to find opponents BattlShip positions. If the BattleShip name is sweep, the 
 * a strategy to exploit the predictable position of the ships is used. This strategy is called the SweeperShotStrtegy.
 * 
 * If the opponent's name is not sweeper, the class used the HuntTargetShotStrategy to predict the opponent's position.
 * 
 * @author Alan Wernick
 * @version 1.1
 */
public class ABSPlayer extends Player 
{
	private BoardPlus playerBoard;
	private Boolean winner;
	private SquareContents[][] opponentBoard;
	private boolean[][] shotsFired;
	private HuntTargetShotStrategy shotPredicter;
	private String opponentName;
	
	
	public ABSPlayer()
	{
		name = "ABSPlayer";
		shotPredicter = new HuntTargetShotStrategy();
	}
	
	@Override
	 public void learnOpponentName(String opponentName) {
		this.opponentName = opponentName;
	}

	@Override
	/**
	 * Maps the type of SquareContent in the last shot.
	 * 
	 * @param the result of the last shot fired.
	 */
	public void learnShotResult(SquareContents result) 
	{
		SquareSpec lastShot = shotPredicter.getLastShot();
		opponentBoard[lastShot.x][lastShot.y] = result;
		
		LinkedHashSet<HashSquareSpec> s = shotPredicter.getfiredShots();
		Iterator<HashSquareSpec> it = s.iterator();
		
		while(it.hasNext())
		{
			HashSquareSpec h = it.next();
			shotsFired[h.getX()][h.getY()] = true; 
		}
		
		for(int i = 0; i < opponentBoard.length; i++)
		{
			for(int j = 0; j < opponentBoard[0].length; j++)
			{
				System.out.print(opponentBoard[j][i]);
			}
			System.out.println();
		}
		
		for(int i = 0; i < opponentBoard.length; i++)
		{
			for(int j = 0; j < opponentBoard[0].length; j++)
			{
				System.out.print("|" + shotsFired[j][i]);	
			}
			System.out.println();
		}
	}

	@Override
	public void learnGameOutcome(Boolean winner) 
	{
		this.winner = winner;
	}

	@Override
	/**
	 * Makes the deployment for every BattleShip game.
	 * 
	 */
	public BoardPlus getDeployment()
	{
		playerBoard = new BoardPlus();
		opponentBoard = new BoardPlus().getContents();
		shotsFired = new boolean[opponentBoard.length][opponentBoard[0].length];
		
		/*if(opponentName == "sweep")
		{
			shotPredicter = new SweeperShotStrategy();
		}*/
		// put ships on the board
		SquareContents[][] shipArray = playerBoard.getContents(); 
		shipArray = placeShips(shipArray);
		playerBoard.setDeployment(shipArray);
		return playerBoard; 
	}
	
	
	/**
	 * Places the Ships in a blank battleship board. The position is the same for every game.
	 * 
	 * @param sa Blank BattleShip board
	 * @return Battleship board with the desired ship position.
	 * @since 1.1
	 */
	
	private SquareContents[][] placeShips(SquareContents[][] sa)
	{
		sa[4][8] = sa[5][8] = sa[6][8] = sa[6][7] = SquareContents.BATTLESHIP; 
		sa[4][1] = sa[4][2] = sa[4][3] = SquareContents.CRUISER;
		sa[2][6] = sa[3][6] = sa[4][6] = SquareContents.CRUISER;
		sa[0][2] = sa[0][3] = SquareContents.DESTROYER;
		sa[8][5] = sa[8][6] = SquareContents.DESTROYER;
		sa[7][3] = sa[8][3] = SquareContents.DESTROYER;
		sa[1][7] = sa[2][4] = sa[6][0] = sa[6][5] = SquareContents.SUBMARINE;
		return sa;
	}

	// Find if a shot was fired
	/**
	 * Calls the PredictShotStrategy to calculate the next shot.
	 * 
	 * @return the shot predicted.
	 */
	@Override
	public SquareSpec makeShot() 
	{	
		return shotPredicter.predictNextShot(opponentBoard);
		//return shotPredicter.randomShot();
	}
	
}
