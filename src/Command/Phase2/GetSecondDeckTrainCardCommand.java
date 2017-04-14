package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM CLIENT -> SERVER
 * A player can grab a second train card from the deck.
 * If the player hasn't picked up a wild from the face up train cards on their first card pick.
 *
 * Created by natha on 2/27/2017.
 */

public class GetSecondDeckTrainCardCommand implements ICommand {
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
