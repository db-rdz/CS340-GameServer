package Command.Phase1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import GameModels.Game;
import Server.ServerFacade;

public class LogoutCommand implements ICommand{
	
    private User user;
    
    private LogoutCommand(){}
    public LogoutCommand(User user){
        this.user = user;
    }

    @Override
    public List<ICommand> execute(){
    	return ServerFacade.SINGLETON.logout(user);
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }

    @Override
    public User getUser() {
        return user;
    }
  
}
