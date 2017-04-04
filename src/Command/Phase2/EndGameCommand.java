package Command.Phase2;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.IClient.InvalidPassword;
import Client.IClient.InvalidUsername;
import Client.User;
import Command.ICommand;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;
import Server.ServerFacade;

public class EndGameCommand implements ICommand {
	
	private int gameId;
	private String authenticationCode;
	
	public EndGameCommand() {}

	@Override
	public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn, InvalidUsername, InvalidPassword {
		return ServerFacade.SINGLETON.endGame(gameId, authenticationCode);
	}

	@Override
	public String getAuthenticationCode() {
		return null;
	}
	
	public int getGameId() {
		return gameId;
	}

}
