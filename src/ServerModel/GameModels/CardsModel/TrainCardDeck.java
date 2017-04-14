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
    // Ryan : added discard pile to deck
    List<TrainCard> _discardPile = new ArrayList<TrainCard>();

    public TrainCardDeck() {
    	fillDeck();
    	setFiveCards();
    	while (checkFiveCardsForWilds())
    	{
    		replaceFiveCards();
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
    
    public List<TrainCard> getDiscardPile()
    {
    	return _discardPile;
    }

    // Ryan : changed return type so we can retrieve the card we get
    public TrainCard getFromTheFiveCards( int chosenCardIndex ) {
        TrainCard selectedCard = _fiveCards.get(chosenCardIndex);
    	if(_fiveCards.remove(chosenCardIndex) != null) {
            _fiveCards.add(drawTop());
            while (checkFiveCardsForWilds())
            {
            	replaceFiveCards();
            }
            return selectedCard;
        }
        return null;
    }

    private void fillDeck() {
        String[] cardType = {"redcard", "whitecard", "orangecard", "greencard", "bluecard", "blackcard", "yellowcard", "pinkcard", "rainbowcard"};
        for (String color : cardType) {
            for (int i = 0; i < 12; i++){
                TrainCard trainCard = new TrainCard(color);
                _L_Deck.add(trainCard);
            }
        }
        shuffle();
    }
    
    /*
     * Ryan
     * initializes/sets 5 new Train Cards
     */
    private void setFiveCards()
    {
    	for (int i = 0; i < 5; i++)
    	{
    		_fiveCards.add(drawTop());
    	}
    }
    
    /*
     * Ryan
     * checks to see if there are more than 3 wilds in the 5 cards
     */
    private Boolean checkFiveCardsForWilds()
    {
    	int numOfWilds = 0;
    	for (TrainCard card : _fiveCards)
    	{
    		if (card.getType() == "rainbowcard")
    		{
    			numOfWilds++;
    		}
    	}
    	if (numOfWilds >= 3)
    	{
    		return true; // needs to be replaced
    	}
    	return false; // doesn't need to be replaced
    }
    
    /*
     * Ryan
     * replaces the five cards if there are more than 3 wilds
     */
    private void replaceFiveCards()
    {
    	_discardPile.addAll(_fiveCards);
    	_fiveCards.clear();
    	setFiveCards();
    }
}
