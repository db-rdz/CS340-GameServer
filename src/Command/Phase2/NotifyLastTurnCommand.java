package Command.Phase2;

import java.util.List;

import Client.IClient.InvalidPassword;
import Client.IClient.InvalidUsername;
import Client.User;
import Command.ICommand;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;

public class NotifyLastTurnCommand implements ICommand {

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

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
