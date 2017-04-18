package Command.Phase1;
import Server.ClientProxy;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;
import Command.ICommand;
import GameModels.Game;
public class GetCommandsCommand implements ICommand{
    private String username;
    private int lastCommandRecievedIndex;
    public GetCommandsCommand(){}
    public GetCommandsCommand(String u){
        username = u;}
    
    public String getUsername()
    {
        return username;
    }
    
    @Override
    public String getAuthenticationCode() {
        return null;
    }
    
    public int getLastCommandRecievedIndex() {
		return lastCommandRecievedIndex;
	}
    
    /**
     * Nathan changes
     * Added functionality for getting the specific commands for users that have logged in.
     * ~~~
     * 
     */
    @Override
    public List<ICommand> execute(){
        List<ICommand> listOfSpecificUserCommands = new ArrayList<>();
        int mapSize = ClientProxy.SINGLETON.get_m_usersCommands().size();
//        System.out.println("mapSize is: " + mapSize);
        
        try {
            if (mapSize > 0 && UserModel.User.get_M_usernameToLoggedInUser().containsKey(username)) { //If there's commands for that logged in user, get them
                for (ICommand command : ClientProxy.SINGLETON.getUserCommands(username, lastCommandRecievedIndex)) { //Does a deep copy of the list of commands
                	listOfSpecificUserCommands.add(command);
                }
//            	System.out.println("getting commands for " + username + ": " + listOfSpecificUserCommands);
//            	System.out.println("lastCommandReceivedIndex: " + lastCommandRecievedIndex);

            }
		} catch (Exception e) {
//			e.printStackTrace();
		}

        return listOfSpecificUserCommands;


    }
//    @JsonIgnore
//    @Override
//    public Game getGame() { return null; }
//    
}