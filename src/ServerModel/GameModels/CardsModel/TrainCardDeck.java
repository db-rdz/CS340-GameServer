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
    	for (int i = 0; i < 5; i++)
    	{
    		_fiveCards.add(drawTop());
    	}
    }

    public void shuffle(){
        Collections.shuffle(_L_Deck);
    }

    public TrainCard drawTop(){
        if(_L_Deck.isEmpty()){
            fillDeck();
        }
        TrainCard topCard = _L_Deck.get(0);
        _L_Deck.remove(topCard);
        return topCard;
    }

    public List<TrainCard> getFiveCards(){
        return _fiveCards;
    }

    // Ryan : changed return type so we can retrieve the card we get
    public TrainCard getFromTheFiveCards( int chosenCardIndex ) {
        TrainCard selectedCard = _fiveCards.get(chosenCardIndex);
    	if(_fiveCards.remove(chosenCardIndex) != null) {
            _fiveCards.add(drawTop());
            return selectedCard;
        }
        return null;
    }

    private void fillDeck() {
        String[] cardType = {"RED", "WHITE", "ORANGE", "GREEN", "BLUE", "PURPLE", "YELLOW", "PINK", "RAINBOW"};
        for (String color : cardType) {
            for (int i = 0; i < 12; i++){
                TrainCard trainCard = new TrainCard(color);
                _L_Deck.add(trainCard);
            }
        }
        shuffle();
    }
}
