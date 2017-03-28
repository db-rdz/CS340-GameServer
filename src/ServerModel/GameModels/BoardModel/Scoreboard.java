package ServerModel.GameModels.BoardModel;

import java.util.List;

public class Scoreboard {
	int points;
	int numberOfTrainCards;
	int numberOfDestCards;
	String playerColor;
	
	public Scoreboard(){}
	public Scoreboard(String playerColor) {
		points = 0;
		numberOfDestCards = 3; //Each player starts with 3 at the beginning
		numberOfTrainCards = 4; //Each player starts with 4
		this.playerColor = playerColor;
	}
	

	
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
	
	public String getPlayerColor() {
		return playerColor;
	}
	public void setPlayerColor(String playerColor) {
		this.playerColor = playerColor;
	}

}
