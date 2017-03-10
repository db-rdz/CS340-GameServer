package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * This command occurs when a player taps "Messages" on the screen.
 * This will cause a display to occur and show what has been broadcasted in the game chat.
 *
 * Created by natha on 2/28/2017.
 */

public class ShowMessageCommand implements ICommand {

    //Data members
    private String str_message;

    //Constructor
    public ShowMessageCommand(String str_message) {
        this.str_message = str_message;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        return null;
    }

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



    public String getStr_message() {
        return str_message;
    }
}
