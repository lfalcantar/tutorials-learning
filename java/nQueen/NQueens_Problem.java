import java.util.*;

public class NQueens_Problem {
	/*This variable store all the solutions found in the board*/
	public static ArrayList<Board> solutions = new ArrayList<Board>();
	/**
	 This method will take a board:Board and will solve the problem
	 based on that board fields, and a starting point row:int*/
	public static boolean solveNQueen(Board board, int row) {
		/*if the N:Number of queens is equal to the size of the board:N
		 * there is a solution*/
		if (board.getNumberQueens() == board.size) {
			solutions.add(board);
			System.out.println("***Solution***");
			board.printBoard();
			return true;
		}
		/*we are beyond the board with no success*/
		if (row >= board.size) {
			return false;
		}
		/*iterate through the board row trying to place a queen,*/
		for (int i = 0; i < board.size; i++) {
			/*Check is safe to place*/
			if (board.safeToPlace(row, i)) {
				/*place queen, to try*/
				board.addChar(row, i, 'Q');
				
				/*recursively traverse all the branches of the recursive tree */
				if (solveNQueen(board, row + 1)) {
					/*This can be change to return true to find only the first answer*/
					continue;
				}
				
				/*if we could not solved, remove the queen to try another position*/
				board.deleteChar(row, i, '*');
			}

		}
		/*if no luck return false*/
		return false;
	}

}
