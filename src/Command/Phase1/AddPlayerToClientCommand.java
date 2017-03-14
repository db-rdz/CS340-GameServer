package Command.Phase1;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import GameModels.Game;

/**
 * This command is only sent to those who are already in a specific game lobby
 * so they can see the new players in the lobby view
 * @author Josh
 *
 */
public class AddPlayerToClientCommand implements ICommand{ // sent after changes from what List... commands sent
	private int int_game_id;
	private String str_username;
	public AddPlayerToClientCommand(){}
	  public AddPlayerToClientCommand(String name, Integer gameId){
		  str_username = name;
		  int_game_id = gameId;
	  }

	
	@JsonIgnore
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
	//    ClientFacade.SINGLETON.addPlayer(str_authentication_code, str_game_id);
	  return null; 
	}

	  
	  public String getStr_username() {
		return str_username;
	}
	  
	public int getInt_game_id() {
		return int_game_id;
	}
	
}
