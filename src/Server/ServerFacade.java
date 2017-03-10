package Server;

import Client.IClient;
import Client.User;
import Command.ICommand;
import Command.Phase1.AddJoinableToClientCommand;
import Command.Phase1.AddPlayerToClientCommand;
import Command.Phase1.AddWaitingToClientCommand;
import Command.Phase1.DeleteGameCommand;
import Command.Phase1.ListJoinableCommand;
import Command.Phase1.LoginRegisterResponseCommand;
import Command.Phase2.*;
import Database.DAO;
import GameModels.Game;
import ServerModel.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by RyanBlaser on 2/5/17.
 */

public class ServerFacade implements IServer {
    
    public static ServerFacade SINGLETON = new ServerFacade();
    
    @Override
    public List<ICommand> login(String username, String password) throws IClient.InvalidUsername, IClient.InvalidPassword, UserAlreadyLoggedIn {
        try {
            // tries to retrieve the user from the database
            if (DAO._SINGLETON.login(username, password))
            {
                UserModel.User theUser = DAO._SINGLETON.getUserByUserName(username);
                User user = new User();
                user.setUsername(theUser.get_S_username());
                user.setPassword(theUser.get_S_password());
                user.setStr_authentication_code(theUser.get_S_token());
                             
               
                List<ICommand> commands = new ArrayList<>();

//                
                if (theUser.get_L_joinedGames().isEmpty())
                {
                    List<Game> games = ServerModel.SINGLETON.getAvailableGames();
                    List<Integer> gameIds = new ArrayList<>();
                    for (Game game : games) {
                    	gameIds.add(game.get_i_gameId());
                    }
                    ListJoinableCommand listJoinableCommand = new ListJoinableCommand(gameIds);
                    commands.add(listJoinableCommand); //Need to update what games there are first before login
                }
                else
                {
                    Boolean isStarted = false;
                    List<Game> startedGames = ServerModel.SINGLETON.getStartedGames();
                    for (int i = 0; i < startedGames.size(); i++)
                    {
                    	//A user can only be in one game at a time
                        if(theUser.get_L_joinedGames().get(0) == startedGames.get(i).get_i_gameId())
                        {
                            isStarted = true;
                            break;
                        }
                    }
                    if (isStarted)
                    {
                        // go straight to game activity
                    }
                    else
                    {
                        // go to waiting view
                    }
                }
                
                //Update games first then have the user login
                LoginRegisterResponseCommand lrsc = new LoginRegisterResponseCommand(user);
                commands.add(lrsc);
                
                ClientProxy.SINGLETON.get_m_usersCommands().put(username, new ArrayList<ICommand>());
                ServerModel.SINGLETON.logIn(theUser.get_S_username());
                System.out.println("Logging in user: " + DAO._SINGLETON.getUserByAccessToken(theUser.get_S_token()).get_S_username());
                System.out.println("Authorization code: " + theUser.get_S_token());
                
//                new GetCommandsCommand(theUser.get_S_username()).execute();
                
                return commands;
            }
        } catch (SQLException e) {
        	System.err.println("@login");
            e.printStackTrace();
        }
        catch (IClient.InvalidPassword e) {
            throw e;
        } catch (IClient.InvalidUsername e) {
            throw e;
        } catch (UserAlreadyLoggedIn e) {
            throw e;
        }
        return null; //Returns null if the try catches the error.
    }
    
    @Override
    public List<ICommand> register(String username, String password) throws IClient.UsernameAlreadyExists, UserAlreadyLoggedIn {
        try {
            if (!DAO._SINGLETON.registerUser(username, password)) {
                throw new IClient.UsernameAlreadyExists();
            }
            
            UserModel.User theUser = DAO._SINGLETON.getUserByUserName(username);
            User user = new User();
            user.setUsername(theUser.get_S_username());
            user.setPassword(theUser.get_S_password());
            user.setStr_authentication_code(theUser.get_S_token());
            
            List<ICommand> commands = new ArrayList<>();
                        
            
            LoginRegisterResponseCommand lrsc = new LoginRegisterResponseCommand(user);
            commands.add(lrsc);
            
            List<Game> games = ServerModel.SINGLETON.getAvailableGames();
            List<Integer> gameIds = new ArrayList<>();
            for (Game game : games) {
            	gameIds.add(game.get_i_gameId());
            }
            ListJoinableCommand listJoinableCommand = new ListJoinableCommand(gameIds);
            commands.add(listJoinableCommand);    
            
            Map<String, List<ICommand>> copy = ClientProxy.SINGLETON.get_m_usersCommands();
            System.out.println(copy);
            
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, new ArrayList<ICommand>());
            System.out.println("Registering and logging in user: " + DAO._SINGLETON.getUserByAccessToken(theUser.get_S_token()).get_S_username());
            System.out.println("Authorization code: " + theUser.get_S_token());
            return commands;
            
        } catch (SQLException e) {
        	System.err.println("@registerUser");
            e.printStackTrace();
        }
        
        return null;
    }
   
    
    @Override
    public List<ICommand> removeGame(Game game) {
        
        ICommand deleteGameCommand = new DeleteGameCommand(game.get_i_gameId());
        
        List<ICommand> commands = new ArrayList<>();
        commands.add(deleteGameCommand);

        String username = "";
        
        for (int i = 0; i < UserModel.User.get_L_listOfAllUsers().size(); i++) {
            username = UserModel.User.get_L_listOfAllUsers().get(i).get_S_username();
    		ClientProxy.SINGLETON.get_m_usersCommands().get(username).addAll(commands);
        } //TODO: Include this in the ServerCommunicator.
        
        return commands;
    }
    
    @Override
    public List<ICommand> startGame(int gameId, List<String> usernamesInGame) {
		try {
			Game theGame = Game.getGameWithId(gameId);
	        
	        //send delete game command to all users not in game
	        ICommand deleteGameCommand = new DeleteGameCommand(gameId);
	        
	        List<ICommand> commands = new ArrayList<>();
	        commands.add(deleteGameCommand);
	        
	        String username = "";
	        List<UserModel.User> users = UserModel.User.get_L_listOfAllUsers();
	         
	        for (UserModel.User user : users) {
	            username = user.get_S_username();
	            ClientProxy.SINGLETON.get_m_usersCommands().put(username, commands);
	        }
	        
	        // send to all users in game
	        
	        //        Iterator iter = theGame.get_M_idToGame().values().iterator(); //TODO: is this correct??
	        Iterator iter = theGame.get_M_idToUserInGame().keySet().iterator(); 
	        //this should be an iterator through the set of usernames of all users in the game
	        
	        
	        
	        // TODO: get the deck of cards from the game and assign a hand to each player
	        
	        // Collection<TrainCard> deck = theGame.getDeck();
	        // Collection<TrainCard> hand = null;
	        
	        List<ICommand> otherPlayerCommands = null;
	        List<ICommand> returnCommands = new ArrayList<>();
	        
	        UserModel.User theUser = null;
	        try {
	            theUser = DAO._SINGLETON.getUserByAccessToken(strAuthenticationCode); //TODO: Change to use list of usernames
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        if (theUser != null)
	        {
	            while (iter.hasNext()) {
	                // hand = deck.getHand();
	                username = (String) iter.next();
	                if (username != theUser.get_S_username())
	                {
	                    // otherPlayerCommands = new ArrayList<>();
	                    // otherPlayerCommands.add(new InitializeGameCommand(hand));
	                    ClientProxy.SINGLETON.get_m_usersCommands().get(username).addAll(otherPlayerCommands);
	                }
	                else
	                {
	                    // returnCommands.add(new InitializeGameCommand(hand));
	                }
	            }
	        }
	        
	        return returnCommands;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return null; //Meaning something went wrong here.
}

    
    @Override
    public List<ICommand> addPlayer(String str_authentication_code, int intGameId) throws GameIsFullException {
    	
        // sent to all users                       
        try {
        	UserModel.User theUser = DAO._SINGLETON.getUserByAccessToken(str_authentication_code);
        	Game theGame = DAO._SINGLETON.getGamebyGameId(intGameId); //get the game from id
        	
        	if (!ServerModel.SINGLETON.addPlayerToGame(theUser.get_S_username(), intGameId)) { // not allowed to join
        		throw new GameIsFullException();
        	}
        	else {
        	
                theUser.set_B_isInGame(true); //The user is now in the game
                
//                theGame = DAO._SINGLETON.getGamebyGameId(intGameId); //Get the game from id
                theGame.addPlayerToGame(theUser.get_S_username(), theUser); //Adds creator to the game
              	DAO._SINGLETON.updateGameNumberOfPlayers(intGameId, theGame.get_numberOfPlayers());
                DAO._SINGLETON.UpdateGamePlayer(theGame.get_i_gameId(), theUser.get_S_username(), theGame.get_numberOfPlayers());
                theGame.setPlayer(theGame.get_numberOfPlayers(), theUser);
                theGame = DAO._SINGLETON.getGamebyGameId(intGameId);

                List<UserModel.User> users = new ArrayList<>(); //The users in the game
                for (int i = 1; i <= theGame.get_numberOfPlayers(); i++) {
                	users.add(theGame.getPlayer(i));
                }
                
                ICommand addPlayerToClientCommand = new AddPlayerToClientCommand(theUser.get_S_username(), intGameId);
                
                List<ICommand> commands = new ArrayList<>(); //prevents losing the commands in the map 
                commands.add(addPlayerToClientCommand);
                
                Map<String, List<ICommand>> debug;
                for (int i = 0; i < users.size(); i++) {
                	UserModel.User user = users.get(i);
                	
                	//Only gives the AddPlayerToClientCommand to users that are in a specific game.
                	if (!user.get_S_token().equals(theUser.get_S_token())) {
                		System.out.println(user.get_S_username() + " receiving commmands: " + commands);
                		ClientProxy.SINGLETON.get_m_usersCommands().get(user.get_S_username()).addAll(commands);
                		debug = ClientProxy.SINGLETON.get_m_usersCommands();
                		System.out.println(user.get_S_username() + " has these commands: " + debug.get(user.get_S_username()));
                	}
                	
            }

            // send to user who joined
//            DeleteGameCommand deleteGameCommand = new DeleteGameCommand(intGameId);
                List<String> usernames = new ArrayList<>(); //The usernames of the players already in the lobby
                for (int i = 1; i <= theGame.get_numberOfPlayers(); i++) {
                	usernames.add(theGame.getPlayer(i).get_S_username());
                }

	            List<ICommand> returnCommands = new ArrayList<>();
	            returnCommands.add(new AddWaitingToClientCommand(theGame.get_i_gameId(), usernames, false));
	            
	            return returnCommands;
            } 
        	
        } catch (SQLException exception) {
        	System.err.println("@addPlayer");
        	exception.printStackTrace();
        } 
        return null;
    }
    
    
    @Override
    public List<ICommand> addJoinableGameToServer(Game game, String str_authentication_code) {
        
        try {
            DAO._SINGLETON.deleteAllGames();
            @SuppressWarnings("unused")
			Boolean value = DAO._SINGLETON.addGame(game);
            Game theGame = new Game();
            
            Integer gameId = DAO._SINGLETON.getAllGames().size(); //The size == gameid
            UserModel.User theUser = DAO._SINGLETON.getUserByAccessToken(str_authentication_code);
                        
            try {
            	theUser.set_B_isInGame(true);
            	theUser.addGameToJoinedGames(gameId);
            	theGame = DAO._SINGLETON.getGamebyGameId(gameId);
            	
              	theGame.addPlayerToGame(theUser.get_S_username(), theUser); //Adds creator to the game
              	theGame.set_i_gameOwner(1); //The creator/player1
              	DAO._SINGLETON.updateGameNumberOfPlayers(gameId, theGame.get_numberOfPlayers());
                DAO._SINGLETON.UpdateGamePlayer(theGame.get_i_gameId(), theUser.get_S_username(), theGame.get_numberOfPlayers());
                theGame.setPlayer1(theUser);

            	
            } catch (Exception exception) {
            	exception.printStackTrace();
            }
            Game.insertInAvailableGames(theGame);
            Game.mapGameToId(theGame, gameId);
            
            List<ICommand> commands = new ArrayList<>();
            commands.add(new AddJoinableToClientCommand(theGame));
            
            String username = "";
            Map<String, List<ICommand>> copy = new HashMap<>();
            
            //Sends AddJoinableGameCommands to only logged in users.
            Iterator iterator = UserModel.User.get_M_usernameToLoggedInUser().values().iterator();
            while (iterator.hasNext())
            {
            	UserModel.User newUser = (UserModel.User) iterator.next();
            	if (!newUser.get_S_token().equals(str_authentication_code)) {
            		ClientProxy.SINGLETON.get_m_usersCommands().get(username).addAll(commands);
            		
            	}
            }
            
            
            List<ICommand> returnCommands = new ArrayList<>();
//            returnCommands.add(new AddPlayerToClientCommand(theUser.get_S_username(), theGame.get_i_gameId())); //The creator needs to be in the game he created
//            returnCommands.add(new AddWaitingToClientCommand(theGame));
            List<String> usernames = new ArrayList<>();
            for (int i = 1; i <= theGame.get_numberOfPlayers(); i++) {
            	usernames.add(theGame.getPlayer(i).get_S_username());
            }
            returnCommands.add(new AddWaitingToClientCommand(theGame.get_i_gameId(), usernames, true));
                        
            return returnCommands;
            
        } catch (SQLException e) {
        	System.err.println("@addJoinableGamesToServer");
            e.printStackTrace();
        }
        
        return null;        
    }
    
    @Override
    public List<ICommand> addWaitingGame(Game game) {
        // send to user who called this function only
        ICommand deleteGameCommand = new DeleteGameCommand(game.get_i_gameId());
//        ICommand addWaitingToClientCommand = new AddWaitingToClientCommand(game);
        
        List<ICommand> commands = new ArrayList<>();
        commands.add(deleteGameCommand);
//        commands.add(addWaitingToClientCommand);
        
        return commands;
    }
    
    
    @Override
    public List<ICommand> logout(String str_authentication_code) {
        ServerModel.SINGLETON.logOut(str_authentication_code);
        //ICommand logoutCommand = new LogoutResponseCommand();

        List<ICommand> commands = new ArrayList<>();
        UserModel.User user = new UserModel.User();
        
        try {
            user = DAO._SINGLETON.getUserByAccessToken(str_authentication_code);
        } catch (SQLException e) {
        	System.err.println("@logout");
            e.printStackTrace();
        }
        System.out.println("Logging out user: " + user.get_S_username());
        System.out.println("Authorization code: " + user.get_S_token());
        
        return commands;
    }

}