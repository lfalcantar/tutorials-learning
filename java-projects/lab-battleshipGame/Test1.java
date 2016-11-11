/**
 Test1 is the main class for running a two-player game.  Its
   job is control the flow of play.  It solicits moves from the users,
   and reports the results back to them.  This class has no knowledge
   of the game layout or rules, and so it should be reusable for all
   sorts of turn-based games.

   This version is incomplete, and its only real use is as a driver
   for testing the interface with getSquareContents in the BoardLayout
   class.

   @author  Nigel Ward, University of Texas at El Paso
   @version 1.0
 */
package battleship.object.oriented;

import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Test1 {

	/* the gameboard, associated rules, and current state */
	private BoardLayout board;
	/* for reading shots from the user */
	final BufferedReader in;

	/**
	 * The default Constructor 
	 * will create an InputStreamReader and it will initialize the field.
	 */
	public Test1() {
		InputStreamReader converter = new InputStreamReader(System.in);
		in = new BufferedReader(converter);
	}
	/**
	 * 
	 */
	public static void main(String[] args) {
		Test1 game = new Test1();
		game.initGame();
		game.doShots();
	}
	/**
	 * This method is just a combination of calls to execute a shot and it will repead until all ships are sink
	 * @return Void
	 */
	private void doShots() {

		while (true) {
			processShot(readShot());
		}

	}
	/**
	 * The methods readShot use the buffers to read the user input and it will returned to the method that call it.
	 * @see    doShot() method
	 * @return String : return the line of input that get from the user
	 * @throws Exception if the user input something wrong it will throw an exception and that will cause the game to finish.
	 */
	public String readShot() {

		String currentLine = " ";
		System.out.println("Please enter your shot.");
		while (true) {
			try {
				currentLine = in.readLine();
			} catch (Exception e) {
				System.out.println("exception while trying to read a shot");
				e.printStackTrace();
				System.exit(1);
			}
			return currentLine;
		}
	}

	/**
	 * Calls getSquareContents to find out what is at the specified board
	 * location, and informs the user of that information. Note that the value
	 * returned by getSquareContents is typed, so the compiler will ensure that
	 * it can never return anything but empty, rock, B, C, D, or S.
	 * 
	 * <p>It also has a cheat if the user type 99 it will make a call to the method @see CheatCode that will return a cordinate with a ship not yet sunk
	 * 
	 * @param shotString
	 *            contains three whitespace-separated whole numbers
	 * @since 1.0
	 */
	public void processShot(String shotString) {

		if (shotString.indexOf("99") >= 0)
			System.out.println(board.CheatCode());

		else {
			try {
				SquareContents shotResult;
				shotResult = board.getSquareContents(shotString);
				System.out.print("  You shot at " + shotString);
				if (shotResult == SquareContents.EMPTY) {
					System.out.println(", where there was nothing.");
				} else {
					System.out.println(", where there was a "
							+ shotResult.toString() + ".");
				}
			} catch (ShotNotInRangeException e) {
				System.out.println(e);
				System.out.println("Please Provide a new Coordinates");
				doShots();
			}
		}
	}
	/**
	 * The method initGAme is the one that initialize the board layout and makes the correct calls  to generate the game.
	 * @return Void
	 * @see    main
	 */
	void initGame() {
		System.out.println("Welcome to Battleships.");
		board = new BoardLayout();
		board.dummyPlaceShips();
	}
}