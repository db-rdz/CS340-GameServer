package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import Server.ServerFacade;
import GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM CLIENT -> SERVER
 * This class will allow one player to grab one train card from the deck.
 * There's another command to grab a second card either from the deck or face up train cards.
 *
 * Created by natha on 2/27/2017.
 */

public class GetTopDeckTrainCardCommand implements ICommand {
	private int gameId;
	private String authenticationCode;
	// added turn number to check what turn you're doing
	private int turnNumber;
	
	public GetTopDeckTrainCardCommand(){}
	public GetTopDeckTrainCardCommand(int g)
	{
		gameId = g;
	}
	
	@Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        return ServerFacade.SINGLETON.getTopDeckTrainCard(gameId, authenticationCode, turnNumber);
    }

    @Override
    public String getAuthenticationCode() {
        return authenticationCode;
    }

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
    }

    public int getGameId()
    {
    	return gameId;
    }
    
    public int getTurnNumber() {
		return turnNumber;
	}
}
