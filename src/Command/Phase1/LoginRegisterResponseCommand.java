package Command.Phase1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import GameModels.Game;

public class LoginRegisterResponseCommand implements ICommand {
    private User user;
    private Boolean validCredentials;
    private Boolean userLoggedInAlready;
    private Boolean userRegisteredAlready;

    public LoginRegisterResponseCommand(){}
    public LoginRegisterResponseCommand(User user, boolean credentials, boolean loggedin, boolean registeredalready){
        this.user = user;
        validCredentials = credentials;
        userLoggedInAlready = loggedin;
        userRegisteredAlready = registeredalready;
    }


    @Override
    public List<ICommand> execute(){
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode(){
        return null;
    }
    
    public User getUser() {
		return user;
	}

    public Boolean getValidCredentials() {
        return validCredentials;
    }

    public Boolean getUserLoggedInAlready() {
        return userLoggedInAlready;
    }

    public Boolean getUserRegisteredAlready() {
        return userRegisteredAlready;
    }
}
