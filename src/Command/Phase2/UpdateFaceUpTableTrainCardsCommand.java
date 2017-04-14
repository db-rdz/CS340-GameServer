package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import ServerModel.GameModels.CardsModel.TrainCard;
import GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * FROM SERVER -> CLIENT
 * When a face up table train card is picked from a player, the game will get updated
 * to replace that card with a new one.
 *
 * Created by natha on 2/28/2017.
 */

public class UpdateFaceUpTableTrainCardsCommand implements ICommand {

    //Data members
    private List<TrainCard> trainCards; //TODO: make into a list?

    //Constructor
    public UpdateFaceUpTableTrainCardsCommand(List<TrainCard> trainCards) {
        this.trainCards = trainCards;
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

    public List<TrainCard> getTrainCards() {
        return trainCards;
    }
}
