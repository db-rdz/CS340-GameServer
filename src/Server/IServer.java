package Server;
import Client.IClient;
import Client.User;
import Command.ICommand;
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

    public List<ICommand> login(String username, String password) throws IClient.InvalidUsername, IClient.InvalidPassword, UserAlreadyLoggedIn;
    public List<ICommand> register(String username, String password) throws IClient.UsernameAlreadyExists, UserAlreadyLoggedIn;
    public List<ICommand> addJoinableGameToServer(Game game, String authenticationCode);
    public List<ICommand> addWaitingGame(Game game);
    public List<ICommand> removeGame(Game game);
    public List<ICommand> startGame(int gameId, List<String> usernamesInGame);
    public List<ICommand> addPlayer(String str_authentication_code, int iGameId) throws GameIsFullException;
    public List<ICommand> logout(String str_authentication_code);
}