package ServerModel.GameModels.CardsModel;
import org.apache.commons.lang3.tuple.Pair;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by benjamin on 25/02/17.
 */
public class DestCard{
	 // should map the id of the map used on DestCardTypes
    private int _cardType;

    @JsonProperty("_destination")
    private Pair<String, String> _destination;
    private int points;
    private Boolean _isCompleted;

    public DestCard(){}
    public DestCard(String city1, String city2){
        _destination = Pair.of(city1, city2);
        _isCompleted = false;
    }

    public int get_cardType() {
        return _cardType;
    }

    public Pair<String, String> get_destination() {
        return _destination;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    public Boolean get_isCompleted() {
        return _isCompleted;
    }
    
    public void set_isCompleted(Boolean _isCompleted) {
		this._isCompleted = _isCompleted;
	}
}
