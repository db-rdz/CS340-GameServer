package Command.Phase1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import GameModels.Game;

public class DeleteGameCommand implements ICommand{
	
	private int int_game_id;
	public DeleteGameCommand(){}
	public DeleteGameCommand(int gameId){
	int_game_id = gameId;}
	
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

	public int getInt_game_id() {
		return int_game_id;
	}
}
