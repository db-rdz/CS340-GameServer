package Command.Phase2;

import java.util.List;

import Client.IClient.InvalidPassword;
import Client.IClient.InvalidUsername;
import Command.ICommand;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;

public class NotifyDestCardCompletedCommand implements ICommand {
	
	private String message;
	
	public NotifyDestCardCompletedCommand(String string) {
		message = string;
	}

	@Override
	public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn, InvalidUsername, InvalidPassword {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuthenticationCode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getMessage() {
		return message;
	}
}
