package ServerModel.GameModels.CardsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 22/02/17.
 */
public class TrainCard implements iTrainCard {
    //------------------------------------STATIC VARIABLES------------------------------------//
    List<TrainCard> _L_Deck = new ArrayList<>();
    private String type;

    public TrainCard(){}
    public TrainCard(String typeStr) {
        type = typeStr;
    }

    @Override
    public void shuffle() {

    }
    
    public List<TrainCard> get_L_Deck() {
		return _L_Deck;
	}
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
