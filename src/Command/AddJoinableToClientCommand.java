package Command;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.ClientFacade;
import Client.User;
import GameModels.Game;

public class AddJoinableToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private Game game;
  private AddJoinableToClientCommand(){}
  public AddJoinableToClientCommand(Game g){
    game = g;}

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
  public CommandContainer execute() {
    ClientFacade.SINGLETON.addJoinableGame(game); //TODO:fix the return type
    return null;
  }
  
  @Override
  public Game getGame() { return game; }
}
