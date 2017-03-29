package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import ServerModel.GameModels.BoardModel.Scoreboard;
import GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * Updates the points of a player by adding to the player's current points
 *
 * Created by natha on 2/27/2017.
 */

// Ryan: added a username field so it can be sent to all users and the points can be deducted from each client

public class UpdateScoreboardCommand implements ICommand {

    //Data members
    private List<Scoreboard> scoreboards;

    //Constructor
    public UpdateScoreboardCommand(){}
    public UpdateScoreboardCommand(List<Scoreboard> s) {
        scoreboards = s;
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


    public List<Scoreboard> getScoreboards() {
        return scoreboards;
    }

}
