package ServerModel;

import Database.DAO;
import ServerModel.GameModels.Game;
import ServerModel.GameModels.iGame;
import ServerModel.UserModel.User;
import ServerModel.UserModel.iUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 10/02/17.
 */
public class ServerModel implements iModel {

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
    public List<Game> getUserJoinedGames( String username ){
        return User.getUserWithUsername(username).getJoinedGames();
    }

    @Override
    public Boolean initializeGameFromDB( String username, int gameId ){
        User.getUserWithUsername(username).initializeGame(gameId);
        return true;
    }

//    @Override
//    public Boolean terminateGame( int gameId ){
//        User.getUserWithID(userId).initializeGame(gameId);
//        return true;
//    }

    @Override
    public Boolean addPlayerToGame(String username, int gameID) {
        if(Game.isUserInGame(username, gameID)){
            return false;
        }
        if(Game.isGameFull(gameID)){
            return false;
        }

        Game game = Game.getGameWithId(gameID);
        User.getUserWithUsername(username).addGameToJoinedGames(game);
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
    public Boolean logIn(String username) {
        return User.addLoggedInUser(username);
    }

    @Override
    public Boolean logOut(String username){
        return false;
    }
}
