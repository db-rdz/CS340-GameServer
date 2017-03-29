package ServerModel.GameModels.RouteModel;

import java.awt.Point;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Route implements iRoute {
	
	public Route(){}
	public Route(int color, int weight, String owner, Pair<String, String> p, Point p1, Point p2, 
			String sColor, String mName, int points){
        _i_Color = color;
        _i_Weight = weight;
        _S_Owner = owner;
        _P_ConnectingCities = p;
        point1 = p1;
        point2 = p2;
        _S_Color = sColor;
        _S_MappingName = mName;
        _i_pointValue = points;
    }

    //---------------------------------------CLASS VARIABLES-------------------------------------//
    private int _i_Color;
    private int _i_Weight = 0;
    private String _S_Color = null;
    private String _S_Owner = null;
    private Pair<String, String> _P_ConnectingCities = null;
    private String _S_MappingName = null;
    private int _i_pointValue;

    private Point point1;
    private Point point2;
 

    //----------------------------------------SETTERS AND GETTERS--------------------------------//
    public int get_Color() { return _i_Color; }
    public void set_Color(int color) { _i_Color = color; }

    public int get_Weight() { return _i_Weight; }
    public void set_Weight(int weight) { _i_Weight = weight; }

    public String get_Owner() { return _S_Owner; }
    public void set_Owner(String owner) { _S_Owner = owner; }

    public Pair<String, String> get_ConnectingCities() { return _P_ConnectingCities; }
    public void set_ConnectingCities(Pair<String, String> pair) { _P_ConnectingCities = pair; }

    public Point getPoint1() { return point1; }
    public void setPoint1(Point point1) { this.point1 = point1; }

    public Point getPoint2() { return point2; }
    public void setPoint2(Point point2) {this.point2 = point2; }

    public String get_S_Color() { return _S_Color; }
    public void set_S_Color(String _S_Color) { this._S_Color = _S_Color; }
    
    public String get_S_MappingName() { return _S_MappingName; }
    public void set_S_MappingName(String _S_MappingName) { this._S_MappingName = _S_MappingName; }
    
    public int get_i_pointValue() { return _i_pointValue; }
    public void set_i_pointValue(int p) { _i_pointValue = p; }

}
