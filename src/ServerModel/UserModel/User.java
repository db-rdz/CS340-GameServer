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
    private Boolean _B_isInGame = false;
    private List<Game> _L_joinedGames = new ArrayList<>();

    //_________________________________________________________________________________________________________//




    //-----------------------------------------STATIC VARIABLES------------------------------------------------//
    /**Maps a string () to a user*/
    /** Note: The function of finding a user with a determined id I think should be done here to keep us organized */
    private static Map<String, User> _M_idToUser = new HashMap();

    //_________________________________________________________________________________________________________//



    //-----------------------------------------STATIC FUNCTIONS------------------------------------------------//
    public static User getUserWithUsername(String username){ return _M_idToUser.get(username); }

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

    public static Boolean mapIdToUser(String username, User user){
        _M_idToUser.put(username, user);
        return true;
    }

    //________________________________________________________________________________________________________//




    //-----------------------------------------SETTERS AND GETTERS---------------------------------------------//

    public String get_Username() { return _S_username; }
    public void set_Username(String username) { _S_username = username; }

    public Boolean isUserInGame() { return _B_isInGame; }
    public void set_UserGameStatus(Boolean _B_isInGame) { this._B_isInGame = _B_isInGame; }

    public List<Game> getJoinedGames() { return _L_joinedGames; }
    public void setJoinedGameList(List<Game> _L_joinedGames) { this._L_joinedGames = _L_joinedGames; }


    //________________________________________________________________________________________________________//


    //------------------------------------------CLASS FUNCTIONS-----------------------------------------------//
    public void updateUserModel(){

    }

    public Boolean initializeGame(int gameId ){
        Game createdGame = DAO._SINGLETON.getGameFromId( gameId );
        Game.addGame(createdGame, gameId);
        addGameToJoinedGames(createdGame);
        return true;

    }

    public Boolean addGameToJoinedGames( Game game ){
        _L_joinedGames.add( game );
        return true;
    }






}
