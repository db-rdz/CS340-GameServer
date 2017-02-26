package com.example.ryanblaser.tickettoride.ServerModel.GameModels.CardsModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by benjamin on 22/02/17.
 */
public class TrainCardDeck {

    TrainCardDeck _SINGLETON = new TrainCardDeck();
    List<TrainCard> _L_Deck = new ArrayList<>();

    public void shuffle(){
        Collections.shuffle(_L_Deck);
    }

    public void drawTop(){
        if(!_L_Deck.isEmpty()){
            _L_Deck.get(0);
        }
    }

    public void getFourCards(){

    }



}
