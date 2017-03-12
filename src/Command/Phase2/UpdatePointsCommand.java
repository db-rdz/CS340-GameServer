package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * Updates the points of a player by adding to the player's current points
 *
 * Created by natha on 2/27/2017.
 */

public class UpdatePointsCommand implements ICommand {

    //Data members
    private int int_points_to_add;

    //Constructor
    public UpdatePointsCommand(int int_points_to_add) {
        this.int_points_to_add = int_points_to_add;
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


    public int getInt_points_to_add() {
        return int_points_to_add;
    }
}
