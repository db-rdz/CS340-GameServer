package Command;

import Client.User;
import GameModels.Game;
import Server.ServerFacade;

public class AddGameToServerCommand implements ICommand {
    private Game game;
    private String str_authentication_code;

    private AddGameToServerCommand() {
    }

    public AddGameToServerCommand(Game g) {
        game = g;
//        str_authentication_code = k;
    }

    @Override
    public String getAuthenticationCode() {
        return str_authentication_code;
    }

    @Override
    public User getUser() {
        return null;
    }
    //
    @Override
    public CommandContainer execute() {
    	return ServerFacade.SINGLETON.addJoinableGame(game);
         
    }

    @Override
    public Game getGame() { return game; }
}