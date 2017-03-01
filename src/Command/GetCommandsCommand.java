package Command;
import Server.ClientProxy;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import GameModels.Game;
public class GetCommandsCommand implements ICommand{
  private List<ICommand> list_icommands;
  public GetCommandsCommand(){}
  public GetCommandsCommand(List<ICommand> list){
    list_icommands = list;}

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
    return ClientProxy.SINGLETON.getUserCommands(getUser().getUsername());
  }
  @JsonIgnore
  @Override
  public Game getGame() { return null; }}