package ServerModel.GameModels.CardsModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by benjamin on 22/02/17.
 */
public class TrainCardDeck {

    List<TrainCard> _L_Deck = new ArrayList<TrainCard>();
    List<TrainCard> _fiveCards = new ArrayList<TrainCard>();

    public TrainCardDeck() {
        fillDeck();
    }

    public void shuffle(){
        Collections.shuffle(_L_Deck);
    }

    public void drawTop(){
        if(!_L_Deck.isEmpty()){
            _L_Deck.get(0);
        }
    }

    public List<TrainCard> getFiveCards(){
        //Make sure the list always have 5 cards and return it.
        return _fiveCards;
    }

    private void fillDeck() {
        for (int i = 0; i < 100; i++) {
            TrainCard trainCard = new TrainCard();
            _L_Deck.add(trainCard);
        }
    }

}
