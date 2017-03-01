package Command;

import Server.IServer.GameIsFullException;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import GameModels.Game;
import Server.ServerFacade;

public class AddPlayerToServerCommand implements ICommand{
  private int i_game_id;
  private String str_authentication_code;
  private AddPlayerToServerCommand(){}
  public AddPlayerToServerCommand(int gameId, String k){
	  i_game_id = gameId;
    str_authentication_code = k;}

  @Override
  public User getUser() {
    return null;
  }

  @Override
  public CommandContainer execute() throws GameIsFullException{ // auth key must be changed to Username somewhere
    return ServerFacade.SINGLETON.addPlayer(str_authentication_code, i_game_id);
  }
  @Override
  public String getAuthenticationCode(){
    return str_authentication_code;}
  
  @JsonIgnore
  @Override
  public Game getGame() { return null; }}