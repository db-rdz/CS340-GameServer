package Command.Phase1;
import Server.IServer.UserAlreadyLoggedIn;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import GameModels.Game;
import Server.ServerFacade;

public class RegisterCommand implements ICommand {
    private String username;
    private String password;

    public RegisterCommand() {
    }

    public RegisterCommand(String u, String p) {
        username = u;
        password = p;
    }

    @Override
    public List<ICommand> execute() throws UserAlreadyLoggedIn {
        try {
            return ServerFacade.SINGLETON.register(username, password);
        } catch (Client.IClient.UsernameAlreadyExists usernameAlreadyExists) {
            usernameAlreadyExists.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAuthenticationCode() {
        return null;
    }

    @Override
    public User getUser() {
        return null;
    }
    
//    @JsonIgnore
//    @Override
//    public Game getGame() { return null; }
}