package Command.Phase2;

import java.util.List;

import Client.User;
import Command.ICommand;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;
import Server.ServerFacade;
import ServerModel.GameModels.CardsModel.DestCard;

public class KeepAllDestCardsCommand implements ICommand {

	private int gameId;
	private String authenticationCode;
	private List<DestCard> cardsKept;
	
	public KeepAllDestCardsCommand(){}
	
	public KeepAllDestCardsCommand(int id, String code, List<DestCard> list)
	{
		gameId = id;
		authenticationCode = code;
		cardsKept = list;
	}
	
	@Override
	public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn {
		// TODO Auto-generated method stub
		return ServerFacade.SINGLETON.keepAllDestCards(gameId, authenticationCode, cardsKept);
	}

	@Override
	public String getAuthenticationCode() {
		// TODO Auto-generated method stub
		return authenticationCode;
	}
	
	public int getGameId()
	{
		return gameId;
	}
	
	public List<DestCard> getCardsKept()
	{
		return cardsKept;
	}

}
