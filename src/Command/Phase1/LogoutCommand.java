package Command.Phase1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import GameModels.Game;
import Server.ServerFacade;

public class LogoutCommand implements ICommand{
  private String authenticationCode;
  
  public LogoutCommand(){}
  public LogoutCommand(String k){
	  authenticationCode = k;}

  @Override
  public String getAuthenticationCode() {
    return authenticationCode;
  }
  
  @Override
  public List<ICommand> execute(){
    return ServerFacade.SINGLETON.logout(authenticationCode);
    }
  
  
}
