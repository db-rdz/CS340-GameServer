package Command.Phase1;
import Client.User;
import Command.ICommand;
import GameModels.Game;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListJoinableCommand implements ICommand{ // sent to clients after login/registration
	
	  /**
	   * This is a list of gameIds we send back to the Client.
	   * We don't use Game because the client shouldn't have access to the games they're apart of.
	   */
	  private List<Integer> list_gameIds;
	  public ListJoinableCommand(){}
	  public ListJoinableCommand(List<Integer> list){
		  list_gameIds = list;}
	
	  @JsonIgnore
	  @Override
	  public String getAuthenticationCode() {
	    return null;
	  }
	
	  @JsonIgnore
	  @Override
	  public User getUser() {
	    return null;
	  }
	  
	  @Override
	  public List<ICommand> execute(){
	    return null;
	  }
//	  @JsonIgnore
//	  @Override
//	  public Game getGame() { return null; }
	  
	  public List<Integer> getList_gameIds() {
		return list_gameIds;
	}
}
