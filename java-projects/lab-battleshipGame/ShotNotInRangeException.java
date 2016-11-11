/**
 * <p>The exception ShotNotInRangeException is  use to prevent the user from enter a out of bounds coordinates.
 * @author Luis Alcantar
 *
 */
package battleship.object.oriented;

public class ShotNotInRangeException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 */
	public ShotNotInRangeException() {
		super();
	}
	/**
	 * A constumize constructor with a parameter for a message
	 *@param message : String to give more information to the user 
	 */
	public ShotNotInRangeException(String message) {
		super(message);
	}

}
