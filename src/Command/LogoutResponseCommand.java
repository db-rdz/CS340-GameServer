package Command;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.ClientFacade;
import Client.User;
import GameModels.Game;

public class LogoutResponseCommand implements ICommand{
  
  @Override
  public CommandContainer execute(){
    return ClientFacade.SINGLETON.logoutSucceeded();}

  @Override
  public String getAuthenticationCode() {
    return null;
  }

  @Override
  public User getUser() {
    return null;
  }
  
  @JsonIgnore
  @Override
  public Game getGame() { return null; }
}
