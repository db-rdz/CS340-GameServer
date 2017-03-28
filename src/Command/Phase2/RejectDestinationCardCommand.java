package Command.Phase2;

import java.util.List;

import Client.User;
import Command.ICommand;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;
import Server.ServerFacade;
import ServerModel.GameModels.CardsModel.DestCard;

public class RejectDestinationCardCommand implements ICommand {
	private int _i_gameId;
	private String authenticationCode;
	private DestCard rejectedCard;
	
	public RejectDestinationCardCommand(){}
	public RejectDestinationCardCommand(int id, String code, DestCard card)
	{
		_i_gameId = id;
		authenticationCode = code;
		rejectedCard = card;
	}
	
	@Override
	public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn {
		return ServerFacade.SINGLETON.rejectDestCard(_i_gameId, authenticationCode, rejectedCard);
	}
	
	@Override
	public String getAuthenticationCode() {
		return authenticationCode;
	}
	@Override
	public User getUser() {
		return null;
	}
	
	public int get_i_gameId()
	{
		return _i_gameId;
	}
	
	public DestCard getRejectedCard()
	{
		return rejectedCard;
	}
	
}
