package Command;
import Client.ClientFacade;
import Client.User;
import GameModels.Game;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ListResumableCommand implements ICommand{ // sent to clients after login/registration
  private List<Game> list_game_list;
  private ListResumableCommand(){}
  public ListResumableCommand(List<Game> list){
    list_game_list = list;}

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
    ClientFacade.SINGLETON.listResumableGames(list_game_list); //TODO: Decide which Game class to use
    return null; //TODO:stub
  }
  @JsonIgnore
  @Override
  public Game getGame() { return null; }}

