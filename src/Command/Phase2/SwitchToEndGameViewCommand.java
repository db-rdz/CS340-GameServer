package Command.Phase2;

import java.util.List;

import Client.IClient.InvalidPassword;
import Client.IClient.InvalidUsername;
import Client.User;
import Command.ICommand;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;
import Server.ServerFacade;

public class SwitchToEndGameViewCommand implements ICommand {
	
	private String longestPathHolder;
	
	public SwitchToEndGameViewCommand(String username) {
		longestPathHolder = username;
	}

	@Override
	public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn, InvalidUsername, InvalidPassword {
		return null;
	}

	@Override
	public String getAuthenticationCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLongestPathHolder() {
		return longestPathHolder;
	}
}
