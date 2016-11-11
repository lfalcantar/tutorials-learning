package battleship.object.oriented;


import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Random;


/**
 * <p>BoardLayout is a class exclusively generated of public methods that constructs a matrix representing a board from the game battleship
 * 		, it also communicants with class @see Test1.java to parse and process the shot given by the user.
 * 
 * @author Luis Alcatar
 * @since 
 */
public class BoardLayout {
	private int boardSize;
	private SquareContents boardMatrix[][];

	/**
	 *  This is the default constructor without parameters .This constructor initialize all the rocks and  empty spaces of the matrix
	 */
	public BoardLayout() {
		setSize();
		boardMatrix = new SquareContents[boardSize][boardSize];
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (isRockLocation(i, j)) {
					boardMatrix[i][j] = SquareContents.ROCK;
				} else {
					boardMatrix[i][j] = SquareContents.EMPTY;
				}
			}
		}
	}
	/**
	 *  The methods get the size of the board , by a default random size but if the user decides to change the size it will 
	 *  	modify the size.If the user input an illegal or incorrect format it will continue with the default size
	 * 
	 * @return  Void
	 * @throws  Generic Exception : if anything goes wrong with the BufferedReader it will throw an exception
	 */
	private void setSize() {
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		Random rand = new Random(); // initial boardSize is 5, 6, or 7
		boardSize = 5 + rand.nextInt(2);
		System.out.println("Today's board is " + boardSize + "x" + boardSize
				+ ".");
		System.out.print("If you would like to change it, ");
		System.out.println("please enter the desired size.");
		String currentLine;

		try {
			currentLine = in.readLine();
			boardSize = Integer.parseInt(currentLine);
		} catch (Exception e) {
			System.out
					.println("  Read or parse problem, boardsize not changed");
		}
	}
	/**
	 * This method through a mathematical approach checks if the given coordinates lands in a rock
	 * @param  x integer the horizontal coordinate
	 * @param  y integer the vertical coordinate 
	 * @return boolean : return true  if the given coordinate is a rock | false if is not a rock
	 */
	public boolean isRockLocation(int x, int y) {
		int xLow = reflect(x);
		int yLow = reflect(y);
		return (xLow + yLow <= 1);
	}
	/**
	 * 	This method is a helper of the method isRockLocation that checks if the given cordinate is a rock.
	 * 
	 * @param  int : coordintate @see method isRockLocation
	 * @return int : 
	 */
	private int reflect(int coordinate) {
		if (coordinate > boardSize / 2) {
			return (boardSize - 1) - coordinate;
		} else {
			return coordinate;
		}
	}
	/**
	 * 		This method takes the given coordinates and checks the content 
	 * 
	 * @param shot String 
	 * @return    SquareContents :  The content of the given coordinates
	 * @throws    ShotNotInRangeException : if the coordinate that was parsed is no in the range of the matrix
	 *            it will throw an exception that is handle in a another method @see method processShot in class Test1
	 */
	public SquareContents getSquareContents(String shot)
			throws ShotNotInRangeException {
		int xy[];
		xy = parseShot(shot);

		if (xy[0] < 0 || xy[0] >= boardSize || xy[1] < 0 || xy[1] >= boardSize) {
			throw new ShotNotInRangeException(
					"The shot was no in the range of the boad size");

		} else
			return boardMatrix[xy[0]][xy[1]];

	}
	/**
	 *  parseShot  parse the coordinate of the given by the user ,it split the String with method
	 *  split from class String , it split the String by the space
	 *  	
	 * 
	 * @param  shot : String  this is the coordinate given by the user. 
	 * @return integer []  it will return a array with 2 position 
	 * @see  #getSquareContents(String)
	 */
	public int[] parseShot(String shot) {
		int coordinates[] = new int[2];
		String substrings[] = shot.split("\\s"); // split on whitespace
		coordinates[0] = Integer.parseInt(substrings[0]);
		coordinates[1] = Integer.parseInt(substrings[1]);
		System.out.println("shot parsed as " + coordinates[0] + " "
				+ coordinates[1]);
		return coordinates;
	}
	
	public void dummyPlaceShips() {
		boardMatrix[0][2] = SquareContents.SUBMARINE;
		boardMatrix[2][2] = SquareContents.SUBMARINE;
		boardMatrix[4][2] = SquareContents.SUBMARINE;
		boardMatrix[2][0] = SquareContents.SUBMARINE;
		boardMatrix[3][0] = SquareContents.BATTLESHIP;

	}
	/**
	 * 	CheatCode is a method that will provide a coordinate of the largest ship in
	 *  the game not yet sunk.
	 * 
	 * @return String, indicating the position of the larger Ship not yet sunk.
	 * @since version 1.0
	 */
	public String CheatCode() {
		boolean bFlag = false; Point b = new Point();
		boolean cFlag = false; Point c = new Point();
		boolean dFlag = false; Point d = new Point();
		boolean sFlag = false; Point s = new Point();
		for(int i = 0;i < boardMatrix.length;i++)
		{
			for(int j = 0;j < boardMatrix[i].length;j++)
			{
				if(boardMatrix[i][j] == SquareContents.BATTLESHIP && !bFlag)
				{
					bFlag =true;
					b.setX(i);
					b.setY(j);
				}
				else if(boardMatrix[i][j] == SquareContents.CRUISER && !cFlag)
				{
					cFlag =true;
					c.setX(i);
					c.setY(j);
				}
				else if(boardMatrix[i][j] == SquareContents.DESTROYER && !dFlag)
				{
					dFlag =true;
					d.setX(i);
					d.setY(j);
				}
				else if(boardMatrix[i][j] == SquareContents.SUBMARINE && !sFlag)
				{
					sFlag =true;
					s.setX(i);
					s.setY(j);
				}
			}
		}
		
		if(bFlag)
			return b.toString();
		else if(cFlag)
			return c.toString();
		else if(dFlag)
			return d.toString();
		else if(sFlag)
			return s.toString();
		else 
			return "All ships sunk";
		
	}
}
