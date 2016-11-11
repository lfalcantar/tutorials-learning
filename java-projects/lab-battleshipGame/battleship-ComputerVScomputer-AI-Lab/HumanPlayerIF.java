package bships;

import java.util.Scanner;

public class HumanPlayerIF extends Player {
	public BoardPlus defensiveBoard;
	private Scanner in;
	
	public HumanPlayerIF() {
		this.name = "a human player";
		System.out.println("In humanIF, getting started, name is " + name);
		in = new Scanner(System.in);
		}
	public void learnOpponentName(String opponentName) {
		System.out.println("In humanIF, my opponent is `" + opponentName + "'");
	}
	public SquareSpec makeShot() { 
		System.out.print("In human IF, need user input: whitespace-separated x and y values: ");
		int x = in.nextInt();
		int y = in.nextInt();
		SquareSpec shot = new SquareSpec(x, y);
		return shot;
	} 
	public void learnShotResult(SquareContents result){
		System.out.println("In HumanIf, received result as " + result);
	}
	public void learnGameOutcome(Boolean winner) {
		if (winner) {System.out.println("In HumanIF, I won!");}
		else {System.out.println("In HumanIF, I lost");}
	}
	public String getName() {
			return name;
	}
	public BoardPlus getDeployment() {
		defensiveBoard = new BoardPlus();
		SquareContents[][] shipArray = defensiveBoard.getContents(); 
		placeValidly(shipArray);
		//(new BoardPlusTest("dummy") ).placeWithOneShipTooShort(shipArray);
		defensiveBoard.setDeployment(shipArray);	
		return defensiveBoard; }

	// public just so that BoardTestPlus can try it
	public void placeValidly(SquareContents[][] sa) {
		sa[0][2] = sa[0][3] = sa[0][4] = sa[0][5] = SquareContents.BATTLESHIP; 
		sa[2][0] = sa[2][1] = sa[2][2] = SquareContents.CRUISER;
		sa[2][4] = sa[2][5] = sa[2][6] = SquareContents.CRUISER;
		sa[4][0] = sa[4][1] = SquareContents.DESTROYER;
		sa[4][3] = sa[4][4] = SquareContents.DESTROYER;
		sa[4][6] = sa[4][7] = SquareContents.DESTROYER;
		sa[6][0] = sa[6][2] = sa[6][4] = sa[6][6] = SquareContents.SUBMARINE;
	}
	// two bad deployments for testing the deployment validity check
	private void placeBumpingSameType(final SquareContents[][] shipArray) {
		shipArray[3][3] = SquareContents.CRUISER;
		shipArray[2][2] = SquareContents.CRUISER;
	}
	private void placeWithOneKittyCornerSame(SquareContents[][] sa) {
		sa[0][2] = sa[0][3] = sa[0][4] = sa[0][5] = SquareContents.BATTLESHIP; 
		sa[2][0] = sa[2][1] = sa[2][2] = SquareContents.CRUISER;
		sa[2][4] = sa[2][5] = sa[2][6] = SquareContents.CRUISER;
		sa[4][0] = sa[4][1] = SquareContents.DESTROYER;
		sa[4][3] = sa[4][4] = SquareContents.DESTROYER;
		sa[4][6] = sa[4][7] = SquareContents.DESTROYER;
		sa[6][0] = sa[6][2] = sa[6][4] = sa[7][5] = SquareContents.SUBMARINE;
	}
}

	
