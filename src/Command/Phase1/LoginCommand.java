package Command.Phase1;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import Client.User;
import Command.ICommand;
import GameModels.Game;
import Server.ServerFacade;
import Server.IServer.UserAlreadyLoggedIn;

public class LoginCommand implements ICommand {
	
    private User user;

    public LoginCommand() {
    }

    public LoginCommand(User u) {
        user = u;
    }
    

    @Override
    public List<ICommand> execute() {
        try {
            return ServerFacade.SINGLETON.login(user.getUsername(), user.getPassword());
        } catch (Client.IClient.InvalidUsername invalidUsername) {
            invalidUsername.printStackTrace();
        } catch (Client.IClient.InvalidPassword invalidPassword) {
            invalidPassword.printStackTrace();
        } catch (UserAlreadyLoggedIn e) {
        	e.printStackTrace();
        }
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return user.getStr_authentication_code();
    }

    @Override
    public User getUser() {
        return user;
    }
    
//    @JsonIgnore
//    @Override
//    public Game getGame() { return null; }
}