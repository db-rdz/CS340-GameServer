package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * Notifies when it's a specific player's turn in the game.
 *
 * Created by natha on 2/28/2017.
 */

public class NotifyTurnCommand implements ICommand {

    //Data members
    private String str_notify_message;

    //Constructor
    public NotifyTurnCommand(String str_notify_message) {
        this.str_notify_message = str_notify_message;
    }

    //Functions
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        return null; //TODO: Use showMessage() method or make a new one?
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



    public String getStr_notify_message() {
        return str_notify_message;
    }
}
