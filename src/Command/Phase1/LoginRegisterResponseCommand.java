package Command.Phase1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import GameModels.Game;

public class LoginRegisterResponseCommand implements ICommand{
	  private User user;
	  public LoginRegisterResponseCommand(){}
	  public LoginRegisterResponseCommand(User user){
		  this.user = user;
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
	    return user.getStr_authentication_code();}
	  
//	  @JsonIgnore
//	  @Override
//	  public Game getGame() { return null; }
  }
