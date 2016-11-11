package bships;

public interface PredictShotStrategy 
{
	public SquareSpec predictNextShot(SquareContents[][] opponentBoard);
	public SquareSpec getLastShot();
}
