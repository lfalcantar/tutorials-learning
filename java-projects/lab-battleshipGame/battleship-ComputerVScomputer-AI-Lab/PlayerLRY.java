package bships;

import java.util.LinkedList;
import java.util.Queue;

public class PlayerLRY extends RemotePlayer {

	private int x;
	private int y;
	private SquareContents[][] contents;
	private boolean shotAt[][];
	/*
	 * These variables help the program keep track or the previous and current
	 * ship
	 */
	private SquareSpec pSquare;
	private SquareSpec cSquare;
	/* use to storage adjacency edges */
	private Queue<SquareSpec> queue;
	private int N;
	private int n;
	private boolean sunk;
	private boolean hack;
	private int S, D, C, B;

	// private SquareContents[][] opponent;

	public static void main(String[] args) {
		PlayerLRY LR = new PlayerLRY();
		LR.setupInterface(args);

	}

	public PlayerLRY() {
		this.name = "Luisito Rey";
		this.hack = true;
		this.x = 0;
		this.y = 0;
		this.N = 0;
		this.n = 0;
		this.contents = new SquareContents[BoardPlus.boardSize][BoardPlus.boardSize];
		this.shotAt = new boolean[BoardPlus.boardSize][BoardPlus.boardSize];
		this.sunk = false;
		S = D = C = B = 0;
		/* Rocks */
		shotAt[0][0] = true;
		shotAt[7][8] = true;
		shotAt[0][1] = true;
		shotAt[8][0] = true;
		shotAt[1][0] = true;
		shotAt[8][1] = true;
		shotAt[0][8] = true;
		shotAt[8][8] = true;
		shotAt[0][7] = true;
		shotAt[8][7] = true;
		shotAt[1][8] = true;
		shotAt[7][8] = true;
		this.pSquare = null;
		this.cSquare = null;
		this.queue = new LinkedList<SquareSpec>();
	}

	private void eliminateStrategy() {

	}

	/**
	 * <p>
	 * edgeAdjacency() this method using the variable currentSquare will add to
	 * the Queue all the valid positions edge adjacent to the current ship.
	 */
	public void edgeAdjacency() {
		if (this.x + 1 < 9 && !shotAt[this.x + 1][this.y]) {
			queue.add(new SquareSpec(this.x + 1, this.y));
		}
		if (this.x - 1 >= 0 && !shotAt[this.x - 1][this.y]) {
			queue.add(new SquareSpec(this.x - 1, this.y));
		}
		if (this.y + 1 < 9 && !shotAt[this.x][this.y + 1]) {
			queue.add(new SquareSpec(this.x, this.y + 1));
		}

		if (this.y - 1 >= 0 && !shotAt[this.x][this.y - 1]) {
			queue.add(new SquareSpec(this.x, this.y - 1));
		}

	}

	/**
	 * getSubmarineEdges(SquareSpec result)
	 * <p>
	 * This method is only use for submarines,will automatically mark the edge
	 * adjacent of the Submarine position , that is provide by the parameter.
	 * Due the fact, that nothing can be adjacent to a submarine
	 * 
	 * @param result
	 *            containing the position of the ship submarine
	 */
	public void getSubmarineEdges(SquareSpec result) {
		int i = result.x;
		int j = result.y;

		if (i + 1 < 9 && !shotAt[i + 1][j]) {
			shotAt[i + 1][j] = true;
		}
		if (i - 1 >= 0 && !shotAt[i - 1][j]) {
			shotAt[i - 1][j] = true;
		}
		if (j + 1 < 9 && !shotAt[i][j + 1]) {
			shotAt[i][j + 1] = true;
		}
		if (j - 1 >= 0 && !shotAt[i][j - 1]) {
			shotAt[i][j - 1] = true;
		}

	}

	void learnOpponentName(String opponentName) {
	}

	/**
	 * <p>
	 * This abstract method from the super class is override by LRPlayer
	 * controls the methods and the flow of the shots that hit ships
	 * 
	 */
	void learnShotResult(SquareContents result) {
		if (this.hack) {
			contents[x][y] = result;
			char ch = result.toString().charAt(0);

			if ("BCDA".indexOf(ch) >= 0) {
				if (!sunk) {
					this.N = ch == 'B' ? ch == 'C' ?ch=='A'?8: 3 : 4 : 2;
					this.n = 0;
					sunk = true;

				}
				if (N == n) {
					SquareSpec temp = null;
					while (!queue.isEmpty()) {
						temp = queue.remove();
					}
					shotAt[temp.x][temp.y] = true;
					sunk = false;
				}
				switch (ch) {
				case 'S':
					this.S++;
					break;
				case 'B':
					this.B++;
					break;
				case 'C':
					this.C++;
					break;
				case 'D':
					this.D++;
					break;
				}
				edgeAdjacency();
				N++;

			} else if ("S".indexOf(result.toString().charAt(0)) >= 0) {
				getSubmarineEdges(new SquareSpec(x, y));
			}
		}
	}

	/**
	 * The method makeShot() use the Queue to give the next shot to the
	 * GameController , if the Queue is empty that means we run out of moves we
	 * use Math.random to generate random numbers.
	 */
	public SquareSpec makeShot() {
		/*
		 * If the Queue is empty i have not a single priority , i use rando
		 * instead until i find another ship
		 */
		if (queue.isEmpty()) {
			pSquare = null;
			cSquare = null;
			/*
			 * Get a Random number for x and y and check that is not shot
			 * already
			 */
			do {
				this.x = (int) (Math.random() * 9);
				this.y = (int) (Math.random() * 9);
			} while (shotAt[this.x][this.y]);
			shotAt[x][y] = true;
			return new SquareSpec(x, y);
		} else {
			SquareSpec temp = queue.remove();
			if (!shotAt[temp.x][temp.y]) {
				this.x = temp.x;
				this.y = temp.y;
				shotAt[this.x][this.y] = true;
				return temp;
			}
			return makeShot();/*
							 * If the Queue contains a invalid position reaped
							 * recursively
							 */
		}

	}

	void learnGameOutcome(Boolean winner) {
	}

	/**
	 * getDeployment() will return the boardPlus constructed with the specific
	 * location o the ships LRPlayer
	 * 
	 * @return BoardPlus Containing locations of the ships
	 */
	public BoardPlus getDeployment() {
		BoardPlus defensiveBoard = new BoardPlus();
		SquareContents[][] shipArray = defensiveBoard.getContents();
		int ran = (int) (Math.random() * 50);
		if (ran >= 0 && ran < 25) {
			shipArray[7][2] = shipArray[7][3] = shipArray[7][4] = shipArray[8][4] = SquareContents.BATTLESHIP;
			shipArray[3][0] = shipArray[4][0] = shipArray[4][1] = SquareContents.CRUISER;
			shipArray[3][8] = shipArray[4][8] = shipArray[4][7] = SquareContents.CRUISER;
			shipArray[0][3] = shipArray[1][3] = SquareContents.DESTROYER;
			shipArray[1][5] = shipArray[1][6] = SquareContents.DESTROYER;
			shipArray[5][5] = shipArray[4][5] = SquareContents.DESTROYER;
			shipArray[2][1] = shipArray[8][6] = shipArray[3][6] = shipArray[7][7] = SquareContents.SUBMARINE;
		} else {
			shipArray[3][4] = shipArray[4][6] = shipArray[3][5] = shipArray[3][6] = SquareContents.BATTLESHIP;
			shipArray[0][6] = shipArray[1][6] = shipArray[1][5] = SquareContents.CRUISER;
			shipArray[6][7] = shipArray[7][7] = shipArray[7][6] = SquareContents.CRUISER;
			shipArray[3][1] = shipArray[2][1] = SquareContents.DESTROYER;
			shipArray[3][8] = shipArray[4][8] = SquareContents.DESTROYER;
			shipArray[7][2] = shipArray[7][1] = SquareContents.DESTROYER;
			shipArray[4][0] = shipArray[6][0] = shipArray[5][5] = shipArray[7][4] = SquareContents.SUBMARINE;
		}
		defensiveBoard.setDeployment(shipArray);
		return defensiveBoard;
	}
}
