package Command.Phase1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import GameModels.Game;

public class AddJoinableToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private Game game;
  public AddJoinableToClientCommand(){}
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
  public List<ICommand> execute() {
    return null;
  }
  
  public Game getGame() { return game; }
}
