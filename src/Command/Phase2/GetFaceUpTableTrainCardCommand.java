package Command.Phase2;

import Client.User;
import Command.ICommand;
import Server.IServer;
import Server.ServerFacade;
import GameModels.Game;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ServerModel.GameModels.CardsModel.TrainCard;

import java.util.List;

/**
 * FROM CLIENT -> SERVER
 * This class will allow one player to grab one train card from the face up train cards on the table.
 * There's another command to grab a second card either from the deck or face up train cards. IF the player
 * doesn't choose a wild card first.
 *
 * Created by natha on 2/27/2017.
 */

public class GetFaceUpTableTrainCardCommand implements ICommand {
    //Data member
    private int gameId;

    //Ryan: changed TrainCard to cardIndex to match the model
    private int cardIndex;
    private Boolean isWild; //Is the traincard a wild or normal card?
    private String authenticationCode;
    private int turnNumber;

    //Constructors
    public GetFaceUpTableTrainCardCommand() {
    }

    public GetFaceUpTableTrainCardCommand(int g, int index, Boolean wild, String code, int num) {
        cardIndex = index;
        isWild = wild;
        gameId = g;
        authenticationCode = code;
        turnNumber = num;
    }

    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        return ServerFacade.SINGLETON.getFaceUpTableTrainCard(gameId, cardIndex, isWild, authenticationCode, turnNumber);
    }

    @Override
    public String getAuthenticationCode() {
        return authenticationCode;
    }

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
    }

    public int getGameId() {
        return gameId;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public Boolean getIsWild() {
        return isWild;
    }

    public int getTurnNumber() {
        return turnNumber;
    }
}