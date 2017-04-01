package Command.Phase2;


import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import Server.ServerFacade;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;
import ServerModel.GameModels.CardsModel.TrainCard;
import ServerModel.GameModels.RouteModel.Route;

import java.util.List;

/**
 * Do we need this class??
 * Created by natha on 3/30/2017.
 */

public class StartLastTurnCommand implements ICommand {

    //Data members
    private Route route;
    private int gameId;
    private String authenticationCode;
    private List<TrainCard> cardsUsed;

    //Constructors
    public StartLastTurnCommand(){}
    public StartLastTurnCommand(int g, String code, Route route1, List<TrainCard> cards) {
        route = route1;
        gameId = g;
        authenticationCode = code;
        cardsUsed = cards;
    }

    @Override
    public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn {
        return ServerFacade.SINGLETON.startLastTurn(gameId, authenticationCode, route, cardsUsed);
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

    public Route getRoute() {
        return route;
    }

    public int getGameId() {
        return gameId;
    }

    public List<TrainCard> getCardsUsed() {
        return cardsUsed;
    }
}
