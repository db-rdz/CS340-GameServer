package GameModels;

import ServerModel.GameModels.CardsModel.DestCardDeck;
import ServerModel.GameModels.CardsModel.TrainCardDeck;
import ServerModel.GameModels.PlayerModel.Player;
import UserModel.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.security.ntlm.Server;

import Database.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.sun.org.apache.xerces.internal.impl.xpath.regex.CaseInsensitiveMap.get;

/**
 * Created by benjamin on 10/02/17.
 */
public class Game implements iGame {
	
	public Game() {

	}

    //-----------------------------------------STATIC VARIABLES----------------------------------------//
    /** _M_idToGame maps a game id to a game. */
    private static Map<Integer, Game> _M_idToGame = new HashMap<>();
    /**  These are all the games that haven't started */
    private static List<Game> _L_listOfAvailableGames = new ArrayList<>();
    /**  These are all the games that have already started */
    private static List<Game> _L_listOfStartedGames = new ArrayList<>();
    /**  The max number of players */
    public static int _MAX_PLAYERS = 5;
    /** Not sure if I should still use this var */
    public static int modelNextGameIndex = 0;
    /** Nathan
     *  This contains all the usernames in a list. Used to send back to the client.
     */
    public static List<String> usernames;

    //-----------------------------------------CLASS VARIABLES-----------------------------------------//
    /** _M_idToUserInGame maps a username string to a User. It only maps the user's of the people in the game */
    private Map<String, User> _M_idToUserInGame = new HashMap<>();
    
    /** _i_numberOfPlayers are the current number of users in the game */
    private int _i_numberOfPlayers = 0;
    
    /** _i_gameId is the id of a game in the database */
    private int _i_gameId = -1;
    
    /** This is the user id of the person that created the game. */
    private int _i_gameOwner = -1;

    private String _S_gameName;

    private Boolean _S_active = true;

    /**
     * Nathan
     * These relate to the Games table database
     */
    private User player1 = new User(); //TODO: Gotta do something with the User
    
    private User player2 = new User();
    
    private User player3 = new User();
    
    private User player4 = new User();
    
    private User player5 = new User();
    
    /**
     * Ryan
     * We need to store the deck of cards, the board, and the chat in each game
     */
    
    private TrainCardDeck _deck = new TrainCardDeck();
    
    private DestCardDeck _destCards = new DestCardDeck();
    
    private List<String> _str_chatRoom = new ArrayList<>();    

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
    	try {
    		if(_M_idToGame.get(gameId).get_numberOfPlayers() > _MAX_PLAYERS){
    			return true;
    		}
    		return false;
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }

    /** Returns the list of all games */
    public static List<Game> getAllGames(){
        List<Game> games = new ArrayList<>();
        for (Game game : _M_idToGame.values()) {
        	games.add(game);
        }
        return games;
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
    
    public void set_i_gameOwner(int _i_gameOwner) {
		this._i_gameOwner = _i_gameOwner;
	}
    
    public int get_i_gameOwner() {
		return _i_gameOwner;
	}
    
    public int get_i_gameId() {
    	return _i_gameId;
    }
    
    public void set_i_gameId(int _i_gameId) {
    	this._i_gameId = _i_gameId;
    }

    public Boolean get_S_active() {
        return _S_active;
    }

    public void set_S_active(Boolean _S_active) {
        this._S_active = _S_active;
    }
    
    public Map<String, User> get_M_idToUserInGame() {
    	return _M_idToUserInGame;
    }
    
    public static void set_M_idToGame(Map<Integer, Game> _M_idToGame) {
		Game._M_idToGame = _M_idToGame;
	}
    
    /** Nathan */
    public User getPlayer1() {
		return player1;
	}
    
    public void setPlayer1(User player1) {
		this.player1 = player1;
	}
    
    public User getPlayer2() {
		return player2;
	}
    
    public void setPlayer2(User player2) {
		this.player2 = player2;
	}
    
    public User getPlayer3() {
		return player3;
	}
    
    public void setPlayer3(User player3) {
		this.player3 = player3;
	}
    
    public User getPlayer4() {
		return player4;
	}
    
    public void setPlayer4(User player4) {
		this.player4 = player4;
	}
    
    public User getPlayer5() {
		return player5;
	}
    
    public void setPlayer5(User player5) {
		this.player5 = player5;
	}
    
    public static void setUsernames(List<String> usernames) {
		Game.usernames = usernames;
	}
    
    public static List<String> getUsernames() {
		return usernames;
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
        _i_numberOfPlayers++; //Increments the number of players in the game
        return true;
    }
    
    public void setPlayer(int playerNumber, User player) {
    	switch (playerNumber) {
	    	case 1:
	    		setPlayer1(player);
	    		break;
	    	case 2:
	    		setPlayer2(player);
	    		break;
	    	case 3:
	    		setPlayer3(player);
	    		break;
	    	case 4:
	    		setPlayer4(player);
	    		break;
	    	case 5:
	    		setPlayer5(player);
	    		break;
			default:
				break;
    	}
    }

    public User getPlayer(int playerNumber) {
        switch (playerNumber) {
            case 1:
                return getPlayer1();
            case 2:
                return getPlayer2();
            case 3:
                return getPlayer3();
            case 4:
                return getPlayer4();
            case 5:
                return getPlayer5();
            default:
                return null;
        }
    }
    
    public TrainCardDeck getDeck()
    {
    	return _deck;
    }
    
    public DestCardDeck getDestCards()
    {
    	return _destCards;
    }
    
    public List<String> getChatRoom()
    {
    	return _str_chatRoom;
    }

}
