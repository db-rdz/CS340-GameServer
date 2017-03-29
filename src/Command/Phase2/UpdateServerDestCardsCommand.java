package Command.Phase2;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.IClient.InvalidPassword;
import Client.IClient.InvalidUsername;
import Client.User;
import Command.ICommand;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;
import ServerModel.GameModels.CardsModel.DestCard;

public class UpdateServerDestCardsCommand implements ICommand {
	
	private List<DestCard> destCards;
	
	public UpdateServerDestCardsCommand(List<DestCard> cards) {
		destCards = cards;
	}

	@Override
	public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn, InvalidUsername, InvalidPassword {
		// TODO Auto-generated method stub
		return null;
	}

	@JsonIgnore
	@Override
	public String getAuthenticationCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@JsonIgnore
	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<DestCard> getDestCards() {
		return destCards;
	}

}
