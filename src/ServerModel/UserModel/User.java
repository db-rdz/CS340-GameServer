package ServerModel.UserModel;

import Database.DAO;
import ServerModel.GameModels.Game;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 10/02/17.
 */
public class User implements iUser {

    //-----------------------------------------CLASS VARIABLES-------------------------------------------------//
    private String _S_username = null;
    private String _S_password = null;
    private String _S_token = null;

    private Boolean _B_isInGame = false;
    private Game _G_joinedGame = null;

    //_________________________________________________________________________________________________________//



    //-----------------------------------------STATIC VARIABLES------------------------------------------------//
    /**Maps a string () to a user*/
    /** Note: The function of finding a user with a determined id I think should be done here to keep us organized */
    private static Map<String, User> _M_idToUser = new HashMap();

    //_________________________________________________________________________________________________________//



    //-----------------------------------------STATIC FUNCTIONS------------------------------------------------//
    public static User getUserWithUsername(String username){ return _M_idToUser.get(username); }

    public static List<User> getAllLoggedInUsers(){ return (List<User>) _M_idToUser.values(); }

    public static Boolean addLoggedInUser(String username){
        try {
            User loggedUser = DAO._SINGLETON.getUserByUserName(username);
            mapIdToUser(username, loggedUser);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public static Boolean logOutUser(String username){
        _M_idToUser.remove(username);
        return true;
    }

    public static Boolean mapIdToUser(String username, User user){
        _M_idToUser.put(username, user);
        return true;
    }

    //________________________________________________________________________________________________________//


    //-----------------------------------------SETTERS AND GETTERS---------------------------------------------//

    public String get_Username() { return _S_username; }
    public void set_Username(String username) { _S_username = username; }

    public String get_Password(){ return _S_password; }
    public void set_Password(String password ) { _S_password = password; }

    public String get_Token(){ return _S_token; }
    public void set_Token(String token) { _S_token = token; }

    public Boolean isUserInGame() { return _B_isInGame; }
    public void set_UserGameStatus(Boolean _B_isInGame) { this._B_isInGame = _B_isInGame; }

    public Game get_joinedGame(){ return _G_joinedGame; }
    public void set_joinedGame(Game game){ _G_joinedGame = game; }

    //________________________________________________________________________________________________________//


    //------------------------------------------CLASS FUNCTIONS-----------------------------------------------//
    public void updateUserModel(){

    }

    public Boolean initializeGame(int gameId ){
        Game createdGame = DAO._SINGLETON.getGameFromId( gameId );
        Game.addGameToModel(createdGame, gameId);
        set_joinedGame(createdGame);
        return true;
    }

    public Boolean clearFinishedGame(){
        _G_joinedGame = null;
        _B_isInGame = false;
        return true;
    }

    public Boolean joinGame(Game game){
        _G_joinedGame = game;
        _B_isInGame = true;
        return true;
    }

    public Boolean joinGame(int gameId){
        Game game = Game.getGameWithId(gameId);
        _G_joinedGame = game;
        _B_isInGame = true;
        return true;
    }

    public Boolean exitGame(){
        _G_joinedGame = null;
        _B_isInGame = false;
        return true;
    }

}
