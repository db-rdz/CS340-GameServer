package Command.Phase1;
import Client.User;
import Command.ICommand;
import GameModels.Game;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ListWaitingCommand implements ICommand{ // sent to clients after login/registration
  private List<Integer> list_gameIds;
  public ListWaitingCommand(){}
  public ListWaitingCommand(List<Integer> list){
	  list_gameIds = list;}

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
    return null;
  }
//  @JsonIgnore
//  @Override
//  public Game getGame() { return null; }
  
}
