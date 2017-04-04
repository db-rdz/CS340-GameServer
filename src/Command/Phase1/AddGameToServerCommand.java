package Command.Phase1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import Client.User;
import Command.ICommand;
import GameModels.Game;
import Server.ServerFacade;

public class AddGameToServerCommand implements ICommand {
    private Game game;
    private String str_authentication_code;

    public AddGameToServerCommand() {
    }

    public AddGameToServerCommand(Game g, String k) {
        game = g;
        str_authentication_code = k;
    }

//    @JsonProperty("str_authentication_code")
    @Override
    public String getAuthenticationCode() {
        return str_authentication_code;
    }
    //
    @Override
    public List<ICommand> execute() {
    	return ServerFacade.SINGLETON.addJoinableGameToServer(game, str_authentication_code);
         
    }

    public Game getGame() { return game; }
    
    public String getStr_authentication_code() {
		return str_authentication_code;
	}
}