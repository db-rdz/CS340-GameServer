package Command;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import Client.User;
import GameModels.Game;
import Server.ServerFacade;
import Server.IServer.UserAlreadyLoggedIn;

public class LoginCommand implements ICommand {
	
    private User user;

    private LoginCommand() {
    }

    public LoginCommand(User u) {
        user = u;
    }
    

    @Override
    public CommandContainer execute() {
        try {
//        	System.out.println("hello from logincommand execute()");
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

    @Override
    public String getAuthenticationCode() {
        return user.getStr_authentication_code();
    }

    @Override
    public User getUser() {
        return user;
    }
    
    @JsonIgnore
    @Override
    public Game getGame() { return null; }
}