package Server;

import Client.IClient;
import Client.User;
import Command.ICommand;
import Command.Phase1.AddGameToJoinableListCommand;
import Command.Phase1.SwitchToWaitingActivityCommand;
import Command.Phase1.AddPlayerToClientCommand;
import Command.Phase1.DeleteGameCommand;
import Command.Phase1.ListJoinableCommand;
import Command.Phase1.LoginRegisterResponseCommand;
import Command.Phase1.LogoutResponseCommand;
import Command.Phase2.*;
import Database.DAO;
import GameModels.Game;
import ServerModel.*;
import ServerModel.GameModels.CardsModel.DestCard;
import ServerModel.GameModels.CardsModel.DestCardDeck;
import ServerModel.GameModels.CardsModel.TrainCard;
import ServerModel.GameModels.CardsModel.TrainCardDeck;
import ServerModel.GameModels.RouteModel.iRoute;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sun.corba.se.pept.transport.EventHandler;


/**
 * Created by RyanBlaser on 2/5/17.
 */

public class ServerFacade implements IServer {
    
    public static ServerFacade SINGLETON = new ServerFacade();
    
    @Override
    public List<ICommand> login(String username, String password) throws IClient.InvalidUsername, IClient.InvalidPassword, UserAlreadyLoggedIn {
    	List<ICommand> commands = new ArrayList<>(); //Will be used for sending back bad logins
        try {
            // tries to retrieve the user from the database
            if (DAO._SINGLETON.login(username, password))
            {
                UserModel.User theUser = DAO._SINGLETON.getUserByUserName(username);
                User user = new User();
                user.setUsername(theUser.get_S_username());
                user.setPassword(theUser.get_S_password());
                user.setStr_authentication_code(theUser.get_S_token());
                             
               

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
                LoginRegisterResponseCommand lrsc = new LoginRegisterResponseCommand(user, true, false);
                commands.add(lrsc);
                
                ClientProxy.SINGLETON.get_m_usersCommands().put(username, new ArrayList<ICommand>());
                ServerModel.SINGLETON.logIn(theUser.get_S_username());
                System.out.println("Logging in user: " + theUser.get_S_username());
                System.out.println("Authorization code: " + theUser.get_S_token());
                                
                return commands;
            }
        } catch (SQLException e) {
        	System.err.println("@login");
            e.printStackTrace();
        }
        catch (IClient.InvalidPassword e) {
        	commands.clear();
        	commands.add(new LoginRegisterResponseCommand(null, false, true));
            return commands;
        } catch (IClient.InvalidUsername e) {
        	commands.clear();
        	commands.add(new LoginRegisterResponseCommand(null, false, true));
            return commands;
        } catch (UserAlreadyLoggedIn e) {
        	commands.clear();
        	commands.add(new LoginRegisterResponseCommand(null, true, true));
            return commands;
        }
        return null; //Returns null if the try doesn't catch any defined error.
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
                        
            
            LoginRegisterResponseCommand lrsc = new LoginRegisterResponseCommand(user, true, false);
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
        try {
        	ICommand deleteGameCommand = new DeleteGameCommand(game.get_i_gameId());
        	
        	List<ICommand> commands = new ArrayList<>();
        	commands.add(deleteGameCommand);
        	
        	String username = "";
        	List<UserModel.User> users = DAO._SINGLETON.getAllUsers();
        	for (int i = 0; i < users.size(); i++) {
        		username = users.get(i).get_S_username();
        		ClientProxy.SINGLETON.get_m_usersCommands().get(username).addAll(commands);
        	} //TODO: Include this in the ServerCommunicator.
        	
        	return commands;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }
    
    @Override
    public List<ICommand> startGame(int gameId, List<String> usernamesInGame, String strAuthenticationCode) {
		//TODO put authentication code in startGameCommand on client side
    	try {
			Game theGame = Game.getGameWithId(gameId);
	        
	        // send to all users in game	        
	        List<ICommand> otherPlayerCommands = null;
	        List<ICommand> returnCommands = new ArrayList<>();

	        List<TrainCard> trainCards = null;
	        List<DestCard> destCards = null;
	        
	        TrainCardDeck theTrainCardDeck = theGame.getDeck();
	        DestCardDeck theDestCardDeck = theGame.getDestCards();
	        
        	trainCards = new ArrayList<>();
            for (int j = 0; j < 4; j++)
            {
            	trainCards.add(theTrainCardDeck.drawTop());
            }
            destCards = theDestCardDeck.drawTop3();
            
            //Nathan: The faceup train table cards at the beginning of the game.
        	List<TrainCard> faceupTrainCards = new ArrayList<>();
            for (int j = 0; j < 5; j++)
            {
            	faceupTrainCards.add(theTrainCardDeck.drawTop());
            }

            returnCommands.add(new InitializeGameCommand(trainCards, destCards, faceupTrainCards)); //The creator's cards

            for (int i = 1; i < usernamesInGame.size(); i++) { //Ignore the game creator, start at Player 2
                // initialize each player's hand
            	trainCards = new ArrayList<>();
                for (int j = 0; j < 4; j++) //Create everyone else's cards
                {
                	trainCards.add(theTrainCardDeck.drawTop());
                }
                destCards = theDestCardDeck.drawTop3();
                                
                otherPlayerCommands = new ArrayList<>();
                otherPlayerCommands.add(new InitializeGameCommand(trainCards, destCards, faceupTrainCards));
        		System.out.println(usernamesInGame.get(i) + " receiving commmands: " + otherPlayerCommands);
                ClientProxy.SINGLETON.get_m_usersCommands().get(usernamesInGame.get(i)).addAll(otherPlayerCommands);  
            }
            
	        //TODO: send delete game command to all users not in game
//	        ICommand deleteGameCommand = new DeleteGameCommand(gameId);
//	        
//	        List<ICommand> commands = new ArrayList<>();
//	        commands.add(deleteGameCommand);
//	        
//	        String username = "";
//	        List<UserModel.User> users = DAO._SINGLETON.getAllUsers();
//	         
//	        for (UserModel.User user : users) {
//	        		username = user.get_S_username();
//	            	ClientProxy.SINGLETON.get_m_usersCommands().get(username, commands);
//	        }
	        return returnCommands;
		} catch (Exception e) {
			e.printStackTrace();
		}
    System.err.println("Error @StartGame");
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
//                		System.out.println(user.get_S_username() + " receiving commmands: " + commands);
                		ClientProxy.SINGLETON.get_m_usersCommands().get(user.get_S_username()).addAll(commands);
//                		debug = ClientProxy.SINGLETON.get_m_usersCommands();
//                		System.out.println(user.get_S_username() + " has these commands: " + debug.get(user.get_S_username()));
                	}
                	
            }

            // send to user who joined
//            DeleteGameCommand deleteGameCommand = new DeleteGameCommand(intGameId);
                List<String> usernames = new ArrayList<>(); //The usernames of the players already in the lobby
                for (int i = 1; i <= theGame.get_numberOfPlayers(); i++) {
                	usernames.add(theGame.getPlayer(i).get_S_username());
                }

	            List<ICommand> returnCommands = new ArrayList<>();
	            returnCommands.add(new SwitchToWaitingActivityCommand(theGame.get_i_gameId(), usernames, false));
	            
	            return returnCommands;
            } 
        	
        } catch (SQLException exception) {
        	System.err.println("@addPlayer");
        	exception.printStackTrace();
        } 
        return null;
    }
    
    
    @Override
    public List<ICommand> addJoinableGameToServer(String str_authentication_code) {
        
        try {
            DAO._SINGLETON.deleteAllGames();
            @SuppressWarnings("unused")
			Boolean value = DAO._SINGLETON.addGame(new Game());
            Game theGame = new Game(); //initialize
            
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
            commands.add(new AddGameToJoinableListCommand(theGame.get_i_gameId()));
            
            String username = "";
            Map<String, List<ICommand>> copy = new HashMap<>();
            
            //Sends AddGameToJoinableListCommand to only logged in users.
            Iterator iterator = UserModel.User.get_M_usernameToLoggedInUser().values().iterator();
//            Map<String, List<ICommand>> putIntoClientProxy = ClientProxy.SINGLETON.get_m_usersCommands();
            
            while (iterator.hasNext())
            {
            	UserModel.User newUser = (UserModel.User) iterator.next();
            	if (!newUser.get_S_token().equals(str_authentication_code)) { //Doesn't send the game command to the creator
            		username = newUser.get_S_username();
            		ClientProxy.SINGLETON.get_m_usersCommands().get(username).addAll(commands);            		
            	}
            }
//            ClientProxy.SINGLETON.set_m_usersCommands(putIntoClientProxy);
            
            
            List<ICommand> returnCommands = new ArrayList<>();
            List<String> usernames = new ArrayList<>();
            for (int i = 1; i <= theGame.get_numberOfPlayers(); i++) {
            	usernames.add(theGame.getPlayer(i).get_S_username());
            }
            returnCommands.add(new SwitchToWaitingActivityCommand(theGame.get_i_gameId(), usernames, true));
                        
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
        
        List<ICommand> commands = new ArrayList<>();
        commands.add(deleteGameCommand);
        
        return commands;
    }
    
    
    @Override
    public List<ICommand> logout(User user) {
    	try {
    		System.out.println("Logging out user: " + user.getUsername());
    		System.out.println("Authorization code: " + user.getStr_authentication_code());
    		
	        ServerModel.SINGLETON.logOut(user.getStr_authentication_code());
	        DAO._SINGLETON.updateUserToken(user.getUsername(), ""); //empty token means user is logged out
	
	        List<ICommand> commands = new ArrayList<>();
	        commands.add(new LogoutResponseCommand());
	        
            
            
            return commands;
            
        } catch (Exception e) {
        	System.err.println("@logout");
            e.printStackTrace();
        }
        return null;
    }

 // Ryan
	@Override
	public List<ICommand> broadcastToChatCommand(int gameId, String authenticationToken, String message) {
		// TODO Sends back the entire list of messages, prepends message with the username shows who sent the message
		
		Game theGame = Game.getGameWithId(gameId);
		List<String> chatRoom = theGame.getChatRoom();
		UserModel.User theUser;
		String username = "";
		try {
			theUser = DAO._SINGLETON.getUserByAccessToken(authenticationToken);
			username = theUser.get_S_username();
			message = username + ": " + message;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chatRoom.add(message);
		List<ICommand> returnCommands = new ArrayList<>();
		returnCommands.add(new ShowMessageCommand(chatRoom));
		
		// send to all users in game
		Iterator iter = theGame.get_M_idToUserInGame().keySet().iterator();
		while(iter.hasNext())
		{
			String newUsername = (String) iter.next();
			if (newUsername != username)
			{
				ClientProxy.SINGLETON.get_m_usersCommands().get(newUsername).addAll(returnCommands);
			}
		}
		
		return returnCommands;
	}

	// Ryan
	@Override
	public List<ICommand> claimRoute(int gameId, String authenticationCode, iRoute route) {
		// TODO Need the route model
		Game theGame = Game.getGameWithId(gameId);
		String username = "";
		UserModel.User theUser = null;
		try {
			theUser = DAO._SINGLETON.getUserByAccessToken(authenticationCode);
			username = theUser.get_S_username();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// tells the other players that the route was claimed
		String routeName = "";
		List<ICommand> returnCommands = new ArrayList<>();
		returnCommands.add(new NotifyRouteClaimedCommand("The route from " + routeName + " has been claimed by " + username + "."));
		
		// increments the player's points on every player's screen
		int points = 0;
		//points - route.getPoints();	
		returnCommands.add(new UpdatePointsCommand(points, username));
				
		// send to all users in game
		Iterator iter = theGame.get_M_idToUserInGame().keySet().iterator();
		while(iter.hasNext())
		{
			String newUsername = (String) iter.next();
			if (newUsername != username)
			{
				ClientProxy.SINGLETON.get_m_usersCommands().get(newUsername).addAll(returnCommands);
			}
		}
		
		// decrease car count by the weight of the route claimed
		int weight = 0;
		//weight = route.getWeight();
		returnCommands.add(new UpdateCarCountCommand(weight));
		
		
		return returnCommands;
	}

	// Ryan
	@Override
	public List<ICommand> getTopDeckTrainCard(int gameId) {
		// TODO Do we need to increment the number of train cards each player has on the other player's screens?
		
		//gets the game, gets the deck from the game, and draws the top card 
		TrainCard theCard = Game.getGameWithId(gameId).getDeck().drawTop();
		List<ICommand> returnCommands = new ArrayList<>();
		returnCommands.add(new UpdatePlayerTrainCardsCommand(theCard));
		return returnCommands;
	}

	// Ryan
	@Override
	public List<ICommand> getFaceUpTableTrainCard(int gameId, int cardIndex, boolean isWild) {
		// TODO Do we need to increment the number of train cards each player has on the other player's screens?
		
		Game theGame = Game.getGameWithId(gameId);
		TrainCardDeck theDeck = theGame.getDeck();
		TrainCard theCard = theDeck.getFromTheFiveCards(cardIndex);
		// update the card count of that type for that player
		
		List<TrainCard> fiveCards = theDeck.getFiveCards();
		
		List<ICommand> returnCommands = new ArrayList<>();
		returnCommands.add(new UpdateFaceUpTableTrainCardsCommand(fiveCards));
		return returnCommands;
	}
    
    

}