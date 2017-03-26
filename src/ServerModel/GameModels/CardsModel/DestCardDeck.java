package ServerModel.GameModels.CardsModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by benjamin on 25/02/17.
 */
public class DestCardDeck {
    List<DestCard> _L_Deck = new ArrayList<DestCard>();

    public DestCardDeck() {
        fillDeck();
    }

    public void shuffle(){
        Collections.shuffle(_L_Deck);
    }

    public List<DestCard> drawTop3() {
        if(_L_Deck.isEmpty()){
            fillDeck();
        }
        List<DestCard> threeDestCards = new ArrayList<DestCard>();
        for(int i = 0; i < 3; i++) {
            DestCard destCard = _L_Deck.get(i);
            _L_Deck.remove(i);
            threeDestCards.add(destCard);
        }
        return threeDestCards;
    }

    private void fillDeck() {
        for (int i = 1; i < 31; i++) {
            ArrayList<String> destCardInfo = DestCardTypes._SINGLETON.get_destCards().get(i);
            DestCard destCard = new DestCard(destCardInfo.get(0), destCardInfo.get(1));
            destCard.setPoints(Integer.parseInt(destCardInfo.get(2)) );
            _L_Deck.add(destCard);
        }
        shuffle();
    }
    
    public void returnCard(DestCard card)
    {
    	_L_Deck.add(card);
    }
}
