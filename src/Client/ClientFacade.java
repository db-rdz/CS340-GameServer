package Client;

import GameModels.Game;
import Server.IServer;

import java.util.List;
import java.util.Set;

import Command.CommandContainer;

/**
 * This class accesses the data in the Client Holder/Model.
 * The ClientFacade talks to the ServerProxy class to use these methods.
 * The ClientFacade allows us to communicate to the server from the client side.
 *
 * Created by natha on 2/4/2017.
 *
 * The following note added by 0joshuaolson1 on 2/10/2017:
 * IClient should only have methods the server 'calls' on the ClientProxy.
 * All other methods (without @Override) are called by presenters/fragments/MainActivity.
 */

public class ClientFacade implements IClient {


    public static ClientFacade SINGLETON = new ClientFacade();
    private Set<User> set_users;
    private ClientModel clientmodel;
    //private LoginPresenter loginpresenter;
    //private LobbyPresenter lobbypresenter;

    public CommandContainer login(String username, String password, String authenticationCode) throws InvalidUsername, InvalidPassword {
        try {
            return ServerProxy.SINGLETON.login(username, password);

        } catch (Exception e) { //Catches exception if the login failed.
            e.printStackTrace();
        }
        
        return null;
    }

    public CommandContainer register(String username, String password, String authorizationCode) throws UsernameAlreadyExists {
        try {
            return ServerProxy.SINGLETON.register(username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CommandContainer addResumableGame(Game game) {
        clientmodel.addResumableGame(game);
        //lobbypresenter
		return null;
    }

    @Override
    public CommandContainer addJoinableGame(Game game) {
        clientmodel.addJoinableGame(game);
        //lobbypresenter
		return null;
    }

    @Override
    public CommandContainer addWaitingGame(Game game) {
        clientmodel.addWaitingGame(game);
        //lobbypresenter
		return null;
    }

    @Override
    public CommandContainer removeGame(int gameId) {
        clientmodel.deleteGame(gameId);
        //lobbypresenter
		return null;
    }

    @Override
    public CommandContainer startGame(Game game, String authenticationCode) { // just gameId?
        return ServerProxy.SINGLETON.startGame(game, authenticationCode);
        //lobbypresenter
    }

    public void addPlayerToServer(String str_authentication_code, int gameId) throws IServer.GameIsFullException { // which exception?
        ServerProxy.SINGLETON.addPlayer(str_authentication_code, gameId); //server will get username
        //lobbypresenter
    }

    @Override
    public CommandContainer addPlayer(String username, int gameId){
        clientmodel.addPlayer(username, gameId);
        //lobbypresenter
		return null;
    }

    public void attachObserver() { //necessary?
        //loginpresenter, lobbypresenter
    }

    public void detachObserver() { // necessary?
        //loginpresenter, lobbypresenter
    }

    public CommandContainer logout(String authenticationCode) {
        return ServerProxy.SINGLETON.logout(authenticationCode);
    }

    @Override
    public CommandContainer listJoinableGames(List<Game> listJoinableGames) {
        clientmodel.setJoinableGames(listJoinableGames); //TODO: Change type of game 
        //lobbypresenter
		return null;
    }

    @Override
    public CommandContainer listResumableGames(List<Game> listResumableGames) {
        clientmodel.setResumableGames(listResumableGames);
        //lobbypresenter
		return null;
    }

    @Override
    public CommandContainer listWaitingGames(List<Game> listWaitingGames) {
        clientmodel.setWaitingGames(listWaitingGames);
        //lobbypresenter
		return null;
    }

    @Override
    public CommandContainer loginRegisterSucceeded(User user) {
        clientmodel = new ClientModel();
        clientmodel.setAuthenticationKey(user.getStr_authentication_code());
        clientmodel.setUser(user);
        //change view/presenter
		return null;
    }

    @Override
    public CommandContainer logoutSucceeded() {
        //change view/presenter
    	return null;
    }

    public User find(User user) {
        for (User userSearch : set_users) { //Searches all the users in the set.
            if (userSearch.equals(user)) { //If the username is in the set,
                return userSearch; //return the user found
            }
        }

        return user; //Otherwise return the user that was initially searched for.
    }

    public User getCurrentUser() { return clientmodel.getUser(); }
    public void setCurrentUser(User user) { clientmodel.setUser(user);}

}