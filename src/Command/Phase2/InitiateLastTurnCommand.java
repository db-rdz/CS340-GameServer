package Command.Phase2;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.IClient.InvalidPassword;
import Client.IClient.InvalidUsername;
import Client.User;
import Command.ICommand;
import Server.ServerFacade;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;

public class InitiateLastTurnCommand implements ICommand {

    private int gameId;
    private String authenticationCode;

    public InitiateLastTurnCommand(){}

    @Override
    public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn {
        return ServerFacade.SINGLETON.initiateLastTurn(gameId, authenticationCode);
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
