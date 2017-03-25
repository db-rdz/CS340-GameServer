package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import ServerModel.GameModels.RouteModel.Route;
import GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM CLIENT -> SERVER
 * Sends a message to all the players in the game when a route is claimed by a specific player.
 *
 * Created by natha on 2/28/2017.
 */

public class NotifyRouteClaimedCommand implements ICommand {

    //Data members
    private String str_message;
    private Route route;

    //Constructor
    public NotifyRouteClaimedCommand(String str_message, Route r) {
        this.str_message = str_message;
        route = r;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
    }


    public String getStr_message() {
        return str_message;
    }
    
    public Route getRoute()
    {
    	return route;
    }
}
