package Command;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.ClientFacade;
import Client.User;
import GameModels.Game;

public class LoginRegisterResponseCommand implements ICommand{
  private User user;
  private LoginRegisterResponseCommand(){}
  public LoginRegisterResponseCommand(User user){
	  this.user = user;
    }


  @Override
  public User getUser() {
    return user;
  }
  
  @Override
  public CommandContainer execute(){
    ClientFacade.SINGLETON.loginRegisterSucceeded(user);
    return null;
    }
  @Override
  public String getAuthenticationCode(){
    return null;}
  
  @JsonIgnore
  @Override
  public Game getGame() { return null; }}
