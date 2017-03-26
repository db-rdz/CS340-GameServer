package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import Server.ServerFacade;
import GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ServerModel.GameModels.CardsModel.TrainCard;
import ServerModel.GameModels.RouteModel.Route;
import ServerModel.GameModels.RouteModel.iRoute;

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
    private Route route;
    private int gameId;
    String authenticationCode;
    List<TrainCard> cardsUsedToClaimRoute;

    //Constructors
    public ClaimRouteCommand(){}
    public ClaimRouteCommand(int g, String code, Route route1, List<TrainCard> list) {
        route = route1;
        gameId = g;
        authenticationCode = code;
        cardsUsedToClaimRoute = list;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        return ServerFacade.SINGLETON.claimRoute(gameId, authenticationCode, route, cardsUsedToClaimRoute);
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

    public iRoute getRoute() {
        return route;
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
