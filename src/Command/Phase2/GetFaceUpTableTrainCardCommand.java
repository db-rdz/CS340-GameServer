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
	private int _i_gameId;
	
	//Ryan: changed TrainCard to cardIndex to match the model
	private int _i_cardIndex;
    private Boolean isWild; //Is the traincard a wild or normal card?
    private String _S_authenticationCode;
    private int _i_turnNumber;

    //Constructors
    public GetFaceUpTableTrainCardCommand(){}
    public GetFaceUpTableTrainCardCommand(int g, int index, Boolean wild, String code, int num) {
    	_i_cardIndex = index;
        isWild = wild;
        _i_gameId = g;
        _S_authenticationCode = code;
        _i_turnNumber = num;
    }
    @Override
    public List<ICommand> execute() throws IServer.GameIsFullException {
        return ServerFacade.SINGLETON.getFaceUpTableTrainCard(_i_gameId, _i_cardIndex, isWild, _S_authenticationCode, _i_turnNumber);
    }

    @JsonIgnore
    @Override
    public String getAuthenticationCode() {
        return _S_authenticationCode;
    }

    @JsonIgnore
    @Override
    public User getUser() {
        return null;
    }

    public int getCardIndex() {
        return _i_cardIndex;
    }

    public Boolean getWild() {
        return isWild;
    }
    
    public int getGameId()
    {
    	return _i_gameId;
    }
    
    public String get_S_authenticationCode()
    {
    	return _S_authenticationCode;
    }


}
