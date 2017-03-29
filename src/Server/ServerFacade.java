package Server;

import Client.IClient;
import Client.User;
import Command.ICommand;
import Command.Phase1.AddGameToJoinableListCommand;
import Command.Phase1.AddPlayerToClientCommand;
import Command.Phase1.DeleteGameCommand;
import Command.Phase1.ListJoinableCommand;
import Command.Phase1.LoginRegisterResponseCommand;
import Command.Phase1.LogoutResponseCommand;
import Command.Phase1.SwitchToWaitingActivityCommand;
import Command.Phase2.*;
import Database.DAO;
import GameModels.Game;
import ServerModel.*;
import ServerModel.GameModels.BoardModel.Scoreboard;
import ServerModel.GameModels.CardsModel.DestCard;
import ServerModel.GameModels.CardsModel.DestCardDeck;
import ServerModel.GameModels.CardsModel.TrainCard;
import ServerModel.GameModels.CardsModel.TrainCardDeck;
import ServerModel.GameModels.RouteModel.AllRoutes;
import ServerModel.GameModels.RouteModel.Route;
import ServerModel.GameModels.RouteModel.iRoute;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * ServerFacade implements IServer
 * Created by RyanBlaser on 2/5/17.
 */

public class ServerFacade implements IServer {
    
    /**
     * public static ServerFacade SINGLETON
     */
	public static ServerFacade SINGLETON = new ServerFacade();
    
	/**
	 * This method is used to log a user in given the username and password.
	 * 
	 * Pre: The username and password are formatted correctly (see username and password class)
	 * Pre: The Username exists in the database
	 * Pre: THe password matches the username
	 * 
	 * Post: The user will be logged in and the list of joinable games will appear on the screen.
	 * 
	 * @param username The username of the person trying to login
	 * @param password The password of the person trying to login
	 * @return List<ICommand> Returns the list of commands necessary to initialize the ClientModel
	 * @exception IClient.InvalidUsername This is thrown if the given username does not match any username in the database
	 * @exception IClient.InvalidPassword This is thrown if the given password is not the correct password for that user
	 * @exception UserAlreadyLoggedIn This is thrown if the user is already logged in on another device
	 */
    @Override
    public List<ICommand> login(String username, String password) throws IClient.InvalidUsername, IClient.InvalidPassword, UserAlreadyLoggedIn {
        List<ICommand> commands = new ArrayList<>();

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
                LoginRegisterResponseCommand lrsc = new LoginRegisterResponseCommand(user, true, false, true);
                commands.add(lrsc);
                
                ClientProxy.SINGLETON.get_m_usersCommands().put(username, new ArrayList<ICommand>());
                ServerModel.SINGLETON.logIn(theUser.get_S_username());
                
                System.out.println("\nLogging in user: " + theUser.get_S_username());
                System.out.println("Authorization code: " + theUser.get_S_token());
                
//                new GetCommandsCommand(theUser.get_S_username()).execute();
                
                return commands;
            }
        } catch (SQLException e) {
        	System.err.println("@login");
            e.printStackTrace();
        }
        catch (IClient.InvalidPassword e) {
        	commands.clear();
        	commands.add(new LoginRegisterResponseCommand(null, false, false, false));
            return commands;
        } catch (IClient.InvalidUsername e) {
        	commands.clear();
        	commands.add(new LoginRegisterResponseCommand(null, false, false, false));
            return commands;
        } catch (UserAlreadyLoggedIn e) {
        	commands.clear();
        	commands.add(new LoginRegisterResponseCommand(null, true, true, false));
            return commands;
        }
        return null; //Returns null if the try catches the error.
    }
    
    /**
	 * This method is used to register a user in given the username and password.
	 * 
	 * Pre: The username and password are formatted correctly (see username and password class)
	 * Pre: The Username does not exists in the database
	 * 
	 * Post: The user will be registered, logged in, and the list of joinable games will appear on the screen.
	 * 
	 * @param username The username of the person trying to register
	 * @param password The password of the person trying to register
	 * @return List<ICommand> Returns the list of commands necessary to initialize the ClientModel
	 * @exception IClient.UsernameAlreadyExists This is thrown if the given username already exists in the database
	 * @exception UserAlreadyLoggedIn This is thrown if the user is already logged in on another device
	 */
    @Override
    public List<ICommand> register(String username, String password) throws IClient.UsernameAlreadyExists, UserAlreadyLoggedIn {
        List<ICommand> commands = new ArrayList<>();

    	try {
            if (!DAO._SINGLETON.registerUser(username, password)) {
            	commands.clear();
            	commands.add(new LoginRegisterResponseCommand(null, true, true, true));
                return commands;
            }
            
            UserModel.User theUser = DAO._SINGLETON.getUserByUserName(username);
            User user = new User();
            user.setUsername(theUser.get_S_username());
            user.setPassword(theUser.get_S_password());
            user.setStr_authentication_code(theUser.get_S_token());
                                    
            
            LoginRegisterResponseCommand lrsc = new LoginRegisterResponseCommand(user, true, false, false);
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
            System.out.println("\nRegistering and logging in user: " + DAO._SINGLETON.getUserByAccessToken(theUser.get_S_token()).get_S_username());
            System.out.println("Authorization code: " + theUser.get_S_token());
            return commands;
            
        } catch (SQLException e) {
        	commands.clear();
        	commands.add(new LoginRegisterResponseCommand(null, true, true, true));
            return commands;
        }
        
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
    
    private String findPlayerColor(int index) {
    	List<String> colors = new ArrayList<>(); 
		colors.add("red");
		colors.add("yellow");
		colors.add("blue");
		colors.add("green");
		colors.add("magenta");
		
		return colors.get(index);
    }
    
    /**
	 * This method is used to start a game with a given gameId
	 * 
	 * Pre: the gameId is valid
	 * 
	 * Post: The game will start, the game board will be displayed on each player's screen
	 * 
	 * @param gameId The id of the Game to be retrieved from the model.
	 * @param usernamesInGame Shows each user the usernames of the other players in the game.
	 * @param strAuthenticationCode Authenticates the user starting the game.
	 * @return List<ICommand> Returns the list of commands necessary to initialize the game.
	 */
    @Override
    public List<ICommand> startGame(int gameId, List<String> usernamesInGame, String strAuthenticationCode) {
		//TODO put authentication code in startGameCommand on client side
    	try {
			Game theGame = Game.getGameWithId(gameId);
	        
			List<ICommand> returnCommands = new ArrayList<>();
			List<ICommand> otherPlayerCommands = new ArrayList<>();
			
	        List<TrainCard> trainCards = null;
	        List<DestCard> destCards = null;
	        
	        TrainCardDeck theTrainCardDeck = theGame.getDeck();
	        DestCardDeck theDestCardDeck = theGame.getDestCards();
	      	    	
	        
	        List<Scoreboard> scoreboards = new ArrayList<>();
            for (int i = 0; i < usernamesInGame.size(); i++) {
            	Scoreboard scoreboard = new Scoreboard(findPlayerColor(i));
            	theGame.get_M_PlayerScoreboards().put(usernamesInGame.get(i), scoreboard);
            	scoreboards.add(scoreboard);
            }

        	trainCards = new ArrayList<>();
            for (int j = 0; j < 4; j++)
            {
            	trainCards.add(theTrainCardDeck.drawTop()); //The game creator's train cards
            }
            destCards = theDestCardDeck.drawTop3();
            
            //Nathan: The faceup train table cards at the beginning of the game.
        	List<TrainCard> faceupTrainCards = new ArrayList<>();
            for (int j = 0; j < 5; j++)
            {
            	faceupTrainCards.add(theTrainCardDeck.drawTop());
            }
            
            returnCommands.add(new InitializeGameCommand(trainCards, destCards, faceupTrainCards, scoreboards)); //The creator's cards

            for (int i = 1; i < usernamesInGame.size(); i++) { //Ignore the game creator, start at Player 2
                // initialize each player's hand
            	trainCards = new ArrayList<>();
                for (int j = 0; j < 4; j++) //Create everyone else's cards
                {
                	trainCards.add(theTrainCardDeck.drawTop());
                }
                destCards = theDestCardDeck.drawTop3();
                                
                otherPlayerCommands = new ArrayList<>();
                otherPlayerCommands.add(new InitializeGameCommand(trainCards, destCards, faceupTrainCards, scoreboards));
//        		System.out.println(usernamesInGame.get(i) + " receiving commmands: " + otherPlayerCommands);
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
	return null; //Meaning something went wrong here.
}

    /**
	 * This method is used to add a player to a game.
	 * 
	 * Pre: The user is not already in the game
	 * Pre: The game is not already full
	 * 
	 * Post: The user will be added to the game and the game lobby will display on that user's screen. All other players will be notified.
	 * 
	 * @param str_authentication_code The authentication code of the user being added.
	 * @param intGameId The id of the game to which the user is being added
	 * @return List<ICommand> Returns the list of commands necessary to notify the other players of the added player.
	 * @exception GameIsFullException This is thrown if the game already has 5 players.
	 */
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
                theGame.get_M_idToGame().replace(theGame.get_i_gameId(), theGame);

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
                		ClientProxy.SINGLETON.get_m_usersCommands().get(user.get_S_username()).addAll(commands);
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
    
    /**
	 * This method is used when a user creates a game so it can be stored on the server.
	 * 
	 * Pre: none
	 * 
	 * Post: A newly created game will exist on the server and in the database. All other users will be notified of the new game.
	 * 
	 * @param str_authentication_code The authentication code of the user being added.
	 * @param game The game which is being added.
	 * @return List<ICommand> Returns the list of commands necessary to notify the other users that a new game was created.
	 */
    @Override
    public List<ICommand> addJoinableGameToServer(Game game, String str_authentication_code) {
        
        try {
            @SuppressWarnings("unused")
			Boolean value = DAO._SINGLETON.addGame(new Game());
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
            commands.add(new AddGameToJoinableListCommand(gameId));
            
            String username = "";
            Map<String, List<ICommand>> copy = new HashMap<>();
            
            //Sends AddGameToJoinableListCommand to only logged in users.
            Iterator iterator = UserModel.User.get_M_usernameToLoggedInUser().values().iterator();
            while (iterator.hasNext())
            {
            	UserModel.User newUser = (UserModel.User) iterator.next();
            	if (!newUser.get_S_token().equals(str_authentication_code)) {
            		ClientProxy.SINGLETON.get_m_usersCommands().get(newUser.get_S_username()).addAll(commands);
            		
            	}
            }
            
            
            List<ICommand> returnCommands = new ArrayList<>();
//            returnCommands.add(new AddPlayerToClientCommand(theUser.get_S_username(), theGame.get_i_gameId())); //The creator needs to be in the game he created
//            returnCommands.add(new AddWaitingToClientCommand(theGame));
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
//        ICommand addWaitingToClientCommand = new AddWaitingToClientCommand(game);
        
        List<ICommand> commands = new ArrayList<>();
        commands.add(deleteGameCommand);
//        commands.add(addWaitingToClientCommand);
        
        return commands;
    }
    
    /**
	 * This method is used to log a player out of the server.
	 * 
	 * Pre: none
	 * 
	 * Post: The user will be logged out and the screen will return to the Login View
	 * 
	 * @param str_authentication_code The authentication code of the user being logged out.
	 * @return List<ICommand> Returns the list of commands necessary to log the player out.
	 */
    @Override
    public List<ICommand> logout(String str_authentication_code) {
        ServerModel.SINGLETON.logOut(str_authentication_code);

        List<ICommand> commands = new ArrayList<>();
        
        try {
        	UserModel.User user = DAO._SINGLETON.getUserByAccessToken(str_authentication_code);
            System.out.println("Logging out user: " + user.get_S_username());
            System.out.println("Authorization code: " + user.get_S_token());
            
            DAO._SINGLETON.updateUserToken(user.get_S_username(), null);
            commands.add(new LogoutResponseCommand());
        } catch (SQLException e) {
        	System.err.println("@logout");
            e.printStackTrace();
        }
        
        
        return commands;
    }

 /*
  * -------------------------------------------Phase 3------------------------------------------------------------
  */
    
    /* 
     * Ryan
     * takes a message sent by a player, adds it to the game's chatroom, and sends the list to the other players
     */
	@Override
	public List<ICommand> broadcastToChatCommand(int gameId, String authenticationToken, String message) {
		// Sends back the entire list of messages, prepends message with the username shows who sent the message
		List<ICommand> returnCommands = new ArrayList<>();
		Game theGame = Game.getGameWithId(gameId);
		UserModel.User theUser;
		String username = "";
		try {
			theUser = DAO._SINGLETON.getUserByAccessToken(authenticationToken);
			username = theUser.get_S_username();
			message = username + ": " + message;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		List<String> chatRoom = theGame.getChatRoom();
		chatRoom.add(message);
		returnCommands.add(new ShowMessageCommand(chatRoom));
		
		// send to all users in game
		for (int i = 1; i <= theGame.get_numberOfPlayers(); i++)
		{
			UserModel.User player = theGame.getPlayer(i);
			String newUsername = player.get_S_username();
			if (!newUsername.equals(username))
			{
				ClientProxy.SINGLETON.get_m_usersCommands().get(newUsername).addAll(returnCommands);
			}
		}
		
		return returnCommands;
	}

	/*
	* Ryan
	* Allows a player to claim a route, route color will be set on the client side
	*/
	
	@Override
	public List<ICommand> claimRoute(int gameId, String authenticationCode, Route theRoute, List<TrainCard> cardsUsedToClaimRoute) {
		List<ICommand> returnCommands = new ArrayList<>();
		Game theGame = Game.getGameWithId(gameId);
		String username = "";
		UserModel.User theUser = null;
		try {
			theUser = DAO._SINGLETON.getUserByAccessToken(authenticationCode);
			username = theUser.get_S_username();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// gets the route from the list in the game and sets the owner
		Route route = theGame.getAllRoutes().get_RoutesMap().get(theRoute.get_S_MappingName());
		route.set_Owner(username);
		
		// tells the other players that the route was claimed (maybe make a toast with the message)
		returnCommands.add(new NotifyRouteClaimedCommand("The route from " + route.get_ConnectingCities().getLeft() + " to "
							+ route.get_ConnectingCities().getRight() + " has been claimed by " + username + ".", route));
		theGame.get_M_PlayerScoreboards().get(username).addPoints(route.get_i_pointValue());
		// subtracts the number of cards used from the players total on the scoreboard
		theGame.get_M_PlayerScoreboards().get(username).addTrainCards(-cardsUsedToClaimRoute.size());
		
		// updates the scoreboard
		returnCommands.add(new UpdateScoreboardCommand(new ArrayList<>(theGame.get_M_PlayerScoreboards().values())));
		
		// puts the cards used to claim the route in the discard pile
		theGame.getDeck().getDiscardPile().addAll(cardsUsedToClaimRoute);
		
		// send to all users in game (NotifyRouteClaimed and UpdateScoreboard)
		int currentPlayerNumber = 0;
		for (int i = 1; i <= theGame.get_numberOfPlayers(); i++)
		{
			UserModel.User player = theGame.getPlayer(i);
			String newUsername = player.get_S_username();
			if (!newUsername.equals(username))
			{
				ClientProxy.SINGLETON.get_m_usersCommands().get(newUsername).addAll(returnCommands);
			}
			else
			{
				currentPlayerNumber = i; // identifies the player whose turn it is
			}
		}
		
		// notify next player that it is their turn
		if (currentPlayerNumber == theGame.get_numberOfPlayers())
		{
			currentPlayerNumber = 1; // starts over at player 1
		}
		else
		{
			currentPlayerNumber++; // goes to next player
		}
		ClientProxy.SINGLETON.get_m_usersCommands().get(theGame.getPlayer(currentPlayerNumber).get_S_username()).add(new NotifyTurnCommand());
		
		// decrease car count of player who claimed route by the weight of the route claimed, updates player hand
		returnCommands.add(new UpdateCarCountAndHandCommand(route.get_Weight(), cardsUsedToClaimRoute));
		returnCommands.add(new EndTurnCommand());
		
		return returnCommands;
	}


	// Ryan
	@Override
	public List<ICommand> getTopDeckTrainCard(int gameId, String authenticationCode, int turnNumber) {
		//gets the game, gets the deck from the game, and draws the top card 
		List<ICommand> returnCommands = new ArrayList<>();
		Game theGame = Game.getGameWithId(gameId);
		UserModel.User theUser = null;
		String username = "";
		try {
			theUser = DAO._SINGLETON.getUserByAccessToken(authenticationCode);
			username = theUser.get_S_username();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// TODO: update player count for that card on the server
		
		// updates the scoreboard
		theGame.get_M_PlayerScoreboards().get(username).addTrainCards(1);
		returnCommands.add(new UpdateScoreboardCommand(new ArrayList<>(theGame.get_M_PlayerScoreboards().values())));
		
		int currentPlayerNumber = 0;
		for (int i = 1; i <= theGame.get_numberOfPlayers(); i++)
		{
			UserModel.User player = theGame.getPlayer(i);
			String newUsername = player.get_S_username();
			if (!newUsername.equals(username))
			{
				ClientProxy.SINGLETON.get_m_usersCommands().get(newUsername).addAll(returnCommands);
			}
			else
			{
				currentPlayerNumber = i; // shows whose turn it is
			}
		}
		
		// adds top train card to current user's hand
		returnCommands.add(new UpdatePlayerTrainCardsCommand(theGame.getDeck().drawTop()));
		if (turnNumber == 2)
		{
			if (currentPlayerNumber == theGame.get_numberOfPlayers())
			{
				currentPlayerNumber = 1; // starts over at player 1
			}
			else
			{
				currentPlayerNumber++; // goes to next player
			}
			ClientProxy.SINGLETON.get_m_usersCommands().get(theGame.getPlayer(currentPlayerNumber).get_S_username()).add(new NotifyTurnCommand());
			returnCommands.add(new EndTurnCommand()); // ends current player's turn
		}
		else
		{
			returnCommands.add(new NotifyTurnCommand()); // the current player can draw another card
		}
		
		return returnCommands;
	}

	// Ryan
	@Override
	public List<ICommand> getFaceUpTableTrainCard(int gameId, int cardIndex, boolean isWild, String authenticationCode, int turnNumber) {
		List<ICommand> returnCommands = new ArrayList<>();
		Game theGame = Game.getGameWithId(gameId);
		String username = "";
		UserModel.User theUser = null;
		try {
			theUser = DAO._SINGLETON.getUserByAccessToken(authenticationCode);
			username = theUser.get_S_username();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		TrainCardDeck theDeck = theGame.getDeck();
		TrainCard theCard = theDeck.getFromTheFiveCards(cardIndex);
		returnCommands.add(new UpdateFaceUpTableTrainCardsCommand(theDeck.getFiveCards()));
		
		// updates the scoreboard
		theGame.get_M_PlayerScoreboards().get(username).addTrainCards(1);
		returnCommands.add(new UpdateScoreboardCommand(new ArrayList<>(theGame.get_M_PlayerScoreboards().values())));
		
		// sends new set of face up 5 cards to all players, as well as the updated scoreboard
		int currentPlayerNumber = 0;
		for (int i = 1; i <= theGame.get_numberOfPlayers(); i++)
		{
			UserModel.User player = theGame.getPlayer(i);
			String newUsername = player.get_S_username();
			if (!newUsername.equals(username))
			{
				ClientProxy.SINGLETON.get_m_usersCommands().get(newUsername).addAll(returnCommands);
			}
			else
			{
				currentPlayerNumber = i; // shows whose turn it is
			}
		}
		
		// adds the card sele
		// TODO: update the card count of that type for that player on the server
		returnCommands.add(new UpdatePlayerTrainCardsCommand(theCard));
		
		if (turnNumber == 2)
		{
			if (currentPlayerNumber == theGame.get_numberOfPlayers())
			{
				currentPlayerNumber = 1; // starts over at player 1
			}
			else
			{
				currentPlayerNumber++; // goes to next player
			}
			ClientProxy.SINGLETON.get_m_usersCommands().get(theGame.getPlayer(currentPlayerNumber).get_S_username()).add(new NotifyTurnCommand());
			returnCommands.add(new EndTurnCommand()); // ends current player's turn
		}
		else
		{
			returnCommands.add(new NotifyTurnCommand()); // the current player can draw another card
		}
		
		return returnCommands;
	}
    
    public List<ICommand> rejectDestCard(int gameId, String authenticationCode, DestCard rejectedCard)
    {
    	List<ICommand> returnCommands = new ArrayList<>();
    	Game theGame = Game.getGameWithId(gameId);
    	UserModel.User theUser;
    	String username = "";
		try {
			theUser = DAO._SINGLETON.getUserByAccessToken(authenticationCode);
			username = theUser.get_S_username();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//returns the card to the DestCardDeck in the game
		theGame.getDestCards().returnCard(rejectedCard);
		List<DestCard> rejected = new ArrayList<>();
		rejected.add(rejectedCard);
		
		// updates the scoreboard, but doesn't send until all destination cards are kept/rejected
		theGame.get_M_PlayerScoreboards().get(username).addDestCards(-1);
		
		returnCommands.add(new UpdatePlayerDestinationCardsCommand(rejected));
    	return returnCommands;
    }
    
    public List<ICommand> getDestCards(int gameId, String authenticationCode)
    {
    	List<ICommand> returnCommands = new ArrayList<>();
    	Game theGame = Game.getGameWithId(gameId);
    	UserModel.User theUser;
    	String username = "";
		try {
			theUser = DAO._SINGLETON.getUserByAccessToken(authenticationCode);
			username = theUser.get_S_username();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// gets the three top cards
		DestCardDeck theDeck = theGame.getDestCards();
		returnCommands.add(new UpdateServerDestCardsCommand(theDeck.drawTop3()));
		
		// updates the scoreboard, but doesn't send until all destination cards are kept/rejected
		theGame.get_M_PlayerScoreboards().get(username).addDestCards(3);
		
    	return returnCommands;
    }
    
    public List<ICommand> keepAllDestCards(int gameId, String authenticationCode, List<DestCard> cardsKept)
    {
    	List<ICommand> returnCommands = new ArrayList<>();
    	Game theGame = Game.getGameWithId(gameId);
    	UserModel.User theUser;
    	String username = "";
		try {
			theUser = DAO._SINGLETON.getUserByAccessToken(authenticationCode);
			username = theUser.get_S_username();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
		returnCommands.add(new UpdateScoreboardCommand(new ArrayList<>(theGame.get_M_PlayerScoreboards().values())));

		int currentPlayerNumber = 0;
		for (int i = 1; i <= theGame.get_numberOfPlayers(); i++)
		{
			UserModel.User player = theGame.getPlayer(i);
			String newUsername = player.get_S_username();
			if (!newUsername.equals(username))
			{
				ClientProxy.SINGLETON.get_m_usersCommands().get(newUsername).addAll(returnCommands);
			}
			else
			{
				currentPlayerNumber = i; // shows whose turn it is
			}
		}
		
		if (currentPlayerNumber == theGame.get_numberOfPlayers())
		{
			currentPlayerNumber = 1; // starts over at player 1
		}
		else
		{
			currentPlayerNumber++; // goes to next player
		}
		ClientProxy.SINGLETON.get_m_usersCommands().get(theGame.getPlayer(currentPlayerNumber).get_S_username()).add(new NotifyTurnCommand());
		returnCommands.add(new UpdatePlayerDestinationCardsCommand(cardsKept));
		returnCommands.add(new EndTurnCommand()); // ends current player's turn
		
		return returnCommands;
    }

	public List<ICommand> firstTurn(int gameId, String authenticationCode, List<DestCard> destCards, String type) {
    	List<ICommand> returnCommands = new ArrayList<>();
    	Game theGame = Game.getGameWithId(gameId);
    	UserModel.User theUser;
    	String username = "";
		try {
			theUser = DAO._SINGLETON.getUserByAccessToken(authenticationCode);
			username = theUser.get_S_username();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (type.equals("KEEP"))
		{
			theGame.get_M_PlayerScoreboards().get(username).addDestCards(destCards.size());
			returnCommands.add(new UpdateScoreboardCommand(new ArrayList<>(theGame.get_M_PlayerScoreboards().values())));
			
			int playersThatCompletedFirstTurn = 0;
			// sends new set of face up 5 cards to all players, as well as the updated scoreboard
			for (int i = 1; i <= theGame.get_numberOfPlayers(); i++)
			{
				UserModel.User player = theGame.getPlayer(i);
				String newUsername = player.get_S_username();
				if (!newUsername.equals(username))
				{
					ClientProxy.SINGLETON.get_m_usersCommands().get(newUsername).addAll(returnCommands);
				}
				if (theGame.get_M_PlayerScoreboards().get(newUsername).getNumberOfDestCards() > 0)
				{
					playersThatCompletedFirstTurn++;
				}
			}
			if (playersThatCompletedFirstTurn == theGame.get_numberOfPlayers())
			{
				ClientProxy.SINGLETON.get_m_usersCommands().get(theGame.getPlayer1().get_S_username()).add(new NotifyTurnCommand());
			}
			returnCommands.add(new UpdatePlayerDestinationCardsCommand(destCards));
			returnCommands.add(new EndTurnCommand());
		}
		else
		{
			theGame.getDestCards().returnCard(destCards.get(0));
		}
		
		return returnCommands;
	}

}