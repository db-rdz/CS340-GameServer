package Server;
import Client.IClient;
import Client.User;
import Command.CommandContainer;
import GameModels.Game;
import UserModel.*;

import java.util.List;

/**
 * Created by RyanBlaser on 2/5/17.
 */

public interface IServer {

    public static class GameIsFullException extends Exception {
    }
    
    public static class UserAlreadyLoggedIn extends Exception {
    	
    }

    CommandContainer login(String username, String password) throws IClient.InvalidUsername, IClient.InvalidPassword, UserAlreadyLoggedIn;
    CommandContainer register(String username, String password) throws IClient.UsernameAlreadyExists, UserAlreadyLoggedIn;
    CommandContainer addGame(Game game);
    public CommandContainer addResumableGame(Game game);
    public CommandContainer addJoinableGame(Game game);
    public CommandContainer addWaitingGame(Game game);
    CommandContainer removeGame(Game game);
    CommandContainer startGame(Game game, String str_authentication_code);
    public CommandContainer addPlayer(String str_authentication_code, int iGameId) throws GameIsFullException;
    public CommandContainer logout(String str_authentication_code);
}