package bships;


public class BMain{
	/** Play one game of battleships */

		public static void main(String[] args) throws Exception {
			System.out.println("G started");
			Player playerS = new Sweep();
			Player playerH = new HumanPlayerIF();
			Player playerR = new RandomPlayer(); 
			Player playerY = new PlayerLRY();
			Player joto = new ABSPlayer();
			
			GameController controller = new GameController(playerY, playerS);
			controller.playGame(GameController.DISPLAY_BOARDS);
					System.exit(0);
			System.out.println("all done");
			}
	}