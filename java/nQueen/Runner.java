public class Runner{
   public static void main(String [] args){
    int size = 8;
    Board board = new Board(size);
    NQueens_Problem.solveNQueen(board,0);
  
  }
}
