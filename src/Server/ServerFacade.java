package Server;
import Client.IClient;
import Client.User;
import Command.AddJoinableToClientCommand;
import Command.AddPlayerToClientCommand;
import Command.AddResumableToClientCommand;
import Command.AddWaitingToClientCommand;
import Command.CommandContainer;
import Command.DeleteGameCommand;
import Command.ICommand;
import Command.ListJoinableCommand;
import Command.ListResumableCommand;
import Command.ListWaitingCommand;
import Command.LoginRegisterResponseCommand;
import Command.LogoutResponseCommand;
import Database.DAO;
import Database.DataBase;
import GameModels.Game;
import ServerModel.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by RyanBlaser on 2/5/17.
 */

public class ServerFacade implements IServer {

    public static ServerFacade SINGLETON = new ServerFacade();

    @Override
    public CommandContainer login(String username, String password) throws IClient.InvalidUsername, IClient.InvalidPassword, UserAlreadyLoggedIn {
        try {
//        	System.out.println("hello from the ServerFacade login");
            // tries to retrieve the user from the database
            if (DAO._SINGLETON.login(username, password))
            {
            	UserModel.User theUser = DAO._SINGLETON.getUserByUserName(username);
            	User user = new User();
            	user.setUsername(theUser.get_Username());
            	user.setPassword(theUser.get_Password());
            	user.setStr_authentication_code(theUser.get_Token());

                ICommand success = new LoginRegisterResponseCommand(user);
//                ICommand listJoinableCommand = new ListJoinableCommand(ServerModel.SINGLETON.getAvailableGames());
//                ICommand listResumableCommand = new ListResumableCommand(ServerModel.SINGLETON.getStartedGames());
//                ICommand listWaitingCommand = new ListWaitingCommand(theUser.getJoinedGames()); 
                
                List<String> types = new ArrayList<>();
                types.add("LoginRegisterResponseCommand");
                types.add("ListJoinableCommand");
                types.add("ListResumableCommand");
                types.add("ListWaitingCommand");

                List<ICommand> commands = new ArrayList<>();
                commands.add(success); //Break down the .add for User info just like on client side. This is for ease.
//                commands.add(theUser.get_Username());
//                commands.add(theUser.get_Password());
//                commands.add(theUser.get_Token());
                
//                List<Game> games = ServerModel.SINGLETON.getAvailableGames();
//                List<Integer> gameIds = new ArrayList<>();
//                for (int i = 0; i < games.size(); i++)
//                {
//                	gameIds.add(games.get(i).get_i_gameId());
//                }
//                commands.add(gameIds);
//                
//                List<Integer> startedGameIds = new ArrayList<>();
//                List<Integer> waitingGameIds = new ArrayList<>();
//                games = theUser.getJoinedGames();
//                List<Game> startedGames = ServerModel.SINGLETON.getStartedGames();
//                Boolean set = false;
//                for (int i = 0; i < games.size(); i++)
//                {
//                	set = false;
//                	Game game = games.get(i);
//                	for (int j = 0; j < startedGames.size(); j++)
//                	{
//                		Game startedGame = startedGames.get(j);
//                		if (game.get_i_gameId() == startedGame.get_i_gameId())
//                		{
//                			startedGameIds.add(startedGame.get_i_gameId());
//                			set = true;
//                		}
//                   	}
//                	if (!set)
//                	{
//                		waitingGameIds.add(game.get_i_gameId());
//                	}
//                }
//                commands.add(startedGameIds);
//                commands.add(waitingGameIds);
                
//                commands.add(listJoinableCommand);
//                commands.add(listResumableCommand);
//                commands.add(listWaitingCommand);

//                CommandContainer response = new CommandContainer("hello");
                CommandContainer response = new CommandContainer(types, commands);
                
                System.out.println("Logging in user: " + DAO._SINGLETON.getUserByAccessToken(theUser.get_Token()).get_Username());
                System.out.println("Authorization code: " + theUser.get_Token());

//                
                return response;
            }
	    } catch (SQLException e) {
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
    public CommandContainer register(String username, String password) throws IClient.UsernameAlreadyExists, UserAlreadyLoggedIn {
        try {
            if (!DAO._SINGLETON.registerUser(username, password)) {
                throw new IClient.UsernameAlreadyExists();
            }

            UserModel.User theUser = DAO._SINGLETON.getUserByUserName(username);


//            LoginRegisterResponseCommand success = new LoginRegisterResponseCommand(theUser.get_Username(), theUser.get_Password(), theUser.get_Token());
//
//            ICommand listJoinableCommand = new ListJoinableCommand(ServerModel.SINGLETON.getAvailableGames()); 
//            ICommand listResumableCommand = new ListResumableCommand(ServerModel.SINGLETON.getStartedGames()); 
//            ICommand listWaitingCommand = new ListWaitingCommand(theUser.getJoinedGames());

            List<String> types = new ArrayList<>();
            types.add("LoginRegisterResponseCommand");
            types.add("ListJoinableCommand");
            types.add("ListResumableCommand");
            types.add("ListWaitingCommand");

            List<ICommand> commands = new ArrayList<>();
//            commands.add(success); //Break down the .add for User info just like on client side. This is for ease.
//            commands.add(theUser.get_Username());
//            commands.add(theUser.get_Password());
//            commands.add(theUser.get_Token());
            
//            List<Game> games = ServerModel.SINGLETON.getAvailableGames();
//            List<Integer> gameIds = new ArrayList<>();
//            for (int i = 0; i < games.size(); i++)
//            {
//            	gameIds.add(games.get(i).get_i_gameId());
//            }
//            commands.add(gameIds);
//            
//            List<Integer> startedGameIds = new ArrayList<>();
//            List<Integer> waitingGameIds = new ArrayList<>();
//            games = theUser.getJoinedGames();
//            List<Game> startedGames = ServerModel.SINGLETON.getStartedGames();
//            Boolean set = false;
//            for (int i = 0; i < games.size(); i++)
//            {
//            	set = false;
//            	Game game = games.get(i);
//            	for (int j = 0; j < startedGames.size(); j++)
//            	{
//            		Game startedGame = startedGames.get(j);
//            		if (game.get_i_gameId() == startedGame.get_i_gameId())
//            		{
//            			startedGameIds.add(startedGame.get_i_gameId());
//            			set = true;
//            		}
//               	}
//            	if (!set)
//            	{
//            		waitingGameIds.add(game.get_i_gameId());
//            	}
//            }
//            commands.add(startedGameIds);
//            commands.add(waitingGameIds);
            
//            commands.add(listJoinableCommand);
//            commands.add(listResumableCommand);
//            commands.add(listWaitingCommand);

//            CommandContainer response = new CommandContainer("hello");
            CommandContainer response = new CommandContainer(types, commands);

            System.out.println("Registering and logging in user: " + DAO._SINGLETON.getUserByAccessToken(theUser.get_Token()).get_Username());
            System.out.println("Authorization code: " + theUser.get_Token());
            return response;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public CommandContainer addGame(Game game) {
        Game.addGame(game, game.get_i_gameId());
        AddJoinableToClientCommand addJoinableGameCommand = new AddJoinableToClientCommand(game);

        List<ICommand> commands = new ArrayList<>();
       // commands.add(addJoinableGameCommand);
//        commands.add(game.get_i_gameId());

        List<String> types = new ArrayList<>();
        types.add("AddJoinableCommand");

//        CommandContainer response = new CommandContainer("hello");
      CommandContainer response = new CommandContainer(types, commands);
        String username = "";

        for (int i = 0; i < UserModel.User.get_L_listOfAllUsers().size(); i++) {
            username = UserModel.User.get_L_listOfAllUsers().get(i).get_Username();
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, response);
        }
        
//        types.clear();
//        types.add("AddWaitingCommand");
        
        response.str_type = types;
        
        return response;
    }

    @Override
    public CommandContainer removeGame(Game game) {

        ICommand deleteGameCommand = new DeleteGameCommand(game.get_i_gameId());

        List<ICommand> commands = new ArrayList<>();
        commands.add(deleteGameCommand);

        List<String> types = new ArrayList<>();
        types.add("DeleteGameCommand");

//        CommandContainer response = new CommandContainer("hello");
      CommandContainer response = new CommandContainer(types, commands);
        String username = "";

        for (int i = 0; i < UserModel.User.get_L_listOfAllUsers().size(); i++) {
            username = UserModel.User.get_L_listOfAllUsers().get(i).get_Username();
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, response);
        } //TODO: Include this in the ServerCommunicator.

        return response;
    }

    @Override
    public CommandContainer startGame(Game game, String strAuthenticationCode) { //TODO: use strAuthenticationCode
        Game theGame = Game.getGameWithId(game.get_i_gameId());

        //send delete game command to all users not in game

        ICommand deleteGameCommand = new DeleteGameCommand(game.get_i_gameId());

        List<String> types = new ArrayList<>();
        types.add("DeleteGameCommand");

        List<ICommand> commands = new ArrayList<>();
        commands.add(deleteGameCommand);

//        CommandContainer commandContainer = new CommandContainer("hello");
      CommandContainer commandContainer = new CommandContainer(types, commands);
        String username = "";

        for (int i = 0; i < UserModel.User.get_L_listOfAllUsers().size(); i++) {
            username = UserModel.User.get_L_listOfAllUsers().get(i).get_Username();
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
        }

        // send to all users in game
//        Iterator iter = theGame.get_M_idToGame().values().iterator(); //TODO: is this correct??
        Iterator iter = theGame.get_M_idToUserInGame().values().iterator(); //TODO: is this correct??
        ICommand addResumableToClientCommand = new AddResumableToClientCommand(game);

        types.clear();
        types.add("AddResumableToClientCommand");

        commands.clear();
        commands.add(addResumableToClientCommand);

//        commandContainer = new CommandContainer("hello");
        commandContainer = new CommandContainer(types, commands);

        while (iter.hasNext()) {
            UserModel.User user = (UserModel.User) iter.next();
            username = user.get_Username();
            ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
        }

//        CommandContainer response = new CommandContainer("hello");
      CommandContainer response = new CommandContainer(types, commands);
		return response;
    }

    @Override
    public CommandContainer addPlayer(String strAuthenticationCode, int intGameId) throws GameIsFullException {
        if (!ServerModel.SINGLETON.addPlayerToGame(strAuthenticationCode, intGameId)) {
        	String username = "";
        	List<String> types = new ArrayList<>();
            types.add("DeleteGameCommand");
            
            List<ICommand> commands = new ArrayList<>();
//            commands.add(intGameId);
            
            CommandContainer commandContainer = new CommandContainer(types, commands);
        	for (int i = 0; i < UserModel.User.get_L_listOfAllUsers().size(); i++) {
                UserModel.User theUser = UserModel.User.get_L_listOfAllUsers().get(i);
            	username = theUser.get_Username();
            	
                if (!Game.isUserInGame(username, intGameId))
                	ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
            }
        	throw new GameIsFullException();
        }
            // not allowed to join
        else {
            // sent to all users
            ICommand addPlayerToClientCommand = new AddPlayerToClientCommand(strAuthenticationCode, intGameId);

            List<String> types = new ArrayList<>();
            types.add("AddPlayerToClientCommand");

            List<ICommand> commands = new ArrayList<>();
//            for (int i = 0; i < UserModel.User.get_L_listOfAllUsers().size(); i++) {
//                UserModel.User theUser = UserModel.User.get_L_listOfAllUsers().get(i);
//            	if (theUser.get_Token() == strAuthenticationCode)
//                	commands.add(theUser.get_Username());
//            }
//            commands.add(intGameId);

//            CommandContainer commandContainer = new CommandContainer("hello");
          CommandContainer commandContainer = new CommandContainer(types, commands);
            String username = "";

            for (int i = 0; i < UserModel.User.get_L_listOfAllUsers().size(); i++) {
                UserModel.User theUser = UserModel.User.get_L_listOfAllUsers().get(i);
            	username = theUser.get_Username();
            	
                if (theUser.get_Token() != strAuthenticationCode)
                	ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
            }

            // send to user who joined
            DeleteGameCommand deleteGameCommand = new DeleteGameCommand(intGameId);
//            ICommand addWaitingToClientCommand = new AddWaitingToClientCommand(ServerModel.SINGLETON.getGame(intGameId)); //TODO: decide on which Game class to use
            ICommand addWaitingToClientCommand = new AddWaitingToClientCommand(ServerModel.SINGLETON.getGame(intGameId)); //TODO: this is correct??

            types.clear();
            types.add("DeleteGameCommand");
            types.add("AddWaiting");

            commands.clear();
//            commands.add(intGameId);
//            commands.add(intGameId);

//            CommandContainer response = new CommandContainer("hello");
          CommandContainer response = new CommandContainer(types, commands);
            return response;
        }
    }

    @Override
    public CommandContainer addResumableGame(Game game) {
        //send to user who called function
        ICommand addResumableToClientCommand = new AddResumableToClientCommand(game);

        List<String> types = new ArrayList<>();
        types.add("AddResumableToClientCommand");

        List<ICommand> commands = new ArrayList<>();
        commands.add(addResumableToClientCommand);

//        CommandContainer response = new CommandContainer("hello");
      CommandContainer response = new CommandContainer(types, commands);
        return response;
    }

    @Override
    public CommandContainer addJoinableGame(Game game) {
   	
		try {
			DAO._SINGLETON.deleteAllGames();
			DAO._SINGLETON.addGame(game);
			
//			Game theGame = DAO._SINGLETON.getGameFromId(); //TODO: Still need to do this?
			
			Integer gameId = DAO._SINGLETON.getAllGames().size(); //The size == gameid
//			ServerModel.SINGLETON.addGameToLobby(game);
			
	        List<String> types = new ArrayList<>();
	        types.add("AddJoinableCommand");
	
	        List<ICommand> commands = new ArrayList<>();
	        commands.add(new AddJoinableToClientCommand(game));
	        
	        CommandContainer commandContainer = new CommandContainer(types, commands);
	        String username = "";
        
	        for (int i = 0; i < DAO._SINGLETON.getAllUsers().size(); i++) {
				username = DAO._SINGLETON.getAllUsers().get(i).get_Username();
				ClientProxy.SINGLETON.get_m_usersCommands().put(username, commandContainer);
			}
	        
//	        types.clear();
//	        types.add("AddWaitingCommand");
	        
	        return commandContainer;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;        
    }

    @Override
    public CommandContainer addWaitingGame(Game game) {
        // send to user who called this function only
        ICommand deleteGameCommand = new DeleteGameCommand(game.get_i_gameId());
        ICommand addWaitingToClientCommand = new AddWaitingToClientCommand(game);

        List<String> types = new ArrayList<>();
        types.add("DeleteGameCommand");
        types.add("AddWaitingToClientCommand");

        List<ICommand> commands = new ArrayList<>();
        commands.add(deleteGameCommand);
        commands.add(addWaitingToClientCommand);

//        CommandContainer response = new CommandContainer("hello");
      CommandContainer response = new CommandContainer(types, commands);      
        return response;
    }


    @Override
    public CommandContainer logout(String str_authentication_code) {
//        ServerModel.SINGLETON.logOut(str_authentication_code);
        //ICommand logoutCommand = new LogoutResponseCommand();

        List<String> types = new ArrayList<>();
        types.add("LogoutResponseCommand");

        List<ICommand> commands = new ArrayList<>();
        UserModel.User user = new UserModel.User();
        
        try {
			user = DAO._SINGLETON.getUserByAccessToken(str_authentication_code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        System.out.println("Logging out user: " + user.get_Username());
        System.out.println("Authorization code: " + user.get_Token());

//        CommandContainer response = new CommandContainer("hello");
        CommandContainer response = new CommandContainer(types, commands);
        return response;
    }
}