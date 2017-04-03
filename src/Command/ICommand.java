package Command;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import Client.User;
import Client.IClient.InvalidPassword;
import Client.IClient.InvalidUsername;
import Command.Phase1.AddGameToJoinableListCommand;
import Command.Phase1.AddGameToServerCommand;
import Command.Phase1.AddPlayerToServerCommand;
import Command.Phase1.GetCommandsCommand;
import Command.Phase1.ListJoinableCommand;
import Command.Phase1.LoginCommand;
import Command.Phase1.LoginRegisterResponseCommand;
import Command.Phase1.LogoutCommand;
import Command.Phase1.RegisterCommand;
import Command.Phase1.StartGameCommand;
import Command.Phase2.BroadcastToChatCommand;
import Command.Phase2.ClaimRouteCommand;
import Command.Phase2.EndTurnCommand;
import Command.Phase2.FirstTurnCommand;
import Command.Phase2.GetDestinationCardsCommand;
import Command.Phase2.GetFaceUpTableTrainCardCommand;
import Command.Phase2.GetTopDeckTrainCardCommand;
import Command.Phase2.InitializeGameCommand;
import Command.Phase2.KeepAllDestCardsCommand;
import Command.Phase2.LastTurnCompletedCommand;
import Command.Phase2.NotifyRouteClaimedCommand;
import Command.Phase2.NotifyTurnCommand;
import Command.Phase2.RejectDestinationCardCommand;
import Command.Phase2.ShowMessageCommand;
import Command.Phase2.UpdateCarCountAndHandCommand;
import Command.Phase2.UpdateFaceUpTableTrainCardsCommand;
import Command.Phase2.UpdatePlayerDestinationCardsCommand;
import Command.Phase2.UpdatePlayerTrainCardsCommand;
import GameModels.Game;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;
import sun.net.www.http.KeepAliveCache;

@JsonTypeInfo(use = Id.NAME,
		   	  include = JsonTypeInfo.As.PROPERTY,
			  property = "command")
@JsonSubTypes({
	@Type(value = LoginCommand.class),
	@Type(value = LoginRegisterResponseCommand.class),
	@Type(value = ListJoinableCommand.class),
	@Type(value = AddGameToServerCommand.class),
	@Type(value = AddPlayerToServerCommand.class),
	@Type(value = LogoutCommand.class),
	@Type(value = RegisterCommand.class),
	@Type(value = StartGameCommand.class),
	@Type(value = GetCommandsCommand.class),
	@Type(value = BroadcastToChatCommand.class),
	@Type(value = ClaimRouteCommand.class),
	@Type(value = GetFaceUpTableTrainCardCommand.class),
	@Type(value = RejectDestinationCardCommand.class),
	@Type(value = KeepAllDestCardsCommand.class),
	@Type(value = EndTurnCommand.class),
	@Type(value = GetDestinationCardsCommand.class),
	@Type(value = InitializeGameCommand.class),
	@Type(value = NotifyRouteClaimedCommand.class),
	@Type(value = NotifyTurnCommand.class),
	@Type(value = ShowMessageCommand.class),
	@Type(value = UpdateCarCountAndHandCommand.class),
	@Type(value = UpdateFaceUpTableTrainCardsCommand.class),
	@Type(value = UpdatePlayerDestinationCardsCommand.class),
	@Type(value = UpdatePlayerTrainCardsCommand.class),
	@Type(value = FirstTurnCommand.class),
	@Type(value = LastTurnCompletedCommand.class),
	@Type(value = GetTopDeckTrainCardCommand.class)
})
public interface ICommand {
    public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn, InvalidUsername, InvalidPassword;
    public String getAuthenticationCode();
    public User getUser();
//    public Game getGame();
}
