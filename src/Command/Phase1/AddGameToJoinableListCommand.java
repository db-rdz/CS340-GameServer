package Command.Phase1;

import Command.ICommand;
import com.fasterxml.jackson.annotation.JsonIgnore;

import Client.User;

import java.util.List;

/**
 * This command is only received by NEWLY logged in users
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

    @Override
    public List<ICommand> execute() {
        return null;
    }

    public int getGameId() {
        return gameId;
    }

}
