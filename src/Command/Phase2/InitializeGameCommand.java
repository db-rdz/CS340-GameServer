package Command.Phase2;

import java.util.List;

import Client.User;
import Command.ICommand;
import GameModels.Game;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;

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
