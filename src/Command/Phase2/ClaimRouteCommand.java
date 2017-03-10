package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM CLIENT -> SERVER
 * This command is called whenever a player wants to claim a route.
 * A BroadcastCommand will be called after to tell other players when a route has been claimed.
 *
 * Created by natha on 2/27/2017.
 */

public class ClaimRouteCommand implements ICommand {
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

}
