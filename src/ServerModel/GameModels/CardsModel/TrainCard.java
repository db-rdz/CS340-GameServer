package ServerModel.GameModels.CardsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 22/02/17.
 */
public class TrainCard implements iTrainCard {

    //------------------------------------STATIC VARIABLES------------------------------------//
    List<TrainCard> _L_Deck = new ArrayList<>();
    String _cardType = new String();
    @Override
    public void shuffle() {

    }

    public String get_cardType() {
        return _cardType;
    }

    public void set_cardType(String _cardType) {
        this._cardType = _cardType;
    }
}
