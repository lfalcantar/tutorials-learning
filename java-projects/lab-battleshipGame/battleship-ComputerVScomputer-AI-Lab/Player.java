package bships;

/** A collection of methods implementing a player for Battleships
 *  Can be called in two ways, either by directly calling the specific methods
 *  or by passing in strings to the dispatcher.
 *  The former is a little easier.  The latter will nicely transfer to sockets soon */

 public abstract class Player {
	public String name;
	public SquareContents[][] deployment; // 9 x 9 

	abstract void learnOpponentName(String opponentName);
	abstract void learnShotResult(SquareContents result);
	abstract void learnGameOutcome(Boolean winner);
	
	
	public String getName() {
		return name; }
	abstract BoardPlus getDeployment();
	abstract SquareSpec makeShot(); 
	
	public String dispatcher(String inString) {
		String payload = inString.substring(1 + inString.indexOf(':'));
		if (inString.startsWith("name?")) {
			return getName(); }
		else if (inString.startsWith("deployment?")) {
			return getDeployment().toString(); }
		else if (inString.startsWith("shot?")) {
			return makeShot().toString();}
		else if (inString.startsWith("opponent")) {
			learnOpponentName(payload); 
			return ""; }
		else if (inString.startsWith("result")) {
			learnShotResult(BoardPlus.lookupSquareType(payload.charAt(0)));
			return ""; }
		else if(inString.startsWith("outcome")) {
			learnGameOutcome(Boolean.parseBoolean(payload));
			return ""; }
		else {
			System.out.println("dispatcher, unexpected message: " + inString);
			System.exit(1);
			return "";   // not reached
		}
	}
 }