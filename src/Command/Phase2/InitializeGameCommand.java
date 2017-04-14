package Command.Phase2;

import java.util.List;

import Client.User;
import Command.ICommand;
import GameModels.Game;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;
import ServerModel.GameModels.BoardModel.Scoreboard;
import ServerModel.GameModels.CardsModel.DestCard;
import ServerModel.GameModels.CardsModel.TrainCard;

/**
 * FROM SERVER -> CLIENT
 * This starts each user with the appropriate cards:
 * Receive 3 destination cards, but pick at least 2
 * Receive 4 random train cards (can include wilds)
 *
 * The ClientModel already takes care of the initial car and point count, so no need to initialize from here.
 *
 * Created by natha on 2/27/2017.
 */

//Ryan: the command sends back the 4 train cards and 3 destCards

public class InitializeGameCommand implements ICommand {
	private List<TrainCard> hand;
	private List<DestCard> destinationCards;
	private List<TrainCard> faceupTrainCards;
	private List<Scoreboard> scoreboards;
	
	public InitializeGameCommand(List<TrainCard> hand, List<DestCard> dc, List<TrainCard> faceup, List<Scoreboard> boards)
	{
		this.hand = hand;
		destinationCards = dc;
		faceupTrainCards = faceup;
		scoreboards = boards;
	}
	
	@Override
	public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthenticationCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<TrainCard> getHand()
	{
		return hand;
	}
	
	public List<DestCard> getDestinationCards() {
		return destinationCards;
	}
	
	public List<TrainCard> getFaceupTrainCards() {
		return faceupTrainCards;
	}
	
	public List<Scoreboard> getScoreboards() {
		return scoreboards;
	}

}
