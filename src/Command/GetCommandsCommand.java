package Command;
import Server.ClientProxy;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import GameModels.Game;
public class GetCommandsCommand implements ICommand{
    private String username;
    public GetCommandsCommand(){}
    public GetCommandsCommand(String u){
        username = u;}
    
    public String getUsername()
    {
        return username;
    }
    
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
        return ClientProxy.SINGLETON.getUserCommands(username);
    }
    @JsonIgnore
    @Override
    public Game getGame() { return null; }}
