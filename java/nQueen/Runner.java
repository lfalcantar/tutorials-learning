public class Runner{
   public static void main(String [] args){
	  
    int size = 8; /*this can be change to any number >= 4*/
    Board board = new Board(size);
    NQueens_Problem.solveNQueen(board,0); 
    
  }			
}
