public class Runner{
   public static void main(String [] args){
	  
    int size = 20; /*this can be change to any number*/
    Board board = new Board(size);
    NQueens_Problem.solveNQueen(board,0);
    
  }			
}
