package Command.Phase1;
import Command.ICommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;

import java.util.ArrayList;
import java.util.List;

/**
 * This command is only received from ALREADY logged in users on the server
 */
public class SwitchToWaitingActivityCommand implements ICommand { // sent after changes from what List... commands sent

    private int gameId;
    private List<String> usernames = new ArrayList<>(); //Server sends back a list of usernames to add to the clientmodel
    private Boolean isCreator;

    public SwitchToWaitingActivityCommand() {
    }

    public SwitchToWaitingActivityCommand(int id, List<String> names, Boolean creator) {
        gameId = id;
        usernames = names;
        isCreator = creator;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }
    
	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}


    @Override
    public List<ICommand> execute() {

        return null; //Since client side is all void
    }

    public int getGameId() {
        return gameId;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public Boolean getIsCreator() {
        return isCreator;
    }



}