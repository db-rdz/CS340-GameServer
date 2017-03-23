package Command.Phase1;

import Client.User;
import Command.ICommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by natha on 3/16/2017.
 */

public class AddGameToJoinableListCommand implements ICommand {

    private int gameId;
    public AddGameToJoinableListCommand(){}
    public AddGameToJoinableListCommand(int id){
        gameId = id;}

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
    }

    @Override
    public List<ICommand> execute() {
        return null;
    }

    public int getGameId() {
        return gameId;
    }

}
