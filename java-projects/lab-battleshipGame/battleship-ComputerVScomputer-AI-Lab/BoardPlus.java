package bships;

import java.util.ArrayList;

/** Contains all the battleships-specific game logic */
public class BoardPlus {
	public static final int boardSize = 9;
	/** the contents of the board */
	private SquareContents[][] contents;
	/* true for each square that has received a shot */ 
	private boolean[][] shotSeen;
	
	// ---------------------
	// first a few helper methods, useful in this class and also for players 
	
	/** returns true if x and y are in bounds */
	public boolean areValidIndices(int x, int y) {
		return (x >= 0 && y >=0 && x < boardSize && y < boardSize);
	}
	
	/** returns true if this location is where a rock is (or should be) */
	public boolean isRockLocation(int i, int j) {
		if (i + j <= 1) {return true;}  // one of the 3 lower-left squares 
		i = boardSize - 1 - i;   			// flip horizontally
		if (i + j <= 1) {return true;}    
		j = boardSize - 1 - j;   			// flip vertically
		if (i + j <= 1) {return true;}    
		i = boardSize -1 - i;   			// flip horizontally
		if (i + j <= 1) {return true;}  
		return false;
		}
	
	/** returns true if it's a ship; that is, this defines the ship types */
	public boolean isShip(SquareContents inhabitant) {
		switch(inhabitant) {
			case BATTLESHIP: return true;
			case CRUISER: 	 return true;
			case DESTROYER:  return true;
			case SUBMARINE:  return true;
			case ROCK:	 	 return false;
			case EMPTY:	 	 return false;
		}
		return false;  // should never be reached
	}
	
	public boolean isSubmarine(SquareContents inhabitant) {
		return inhabitant == SquareContents.SUBMARINE;		
	}
	public boolean isRock(SquareContents inhabitant) {
		return inhabitant == SquareContents.ROCK;		
	}
	/** returns the normative length for the specified type of ship */
	public int lengthOfShip(SquareContents ship) {
		switch (ship) {
		case BATTLESHIP: return 4;
		case CRUISER: return 3;
		case DESTROYER: return 2;
		case SUBMARINE: return 1;
		default: 
			System.out.println("internal error in lengthOfShip(" + ship + ")"); 
			return 0;
		}
	}
	
	/** returns a string of 9 lines showing the whole board's contents and status 
	 * Caution: do not change this even slightly, since fromString expects this format */
	public String toString() {
		int x, y;
		String boardDisplayString = "";
		for (y = boardSize - 1; y >= 0; y --) {
			boardDisplayString += "\n   ";
			for (x = 0; x < boardSize; x++) {
				//boardDisplayString += contents[i][j];
				boardDisplayString += showHitInfo(contents[x][y],shotSeen[x][y]);
				boardDisplayString += " ";
			}
		}
		return boardDisplayString;
	}
	
	/** returns the object (of the SquareContents enum) matching this char */
	public boolean isValidSquareChar(int i) {
		for (SquareContents sc : SquareContents.values()) {
			if (sc.toString().charAt(0) == i) { 
				return true; } }
		return false;
	}
	public static SquareContents lookupSquareType(int i) {
		for (SquareContents sc : SquareContents.values()) {
			if (sc.toString().charAt(0) == i) { 
				return sc; } }
		System.out.println("lookupSquareType was given " + i + 
				" but it expected to get one of B,C,D,S,-, or R");
		System.exit(1);
		return null;   // not reached 
	}

	/** helper for toString.  If a ship-square is hit, show b for B, etc. */
	public String showHitInfo(SquareContents inhabitant, Boolean hit) {
		if (isShip(inhabitant) && hit) {
			return (inhabitant.toString()).toLowerCase(); }  
		else {
			return inhabitant.toString();
		}
	}

	// ---------------------
	// now the top-level methods called by GameController 
	
	public BoardPlus() {
		
		contents = new SquareContents[boardSize][boardSize];
		shotSeen = new boolean[boardSize][boardSize];
		int i, j;
		for (i = 0; i < boardSize; i++) {
			for (j = 0; j < boardSize; j++) {
				if (isRockLocation(i,j)) {
					contents[i][j] = SquareContents.ROCK; }
				else {
					contents[i][j] = SquareContents.EMPTY; }
				shotSeen[i][j] = false;
			}
		} 
	}
	
	/** Sets contents based on the print-representation of the board. 
	 *  Each deployment is 9 lines, each starts with 4 whitespace 
	 *  characters, each has 9 payload characters interleaved with 
	 *  space characters 
	 */
	public void fromString(String deployment) {
		final int initialWhitespaceLen = 4;
		final int lineLen = initialWhitespaceLen + boardSize * 2;
		int x,y, charLocation; char shipChar;
		for (y = 0; y < boardSize; y++) {
			for (x = 0; x < boardSize; x++) {
				charLocation = (boardSize - y - 1) * lineLen 
							    + initialWhitespaceLen + 2 * x;
				shipChar = deployment.charAt(charLocation);
				contents[x][y] = lookupSquareType(shipChar); 
			}
		}	
	}	
	
	public SquareContents[][] getContents() {
		return contents;
	} 
		
	public void setDeployment(SquareContents[][] inputBoard) {
			contents = inputBoard;      // security risk; should clone?
	}
	/* true if every square of every ship has been hit */
	public boolean allSunkStrictRule() {
		int i, j;
		for (i = 0; i < boardSize; i++) {
			for (j = 0; j < boardSize; j++) {
				if (isShip(contents[i][j]) && !shotSeen[i][j]) {
						return false; 
				}    
			}	
		}
		return true;
	}
	
	private boolean[][] alreadyScannedDuringStillAliveCheck; 
	/* true if every ship has been hit in at least one place */
	public boolean allSunk() {
		int i, j;
		for (i = 0; i < boardSize; i++) {
			for (j = 0; j < boardSize; j++) {
				if (isShip(contents[i][j])) { 
					alreadyScannedDuringStillAliveCheck = 
							new boolean[boardSize][boardSize];
					if (stillAlive(i,j)) {
						return false;} 
				}    
			}	
		}
		return true;
	}

	private boolean stillAlive(int x, int y) {
		int i, j;
		if (shotSeen[x][y]) {
				return false; }
		// prepare to recursively look at neighbors
		alreadyScannedDuringStillAliveCheck[x][y] = true; 
		for (i = x - 1; i <= x + 1; i++) {
			for (j = y -1; j <= y + 1; j++) {
				if (!areValidIndices(i,j)) {
					continue; }
				if (!isShip(contents[i][j])) {
					continue; } 
				if (alreadyScannedDuringStillAliveCheck[i][j]) {
					continue; }
				if (stillAlive(i,j) == false) {
					// some part of the same ship was hit, so this ship is sunk
					return false; }	
			}
		}
		return true;
	}
	
	public SquareContents processShot(SquareSpec shot) {
		if (shot.x >= BoardPlus.boardSize || shot.y >= BoardPlus.boardSize){
			return SquareContents.EMPTY; }
		shotSeen[shot.x][shot.y] = true;
		SquareContents result = contents[shot.x][shot.y];
		return result;		
	}
	
	// now the private ugly stuff for checking deployment validity 
	private boolean [][] wasChecked;
	private int numShips [];
	
	private void initializeValidityCheck() { 
		wasChecked = new boolean[boardSize][boardSize];  // initially all false 
		numShips = new int[4];  // there are 4 ship types; initialize counts to 0 
	}
	
	public boolean validDeployment() {
		initializeValidityCheck();
		int i, j;
		for (i = 0; i < boardSize; i++) {
			for (j = 0; j < boardSize; j++) {
				if (missingOrExtraRock(i,j)) {
					System.out.println("fail: missing or extra rock at " + i + " " + j);
					return false; }
				if (bumpsNeighbor(i,j)){
					System.out.println("fail bumps neighbor\n" + this.toString()); 
					return false; }
				if (malformedShip(i,j)) {
					return false; } 
			}
		}
		if (shipCountsIncorrect()) {return false;}
		return true;
	}
	
	// initializes contents[][] as a side effect
	boolean canParseDeployment(String dep) {
		int i, row = 0, col = 0, offset = 0;
		// make sure there are spaces separating every board-line
		for (i = boardSize; i < (boardSize +1 ) * boardSize; i += (boardSize + 1)) {
			if (dep.charAt(i) != ' ') {
				System.out.println("fail: expected a space, not a '" + dep.charAt(i) + 
									"' at position " + i + " in " + dep);
				return false;}
		}
		for (row = 0; row < boardSize; row++) {
			for (col = 0; col < boardSize; col++) {
				offset = row * (boardSize + 1) + col;
				char squareChar = dep.charAt(offset);
				/*System.out.println("row: " + row + ", col: " + col + ", offset: " + offset + 
						",  char: " + squareChar);*/
				if (!isValidSquareChar(squareChar)) {
					System.out.println("fail: invalid character " + squareChar);
					return false; }
				contents[boardSize - 1 - row][col] = lookupSquareType(squareChar);
			}
		}
		System.out.println("deployment string parsed successfully");
		return true;
	}

	private boolean missingOrExtraRock(int i, int j) {
		if (isRockLocation(i,j) && contents[i][j] != SquareContents.ROCK) {
			System.out.println("Bad Deployment: " + "missing rock at " + new SquareSpec(i,j)); 
			System.out.println(this.toString());
			return true; }		
		if (!isRockLocation(i,j) && isRock(contents[i][j])) {
			System.out.println("Bad Deployment: " + "egregious rock at " + new SquareSpec(i,j)); 
			System.out.println(this.toString());
			return true; }
		else return false;
	}
	
	private boolean submarineByRock(int i, int j){
		if (isSubmarine(contents[i][j]) &&
				(rockAt(i, j+1) ||
				 rockAt(i+1, j) ||
				 rockAt(i-1, j) ||
				 rockAt(i, j-1) )) {
		return true; }
		else return false;
		}
	
	private boolean rockAt(int i, int j) {
		if (!areValidIndices(i,j)) {
			return false; }
		if (isRock(contents[i][j])) {
			return true; }
		return false;
	}
	
	/** does this square contain a ship and a neighboring square with a different ship? */ 
	private boolean bumpsNeighbor(int i, int j) {
		SquareContents currentShipType;
		currentShipType = contents[i][j];
		if (!isShip(currentShipType)) { 
			return false; }
		// check the edge neighbors
		if (differentAt(i, j+1, currentShipType) ||
			differentAt(i+1, j, currentShipType) ||
			differentAt(i-1, j, currentShipType) ||
			differentAt(i, j-1, currentShipType) ) {
			return true; }
		if (isSubmarine(currentShipType)) {
			// a submarine corner-touching anything is okay
			return false; 
		}
		// check the corner neighbors
		if (touchesButIsUnattached(i,j,+1,+1) || 
			touchesButIsUnattached(i,j,-1,+1) || 
			touchesButIsUnattached(i,j,-1,-1) || 
			touchesButIsUnattached(i,j,-1,+1) ){
			return true;
		}
		else return false;
	}
	
	private boolean differentAt(int x, int y, SquareContents referenceShipType) {
		if (!areValidIndices(x,y)) {
			return false; }
		SquareContents myType = contents[x][y];
		if (isShip(myType) && myType != referenceShipType) {
			System.out.println("Bad Deployment: " + 
					"a ship of type " + referenceShipType +   
					" is bumping into ship of type " + myType + " at " + new SquareSpec(x,y));
			return true; }
		else return false;
		} 
	
	/** before this is invoked we know, thanks to the differentAt check, that,
	 * if there is a ship in a bridging square, then it has the same type as its neighbors */
	 private boolean touchesButIsUnattached(int x, int y, int deltaX, int deltaY) {
		 if (!areValidIndices(x+deltaX,y+deltaY)) {
				 return false; }
		 if (isSubmarine(contents[x+deltaX][y+deltaY])) {
			 return false; }
		 if (isShip(contents[x+deltaX][y+deltaY]) &&
			!isShip(contents[x+deltaX][y]) &&     // check the two possible bridging squares
			!isShip(contents[x][y+deltaY])) {
			System.out.println("Bad Deployment: " + 
					"corner bump between a ship of type " + contents[x][y] + 
					" at " + new SquareSpec(x,y) +
					" and the neighbor at " + new SquareSpec(x+deltaX,y+deltaY));
			System.out.print(this.toString());
			return true;
		}
		else return false;
	}

		/** if a ship, checks for correct length and not bumping other ships.
		 * as side effects, modify wasChecked and maybe numShips */ 
		private boolean malformedShip(int i, int j) {
			if (wasChecked[i][j]) {
				return false; }
			if (!isShip(contents[i][j])) 
				{return false; }
			SquareContents shipType;
			shipType = contents[i][j];
			ArrayList<SquareSpec> newShip; 
			newShip = findAndAssembleShip(i, j, null);
			if (newShip.size() != lengthOfShip(shipType)) {
				System.out.println("Bad Deployment: " + 
						"ship " + shipType + " at " + new SquareSpec(i,j) +
						" has length " + newShip.size() + " instead of " + lengthOfShip(shipType));
				return true;
			}
			numShips[shipType.ordinal()]++;
			return false;
		} 						
		
		/** build from this square to seek the other components of the ship.
		   Using two for loops, but any way to iterate over the squares would work */
		private ArrayList <SquareSpec> findAndAssembleShip(int i, int j, ArrayList currentShip) {
			// first we do 3 simple tests, necessary for recursive subcalls  
			if (!areValidIndices(i,j)) {
				return null;}
			if (wasChecked[i][j]) { // already checked, and thus added to currentShip if needed
				return null; }
			if (!isShip(contents[i][j])){
				wasChecked[i][j] = true;
				return null; }
			// we've found a previously unprocessed ship-square
			SquareContents currentShipType, neighborContents;
			currentShipType = contents[i][j];
			wasChecked[i][j]= true;  // prevent recursive infinite loops
			if (currentShip == null) {
				// this is the first square of a new-found ship
				currentShip = new ArrayList <SquareSpec> (); }
			currentShip.add(new SquareSpec(i,j)); 
			// now look at the East, West, South, and North neighbors (recursively)
			findAndAssembleShip(i-1, j, currentShip);
			findAndAssembleShip(i+1, j, currentShip);
			findAndAssembleShip(i, j-1, currentShip);
			findAndAssembleShip(i, j+1, currentShip);
			return currentShip;
		}
		
		private boolean shipCountsIncorrect() {
			if (numShips[SquareContents.BATTLESHIP.ordinal()] != 1) {
				System.out.println("Bad Deployment: number of Battleships != 1"); return true; }
			if (numShips[SquareContents.CRUISER.ordinal()] != 2) { 
				System.out.println("Bad Deployment: number of Cruisers != 2"); return true; }
			if (numShips[SquareContents.DESTROYER.ordinal()] != 3) { 
				System.out.println("Bad Deployment: number of Destroyers != 3"); return true; }
			if (numShips[SquareContents.SUBMARINE.ordinal()] != 4) {
				System.out.println("Bad Deployment: number of Submarines != 4"); return true; } 
			return false; 
		}
}
