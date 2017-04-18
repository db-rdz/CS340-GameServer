package ServerModel;

import GameModels.Game;
import GameModels.iGame;
import UserModel.User;
import UserModel.iUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 10/02/17.
 */
public class ServerModel implements iModel {
	
	public static ServerModel SINGLETON = new ServerModel();

    @Override
    public List<Game> getAvailableGames() {
        return Game.get_allAvailableGames();
    }

    @Override
    public List<Game> getStartedGames() {
        return Game.get_allStartedGames();
    }

    @Override
    public List<Game> getAllGames() {
        return Game.getAllGames();
    }

    @Override
    public List<Integer> getUserJoinedGames( String username ){
        return User.getUserWithUsername(username).get_L_joinedGames();
    }

    @Override
    public Boolean initializeGameFromDB( String username, int gameId, Game game ){
        User.getUserWithUsername(username).initializeGame(gameId, game);
        return true;
    }

//    @Override
//    public Boolean terminateGame( int gameId ){
//        User.getUserWithID(userId).initializeGame(gameId);
//        return true;
//    }

    @Override
    public Boolean addPlayerToGame(String username, int gameID) {
    	if(Game.isGameFull(gameID)){
    		return false;
    	}
        if(Game.isUserInGame(username, gameID)){
            return false;
        }

        Game game = Game.getGameWithId(gameID);
        User.getUserWithUsername(username).addGameToJoinedGames(gameID);
        return true;
    }

    @Override
    public Boolean removePlayerFromGame(String username, int gameID) {
        return Game.getGameWithId(gameID).removePlayer(username);
    }

    @Override
    public void removeAllPlayersFromGame(int gameID) {
        Game.getGameWithId(gameID).removeAllPlayer();
    }

    @Override
    public Boolean addGameToLobby(iGame game) {
        return null;
    }

    @Override
    public Boolean logIn(String username, User user) {
        return User.addLoggedInUser(username, user);
    }

    @Override
    public Boolean logOut(String username){
    	
//        User.get_L_listOfAllUsers().remove(username);
    	return false;
    }

	public Game getGame(int intGameId) {
		
		Game game = null;
		try {
			game = Game.getAllGames().get(--intGameId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return game;
	}
}
