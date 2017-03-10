package Command.Phase2;

import java.util.List;

import Client.User;
import Command.ICommand;
import GameModels.Game;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;

public class InitializeGameCommand implements ICommand {

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

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
