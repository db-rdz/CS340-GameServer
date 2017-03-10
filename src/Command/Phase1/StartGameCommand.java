package Command.Phase1;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import GameModels.Game;
import Server.ServerFacade;

public class StartGameCommand implements ICommand {
    private int gameId;
    private List<String> usernamesInGame;
    
    private StartGameCommand(){}
    public StartGameCommand(int g, List<String> k){
        gameId = g;
        usernamesInGame = k;}

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
    }

    @Override
    public List<ICommand> execute(){
    	ServerFacade.SINGLETON.startGame(gameId, usernamesInGame);
        return null;
    }

    @Override
    public String getAuthenticationCode(){
        return null;}

    public int getGameId() {
        return gameId;
    }

    public List<String> getUsernamesInGame() {
        return usernamesInGame;
    }
}

