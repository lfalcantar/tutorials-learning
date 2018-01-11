package cs3360.utep.second;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;


public class AutomataFactory {

	/* Contains The alphabet of the language */
	static Set<Character> alpha = new HashSet<Character>();

	/* The final NFA will be store and save here */
	static Automata automata = new Automata();
	/* The final DFA will be store and save here */
	static Automata dfa = new Automata();

	public static void main(String[] args) {
		/*String of the language store in this String*/
		String language = "";
		Scanner scan = new Scanner(System.in);
		
		int ans = 1;
		while ( ans != 0) {

			switch (ans) {
			case 1:
				System.out.println("Please provide a language to create NFA");
				language = scan.nextLine();
				getAlpha(language);
				automata = factoryR(language, 0);
				ans =2;
				break;
			case 2:
				System.out.println("if you want to print the NFA type \"print\"");
				System.out.println("if you want to test a string in the NFA type \"test\"");
				System.out.println("if you want to generate the DFA with the NFA type \"dfa\"");

				String snfa =scan.nextLine();
				if(snfa.toLowerCase().equals("dfa")){
					makeDfa();
					ans =3;
				}else if(snfa.toLowerCase().equals("print")){
					System.out.println("********************NFA************************");
					print(automata);
					System.out.println("********************NFA************************");
				}else if(snfa.toLowerCase().equals("test")){
					System.out.println("Please provde the string to test");
					snfa =scan.nextLine();
					if(checkStringValid(snfa, automata.getStartState(), 0)){
						System.out.println("is recognized");
					}
					else{
						System.out.println("is not recognized");
					}
				}
				break;
			case 3:
				System.out.println("if you want to print the DFA type \"print\"");
				System.out.println("if you want to test a string in the dfa type \"test\"");
				System.out.println("if you want to go to the start menu type \"menu\"");
				String sdfa =scan.nextLine();
				
				
				if(sdfa.toLowerCase().equals("menu")){
					makeDfa();
					ans =1;
				}else if(sdfa.toLowerCase().equals("print")){
					System.out.println("********************DFA************************");
					print(dfa);
					System.out.println("********************DFA************************");
				}else if(sdfa.toLowerCase().equals("test")){
					System.out.println("Please provde the string to test");
					sdfa =scan.nextLine();
					if(checkStringValid(sdfa, dfa.getStartState(), 0)){
						System.out.println("is recognized");
					}
					else{
						System.out.println("is not recognized");
					}
				}
				
				break;
			}

		}
		
		scan.close();

	}

	/**
	 * Given a sting it check if is in the language , if at the end the last
	 * state to visit when the string is read complete will return true
	 * otherwise false
	 * 
	 * @param L
	 *            : is the string to test
	 * @param s
	 *            : is the start state of the NFA or DFA
	 * @param index
	 *            : to check the correct position of the index
	 * @return true or false : if the string is part of the language true
	 *         otherwise false;
	 */
	public static boolean checkStringValid(String L, State s, int index) {
		if (index == L.length())
			if (s.isFinalState()) {
				return true;
			} else {
				return false;
			}
		else {
			boolean b = true;
			for (Transition t : s.getTransitions()) {

				if (t.getTransitionName() == '$') {
					b = checkStringValid(L, t.getPointTo(), index);
				}
				if (t.getTransitionName() == L.charAt(index)) {
					b = checkStringValid(L, t.getPointTo(), index + 1);
				}
			}
			return b;
		}
	}

	/* Get final states from the nfa make final states of the DFA */
	public static Set<Integer> findSets() {

		Set<Integer> finals = new HashSet<>();

		for (State s : automata.getStates()) {
			if (s.isFinalState()) {
				finals.add(s.stateName);
			}
		}
		return finals;
	}

	/**
	 * This method creates a DFA with using a construct NFA The method creates
	 * the start wit a set of the epsilon closures and use that set to find all
	 * the other set that with the given alphabet can take with transition to
	 * other states
	 * */
	public static void makeDfa() {

		/* all the sets and epsilon closures that are found in the NFA */
		HashSet<HashSet<State>> dfaSets = new HashSet<HashSet<State>>();

		/* map for the transition */
		Map<Set<State>, HashMap<Character, Set<State>>> DfaTransition = new HashMap<Set<State>, HashMap<Character, Set<State>>>();

		Map<Set<State>, State> dfaTransition = new HashMap<Set<State>, State>();

		/* get all the epsilon closuers of the start state */
		HashSet<State> temp2 = addEpsilons(automata.getStartState(), true, '$');
		dfaSets.add(temp2);

		/* find all states that are final in the NFA */
		Set<Integer> finalSets = findSets();

		/* make first state and make it start state */
		State startState = new State(State.assignDFAID());
		startState.setStartState(true);
		/* set as the start state of DFA */
		dfa.setStartState(startState);
		dfa.getStates().add(startState);
		/* add it to the transition map with the set of closures as key */
		dfaTransition.put(temp2, startState);

		/* sink state */
		State sink = new State(-1);
		dfa.getStates().add(sink);

		/*
		 * stack (queue) use to control the flow of sets going through the
		 * method
		 */
		Stack<HashSet<State>> queue = new Stack<HashSet<State>>();
		queue.add(temp2);

		while (!queue.empty()) {

			HashSet<State> tempSets = queue.pop();

			for (char ch : alpha) {
				/* new set for the transition with respect to the alpha */
				HashSet<State> tran = new HashSet<State>();

				/* traverse all the states in the set */
				for (State s : tempSets) {
					tran.addAll(addEpsilons(s, false, ch));
				}
				/*if is the set is no in the list we added if is to avoid loops in the queue we skip this process*/
				if (!dfaSets.contains(tran)) {
					queue.add(tran);
					dfaSets.add(tran);
				}
				/*if the do no contains the key <set> that it generates we aded with a new state as value*/
				if (!dfaTransition.containsKey(tran)) {
					State s = new State(State.assignDFAID());
					dfaTransition.put(tran, s);
				}
				/*if the sie of the new se if 0, this is pointing to the sink state name -1*/
				if (tran.size() == 0) {
					dfaTransition.get(tempSets).getTransitions()
							.add(new Transition(sink, ch));
					dfa.getStates().add(dfaTransition.get(tran));
				} else {
					dfaTransition.get(tempSets).getTransitions()
							.add(new Transition(dfaTransition.get(tran), ch));
					dfa.getStates().add(dfaTransition.get(tran));
				}
			}
		}

		/* make required states final */
		for (Set<State> set : dfaSets) {
			for (State s : set) {
				for (int i : finalSets) {
					if (s.stateName == i) {
						dfaTransition.get(set).setFinalState(true);
					}
				}
			}
		}

	}

	/**
	 * Add epsilon will traverse the nodes looking for the epsilon closures, and
	 * the transition that a character of the alphabet has
	 * 
	 * @param s
	 *            : the state
	 * @param isStart
	 *            : if is the start state
	 * @param ch
	 *            : the alphabet we are looking for
	 * @return return a set of all the states that with ch reach to epsilon or a
	 *         state
	 */
	public static HashSet<State> addEpsilons(State s, boolean isStart, char ch) {
		HashSet<State> set = new HashSet<State>();
		
		if (isStart)
			set.add(s);

		for (Transition t : s.getTransitions()) {
			/*if the transition is equl to the ch char the method call itself to add the following  with epsilon*/
			if (t.getTransitionName() == ch) {
				Set<State> temp = addEpsilons(t.getPointTo(), true, '$');
				set.addAll(temp);
			}
		}

		return set;
	}

	/**
	 * FactoryR is a recursive method that generates the NFA with a given String
	 * using two stacks one for operator and one for NFA it iterate over the
	 * string parsing and creating different NFA's at the end it concatenate all
	 * elements inside the Stack of NFA, the method uses two stacks for the NFA
	 * and the operators such as union.
	 * 
	 * @param STRING
	 *            :The language
	 * @param INTEGER
	 *            :Use as reference for the inde
	 * @return AUTOMATA :complete NFA
	 */
	public static Automata factoryR(String s, int x) {

		Stack<Automata> st = new Stack<Automata>();

		Stack<Character> op = new Stack<Character>();

		for (int i = x; i < s.length(); i++) {

			char ch = s.charAt(i);

			/* if ch is equal '(' */
			if (symbolValue(ch) == 1) {
				String s2 = findParenthesis(s, i);
				st.push(factoryR(s2, 1));
				i = i + s2.length() - 1;
			}
			/* if ch is equal ')' */
			else if (symbolValue(ch) == 2) {
				/*if is equal to close parenthesis pop all the stack and go back*/
				while (st.size() > 1) {
					/*if the stack pop contains an element  pop it because there is a union pending*/
					if (op.size() == 1) {
						Automata t1 = st.pop();
						Automata t2 = st.pop();
						st.push(unionAutomatas(t2, t1));
					} else {
						Automata t1 = st.pop();
						Automata t2 = st.pop();
						st.push(concatinate(t2, t1));
					}
				}
				return st.pop();
			}
			/* if ch is equal '*' */
			else if (symbolValue(ch) == 3) {
				st.push(makeStar(st.pop()));
			}
			/* if ch is equal '+' */
			else if (symbolValue(ch) == 4) {
				st.push(makePlus(st.pop()));
			}/* if ch is equal 'u'|'|' */
			else if (symbolValue(ch) == 5) {
				op.push('u');
				String s2 = s.substring(i);
				st.push(factoryR(s2, 1));
				i = i + s2.length();
			}
			/* if ch is equal '.' */
			else if (symbolValue(ch) == 6) {
			}
			/* if ch is equal '[a|A...z|Z]' */
			else {
				st.push(createSimpleAutomata(ch));
			}
		}

		/* Case where there is no parenthesis */
		while (st.size() > 1) {
			if (op.size() == 1) {
				Automata t1 = st.pop();
				Automata t2 = st.pop();
				st.push(unionAutomatas(t2, t1));
			} else {
				Automata t1 = st.pop();
				Automata t2 = st.pop();
				st.push(concatinate(t2, t1));
			}
		}
		return st.pop();
	}

	/*
	 * Find the other parenthesis corresponding to the position i and return
	 * that substring
	 */
	public static String findParenthesis(String s, int i) {
		int x = 1;
		int j;
		for (j = i + 1; j < s.length(); j++) {
			if (x == 0) {
				break;
			}
			if (s.charAt(j) == ')') {
				x -= 1;
			}
			if (s.charAt(j) == '(') {
				x += 1;
			}
		}
		return s.substring(i, j);
	}

	/*
	 * This helper method will evaluate the param:Character ch and find the
	 * correct representation.
	 * 
	 * @return : it return an integer that represents the symbol
	 */
	public static int symbolValue(char ch) {
		switch (ch) {
		case '(':
			return 1;
		case ')':
			return 2;
		case '*':
			return 3;
		case '+':
			return 4;
		case 'u':
			return 5;
		case '|':
			return 5;
		case '.':
			return 6;
		default:
			return 7;
		}
	}

	/*
	 * Get the alphabet of the language using a SET to store all the characters
	 * of the string
	 */
	public static void getAlpha(String ls) {
		char ch = ' ';
		for (int i = 0; i < ls.length(); i++) {
			ch = ls.charAt(i);
			if (symbolValue(ch) == 7) {
				alpha.add(ch);
			}
		}
	}

	/**
	 * Generates a automata with given strings
	 * 
	 * @param Character
	 *            : the Transition of the new automata
	 * @return The new Generate automata
	 * */
	public static Automata createSimpleAutomata(char ch) {
		/* temp : use as place holder */
		Automata temp = new Automata();

		/* create the start state of the automata */
		State s1 = new State(State.assignNameID());
		s1.setStartState(true);

		/* create the final state of the automata */
		State s2 = new State(State.assignNameID());
		s2.setFinalState(true);

		/*
		 * create the link between the state s1(start state) and s2(final state)
		 * with the transition ch:param
		 */
		s1.getTransitions().add(new Transition(s2, ch));

		/* add states to the map containing all the sates of the automata */
		temp.getStates().add(s1);
		temp.getStates().add(s2);

		/* set the link to the start state */
		temp.setStartState(s1);

		return temp;
	}

	/**
	 * Union takes two parameters both Automata's objects and return the union
	 * both,The union will be a single new automata.It creates a new state and
	 * make a Transition to the start state of both automata'ss
	 *
	 * @param Automata
	 *            : First automata for union
	 * @param Automata
	 *            : second automata for union
	 * @return Automata: the new automata representing the union of the
	 *         parameters
	 */
	public static Automata unionAutomatas(Automata automata1, Automata automata2) {

		Automata temp = new Automata();

		/*
		 * create start stare that will be the union of both automata1 and
		 * automata2
		 */
		State s1 = new State(State.assignNameID());
		/* Connect state 1 with the two automata's */
		s1.getTransitions().add(new Transition(automata1.getStartState(), '$'));
		s1.getTransitions().add(new Transition(automata2.getStartState(), '$'));

		/* make s1 start */
		s1.setStartState(true);

		/* get start states of two automatas and meke them not start states */
		automata1.getStartState().setStartState(false);
		automata2.getStartState().setStartState(false);

		/* the new automata will have s1 as start state */
		temp.setStartState(s1);

		/* add to the list of states */
		temp.getStates().add(s1);

		temp.getStates().addAll(automata1.getStates());
		temp.getStates().addAll(automata2.getStates());

		return temp;
	}

	/**
	 * Concatenates two automata and return the new generate automata,
	 * representing the concatination of the two automata's
	 * 
	 * @param automata1
	 * @param automata2
	 * @return
	 */
	public static Automata concatinate(Automata automata1, Automata automata2) {

		/* create new automata */
		Automata newAutomata = new Automata();

		/*
		 * connect final states form A1 to start state of A2 with epsilon
		 * transitions and make them not final
		 */
		for (State s : automata1.getStates()) {
			if (s.isFinalState()) {
				s.getTransitions().add(
						new Transition(automata2.getStartState(), '$'));
				s.setFinalState(false);
			}
		}
		/* make not start state start stae of A2 */
		automata2.getStartState().setStartState(false);

		/* add all states of A1 and A2 to the new automtara */
		newAutomata.getStates().addAll(automata1.getStates());
		newAutomata.getStates().addAll(automata2.getStates());

		/* make the start state of the new autmata the start state of the A1 */
		newAutomata.setStartState(automata1.getStartState());

		return newAutomata;
	}

	/**
	 * This method takes a Automata and will make it plus, each final transition
	 * in the automata will be connected to the start state of the automata
	 * 
	 * @param temp
	 *            : is the automata that wil be plus (temp)+
	 * @return A new automata representing the (automata)+
	 */
	public static Automata makePlus(Automata temp) {

		for (State s : temp.getStates()) {
			if (s.isFinalState()) {
				s.getTransitions().add(
						new Transition(temp.getStartState(), '$'));
			}
		}
		return temp;
	}

	/**
	 * Creates a new Automata that will be start using the method plus and
	 * crating a new final state that will have a transition to the start state
	 * of the automata(param)
	 * 
	 * @param temp
	 *            : the Automata that will be star (temp)*
	 * @return A new Automata that is star (Automata)*
	 * @see makepPlus
	 */
	public static Automata makeStar(Automata temp) {
		/* make the NFA plus */
		temp = makePlus(temp);

		/*
		 * create new final and start state create AND create a transtion to the
		 * start state of temp
		 */
		State s1 = new State(State.assignNameID());
		s1.setStartState(true);
		s1.getTransitions().add(new Transition(temp.getStartState(), '$'));
		s1.setFinalState(true);

		/* add it to the list of states */
		temp.getStates().add(s1);

		/* take the start state and meke it not start */
		temp.getStartState().setStartState(false);

		/* Set the new start state */
		temp.setStartState(s1);

		return temp;
	}

	/**
	 * This helper method will give a representation of what are the states that
	 * complement this automata
	 * 
	 * @param temp
	 */
	public static void print(Automata temp) {

		HashSet<State> set = new HashSet<State>();

		System.out.println("Start State ->(" + temp.getStartState() + ")");
		for (State s : temp.getStates()) {

			if (!set.contains(s)) {

				for (Transition ts : s.getTransitions()) {
					if (s.isFinalState())
						System.out.print("((" + s + "))");
					else
						System.out.print("(" + s + ")");
					System.out.print(" - > "
							+ (ts.getTransitionName() == '$' ? "E" : ts
									.getTransitionName()) + " -> ");
					if (ts.getPointTo().isFinalState())
						System.out.print("((" + ts.getPointTo() + "))");
					else
						System.out.print("(" + ts.getPointTo() + ")");
					System.out.println("");

				}
				System.out.println("");
				set.add(s);
			}
		}

	}

}
