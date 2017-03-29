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
 * When a player wants more destination cards, this command will be called.
 * The client will respond with a  SelectRequestedDestinationCard command (though it could be multiple cards)
 *
 * Created by natha on 2/28/2017.
 */

public class GetDestinationCardsCommand implements ICommand {
    private String authenticationCode;
    private int gameId;
 
	
	@Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        return ServerFacade.SINGLETON.getDestCards(gameId, authenticationCode);
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
    
    public int getGameId() {
        return gameId;
    }

}
