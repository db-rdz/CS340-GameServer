package Command;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.ClientFacade;
import Client.User;
import GameModels.Game;

public class DeleteGameCommand implements ICommand{
  private int int_game_id;
  private DeleteGameCommand(){}
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
  public CommandContainer execute(){
    ClientFacade.SINGLETON.removeGame(int_game_id);
    return null; //TODO: stub
  }
  @JsonIgnore
@Override
public Game getGame() {
	return null;
}
}
