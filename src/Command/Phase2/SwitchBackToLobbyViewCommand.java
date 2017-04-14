package Command.Phase2;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;

import java.util.List;

/**
 * Created by natha on 4/4/2017.
 */

public class SwitchBackToLobbyViewCommand implements Command.ICommand {
    @Override
    public List<Command.ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn {
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }
}
