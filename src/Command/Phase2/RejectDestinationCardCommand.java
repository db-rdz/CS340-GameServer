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
	private String _s_authenticationCode;
	private DestCard rejectedCard;
	
	public RejectDestinationCardCommand(int id, String code, DestCard card)
	{
		_i_gameId = id;
		_s_authenticationCode = code;
		rejectedCard = card;
	}
	
	@Override
	public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn {
		return ServerFacade.SINGLETON.rejectDestCard(_i_gameId, _s_authenticationCode, rejectedCard);
	}
	@Override
	public String getAuthenticationCode() {
		return _s_authenticationCode;
	}
	@Override
	public User getUser() {
		return null;
	}
	
	public int get_i_gameId()
	{
		return _i_gameId;
	}
	
	public String get_s_AuthenticationCode()
	{
		return _s_authenticationCode;
	}
	
	public DestCard getRejectedCard()
	{
		return rejectedCard;
	}
	
}
