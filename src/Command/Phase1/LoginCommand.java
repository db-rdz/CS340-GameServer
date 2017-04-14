package Command.Phase1;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sun.xml.internal.ws.api.pipe.ThrowableContainerPropertySet;

import Client.User;
import Client.IClient.InvalidPassword;
import Client.IClient.InvalidUsername;
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
    public List<ICommand> execute() throws InvalidUsername, InvalidPassword, UserAlreadyLoggedIn {
        try {
            return ServerFacade.SINGLETON.login(user.getUsername(), user.getPassword());
        } catch (Client.IClient.InvalidUsername invalidUsername) {
            invalidUsername.printStackTrace();
            throw invalidUsername;
        } catch (Client.IClient.InvalidPassword invalidPassword) {
            invalidPassword.printStackTrace();
            throw invalidPassword;
        } catch (UserAlreadyLoggedIn e) {
        	e.printStackTrace();
        	throw e;
        }
    }
    

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return user.getStr_authentication_code();
    }
    
    public User getUser() {
		return user;
	}
}