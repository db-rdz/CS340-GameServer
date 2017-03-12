package UserModel;

import Database.DAO;
import GameModels.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benjamin on 10/02/17.
 */


public class User implements iUser {
	
	public User() {}

    //-----------------------------------------CLASS VARIABLES-------------------------------------------------//
    private String _S_username = null;
    private String _S_password = null;
    private String _S_token = null;

    private Boolean _B_isInGame = false;
    /**
     * Changed to Integer so it can serialize. Identical to the clientmodel gameId map
     */
    private List<Integer> _L_joinedGames = new ArrayList<>();
    private Boolean _B_isLoggedIn = false; //Determines if the user is logged in or not. Used in ClientProxy.

    //_________________________________________________________________________________________________________//




    //-----------------------------------------STATIC VARIABLES------------------------------------------------//
    /**Maps a string () to a user*/
    /** Note: The function of finding a user with a determined id I think should be done here to keep us organized */
    private static Map<String, User> _M_usernameToLoggedInUser = new HashMap();
    private  List<User> _L_listOfAllUsers = new ArrayList<>();

    //_________________________________________________________________________________________________________//



    //-----------------------------------------STATIC FUNCTIONS------------------------------------------------//
    public static User getUserWithUsername(String username){ return _M_usernameToLoggedInUser.get(username); }

    public static Boolean addLoggedInUser(String username){
        try {
            User loggedUser = DAO._SINGLETON.getUserByUserName(username);
            loggedUser.set_B_isLoggedIn(true);
            mapUsernameToLoggedInUser(username, loggedUser);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * Nathan
     * Changed user to loggedUser for consistency
     * Changed method name from "mapIdToUser" -> "mapUsernameToLoggedInUser"
     * 
     * @param username
     * @param loggedUser
     * @return
     */
    public static Boolean mapUsernameToLoggedInUser(String username, User loggedUser){
        _M_usernameToLoggedInUser.put(username, loggedUser);
        return true;
    }

    //________________________________________________________________________________________________________//




    //-----------------------------------------SETTERS AND GETTERS---------------------------------------------//

    public String get_S_username() {
        return _S_username;
    }

    public void set_S_username(String _S_username) {
        this._S_username = _S_username;
    }

    public String get_S_password() {
        return _S_password;
    }

    public void set_S_password(String _S_password) {
        this._S_password = _S_password;
    }

    public String get_S_token() {
        return _S_token;
    }

    public void set_S_token(String _S_token) {
        this._S_token = _S_token;
    }

    public Boolean get_B_isInGame() {
        return _B_isInGame;
    }

    public void set_B_isInGame(Boolean _B_isInGame) {
        this._B_isInGame = _B_isInGame;
    }

    public List<Integer> get_L_joinedGames() {
        return _L_joinedGames;
    }

    public void set_L_joinedGames(List<Integer> _L_joinedGames) {
        this._L_joinedGames = _L_joinedGames;
    }
    
    public void set_B_isLoggedIn(Boolean _B_isLoggedIn) {
		this._B_isLoggedIn = _B_isLoggedIn;
	}

    public Boolean get_B_isLoggedIn() {
        return _B_isLoggedIn;
    }

    public static void set_M_usernameToLoggedInUser(Map<String, User> _M_usernameToLoggedInUser) {
        User._M_usernameToLoggedInUser = _M_usernameToLoggedInUser;
    }
    
    public static Map<String, User> get_M_usernameToLoggedInUser() {
		return _M_usernameToLoggedInUser;
	}

    public void set_L_listOfAllUsers(List<User> _L_listOfAllUsers) {
        this._L_listOfAllUsers = _L_listOfAllUsers;
    }
    
    public static List<User> get_L_listOfAllUsers() {
		return null;
	}

//    public String get_Username() { return _S_username; }
//    public void set_Username(String username) { _S_username = username; }
//
//    public String get_Password(){ return _S_password; }
//    public void set_Password(String password ) { _S_password = password; }
//
//    public String get_Token(){ return _S_token; }
//    public void set_Token(String token) { _S_token = token; }
//
//
//    public Boolean isUserInGame() { return _B_isInGame; }
//    public void set_UserGameStatus(Boolean _B_isInGame) { this._B_isInGame = _B_isInGame; }
//
//    public List<Game> getJoinedGames() { return _L_joinedGames; }
//    public void setJoinedGameList(List<Game> _L_joinedGames) { this._L_joinedGames = _L_joinedGames; }
//
//    public static List<User> get_L_listOfAllUsers() { //TODO: Implement!
//        return null;
//    }
//
//    public Boolean isLoggedIn() {
//        return _B_isLoggedIn;
//    }
//    public void set_B_isLoggedIn(Boolean _B_isLoggedIn) {
//        this._B_isLoggedIn = _B_isLoggedIn;
//    }
//
//    public static Map<String, User> get_M_idToUser() {
//        return _M_idToUser;
//    }




    //________________________________________________________________________________________________________//


    //------------------------------------------CLASS FUNCTIONS-----------------------------------------------//
    public void updateUserModel(){

    }

    public Boolean initializeGame(int gameId ){
        Game createdGame = DAO._SINGLETON.getGameFromId( gameId );
        Game.addGame(createdGame, gameId);
        addGameToJoinedGames(gameId);
        return true;
    }

    public Boolean addGameToJoinedGames( int gameId ){
        _L_joinedGames.add( gameId );
        return true;
    }



}
