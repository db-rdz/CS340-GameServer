package com.example.ryanblaser.tickettoride.ServerModel.GameModels;

import com.example.ryanblaser.tickettoride.ServerModel.GameModels.PlayerModel.iPlayer;
import com.example.ryanblaser.tickettoride.ServerModel.UserModel.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

import static com.sun.org.apache.xerces.internal.impl.xpath.regex.CaseInsensitiveMap.get;

/**
 * Created by benjamin on 10/02/17.
 */
public class Game implements iGame, Cloneable {



    //-----------------------------------------STATIC VARIABLES----------------------------------------//
    /** _M_idToGame maps a game id to a game. */
    private static Map<Integer, Game> _M_idToGame = new HashMap();
    /**  These are all the games that haven't started */
    private static Map<Integer, Game> _M_listOfAvailableGames = new HashMap<>();
    /**  These are all the games that have already started */
    private static Map<Integer, Game> _M_listOfStartedGames = new HashMap<>();
    /**  The max number of players */
    public static int _MAX_PLAYERS = 4;
    /** Not sure if I should still use this var */
    public static int modelNextGameIndex = 0;


    //-----------------------------------------CLASS VARIABLES-----------------------------------------//

    /** The name of the game. I don't think we are using this one. */
    private String _S_gameName = null;
    /** tells if a game has already begun. */
    private Boolean _B_active = null;
    /** The id of the game in the database */
    private int _i_gameId = -1;
    /** The next player to make a move */
    private int _i_nextPlayerTurn = 0;

    /** _M_idToUserInGame maps a username string to a User. It only maps the user's of the people in the game */
    private Map<String, User> _M_idToUserInGame = new LinkedHashMap<>();
    /** _M_idToUserInGame maps a username string to a Player. Player extends User */
    private Map<String, User> _M_idToPlayerInGame = new LinkedHashMap<>();
    /** _L_turnList lists the order of the players in the game */
    private List<String> _L_turnList = new ArrayList<>();
    /** _i_numberOfPlayers are the current number of users in the game */
    private int _i_numberOfPlayers = 0;
    /** This is the username of the person that created the game. */
    private String _S_ownerUsername = null;

    private String _S_gameName;

    private Boolean _S_active = true;

    //-----------------------------------------STATIC FUNCTIONS----------------------------------------//

    /** Gets the corresponding game mapped to the specified id.  */
    public static Game getGameWithId(int gameId){
        return _M_idToGame.get(gameId);
    }

    /** Tells if an specific user is in the game */
    public static Boolean isUserInGame( String username, int gameId ){
        // Will get user if user in game. If not it will return false.
        User result = _M_idToGame.get(gameId).getUserInGame(username);
        //Check for the result and return false if the result is null
        if(result == null){ return false; }
        //If the function made it this far means that the user is in the game.
        return true;
    }

    /** Tells if a game is full or not  */
    public static Boolean isGameFull(int gameId){
        if(_M_idToGame.get(gameId).get_numberOfPlayers() > 3){
            return true;
        }
        return false;
    }

    /** Returns the list of all games */
    public static List<Game> getAllGames(){
        return (List<Game>) _M_idToGame.values();
    }


    /** Adds an already created game in the available games list and maps it to its Id */
    public static Boolean addGameToModel(Game game, int gameId){
        mapGameToId(game, gameId);
        insertInAvailableGames(game);
        return true;
    }


    /** Maps the game to its game id */
    public static Boolean mapGameToId(Game game, int gameId){
        _M_idToGame.put(gameId, game);
        return true;
    }

    /** Adds the game to the available game list. */
    public static Boolean insertInAvailableGames(Game game){
        _M_listOfAvailableGames.put(game.get_gameId(), game);
        return true;
    }

    /** updates the Server Game model */
    public static void update(){}

    //-----------------------------------------SETTER AND GETTERS-------------------------------------//
    public int get_numberOfPlayers() { return _i_numberOfPlayers; }
    public void set_numberOfPlayers(int _i_numberOfPlayers) { this._i_numberOfPlayers = _i_numberOfPlayers; }

    public static List<Game> get_allAvailableGames() { return (List<Game>)_M_listOfAvailableGames.values(); }
    public static void set_availableGameList(Map<Integer, Game> AvailableGames) {_M_listOfAvailableGames = AvailableGames;}

    public static List<Game> get_allStartedGames() { return (List<Game>) _M_listOfStartedGames.values(); }
    public static void set_StartedGameList(Map<Integer, Game> StartedGames) { _M_listOfStartedGames = StartedGames; }

    public String get_gameName() { return _S_gameName; }
    public void set_gameName(String _S_gameName) { this._S_gameName = _S_gameName; }

    public Boolean is_active() { return _B_active; }
    public void set_active(Boolean _B_active) { this._B_active = _B_active; }

    public int get_gameId() { return _i_gameId; }
    public void set_gameId(int _i_gameId) { this._i_gameId = _i_gameId; }

    public String get_gameOwner(){ return _S_ownerUsername; }
    public void set_gameOwner(String username){ _S_ownerUsername = username; }

    public String get_S_gameName() {
        return _S_gameName;
    }

    public void set_S_gameName(String _S_gameName) {
        this._S_gameName = _S_gameName;
    }

    public Boolean get_S_active() {
        return _S_active;
    }

    public void set_S_active(Boolean _S_active) {
        this._S_active = _S_active;
    }


    //-----------------------------------------CLASS FUNCTIONS----------------------------------------//

        //---------USER ACTIONS (IE. GET USER, GET ALL PLAYERS, ADD PLAYER, REMOVE PLAYER, ETC)-------//

    /** Uses a map to return the User object associated with a user id (returns null if user is not in the game) */
    private User getUserInGame( String username ){
        return _M_idToUserInGame.get(username);
    }

    private List<String> getAllPlayers(){
        return (List<String>)_M_idToUserInGame.keySet();
    }

    public Boolean addPlayerToGame(User user){
        _M_idToUserInGame.put(user.get_Username(), user);
        return true;
    }

    public Boolean removePlayer(String username ){
        User player = _M_idToUserInGame.get(username);
        if( player != null ){
            player.exitGame();
            _M_idToUserInGame.remove(username);
            return true;
        }
        return false;
    }

    public void removeAllPlayer(){
        List<String> allPlayers = getAllPlayers();
        for(int i = 0; i < allPlayers.size(); i++){
            removePlayer(allPlayers.get(i));
        }
    }

    public Boolean addPlayerToGame( String username, User user){
        _M_idToUserInGame.put(username, user);
        return true;
    }
<<<<<<< HEAD

        //------------------GAME ACTIONS (IE. START, CANCEL, DESTROY, ETC...)-------------------------//

    public Boolean startGame(){
        if(_i_numberOfPlayers >= 2){
            moveGameToStartedGames();
            _L_turnList = (List<String>) _M_idToUserInGame.keySet();
            return true;
        }
        return false;
    }

    public void destroyStartedGame(){
        _M_idToGame.remove(_i_gameId);
        _M_listOfStartedGames.remove(_i_gameId);
    }

    public void destroyAvailableGame(){
        _M_idToGame.remove(_i_gameId);
        _M_listOfAvailableGames.remove(_i_gameId);
    }

    public Boolean endGame(){
        removeAllPlayer();
        destroyStartedGame();
        return true;
    }

    public Boolean cancelGame(){
        if(_B_active == true){
            return false;
        }
        removeAllPlayer();
        destroyAvailableGame();
        return true;
    }


    public Boolean moveGameToStartedGames(){
        _M_listOfAvailableGames.remove(_i_gameId);
        _M_listOfStartedGames.put(_i_gameId, this);
        return true;
    }

    @Override
    public Game clone(){
        Game game = new Game();
        List<User> new_userList = (List<User>) _M_idToUserInGame.values();
  //      game.setPlayers(players); //TODO: edit this
        game.set_gameId(_i_gameId);
        return game;
    }











=======
>>>>>>> d1407a606be8cafa8691643a6e2a0e9215c38226
}
