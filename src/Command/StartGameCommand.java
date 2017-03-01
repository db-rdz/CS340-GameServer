package Command;
import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import GameModels.Game;
import Server.ServerFacade;

public class StartGameCommand implements ICommand {
    private Game game;
    private String str_authentication_code;
    private StartGameCommand(){}
    public StartGameCommand(Game g, String k){
        game = g;
        str_authentication_code = k;}

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public CommandContainer execute(){
        return ServerFacade.SINGLETON.startGame(game, str_authentication_code);}
    @Override
    public String getAuthenticationCode(){
        return str_authentication_code;}
    
    @JsonIgnore
    @Override
    public Game getGame() { return null; }}
