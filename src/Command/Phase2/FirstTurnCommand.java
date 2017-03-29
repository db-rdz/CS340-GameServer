package Command.Phase2;

import java.util.List;

import Client.IClient.InvalidPassword;
import Client.IClient.InvalidUsername;
import Client.User;
import Command.ICommand;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;
import Server.ServerFacade;
import ServerModel.GameModels.CardsModel.DestCard;

public class FirstTurnCommand implements ICommand {

	private int gameId;
    private String authenticationCode;
    private List<DestCard> destCards;
    private String type;
    
	public FirstTurnCommand(){}
	public FirstTurnCommand(int id, String code, List<DestCard> cards, String type)
	{
		gameId = id;
		authenticationCode = code;
		destCards = cards;
		this.type = type;
	}
    
    @Override
	public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn, InvalidUsername, InvalidPassword {
		// TODO Auto-generated method stub
		return ServerFacade.SINGLETON.firstTurn(gameId, authenticationCode, destCards, type);
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
	public int getGameId() {
        return gameId;
    }

    public List<DestCard> getDestCards() {
        return destCards;
    }
    
    public String getType()
    {
    	return type;
    }
}
