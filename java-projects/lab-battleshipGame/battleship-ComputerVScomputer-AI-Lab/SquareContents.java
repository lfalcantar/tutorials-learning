package bships;

public enum SquareContents {
	BATTLESHIP {public String toString() {return "B";}},
	CRUISER {public String toString() {return "C";}},
	DESTROYER {public String toString() {return "D";}},
	SUBMARINE {public String toString() {return "S";}},
	ROCK {public String toString() {return "R";}},
	EMPTY {public String toString() {return "-";}}
}