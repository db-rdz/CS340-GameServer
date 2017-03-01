package Command;
import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.ClientFacade;
import Client.User;
import GameModels.Game;

public class AddPlayerToClientCommand implements ICommand{ // sent after changes from what List... commands sent
  private int int_game_id;
  private String str_authentication_code;
  private AddPlayerToClientCommand(){}
  public AddPlayerToClientCommand(String code, Integer gameId){
    str_authentication_code = code;
    int_game_id = gameId;}

  @Override
  public String getAuthenticationCode() {
    return str_authentication_code;
  }

  @Override
  public User getUser() {
    return null;
  }

  @Override
  public CommandContainer execute(){
//    ClientFacade.SINGLETON.addPlayer(str_authentication_code, str_game_id);
    return null; //TODO: stub
  }
  @JsonIgnore
  @Override
  public Game getGame() { return null; }}
