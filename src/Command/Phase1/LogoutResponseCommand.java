package Command.Phase1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import GameModels.Game;

public class LogoutResponseCommand implements ICommand{
  
  @Override
  public List<ICommand> execute(){
    return null;}

  @Override
  public String getAuthenticationCode() {
    return null;
  }
}
