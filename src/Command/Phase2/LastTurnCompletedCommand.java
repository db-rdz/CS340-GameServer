package Command.Phase2;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import Server.ServerFacade;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;
import ServerModel.GameModels.CardsModel.DestCard;

/**
 * Created by natha on 4/1/2017.
 */

public class LastTurnCompletedCommand implements ICommand {
	
    private int gameId;
    private String authenticationCode;

    public LastTurnCompletedCommand(){}

    @Override
    public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn {
        return ServerFacade.SINGLETON.lastTurnCompleted(gameId, authenticationCode);
    }

    @Override
    public String getAuthenticationCode() {
        return authenticationCode;
    }
    
    public int getGameId() {
        return gameId;
    }

}
