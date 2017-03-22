package Command;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import Client.User;
import Command.Phase1.AddGameToServerCommand;
import Command.Phase1.AddJoinableGameCommand;
import Command.Phase1.AddPlayerToServerCommand;
import Command.Phase1.GetCommandsCommand;
import Command.Phase1.ListJoinableCommand;
import Command.Phase1.LoginCommand;
import Command.Phase1.LoginRegisterResponseCommand;
import Command.Phase1.LogoutCommand;
import Command.Phase1.RegisterCommand;
import Command.Phase1.StartGameCommand;
import GameModels.Game;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;

@JsonTypeInfo(use = Id.NAME,
		   	  include = JsonTypeInfo.As.PROPERTY,
			  property = "command")
@JsonSubTypes({
	@Type(value = LoginCommand.class),
	@Type(value = LoginRegisterResponseCommand.class),
	@Type(value = AddJoinableGameCommand.class),
	@Type(value = ListJoinableCommand.class),
	@Type(value = AddGameToServerCommand.class),
	@Type(value = AddPlayerToServerCommand.class),
	@Type(value = LogoutCommand.class),
	@Type(value = RegisterCommand.class),
	@Type(value = StartGameCommand.class),
	@Type(value = GetCommandsCommand.class)
})
public interface ICommand {
    public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn;
    public String getAuthenticationCode();
    public User getUser();
//    public Game getGame();
}
