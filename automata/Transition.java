package cs3360.utep.second;

public class Transition {
	
	private State pointTo;
	private char transition;
	
	
	public Transition() {
		this.pointTo = null;
		transition = '0';
	}
	
	public Transition(State tran ,char name) {
		this.pointTo = tran;
		transition = name;
	}
	
	public void addTransition(State s , char t){
		pointTo = s;
		transition = t;
	}


	public State getPointTo() {
		return pointTo;
	}


	public void setPointTo(State pointTo) {
		this.pointTo = pointTo;
	}


	public char getTransitionName() {
		return transition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pointTo == null) ? 0 : pointTo.hashCode());
		result = prime * result + transition;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transition other = (Transition) obj;
		if (pointTo == null) {
			if (other.pointTo != null)
				return false;
		} else if (!pointTo.equals(other.pointTo))
			return false;
		if (transition != other.transition)
			return false;
		return true;
	}
	
	
	
}
