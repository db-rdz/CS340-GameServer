package com.example.ryanblaser.tickettoride.ServerModel;

import com.example.ryanblaser.tickettoride.Database.DAO;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.Game;
import com.example.ryanblaser.tickettoride.ServerModel.GameModels.iGame;
import com.example.ryanblaser.tickettoride.ServerModel.UserModel.User;
import com.example.ryanblaser.tickettoride.ServerModel.UserModel.iUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 10/02/17.
 */
public class ServerModel implements iModel {

    public static ServerModel _SINGLETON = new ServerModel();

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
    public User getUserWithUsername( String username ){ return User.getUserWithUsername(username); }

    @Override
    public List<User> getAllLoggedInUsers(){ return User.getAllLoggedInUsers(); }

    @Override
    public Game getUserJoinedGame( String username ){
        return User.getUserWithUsername(username).get_joinedGame();
    }

    @Override
    public Boolean initializeGameFromDB( String username, int gameId ){
        User.getUserWithUsername(username).initializeGame(gameId);
        return true;
    }

    @Override
    public Boolean startGame(int gameId){ return Game.getGameWithId(gameId).startGame(); }

    @Override
    public Boolean cancelGame(int gameId){ return Game.getGameWithId(gameId).cancelGame(); }

    @Override
    public Boolean endGame(int gameId){ return Game.getGameWithId(gameId).endGame(); }


    @Override
    public Boolean addPlayerToGame(String username, int gameID) {
        User user = User.getUserWithUsername(username);
        if(user.isUserInGame()){
            return false;
        }
        if(Game.isGameFull(gameID)){
            return false;
        }

        Game game = Game.getGameWithId(gameID);
        game.addPlayerToGame(user);
        user.joinGame(game);
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
    public Boolean logOut(String username){ return User.logOutUser(username); }

    @Override
    public Game getGame(int gameId) {
        return Game.getGameWithId(gameId);
    }
}
