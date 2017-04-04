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
 * This class updates the car amount for a player.
 * Decreases the car count whenever a ClaimRouteCommand is called.
 * So this class will contain the amount of cars USED to claim a route.
 * The ClientFacade will then subtract the current player's cars by the amount given in this class.
 *
 * Created by natha on 2/27/2017.
 */

public class UpdateCarCountAndHandCommand implements ICommand {

    //Data members
    private int int_cars_used;
    private List<TrainCard> cardsUsedToClaimRoute;
    
    public UpdateCarCountAndHandCommand(){}

    //Constructor
    public UpdateCarCountAndHandCommand(int int_used_cars, List<TrainCard> cardsUsedToClaimRoute) {
        this.int_cars_used = int_used_cars;
        this.cardsUsedToClaimRoute = cardsUsedToClaimRoute;
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



    public int getInt_cars_used() {
        return int_cars_used;
    }
    
    public List<TrainCard> getCardsUsedToClaimRoute()
    {
    	return cardsUsedToClaimRoute;
    }
}
