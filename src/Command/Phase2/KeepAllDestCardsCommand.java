package Command.Phase2;

import java.util.List;

import Client.User;
import Command.ICommand;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;
import Server.ServerFacade;

public class KeepAllDestCardsCommand implements ICommand {

	private int gameId;
	private String authenticationCode;
	
	@Override
	public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn {
		// TODO Auto-generated method stub
		return ServerFacade.SINGLETON.keepAllDestCards(gameId, authenticationCode);
	}

	@Override
	public String getAuthenticationCode() {
		// TODO Auto-generated method stub
		return authenticationCode;
	}

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getGameId()
	{
		return gameId;
	}

}
