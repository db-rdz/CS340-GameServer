package Client;

import Command.AddJoinableToClientCommand;
import Command.AddPlayerToClientCommand;
import Command.AddResumableToClientCommand;
import Command.AddWaitingToClientCommand;
import Command.CommandContainer;
import Command.DeleteGameCommand;
import Command.GetCommandsCommand;
import Command.ICommand;
import Command.ListJoinableCommand;
import Command.ListResumableCommand;
import Command.ListWaitingCommand;
import Command.LoginCommand;
import Command.LoginRegisterResponseCommand;
import Command.LogoutCommand;
import Command.LogoutResponseCommand;
import Command.RegisterCommand;
import Command.StartGameCommand;
import GameModels.Game;
import Server.IServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by natha on 2/7/2017.
 */

public class ServerProxy implements IServer {

    public static ServerProxy SINGLETON = new ServerProxy();

    private ServerProxy() {

    }


    @Override
    public CommandContainer login(String username, String password) throws IClient.InvalidUsername, IClient.InvalidPassword {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("LoginCommand");

        List<ICommand> commands = new ArrayList<>();
//        commands.add(new LoginCommand(username));
//        commands.add(username);
//        commands.add(password);
//        commands.add(authoritzationCode);
        
        System.out.println("Object type: " + commands.get(0).getClass());

//        CommandContainer loginCommand = new CommandContainer("hello");
        CommandContainer loginCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, loginCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginCommand;
    }

    @Override
    public CommandContainer register(String username, String password) throws IClient.UsernameAlreadyExists {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("RegisterCommand");

        List<ICommand> commands = new ArrayList<>();
//        commands.add(new RegisterCommand(username));
//        commands.add(username);
//        commands.add(password);

//        CommandContainer registerCommand = new CommandContainer("hello");
        CommandContainer registerCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, registerCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return registerCommand;
    }


    @Override
    public CommandContainer addResumableGame(Game game) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("AddResumable");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new AddResumableToClientCommand(game));

//        CommandContainer addGameCommand = new CommandContainer("hello");
        CommandContainer addGameCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, addGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addGameCommand;
    }

    @Override
    public CommandContainer addJoinableGame(Game game) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("AddJoinable");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new AddJoinableToClientCommand(game));

//        CommandContainer addGameCommand = new CommandContainer("hello");
        CommandContainer addGameCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, addGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addGameCommand;
    }

    @Override
    public CommandContainer addWaitingGame(Game game) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("AddWaiting");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new AddWaitingToClientCommand(game));

//        CommandContainer addGameCommand = new CommandContainer("hello");
        CommandContainer addGameCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, addGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addGameCommand;
    }


    @Override
    public CommandContainer removeGame(Game game) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("DeleteGame");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new DeleteGameCommand(game.get_i_gameId()));

//        CommandContainer removeGameCommand = new CommandContainer("hello");
        CommandContainer removeGameCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, removeGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }

    @Override
    public CommandContainer startGame(Game game, String authorizationCode) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("StartGame");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new StartGameCommand(game, authorizationCode));

//        CommandContainer startGameCommand = new CommandContainer("hello");
        CommandContainer startGameCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, startGameCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return startGameCommand;
    }

    @Override
    public CommandContainer addPlayer(String authenticationCode, int gameId) {
        String urlSuffix = "/command";

        List<String> types = new ArrayList<>();
        types.add("AddPlayer");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new AddPlayerToClientCommand(authenticationCode, gameId));

//        CommandContainer addPlayerCommand = new CommandContainer("hello");
        CommandContainer addPlayerCommand = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, addPlayerCommand);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return addPlayerCommand;
    }

    //Added these functions after seeing the Command package
//    public CommandContainer logout(String authenticationCode) {
//        String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("Logout");
//
//        List<Object> commands = new ArrayList<>();
//        commands.add(new LogoutCommand(authenticationCode));
//
//        CommandContainer logoutCommand = new CommandContainer(types, commands);
//
//        try {
//            ClientCommunicator.SINGLETON.send(urlSuffix, logoutCommand);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return logoutCommand;
//    }
//
//    public void listJoinableGames(Set<Game> listJoinableGames) {
//        String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("ListJoinable");
//
//        List<Object> commands = new ArrayList<>();
////        commands.add(new ListJoinableCommand(listJoinableGames));
//
//        CommandContainer listJoinableCommand = new CommandContainer(types, commands);
//
//        try {
//            ClientCommunicator.SINGLETON.send(urlSuffix, listJoinableCommand);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void listResumableGames(Set<Game> listResumableGames) {
//        String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("ListResumable");
//
//        List<Object> commands = new ArrayList<>();
////        commands.add(new ListResumableCommand(listResumableGames));
//
//        CommandContainer listResumableCommand = new CommandContainer(types, commands);
//
//        try {
//            ClientCommunicator.SINGLETON.send(urlSuffix, listResumableCommand);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void listWaitingGames(Set<Game> listWaitingGames) {
//        String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("ListWaiting");
//
//        List<Object> commands = new ArrayList<>();
////        commands.add(new ListWaitingCommand(listWaitingGames));
//
//        CommandContainer listWaitingCommand = new CommandContainer(types, commands);
//
//        try {
//            ClientCommunicator.SINGLETON.send(urlSuffix, listWaitingCommand);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void loginRegisterSucceeded(User user, String authenticationCode) {
//        String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("LoginRegisterResponse");
//
//        List<Object> commands = new ArrayList<>();
//        commands.add(new LoginRegisterResponseCommand(user, authenticationCode));
//
//        CommandContainer loginRegisterResponseCommand = new CommandContainer(types, commands);
//
//        try {
//            ClientCommunicator.SINGLETON.send(urlSuffix, loginRegisterResponseCommand);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void logoutSucceeded() {
//        String urlSuffix = "/command";
//
//        List<String> types = new ArrayList<>();
//        types.add("LogoutResponse");
//
//        List<Object> commands = new ArrayList<>();
//        commands.add(new LogoutResponseCommand());
//
//        CommandContainer logoutResponseCommand = new CommandContainer(types, commands);
//
//        try {
//            ClientCommunicator.SINGLETON.send(urlSuffix, logoutResponseCommand);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public CommandContainer checkForCommands()
    {
        String urlSuffix = "/update";

        List<String> types = new ArrayList<>();
        types.add("GetCommandsCommand");

        List<ICommand> commands = new ArrayList<>();
        commands.add(new GetCommandsCommand());

        CommandContainer checkForCommands = new CommandContainer(types, commands);

        try {
            ClientCommunicator.SINGLETON.send(urlSuffix, checkForCommands);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


	@Override
	public CommandContainer addGame(Game game) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public CommandContainer logout(String str_authentication_code) {
		// TODO Auto-generated method stub
		return null;
	}

}