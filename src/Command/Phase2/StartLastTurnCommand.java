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

    //Constructors
    public StartLastTurnCommand(){}

    @Override
    public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn {
        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return null;
    }

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
}
