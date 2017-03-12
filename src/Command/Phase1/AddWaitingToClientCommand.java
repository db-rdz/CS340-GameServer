package Command.Phase1;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import GameModels.Game;

public class AddWaitingToClientCommand implements ICommand{ // sent after changes from what List... commands sent
	
	private int gameId;
	private List<String> usernames;
	private Boolean isCreator;
//  private Game game;
	public AddWaitingToClientCommand(){}
//  public AddWaitingToClientCommand(Game g){
//    game = g;}
	public AddWaitingToClientCommand(int id, List<String> names, Boolean creator) {
		gameId = id;
		usernames = names;
		isCreator = creator;
	}

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
//  public Game getGame() { return game; }
	
	public int getGameId() {
		return gameId;
	}
	
	public List<String> getUsernames() {
		return usernames;
	}
	
	public Boolean getIsCreator() {
		return isCreator;
	}
}
