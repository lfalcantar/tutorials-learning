public class Board {
	private char[][] board;
	private int numberQueens;
	public int size;

	public Board(int size) {
		this.size = size;
		this.board = new char[size][size];
		this.numberQueens = 0;
		initBoard();
	}

	public char[][] getBoard() {
		return this.board;
	}

	public void initBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = '*';
			}
		}
	}

	public boolean safeToPlace(int row, int col) {
		return checkColum(col) && checkLeftDiagonal(row, col) && checkRightDiagonal(row, col) ? true : false;
	}

	public boolean checkColum(int col) {
		for (int j = 0; j < board.length; j++) {
			if (board[j][col] == 'Q') {
				return false;
			}
		}
		return true;
	}

	public boolean checkLeftDiagonal(int row, int column) {
		/* Checking the down of the left diagonal */
		int i = row, j = column;
		while (checkIndices(i, j)) {
			if (board[i++][j--] == 'Q') {
				return false;
			}
		}
		/* Checking the up of the left diagonal */
		i = row;
		j = column;
		while (checkIndices(i, j)) {
			if (board[i--][j++] == 'Q') {
				return false;
			}
		}
		return true;
	}

	public boolean checkRightDiagonal(int row, int column) {
		/* Checking the down of the rigth diagonal */
		int i = row, j = column;
		while (checkIndices(i, j)) {
			if (board[i++][j++] == 'Q') {
				return false;
			}
		}
		/* Checking the up of the rigth diagonal */
		i = row;
		j = column;
		while (checkIndices(i,j)) {
			if (board[i--][j--] == 'Q') {
				return false;
			}
		}
		return true;
	}

	public void addChar(int row, int column, char c) {
		this.board[row][column] = c;
		this.numberQueens++;
	}
	
	public boolean checkIndices(int i, int j){
		return i < size && j < size && j >= 0 && i >= 0?true:false;
	}

	public void deleteChar(int row, int column, char c) {
		this.board[row][column] = c;
		this.numberQueens--;
	}

	public int getNumberQueens() {
		return numberQueens;
	}

	public void printBoard() {
		for (char r[] : board) {
			for (char c : r) {
				System.out.print(" " + c);
			}
			System.out.println("");
		}

	}

}
