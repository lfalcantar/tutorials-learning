import Board;
public class Runner{
   public static void main(String [] args){
    int size = 8;
    Board board = new Board(8);
    NQueens_Problem.solveNQueen(board,0);
  
  }
}
