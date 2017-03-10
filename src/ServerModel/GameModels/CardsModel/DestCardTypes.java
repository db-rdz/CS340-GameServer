package ServerModel.GameModels.CardsModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by raulbr on 3/10/17.
 */

public class DestCardTypes {
    public static DestCard _SINGLETON = new DestCard();
    private HashMap<Integer, ArrayList<String> > _destCards = new HashMap<Integer, ArrayList<String> >();

    // ArrayList index 0 = FROM city
    // ArrayList index 1 = DESTINATION city
    // ArrayList index 2 = Points
    public DestCardTypes() {
        if(_destCards.isEmpty()) {
            _destCards.put(1, new ArrayList<String>
                    (Arrays.asList("losAngeles", "NewYork", "21"))
            );
            _destCards.put(2, new ArrayList<String>
                    (Arrays.asList("Duluth", "Houston", "8"))
            );
            _destCards.put(3, new ArrayList<String>
                    (Arrays.asList("SaultSt.Marie", "Nashville", "8"))
            );
            _destCards.put(4, new ArrayList<String>
                    (Arrays.asList("NewYork", "Atlanta", "6"))
            );
            _destCards.put(5, new ArrayList<String>
                    (Arrays.asList("Portland", "Nashville", "17"))
            );
            _destCards.put(6, new ArrayList<String>
                    (Arrays.asList("Vancouver", "Montréal", "20"))
            );
            _destCards.put(7, new ArrayList<String>
                    (Arrays.asList("Duluth", "ElPaso", "10"))
            );
            _destCards.put(8, new ArrayList<String>
                    (Arrays.asList("Toronto", "Miami", "10"))
            );
            _destCards.put(9, new ArrayList<String>
                    (Arrays.asList("Portland", "Phoenix", "11"))
            );
            _destCards.put(10, new ArrayList<String>
                    (Arrays.asList("Dallas", "NewYork", "11"))
            );
            _destCards.put(11, new ArrayList<String>
                    (Arrays.asList("Calgary", "SaltLakeCity", "7"))
            );
            _destCards.put(12, new ArrayList<String>
                    (Arrays.asList("Calgary", "Phoenix", "13"))
            );
            _destCards.put(13, new ArrayList<String>
                    (Arrays.asList("LosAngeles", "Miami", "20"))
            );
            _destCards.put(14, new ArrayList<String>
                    (Arrays.asList("Winnipeg", "LittleRock", "11"))
            );
            _destCards.put(15, new ArrayList<String>
                    (Arrays.asList("SanFrancisco", "Atlanta", "17"))
            );
            _destCards.put(16, new ArrayList<String>
                    (Arrays.asList("KansasCity", "Houston", "5"))
            );
            _destCards.put(17, new ArrayList<String>
                    (Arrays.asList("LosAngeles", "Chicago", "16"))
            );
            _destCards.put(18, new ArrayList<String>
                    (Arrays.asList("Denver", "Pittsburgh", "11"))
            );
            _destCards.put(19, new ArrayList<String>
                    (Arrays.asList("Chicago", "SantaFe", "9"))
            );
            _destCards.put(20, new ArrayList<String>
                    (Arrays.asList("Vancouver", "SantaFe", "13"))
            );
            _destCards.put(21, new ArrayList<String>
                    (Arrays.asList("Boston", "Miami", "12"))
            );
            _destCards.put(22, new ArrayList<String>
                    (Arrays.asList("Chicago", "NewOrleans", "7"))
            );
            _destCards.put(23, new ArrayList<String>
                    (Arrays.asList("Montreal", "Atlanta", "9"))
            );
            _destCards.put(24, new ArrayList<String>
                    (Arrays.asList("Seattle", "NewYork", "22"))
            );
            _destCards.put(25, new ArrayList<String>
                    (Arrays.asList("Denver", "ElPaso", "4"))
            );
            _destCards.put(26, new ArrayList<String>
                    (Arrays.asList("Helena", "LosAngeles", "8"))
            );
            _destCards.put(27, new ArrayList<String>
                    (Arrays.asList("Winnipeg", "Houston", "12"))
            );
            _destCards.put(28, new ArrayList<String>
                    (Arrays.asList("Montreal", "NewOrleans", "13"))
            );
            _destCards.put(29, new ArrayList<String>
                    (Arrays.asList("SaultSt.Marie", "OklahomaCity", "9"))
            );
            _destCards.put(30, new ArrayList<String>
                    (Arrays.asList("Seattle", "LosAngeles", "9"))
            );
        }
    }

    public HashMap<Integer, ArrayList<String>> get_destCards() {
        return _destCards;
    }

    public void set_destCards(HashMap<Integer, ArrayList<String>> _destCards) {
        this._destCards = _destCards;
    }
}
