package Command.Phase2;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Command.ICommand;
import Server.IServer.GameIsFullException;
import Server.IServer.UserAlreadyLoggedIn;

/**
 * Created by natha on 4/4/2017.
 */

public class NotifyDestCardCommand implements ICommand {

    public NotifyDestCardCommand(){}
    
    @Override
    public List<ICommand> execute() throws GameIsFullException, UserAlreadyLoggedIn {
        return null;
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return null;
    }
}
