package Command;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import Client.User;
import GameModels.Game;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;

@JsonTypeInfo(use = Id.NAME,
		   	  include = JsonTypeInfo.As.PROPERTY,
			  property = "command")
@JsonSubTypes({
	@Type(value = LoginCommand.class),
	@Type(value = LoginRegisterResponseCommand.class),
	@Type(value = AddJoinableToClientCommand.class),
	@Type(value = AddGameToServerCommand.class),
	@Type(value = AddPlayerToServerCommand.class),
	@Type(value = LogoutCommand.class),
	@Type(value = RegisterCommand.class),
	@Type(value = StartGameCommand.class)
})
public interface ICommand {
    public CommandContainer execute() throws GameIsFullException, UserAlreadyLoggedIn;
    public String getAuthenticationCode();
    public User getUser();
    public Game getGame();
}
