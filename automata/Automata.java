package cs3360.utep.second;

/*
 * author : Luis Alcantar
 **/
import java.util.ArrayList;

public class Automata {
	/* map containing all the states of the the Automata */
	private ArrayList<State> states;

	/* Pointer to the start state */
	private State startState;

	public Automata() {
		states = new ArrayList<State>();
		startState = null;
	}

	/* return the list containing all the states */
	public ArrayList<State> getStates() {
		return states;
	}

	/* set the map with all the new states */
	public void setStates(ArrayList<State> states) {
		this.states = states;
	}

	/* Get the start state of the Automata */
	public State getStartState() {
		return startState;
	}

	/* set the start state of the automata */
	public void setStartState(State startState) {
		this.startState = startState;
	}
}
