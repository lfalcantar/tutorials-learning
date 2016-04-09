import java.util.*;

public class NQueens_Problem {
	/*This variable store all the solutions found in the board*/
	public static ArrayList<Board> solutions = new ArrayList<Board>();

	public static boolean solveNQueen(Board board, int row) {

		if (board.getNumberQueens() == board.size) {
			solutions.add(board);
			System.out.println("***Solution***");
			board.printBoard();
			return true;
		}
		if (row >= board.size) {
			return false;
		}

		for (int i = 0; i < board.size; i++) {

			if (board.safeToPlace(row, i)) {

				board.addChar(row, i, 'Q');
				
				if (solveNQueen(board, row + 1)) {
					continue;
				}

				board.deleteChar(row, i, '*');
			}

		}
		return false;
	}

}
