package ServerModel.GameModels;

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
    /** Not sure if I should still use this var */
    public static int modelNextGameIndex = 0;


    //-----------------------------------------CLASS VARIABLES-----------------------------------------//
    /** _M_idToUserInGame maps a user id to a User. It only maps the user's of the people in the game */
    private Map<Integer, User> _M_idToUserInGame = new HashMap<>();

    private int _i_numberOfPlayers = 0;
    /** This is the id of the game in the database */
    private int gameModelIndex = -1;

    //-----------------------------------------STATIC FUNCTIONS----------------------------------------//

    /** Tells if an specific user is in the game */
    public static Boolean isUserInGame( int userId, int gameId ){
        // Will get user if user in game. If not it will return false.
        User result = _M_idToGame.get(gameId).getUserInGame(userId);
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

    /** updates the Server Game model */
    public static void update(){}

    //-----------------------------------------SETTER AND GETTERS-------------------------------------//
    public int get_numberOfPlayers() { return _i_numberOfPlayers; }
    public void set_numberOfPlayers(int _i_numberOfPlayers) { this._i_numberOfPlayers = _i_numberOfPlayers; }

    //-----------------------------------------CLASS FUNCTIONS----------------------------------------//
    /** Uses a map to return the User object associated with a user id (returns null if user is not in the game) */
    private User getUserInGame( int userId ){
        return _M_idToUserInGame.get(userId);
    }







}
