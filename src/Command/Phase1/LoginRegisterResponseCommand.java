package Command.Phase1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import GameModels.Game;

public class LoginRegisterResponseCommand implements ICommand{
	
    private User user;
    private Boolean validCredentials; //True if credentials are good
    private Boolean userLoggedInAlready; //False if the user isn't logged in already
    private Boolean userRegisteredAlready;
  
    public LoginRegisterResponseCommand(){}
    public LoginRegisterResponseCommand(User user, Boolean valid, Boolean loggedIn, Boolean registedAlready){
    	this.user = user;
    	validCredentials = valid;
    	userLoggedInAlready = loggedIn;
    	userRegisteredAlready = registedAlready;
    }


    @Override
    public User getUser() {
    	return user;
    }
  
    @Override
    public List<ICommand> execute(){
    	return null;
    }
  
    @JsonIgnore
    @Override
    public String getAuthenticationCode(){
    	return user.getStr_authentication_code();
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
