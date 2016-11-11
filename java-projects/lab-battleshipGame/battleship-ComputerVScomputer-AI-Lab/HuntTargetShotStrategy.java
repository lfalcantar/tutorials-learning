package bships;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Stack;

/**
 * This class implements the Hunt/Target Algorithm found in the DataGenetics blog concerning
 * BattleShip AI. The code was based in the blog's analysis of the algorithm, but the implementation 
 * is completely original. The BoardPlus class and methods are utilized internally to check for basic
 * BattleShip rules (rock location, isShip, validIndeces, etc..).
 * 
 * This implementation of the Hunt/Target Algorithm utilizes a LinkedHashSet to keep track of
 * the fired shots. The LinkedHashSet is used in both the Target Mode and Hunt Mode to prevent
 * from firing the same shot twice. Also, a LinkedHashSet was chosen due to its O(1) time complexity for
 * its basic operations(add, remove, contains), its duplicate prevention, and its insertion order to 
 * obtain the last shot fired easily. 
 * 
 * 
 * @author Alan Wernick
 * @version 1.0
 */
public class HuntTargetShotStrategy implements PredictShotStrategy {
	private HashSquareSpec targetShot;
	private HashSquareSpec lastShot;
	private Random randomGenerator;
	private LinkedHashSet<HashSquareSpec> firedShots;
	private boolean targetMode;
	private Stack<HashSquareSpec> adjacentSquares;
	private ArrayDeque<HashSquareSpec> shipsFound;
	private BoardPlus opponentBoard;
	
	public LinkedHashSet<HashSquareSpec> getfiredShots()
	{
		return firedShots;
	}
	
	public HuntTargetShotStrategy()
	{
		randomGenerator = new Random();
		firedShots = new LinkedHashSet<HashSquareSpec>();
		targetMode = false;
		adjacentSquares = new Stack<HashSquareSpec>();
		shipsFound = new ArrayDeque<HashSquareSpec>();
		opponentBoard = new BoardPlus();
	}
	
	public static void main(String [] args)
	{
		HuntTargetShotStrategy hs = new HuntTargetShotStrategy();
		
		HashSquareSpec a = new HashSquareSpec(1,2);
		HashSquareSpec b = new HashSquareSpec(2,2);
		HashSquareSpec c = new HashSquareSpec(1,2);
		HashSquareSpec d = new HashSquareSpec(3,2);
		
		hs.firedShots.add(a);
		hs.firedShots.add(b);
		hs.firedShots.add(c);
		hs.firedShots.add(d);
		System.out.println(a.equals((SquareSpec)c));
		Iterator l = hs.firedShots.iterator();
		while(l.hasNext())
		{
			System.out.println(l.next().hashCode());
		}
	}
	/*Hunt Mode
	 * Randomly fires shots until something is hit. Checks the shotsFired and compares it with
	 * the opponentBoard array. If the last shot in the shotsFired hits a ship, then the 
	 * algorithm changes to Target Mode.
	 * 
	 * @see bships.PredictShotStrategy#predictNextShot(bships.SquareContents[][])
	 */
	@Override
	public HashSquareSpec predictNextShot(SquareContents[][] opponentBoardArray) 
	{
		System.out.println(firedShots.size());
		Iterator l = firedShots.iterator();
		while(l.hasNext())
			System.out.println(l.next());
		
		opponentBoard.setDeployment(opponentBoardArray);
		
		if(firedShots.isEmpty())
		{
			System.out.println("Random Fire!1");
			return randomShot();
		}
		if(targetMode)
		{
				System.out.println("Random Fire!2");
				return targetModeShot();
		}
		 if(!opponentBoard.isShip(opponentBoard.getContents()[lastShot.x][lastShot.y]))
		{
			System.out.println("Random Fire!3");
			return randomShot();
		}
		else
		{
			System.out.println("Random Fire!4");
			return targetModeShot();
		}
		//never reached.
	}
	
	public HashSquareSpec getLastShot()
	{
		return lastShot;
	}
	
	public HashSquareSpec randomShot()
	{
		int x = randomGenerator.nextInt(BoardPlus.boardSize);
		int y = randomGenerator.nextInt(BoardPlus.boardSize);
		System.out.println("Random! Shots!");
		if(!opponentBoard.isRockLocation(x,y))
		{
			targetMode = false;
			lastShot = new HashSquareSpec(x,y);
			if(!firedShots.add(lastShot))
				return randomShot();
			else
				return lastShot;
		}
		else
			return randomShot();
	}
	/*Get last shot and get the edge adjacent squares. If nothing is found, or if the ships is sunk,
	 * return to hunt mode.
	 */
	
	private HashSquareSpec targetModeShot()
	{
		int x = lastShot.x;
		int y = lastShot.y;
		
		targetMode = true;
		
		if(adjacentSquares.isEmpty())
		{
			if(!isShip(x,y))
				return randomShot();
				
			/*if(opponentBoard.getContents()[lastShot.x][lastShot.y] == SquareContents.SUBMARINE)
			{
				invalidateSubmarineEdges(x, y);
				return randomShot();
			}*/
			HashSquareSpec[] directions = new HashSquareSpec[4];
			
			if(opponentBoard.getContents()[lastShot.x][lastShot.y] == SquareContents.SUBMARINE)
			{
				invalidateSubmarineEdges(x, y);
				directions[0] = new HashSquareSpec(x+1, y+1);
				directions[1] = new HashSquareSpec(x+1, y-1);
				directions[2] = new HashSquareSpec(x-1, y+1);
				directions[3] = new HashSquareSpec(x-1, y-1);
			}
			
			else
			{
			//Assign the up, down, left, and right coordinates to new HashSquareSpec objects
			directions[0] = new HashSquareSpec(x, y+1);
			directions[1] = new HashSquareSpec(x, y-1);
			directions[2] = new HashSquareSpec(x+1, y);
			directions[3] = new HashSquareSpec(x-1, y);
			}
			//Stack all the edge adjacent squares of the previous shot.
			for(int i = 0; i < directions.length; i++)
			{
				//Check all the directions to see if they are valid.
				if(opponentBoard.areValidIndices(directions[i].x,directions[i].y) && !opponentBoard.isRockLocation(directions[i].x, directions[i].y) && opponentBoard.getContents()[directions[i].x][directions[i].y] == SquareContents.EMPTY && !adjacentSquares.contains(directions[i]))
				{
						System.out.println(directions[i]);
						System.out.println(opponentBoard.getContents()[directions[i].x][directions[i].y]);
						System.out.println("Stop");
						adjacentSquares.add(directions[i]);
				}
				else if(!opponentBoard.areValidIndices(directions[i].x,directions[i].y))
				{
					switch (i)
					{
						case 0:
						case 1:
							if(opponentBoard.areValidIndices(directions[i+2].x,directions[i+2].y))
								adjacentSquares.add(directions[i+2]);
							break;
						case 2:
						case 3:
							if(opponentBoard.areValidIndices(directions[i-2].x,directions[i-2].y))
								adjacentSquares.add(directions[i-2]);
							break;
					}
				}
			}
			if(!adjacentSquares.isEmpty())
			{
				System.out.println("Firing!");
				lastShot = adjacentSquares.pop();
				if(firedShots.add(lastShot))
				{
					System.out.println(lastShot);
					return lastShot;
				}
					
				else
				{
					while(!firedShots.add(lastShot) && !adjacentSquares.isEmpty())
					{
						lastShot = adjacentSquares.pop();
						if(firedShots.add(lastShot))
							return lastShot;
					}
					return randomShot();
				}
			}
			else
				return randomShot();
		}
		System.out.println(lastShot);
		
		if(!isShip(lastShot.x,lastShot.y))
		{
			System.out.println(lastShot+"0");
			if(!adjacentSquares.isEmpty())
			{
				lastShot = adjacentSquares.pop();
				if(firedShots.add(lastShot))
					return lastShot;
				else
				{
					while(!firedShots.add(lastShot) && !adjacentSquares.isEmpty())
					{
						lastShot = adjacentSquares.pop();
						if(firedShots.add(lastShot))
							return lastShot;
					}
				}
				
				while(!firedShots.add(lastShot) && !shipsFound.isEmpty())
				{
					lastShot = shipsFound.poll();
					if(firedShots.add(lastShot))
						return lastShot;
				}
					return randomShot();
	
			}
		}
		else if(isShip(lastShot.x,lastShot.y))
		{
			invalidateCornerEdges(lastShot.x,lastShot.y);
			
			shipsFound.addLast(new HashSquareSpec(lastShot.x, lastShot.y));
			
			if(!adjacentSquares.isEmpty())
			{
				System.out.println("I am here!");
				lastShot = adjacentSquares.pop();
				System.out.println(lastShot);
				System.out.println(firedShots.contains(lastShot));
				if(firedShots.add(lastShot))
				{
					System.out.println("I am also here!");
					return lastShot;
				}
				
				while(!firedShots.add(lastShot) && !adjacentSquares.isEmpty())
				{
					System.out.println("I am also here!3");
					lastShot = adjacentSquares.pop();
					if(firedShots.add(lastShot))
					{
						return lastShot;
					}
				}
			}
			System.out.println("I am also here!4");
				//Check if the last shot is permitted, or if the adjacentSquares is empty
			while(!firedShots.add(lastShot) && !shipsFound.isEmpty())
			{
				lastShot = shipsFound.poll();
				if(firedShots.add(lastShot))
					return lastShot;
			}
				return randomShot();
		}
		return randomShot();
	}
	
	private void invalidateCornerEdges(int x, int y)
	{
		HashSquareSpec[] directions = new HashSquareSpec[4];
		directions[0] = new HashSquareSpec(x+1,y+1);
		directions[1] = new HashSquareSpec(x+1, y-1);
		directions[2] = new HashSquareSpec(x-1,y+1);
		directions[3] = new HashSquareSpec(x-1,y-1);
		
		for(HashSquareSpec square: directions)
		{
			if(opponentBoard.areValidIndices(square.x,square.y) && !opponentBoard.isRockLocation(square.x, square.y))
			{
				if(opponentBoard.getContents()[square.x][square.y] == SquareContents.SUBMARINE)
				{
					invalidateSubmarineEdges(square.x, square.y);
				}
				else
					firedShots.add(square);
			}
		}
	}
	
	private void invalidateSubmarineEdges(int x, int y)
	{
		HashSquareSpec[] directions = new HashSquareSpec[4];
		directions[0] = new HashSquareSpec(x,y+1);
		directions[1] = new HashSquareSpec(x,y-1);
		directions[2] = new HashSquareSpec(x+1,y);
		directions[3] = new HashSquareSpec(x-1,y);
		
		for(HashSquareSpec square: directions)
		{
			if(opponentBoard.areValidIndices(square.x,square.y) && !opponentBoard.isRockLocation(square.x, square.y))
			{
				System.out.println("Calling the invalidate sub: " + square);
				firedShots.add(square);
			}
		}
	}
	private boolean isShip(int x, int y)
	{
		
		return opponentBoard.isShip(opponentBoard.getContents()[x][y]);
	}
}

