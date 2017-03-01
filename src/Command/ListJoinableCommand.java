package Command;
import Client.ClientFacade;
import Client.User;
import GameModels.Game;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ListJoinableCommand implements ICommand{ // sent to clients after login/registration
  private List<Game> list_game_list;
  private ListJoinableCommand(){}
  public ListJoinableCommand(List<Game> list){
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
    ClientFacade.SINGLETON.listJoinableGames(list_game_list); //TODO: Decide which Game class to use
    return null;
  }
  @JsonIgnore
  @Override
  public Game getGame() { return null; }}
