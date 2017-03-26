package ServerModel.GameModels.BoardModel;

public class Scoreboard {
	int points;
	int numberOfTrainCards;
	int numberOfDestCards;
	
	public int getPoints() { return points; }
	public void addPoints(int addedPoints)
	{
		points += addedPoints;
	}
	
	public int getNumberOfTrainCards() { return numberOfTrainCards; }
	public void addTrainCards(int addedCard)
	{
		numberOfTrainCards += addedCard;
	}
	
	public int getNumberOfDestCards() { return numberOfDestCards; }
	public void addDestCards(int addedCard)
	{
		numberOfDestCards += addedCard;
	}

}
