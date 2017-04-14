package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM CLIENT -> SERVER
 * This command is called when a player taps on the destination cards he wants to keep.
 * The same player's hand will then be updated after this command
 *
 * Created by natha on 2/28/2017.
 */

public class SelectRequestedDestinationCardCommand implements ICommand {
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }


}
