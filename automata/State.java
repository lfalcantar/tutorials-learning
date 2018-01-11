package cs3360.utep.second;

/*
 * author : Luis Alcantar
 **/
import java.util.ArrayList;
import java.util.List;

public class State {
	/*
	 *NFA States names will be an Integers, this variable is to represent that
	 * number incrementing every time an state is created.
	 */
	private static int nameID = 0;
	
	/*
	 * DFA States names will be an Characters andwill start in cahracter 'A' | number 65, this variable
	 *  is to represent that
	 * number incrementing every time an state is created.
	 */
	private static int dfaID  = 0;  

	/* numeric representation of the name of the state */
	public int stateName;
	

	/*
	 * Map with all the transitions of the state Character represents the
	 * transition to another state state the pointer to the corresponding state
	 */
	private List<Transition> transitions;

	/*
	 * this variables indicate, aspects of the state, if is final and if is
	 * start state
	 */
	private boolean isStartState;
	private boolean isFinalState;

	/* Customize constructor for state */
	public State(int name) {
		stateName = name;
		transitions = new ArrayList<Transition>();
		isStartState = false;
		isFinalState = false;
	}

	/* get the map containing all the transitions to this state */
	public List<Transition> getTransitions() {
		return transitions;
	}

	/* set the transition for this state */
	public void setTransitions(ArrayList<Transition> transitions) {
		this.transitions = transitions;
	}

	/* return boolean true of false: depending if the state is start or not */
	public boolean isStartState() {
		return isStartState;
	}

	/* set this boolean variable to make it start */
	public void setStartState(boolean isStartState) {
		this.isStartState = isStartState;
	}

	/* return true or false if the state is a final state */
	public boolean isFinalState() {
		return isFinalState;
	}

	/* set this boolean variable to true or false */
	public void setFinalState(boolean isFinalState) {
		this.isFinalState = isFinalState;
	}
	/*get the name id to assign to a new NFA state, and also increment for next call */
	public static int assignNameID() {
		return nameID++;
	}
	
	/*get the number id to assign to a new  DFA state, and also increment for next call */
	public static int assignDFAID() {
		return dfaID++;
	}

	/*get the nameID variable to find out how many states had been create*/
	public static int getNameID() {
		return nameID;
	}
	
	/*set the name id variable RESET the names in a new automata*/
	public static void setNameID(int nameID) {
			nameID = nameID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stateName;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		State other = (State) obj;
		if (stateName != other.stateName)
			return false;
		return true;
	}
	
	public String toString(){
		return ""+stateName;
	}
	
	
	

}
