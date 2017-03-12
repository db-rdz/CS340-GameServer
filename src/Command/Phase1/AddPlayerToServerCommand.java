package Command.Phase1;

import Server.IServer.GameIsFullException;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import Client.User;
import Command.ICommand;
import GameModels.Game;
import Server.ServerFacade;

public class AddPlayerToServerCommand implements ICommand{
	  private int i_game_id;
	  private String str_authentication_code;
	  public AddPlayerToServerCommand(){}
	  public AddPlayerToServerCommand(int gameId, String k){
		  i_game_id = gameId;
		  str_authentication_code = k;}
	
	  @JsonIgnore
	  @Override
	  public User getUser() {
	    return null;
	  }
	
	  @Override
	  public List<ICommand> execute() throws GameIsFullException{ // auth key must be changed to Username somewhere
	    return ServerFacade.SINGLETON.addPlayer(str_authentication_code, i_game_id);
	  }
	  
	  @JsonIgnore
	  @Override
	  public String getAuthenticationCode(){
	    return null;}
	  
	  public int getI_game_id() {
		return i_game_id;
	  }
	  
	public String getStr_authentication_code() {
		return str_authentication_code;
	}
  }