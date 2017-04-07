package ServerModel.GameModels.CardsModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by raulbr on 3/10/17.
 */

public class DestCardTypes {
    public static DestCardTypes _SINGLETON = new DestCardTypes();
    private HashMap<Integer, ArrayList<String> > _destCards = new HashMap<Integer, ArrayList<String> >();

    // ArrayList index 0 = FROM city
    // ArrayList index 1 = DESTINATION city
    // ArrayList index 2 = Points
    public DestCardTypes() {
        if(_destCards.isEmpty()) {
            _destCards.put(1, new ArrayList<String>
                    (Arrays.asList("Los Angeles", "New York", "21"))
            );
            _destCards.put(2, new ArrayList<String>
                    (Arrays.asList("Duluth", "Houston", "8"))
            );
            _destCards.put(3, new ArrayList<String>
                    (Arrays.asList("Sault St.Marie", "Nashville", "8"))
            );
            _destCards.put(4, new ArrayList<String>
                    (Arrays.asList("New York", "Atlanta", "6"))
            );
            _destCards.put(5, new ArrayList<String>
                    (Arrays.asList("Portland", "Nashville", "17"))
            );
            _destCards.put(6, new ArrayList<String>
                    (Arrays.asList("Vancouver", "Montreal", "20"))
            );
            _destCards.put(7, new ArrayList<String>
                    (Arrays.asList("Duluth", "El Paso", "10"))
            );
            _destCards.put(8, new ArrayList<String>
                    (Arrays.asList("Toronto", "Miami", "10"))
            );
            _destCards.put(9, new ArrayList<String>
                    (Arrays.asList("Portland", "Phoenix", "11"))
            );
            _destCards.put(10, new ArrayList<String>
                    (Arrays.asList("Dallas", "New York", "11"))
            );
            _destCards.put(11, new ArrayList<String>
                    (Arrays.asList("Calgary", "Salt Lake City", "7"))
            );
            _destCards.put(12, new ArrayList<String>
                    (Arrays.asList("Calgary", "Phoenix", "13"))
            );
            _destCards.put(13, new ArrayList<String>
                    (Arrays.asList("Los Angeles", "Miami", "20"))
            );
            _destCards.put(14, new ArrayList<String>
                    (Arrays.asList("Winnipeg", "Little Rock", "11"))
            );
            _destCards.put(15, new ArrayList<String>
                    (Arrays.asList("San Francisco", "Atlanta", "17"))
            );
            _destCards.put(16, new ArrayList<String>
                    (Arrays.asList("Kansas City", "Houston", "5"))
            );
            _destCards.put(17, new ArrayList<String>
                    (Arrays.asList("Los Angeles", "Chicago", "16"))
            );
            _destCards.put(18, new ArrayList<String>
                    (Arrays.asList("Denver", "Pittsburgh", "11"))
            );
            _destCards.put(19, new ArrayList<String>
                    (Arrays.asList("Chicago", "Santa Fe", "9"))
            );
            _destCards.put(20, new ArrayList<String>
                    (Arrays.asList("Vancouver", "Santa Fe", "13"))
            );
            _destCards.put(21, new ArrayList<String>
                    (Arrays.asList("Boston", "Miami", "12"))
            );
            _destCards.put(22, new ArrayList<String>
                    (Arrays.asList("Chicago", "New Orleans", "7"))
            );
            _destCards.put(23, new ArrayList<String>
                    (Arrays.asList("Montreal", "Atlanta", "9"))
            );
            _destCards.put(24, new ArrayList<String>
                    (Arrays.asList("Seattle", "New York", "22"))
            );
            _destCards.put(25, new ArrayList<String>
                    (Arrays.asList("Denver", "El Paso", "4"))
            );
            _destCards.put(26, new ArrayList<String>
                    (Arrays.asList("Helena", "Los Angeles", "8"))
            );
            _destCards.put(27, new ArrayList<String>
                    (Arrays.asList("Winnipeg", "Houston", "12"))
            );
            _destCards.put(28, new ArrayList<String>
                    (Arrays.asList("Montreal", "New Orleans", "13"))
            );
            _destCards.put(29, new ArrayList<String>
                    (Arrays.asList("Sault St.Marie", "Oklahoma City", "9"))
            );
            _destCards.put(30, new ArrayList<String>
                    (Arrays.asList("Seattle", "Los Angeles", "9"))
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
