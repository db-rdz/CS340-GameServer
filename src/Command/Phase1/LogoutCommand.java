package Command.Phase1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import GameModels.Game;
import Server.ServerFacade;

public class LogoutCommand implements ICommand{
  private String str_authentication_code;
  public LogoutCommand(){}
  public LogoutCommand(String k){
    str_authentication_code = k;}

  @Override
  public String getAuthenticationCode() {
    return null;
  }

  @Override
  public User getUser() {
    return null;
  }
  
  @Override
  public List<ICommand> execute(){
    return ServerFacade.SINGLETON.logout(str_authentication_code);
    }
  
//  @JsonIgnore
//  @Override
//  public Game getGame() { return null; }
  
}
