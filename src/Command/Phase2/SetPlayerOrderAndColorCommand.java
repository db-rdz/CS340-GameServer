package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * Sets the order and color for each player in the game.
 * The order of players depends on when the player joined the game.
 * The color for each player is set by the program. Player can't choose their color.
 *
 * TODO: Do we need this class still?
 *
 * Created by natha on 2/27/2017.
 */

public class SetPlayerOrderAndColorCommand implements ICommand {


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
