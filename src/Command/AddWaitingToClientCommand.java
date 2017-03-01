package Command;
import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.ClientFacade;
import Client.User;
import GameModels.Game;

public class AddWaitingToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private Game game;
  private AddWaitingToClientCommand(){}
  public AddWaitingToClientCommand(Game g){
    game = g;}

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
    return ClientFacade.SINGLETON.addWaitingGame(game);
  }
  @Override
  public Game getGame() { return game; }}
