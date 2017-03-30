package ServerModel.GameModels.BoardModel;

import java.util.List;

public class Scoreboard {
	int points;
	int numberOfTrainCards;
	int numberOfDestCards;
	String playerColor;
    private int playerCarCount;
	
	public Scoreboard(){}
	public Scoreboard(String playerColor) {
		points = 0;
		numberOfDestCards = 0; //Each player starts with 0, will get updated with firstTurn()
		numberOfTrainCards = 4; //Each player starts with 4
		this.playerColor = playerColor;
		playerCarCount = 45; //Each player starts with 45
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

    public int getPlayerCarCount() {
        return playerCarCount;
    }
}
