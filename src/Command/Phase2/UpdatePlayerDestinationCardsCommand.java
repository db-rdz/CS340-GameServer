package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import ServerModel.GameModels.CardsModel.DestCard;
import GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * Updates when a player gets new destination cards.
 * This updated number also tells other players how many destination cards they have currently.
 *
 * Created by natha on 2/28/2017.
 */

public class UpdatePlayerDestinationCardsCommand implements ICommand {

    //Data members
    private List<DestCard> destCards;

    //Constructor
    public UpdatePlayerDestinationCardsCommand(List<DestCard> destCards) {
        this.destCards = destCards;
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


    public List<DestCard> getDestCards() {
        return destCards;
    }
}
