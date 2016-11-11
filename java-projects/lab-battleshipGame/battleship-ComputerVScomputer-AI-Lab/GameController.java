package bships;

/** A basic framework for any two-player turn-by-turn game */
public class GameController {
	final static int DISPLAY_NOTHING=0, DISPLAY_RESULTS=1, DISPLAY_SHOTS=2, DISPLAY_BOARDS=3;
	final int maxRounds = BoardPlus.boardSize * BoardPlus.boardSize + 1; 
	final static int boardDisplayInterval = 1;
	private Player[] players;
	private BoardPlus[] board;
	private int verbosity;

	public GameController(Player player0, Player player1) {
		verbosity = DISPLAY_SHOTS;
		board = new BoardPlus[2];
		board[0] = new BoardPlus();
		board[1] = new BoardPlus();
		
		players = new Player[2];
		players[0] = player0;
		players[1] = player1;
	}
	
	public void playGame(int verbosity) {
		this.verbosity = verbosity;
		int i, round; 
		for (i = 0; i < 2; i++) {
			String playerName = driver(i,"name?:");
			String opponentName = players[1-i].dispatcher("name?:");
			if (verbosity >= DISPLAY_RESULTS) {
				System.out.println("Player is: " + playerName + " (opponent is: " + opponentName + ")"); } 
			driver(i,"opponent:" + opponentName);
			board[i].fromString(driver(i,"deployment?:"));
			if (verbosity >= DISPLAY_BOARDS) {
				System.out.println("... returned deployment ..." + board[i]); }
			if (!board[i].validDeployment()) {
				announceWinnerAndCleanup(1-i, "opponent made an invalid deployment"); } 
		}		
		
		for (round = 0; round < maxRounds; round++) {
			System.out.println("round " + round + ": "); 
			// player 0 shoots at board 1, then player 1 at board 0
			shotFromTo(0,1);  
			shotFromTo(1,0);   
			if (round > 0 && round % boardDisplayInterval == 0 && verbosity >= DISPLAY_BOARDS) {
				System.out.println("\n Board 0" + board[0] + "\n Board 1" + board[1]);
			}
		}
	}
	
	private void shotFromTo(int fromPlayer, int toPlayer)  {
		SquareSpec shot;
		shot = new SquareSpec(players[fromPlayer].dispatcher("shot?:"));
		if (verbosity>=DISPLAY_SHOTS) {
			System.out.print(" " + players[fromPlayer].dispatcher("name?:") + " shot at " + shot); }
		SquareContents result = board[toPlayer].processShot(shot);
		if (verbosity >= DISPLAY_SHOTS) {
			System.out.println(" ... result was " + result); }
		players[fromPlayer].dispatcher("result:" + result);	
		if (board[toPlayer].allSunkStrictRule()) {    // changed from allSunk on March 26, 2014, ngw
			announceWinnerAndCleanup(fromPlayer, "opponent's ships all sunk"); } 
	}	
	

	private void announceWinnerAndCleanup(int winnerID, String reason) {
		int i;
		for (i = 0; i < 2; i++) {
			driver(i,"outcome:" + (i == winnerID)); }  // send true to the winner
		if (verbosity >= DISPLAY_RESULTS) {
				System.out.println("Player " + players[winnerID].getName() + " wins! (" + reason + ")");}
			// write to the logfile?
			System.exit(0);
	}
	
	/* we could catch *all* exceptions that are caused by a player,
	 * which would make this program somewhat more robust, but
	 * players could still go into infinite loops, so for true robustness, 
	 * we should make the players into separate processes (pending) 
	 */
	private String driver(int playerID, String message) { 
		return players[playerID].dispatcher(message);
	}
}

	