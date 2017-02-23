package ServerModel.GameModels;

import ServerModel.GameModels.PlayerModel.iPlayer;
import ServerModel.UserModel.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.sun.org.apache.xerces.internal.impl.xpath.regex.CaseInsensitiveMap.get;

/**
 * Created by benjamin on 10/02/17.
 */
public class Game implements iGame {



    //-----------------------------------------STATIC VARIABLES----------------------------------------//
    /** _M_idToGame maps a game id to a game. */
    private static Map<Integer, Game> _M_idToGame = new HashMap();
    /**  These are all the games that haven't started */
    private static List<Game> _L_listOfAvailableGames = new ArrayList<>();
    /**  These are all the games that have already started */
    private static List<Game> _L_listOfStartedGames = new ArrayList<>();
    /**  The max number of players */
    public static int _MAX_PLAYERS = 4;
    /** Not sure if I should still use this var */
    public static int modelNextGameIndex = 0;


    //-----------------------------------------CLASS VARIABLES-----------------------------------------//
    /** _M_idToUserInGame maps a username string to a User. It only maps the user's of the people in the game */
    private Map<String, User> _M_idToUserInGame = new HashMap<>();
    /** _i_numberOfPlayers are the current number of users in the game */
    private int _i_numberOfPlayers = 0;
    /** This is the user id of the person that created the game. */
    private int _i_gameOwner = -1;

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
    public static Boolean addGame(Game game, int gameId){
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
        _L_listOfAvailableGames.add(game);
        return true;
    }

    /** updates the Server Game model */
    public static void update(){}

    //-----------------------------------------SETTER AND GETTERS-------------------------------------//
    public int get_numberOfPlayers() { return _i_numberOfPlayers; }
    public void set_numberOfPlayers(int _i_numberOfPlayers) { this._i_numberOfPlayers = _i_numberOfPlayers; }

    public static List<Game> get_allAvailableGames() { return _L_listOfAvailableGames; }
    public static void set_availableGameList(List<Game> AvailableGames) {_L_listOfAvailableGames = AvailableGames;}

    public static List<Game> get_allStartedGames() { return _L_listOfStartedGames; }
    public static void set_StartedGameList(List<Game> StartedGames) { _L_listOfStartedGames = StartedGames; }

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
    /** Uses a map to return the User object associated with a user id (returns null if user is not in the game) */
    private User getUserInGame( String username ){
        return _M_idToUserInGame.get(username);
    }

    public Boolean removePlayer(String username ){
        User player = _M_idToUserInGame.get(username);
        if( player != null ){
            _M_idToUserInGame.remove(username);
            return true;
        }
        return false;
    }

    public void removeAllPlayer(){
        _M_idToUserInGame = new HashMap<>();
    }

    public Boolean addPlayerToGame( String username, User user){
        _M_idToUserInGame.put(username, user);
        return true;
    }
}
