package bships; 

import java.util.Random;

public class RandomPlayer extends Player {
	private Random randomGenerator;
	public BoardPlus defensiveBoard;
	
	public RandomPlayer() {
		name = "random";
		randomGenerator = new Random();
	}
	
	public void learnOpponentName(String opponentName) {
	}

	public void learnShotResult(SquareContents result){
		/* System.out.println("in random: result was " + result); */
	}

	public SquareSpec makeShot() {
		int x = randomGenerator.nextInt(BoardPlus.boardSize);
		int y = randomGenerator.nextInt(BoardPlus.boardSize);
		SquareSpec  shot = new SquareSpec(x,y);
		return shot;
	}

	public void learnGameOutcome(Boolean winner) {
	}

	public String getName() {
		return name;
	}

	/* public BoardPlus getDeploymentOld() {
		defensiveBoard = new BoardPlus();
		// put ships on the board
		SquareContents[][] shipArray = defensiveBoard.getContents(); 
		placeValidlyForRandom(shipArray);
		defensiveBoard.setDeployment(shipArray);
		return defensiveBoard; } */
	
	public static  void placeValidlyForRandom(SquareContents[][] sa) {
		sa[0][2] = sa[0][3] = sa[0][4] = sa[1][4] = SquareContents.BATTLESHIP; 
		sa[2][0] = sa[2][1] = sa[2][2] = SquareContents.CRUISER;
		sa[2][6] = sa[2][7] = sa[2][8] = SquareContents.CRUISER;
		sa[4][0] = sa[4][1] = SquareContents.DESTROYER;
		sa[4][3] = sa[4][4] = SquareContents.DESTROYER;
		sa[4][6] = sa[4][7] = SquareContents.DESTROYER;
		sa[6][1] = sa[6][3] = sa[6][5] = sa[6][7] = SquareContents.SUBMARINE;
	}
	
	/** Below this point is experimental */
	void placeWithOneShipTooFew(SquareContents[][] sa) { 
		sa[8][2] = sa[8][3] = sa[8][4] = sa[8][5] = SquareContents.BATTLESHIP; 
		sa[4][8] = sa[5][8] = sa[6][8] = SquareContents.CRUISER;
		sa[2][6] = sa[2][7] = sa[2][8] = SquareContents.CRUISER;
		sa[4][0] = sa[4][1] = SquareContents.DESTROYER;
		sa[4][3] = sa[4][4] = SquareContents.DESTROYER;
		sa[4][6] = sa[5][6] = SquareContents.DESTROYER;
		sa[1][2] = sa[0][5] = sa[6][4] = SquareContents.SUBMARINE;
	}
	
	
	/** addLastSubmarine -- Assuming the programmer has a fixed deployment
	  for all the other ships, this method chooses a position for the last submarine.
	  
	  The approach involves some randomness to make it unpredictable but also
	  some strategy.  It's uncommented so people can't steal my brilliant idea:)

	  * @pre   all ships have been deployed except one last submarine
	  * @post  a valid complete deployment

	  * @author Nigel Ward
	  * @param board  an object whose two-dimensional ship array we operate on 
	  */

	public void addLastSubmarine(BoardPlus board) {
	   int suitability[][];    
	   SquareContents[][] sa = board.getContents();
	   suitability = new int[9][9];//set the length
	   int i, j, x, y;
	   for (i=0; i < 9; i++) {
		   for (j = 0; j < 9; j++) {
			   if (board.isRockLocation(i,j)) {
				   	suitability[i][j] = -20;// the probability of those positions are less
			   } } }
	   for (i = 0; i < 9; i++) {
	     for (j = 0; j < 9; j++) {
	         if (board.isShip(sa[i][j])) {
	     	 suitability[i][j] = -20; }}}// the positions that are already taken 
	   for (i = 0; i < 9; i++) {
	     for (j = 0; j < 9; j++) {
	       for(x = i-1; x <= i+1; x++) {
	          for(y = j-1; y<= j+1; y++) {
		         if (board.areValidIndices(x,y) && board.isShip(sa[x][y])) {
					 suitability[i][j] = -10; } } } } }
	   for (i = 0; i < 9; i++) {
	     for (j = 0; j < 9; j++) {
	       for(x = i-1; x <= i+1; x++) {
	          for(y = j-1; y<= j+1; y++) {
		         if (board.areValidIndices(x,y) && board.isRockLocation(x,y)) {
			 suitability[i][j] += 1; } } } } }
	   for (i = 0; i < 9; i++) {
	     for (j = 0; j < 9; j++) {
	       for(x = i-2; x <= i+2; x++) {
	          for(y = j-2; y<= j+2; y++) {
		     if (board.areValidIndices(x,y) && board.isShip(sa[x][y]) 
		    		 	|| board.isRockLocation(x,y)) {
	        	 suitability[i][j] += 1; }}}}} 
	   for (i = 0; i < 9; i++) {
		   for (j = 0; j < 9; j++) {
			   suitability[i][j] += new Random().nextInt(1); } }
	   int bestValue = -999, bestI = 0, bestJ = 0;
	   int row, col;
	   for (row = 8; row >=0; row--) {
		   //System.out.println("");
		   for (col =0 ; col < 9; col++) {
	    	  //System.out.print("  " + suitability[row][col]);
	    	  if (suitability[col][row] > bestValue) {
	    		  bestValue = suitability[row][col];
	    		  bestI = col;
	    		  bestJ = row; }}}
	   // finally put the submarine there
	   sa[bestI][bestJ] = SquareContents.SUBMARINE;
	}

	
	public BoardPlus getDeployment() {
		defensiveBoard = new BoardPlus();
		SquareContents[][] shipArray = defensiveBoard.getContents(); 
		placeWithOneShipTooFew(shipArray);
		addLastSubmarine(defensiveBoard);
		defensiveBoard.setDeployment(shipArray);
		return defensiveBoard;
	}
	     }
	     
