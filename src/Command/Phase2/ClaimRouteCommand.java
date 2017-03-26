package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import Server.ServerFacade;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM CLIENT -> SERVER
 * This command is called whenever a player wants to claim a route.
 * A BroadcastCommand will be called after to tell other players when a route has been claimed.
 *
 * Created by natha on 2/27/2017.
 */

public class ClaimRouteCommand implements ICommand {
    //Data members
    private String mappingName;
    private int gameId;
    String authenticationCode;

    //Constructors
    public ClaimRouteCommand(){}
    public ClaimRouteCommand(int g, String code, String route1) {
    	mappingName = route1;
        gameId = g;
        authenticationCode = code;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        return ServerFacade.SINGLETON.claimRoute(gameId, authenticationCode, mappingName);
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return authenticationCode;
    }

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
    }

    public String getMappingName() {
		return mappingName;
	}
    
    public int getGameId()
    {
    	return gameId;
    }
    
    public String getStrAuthenticationCode()
    {
    	return authenticationCode;
    }

}
