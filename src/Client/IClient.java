package Client;

import GameModels.Game;

import java.util.List;

import Command.CommandContainer;

/**
 * Created by natha on 2/1/2017.
 */

public interface IClient {

    /*
     * Created own Exceptions for when checking login authorization
     */
    public static class InvalidUsername extends Exception {
    }

    public static class InvalidPassword extends Exception {
    }

    public static class UsernameAlreadyExists extends Exception {
    }

    //TODO: Should we change the return type to CommandContainer to match IServer?
    public CommandContainer login(String username, String password, String authenticationCode) throws InvalidUsername, InvalidPassword;
    public CommandContainer register(String username, String password, String authorizationCode) throws UsernameAlreadyExists;
    public CommandContainer addResumableGame(Game game);
    public CommandContainer addJoinableGame(Game game);
    public CommandContainer addWaitingGame(Game game);
    public CommandContainer removeGame(int gameId);
    public CommandContainer startGame(Game game, String authorizationCode);
    public CommandContainer addPlayer(String username, int gameId);
    public CommandContainer logout(String str_authentication_code);
    public CommandContainer listJoinableGames(List<Game> listJoinableGames);
    public CommandContainer listResumableGames(List<Game> listResumableGames);
    public CommandContainer listWaitingGames(List<Game> listWaitingGames);
    public CommandContainer loginRegisterSucceeded(User user);
    public CommandContainer logoutSucceeded();

    public void attachObserver(/* Observer object */);
    public void detachObserver(/* Observer object */);
}
