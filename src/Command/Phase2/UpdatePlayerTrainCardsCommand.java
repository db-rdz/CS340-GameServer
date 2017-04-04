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
 * Adds the selected train card to the player's hand. So the player should have a visual que of the new card on his screen.
 * This command is called whenever a GetCardCommand is called.
 *
 * Created by natha on 2/27/2017.
 */

public class UpdatePlayerTrainCardsCommand implements ICommand {

    //Data members
    private TrainCard trainCard; //TODO: Should be a List? Depends on Client implementation

    //Constructor
    public UpdatePlayerTrainCardsCommand(TrainCard trainCard) {
        this.trainCard = trainCard;
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

    public TrainCard getTrainCard() {
        return trainCard;
    }
}
