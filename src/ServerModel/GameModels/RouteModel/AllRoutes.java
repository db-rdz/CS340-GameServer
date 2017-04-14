package ServerModel.GameModels.RouteModel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

public class AllRoutes {
	
	final int ONE = 1;
	final int TWO = 2;
	final int THREE = 4;
	final int FOUR = 7;
	final int FIVE = 10;
	final int SIX = 15;
	
	public AllRoutes() {
		initAllRoutes();
	}

    //---------------------------------------CLASS VARIABLES------------------------------------//
    private Boolean _B_areRoutesSet = false;
    private List<Route> _L_allRoutes = new ArrayList<>();
    private List<Route> _L_availableRoutes = new ArrayList<>();
    private Map<String, Route> _M_nameToRoute = new HashMap<>();
    
    //---------------------------------------CLASS FUCNTIONS-------------------------------------//
    public List<Route> get_allRoutes(){
        return new ArrayList<Route>(_M_nameToRoute.values()); }

    public Map<String, Route> get_RoutesMap() { return _M_nameToRoute; }
    public void set_RoutesMap(Map<String, Route> nameToRoute) { _M_nameToRoute = nameToRoute; }

    public Boolean are_RoutesSet(){
        return _B_areRoutesSet;
    }


    public void initAllRoutes() {
        //-------------------------RED ROUTES---------------------//
        int color = -65536; //android Color.RED
        int weight = 5;
        Pair<String, String> p = Pair.of("Helena", "Omaha");
        String mappingName = "Helena-Omaha";
        Point p1 = new Point(823, 505);
        Point p2 = new Point(1118, 622);

        Route r = new Route(color, weight, null, p, p1, p2, "RED", mappingName, FIVE);
        _M_nameToRoute.put(mappingName, r);


        //-------------------SaltLakeCity-Denver------------------//
        weight = 3;
        p = Pair.of("Salt Lake City", "Denver");
        mappingName = "SaltLakeCity-Denver-1";
        p1 = new Point(678, 700);
        p2 = new Point(861, 746);

        r = new Route(color, weight, null, p, p1, p2, "RED", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);

        //-------------------Denver-OklahomaCity------------------//
        weight = 4;
        p = Pair.of("Denver", "Oklahoma City");
        mappingName = "Denver-OklahomaCity";
        p1 = new Point(901, 803);
        p2 = new Point(1136, 894);

        r = new Route(color, weight, null, p, p1, p2, "RED", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);

        //-------------------ElPaso-Dallas------------------//
        weight = 4;
        p = Pair.of("El Paso", "Dallas");
        mappingName = "ElPaso-Dallas";
        p1 = new Point(919, 1109);
        p2 = new Point(1171, 1072);

        r = new Route(color, weight, null, p, p1, p2, "RED", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);


        //-------------------Duluth-Chicago------------------//
        weight = 3;
        p = Pair.of("Duluth", "Chicago");
        mappingName = "Duluth-Chicago";
        p1 = new Point(1240, 486);
        p2 = new Point(1415, 554);

        r = new Route(color, weight, null, p, p1, p2, "RED", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);


        //-------------------NewOrleans-Miami------------------//
        weight = 6;
        p = Pair.of("New Orleans", "Miami");
        mappingName = "NewOrleans-Miami";
        p1 = new Point(1501, 1106);
        p2 = new Point(1840, 1187);

        r = new Route(color, weight, null, p, p1, p2, "RED", mappingName, SIX);
        _M_nameToRoute.put(mappingName, r);

        //-------------------NewYork-Boston------------------//
        weight = 6;
        p = Pair.of("New York", "Boston");
        mappingName = "NewYork-Boston-2";
        p1 = new Point(1972, 386);
        p2 = new Point(1874, 466);

        r = new Route(color, weight, null, p, p1, p2, "RED", mappingName, SIX);
        _M_nameToRoute.put(mappingName, r);


        //-------------------WHITE ROUTES------------------//
        color = -1; // android Color.WHITE
        weight = 6;
        p = Pair.of("Calgary", "Winnipeg");
        mappingName = "Calgary-Winnipeg";
        p1 = new Point(617, 229);
        p2 = new Point(993, 241);

        r = new Route(color, weight, null, p, p1, p2, "WHITE", mappingName, SIX);
        _M_nameToRoute.put(mappingName, r);

        //-------------------SanFrancisco-SaltLakeCity------------------//
        weight = 5;
        p = Pair.of("San Francisco", "Salt Lake City");
        mappingName = "SanFrancisco-SaltLakeCity-2";
        p1 = new Point(321, 827);
        p2 = new Point(625, 732);

        r = new Route(color, weight, null, p, p1, p2, "WHITE", mappingName, FIVE);
        _M_nameToRoute.put(mappingName, r);


        //-------------------Phoenix-Denver------------------//
        weight = 5;
        p = Pair.of("Phoenix", "Denver");
        mappingName = "Phoenix-Denver";
        p1 = new Point(651, 1019);
        p2 = new Point(861, 786);

        r = new Route(color, weight, null, p, p1, p2, "WHITE", mappingName, FIVE);
        _M_nameToRoute.put(mappingName, r);

        //-------------------LittleRock-Nashville------------------//
        weight = 3;
        p = Pair.of("Little Rock", "Nashville");
        mappingName = "LittleRock-Nashville";
        p1 = new Point(1361, 902);
        p2 = new Point(1531, 831);

        r = new Route(color, weight, null, p, p1, p2, "WHITE", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);

        //-------------------SaintLouis-Chicago------------------//
        weight = 2;
        p = Pair.of("Saint Louis", "Chicago");
        mappingName = "SaintLouis-Chicago-2";
        p1 = new Point(1445, 616);
        p2 = new Point(1383, 722);

        r = new Route(color, weight, null, p, p1, p2, "WHITE", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-------------------Chicago-Toronto------------------//
        weight = 4;
        p = Pair.of("Chicago", "Toronto");
        mappingName = "Chicago-Toronto";
        p1 = new Point(1443, 552);
        p2 = new Point(1651, 412);

        r = new Route(color, weight, null, p, p1, p2, "WHITE", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);

        //-------------------Pittsburgh-NewYork------------------//
        weight = 2;
        p = Pair.of("Pittsburgh", "New York");
        mappingName = "Pittsburgh-NewYork-1";
        p1 = new Point(1712, 535);
        p2 = new Point(1816, 472);

        r = new Route(color, weight, null, p, p1, p2, "WHITE", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-------------------ORANGE ROUTES------------------//
        color = 0xFFA500; //android Color.rgb(255, 165,0);
        weight = 5;
        p = Pair.of("San Francisco", "Salt Lake City");
        mappingName = "SanFrancisco-SaltLakeCity-1";
        p1 = new Point(315, 808);
        p2 = new Point(618, 711);

        r = new Route(color, weight, null, p, p1, p2, "ORANGE", mappingName, FIVE);
        _M_nameToRoute.put(mappingName, r);

        //-------------------LasVegas-SaltLakeCity------------------//
        weight = 3;
        p = Pair.of("Las Vegas", "Salt Lake City");
        mappingName = "LasVegas-SaltLakeCity";
        p1 = new Point(538, 879);
        p2 = new Point(645, 738);

        r = new Route(color, weight, null, p, p1, p2, "ORANGE", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);

        //-------------------Denver-KansasCity------------------//
        weight = 4;
        p = Pair.of("Denver", "Kansas City");
        mappingName = "Denver-KansasCity";
        p1 = new Point(927, 793);
        p2 = new Point(1178, 751);

        r = new Route(color, weight, null, p, p1, p2, "ORANGE", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);


        //-------------------NewOrleans-Atlanta------------------//
        weight = 4;
        p = Pair.of("New Orleans", "Atlanta");
        mappingName = "NewOrleans-Atlanta-2";
        p1 = new Point(1477, 1102);
        p2 = new Point(1630, 900);

        r = new Route(color, weight, null, p, p1, p2, "ORANGE", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);


        //-------------------Chicago-Pittsburgh------------------//
        weight = 3;
        p = Pair.of("Chicago", "Pittsburgh");
        mappingName = "Chicago-Pittsburgh-1";
        p1 = new Point(1466, 557);
        p2 = new Point(1659, 531);

        r = new Route(color, weight, null, p, p1, p2, "ORANGE", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);


        //-------------------NewYork-Washington------------------//
        weight = 2;
        p = Pair.of("New York", "Washington");
        mappingName = "NewYork-Washington-1";
        p1 = new Point(1851, 497);
        p2 = new Point(1858, 620);

        r = new Route(color, weight, null, p, p1, p2, "ORANGE", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-------------------GREEN ROUTES------------------//
        color = -16711936;//android Color.GREEN;
        weight = 5;
        p = Pair.of("San Francisco", "Portland");
        mappingName = "SanFrancisco-Portland-1";
        p1 = new Point(290, 483);
        p2 = new Point(264, 803);

        r = new Route(color, weight, null, p, p1, p2, "GREEN", mappingName, FIVE);
        _M_nameToRoute.put(mappingName, r);


        //-------------------Helena-Denver------------------//
        weight = 4;
        p = Pair.of("Helena", "Denver");
        mappingName = "Helena-Denver";
        p1 = new Point(791, 500);
        p2 = new Point(882, 738);

        r = new Route(color, weight, null, p, p1, p2, "GREEN", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);

        //-------------------ElPaso-Houston------------------//
        weight = 6;
        p = Pair.of("ElPaso", "Houston");
        mappingName = "ElPaso-Houston";
        p1 = new Point(880, 1122);
        p2 = new Point(1259, 1153);

        r = new Route(color, weight, null, p, p1, p2, "GREEN", mappingName, SIX);
        _M_nameToRoute.put(mappingName, r);

        //-------------------LittleRock-NewOrleans------------------//
        weight = 3;
        p = Pair.of("Little Rock", "New Orleans");
        mappingName = "LittleRock-NewOrleans";
        p1 = new Point(1351, 924);
        p2 = new Point(1434, 1093);

        r = new Route(color, weight, null, p, p1, p2, "GREEN", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);


        //-------------------SaintLouis-Chicago------------------//
        weight = 2;
        p = Pair.of("SaintLouis", "Chicago");
        mappingName = "SaintLouis-Chicago-1";
        p1 = new Point(1426, 605);
        p2 = new Point(1364, 711);

        r = new Route(color, weight, null, p, p1, p2, "GREEN", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-------------------SaintLouis-Pittsburgh------------------//
        weight = 5;
        p = Pair.of("SaintLouis", "Pittsburgh");
        mappingName = "SaintLouis-Pittsburgh";
        p1 = new Point(1393, 736);
        p2 = new Point(1670, 579);

        r = new Route(color, weight, null, p, p1, p2, "GREEN", mappingName, FIVE);
        _M_nameToRoute.put(mappingName, r);

        //-------------------Pittsburgh-NewYork------------------//
        weight = 2;
        p = Pair.of("Pittsburgh", "New York");
        mappingName = "Pittsburgh-NewYork-2";
        p1 = new Point(1723, 554);
        p2 = new Point(1827, 491);

        r = new Route(color, weight, null, p, p1, p2, "GREEN", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-------------------BLUE ROUTES------------------//
        color = -16776961; //android Color.BLUE;
        weight = 6;
        p = Pair.of("Portland", "Salt Lake City");
        mappingName = "Portland-SaltLakeCity";
        p1 = new Point(330, 468);
        p2 = new Point(635, 681);

        r = new Route(color, weight, null, p, p1, p2, "BLUE", mappingName, SIX);
        _M_nameToRoute.put(mappingName, r);


        //-------------------Helena-Winnipeg------------------//
        weight = 4;
        p = Pair.of("Helena", "Winnipeg");
        mappingName = "Helena-Winnipeg";
        p1 = new Point(983, 275);
        p2 = new Point(803, 457);

        r = new Route(color, weight, null, p, p1, p2, "BLUE", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);

        //-------------------SantaFe-OklahomaCity------------------//
        weight = 3;
        p = Pair.of("Santa Fe", "Oklahoma City");
        mappingName = "SantaFe-OklahomaCity";
        p1 = new Point(910, 934);
        p2 = new Point(1096, 916);

        r = new Route(color, weight, null, p, p1, p2, "BLUE", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);


        //-------------------Omaha-Chicago------------------//
        weight = 4;
        p = Pair.of("Omaha", "Chicago");
        mappingName = "Omaha-Chicago";
        p1 = new Point(1190, 640);
        p2 = new Point(1420, 582);

        r = new Route(color, weight, null, p, p1, p2, "BLUE", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);


        //-------------------KansasCity-SaintLouis------------------//
        weight = 2;
        p = Pair.of("Kansas City", "Saint Louis");
        mappingName = "KansasCity-SaintLouis-2";
        p1 = new Point(1226, 721);
        p2 = new Point(1348, 718);

        r = new Route(color, weight, null, p, p1, p2, "BLUE", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-------------------Atlanta-Miami------------------//
        weight = 5;
        p = Pair.of("Atlanta", "Miami");
        mappingName = "Atlanta-Miami";
        p1 = new Point(1653, 913);
        p2 = new Point(1850, 1164);

        r = new Route(color, weight, null, p, p1, p2, "BLUE", mappingName, FIVE);
        _M_nameToRoute.put(mappingName, r);


        //-------------------Montreal-NewYork------------------//
        weight = 3;
        p = Pair.of("Montreal", "New York");
        mappingName = "Montreal-NewYork";
        p1 = new Point(1813, 262);
        p2 = new Point(1843, 446);

        r = new Route(color, weight, null, p, p1, p2, "BLUE", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);


        //-------------------PURPLE(BLACK) ROUTES------------------//
        color = -16777216;//android Color.BLACK;
        weight = 6;
        p = Pair.of("Los Angeles", "El Paso");
        mappingName = "LosAngeles-ElPaso";
        p1 = new Point(446, 1044);
        p2 = new Point(821, 1114);

        r = new Route(color, weight, null, p, p1, p2, "BLACK", mappingName, SIX);
        _M_nameToRoute.put(mappingName, r);

        //-------------------Helena-Duluth------------------//
        weight = 6;
        p = Pair.of("Helena", "Duluth");
        mappingName = "Helena-Duluth";
        p1 = new Point(808, 477);
        p2 = new Point(1192, 477);

        r = new Route(color, weight, null, p, p1, p2, "BLACK", mappingName, SIX);
        _M_nameToRoute.put(mappingName, r);

        //-------------------Winnipeg-Duluth------------------//
        weight = 4;
        p = Pair.of("Winnipeg", "Duluth");
        mappingName = "Winnipeg-Duluth";
        p1 = new Point(1027, 272);
        p2 = new Point(1207, 454);

        r = new Route(color, weight, null, p, p1, p2, "BLACK", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);


        //-------------------SaultSt.Marie-Montreal------------------//
        weight = 5;
        p = Pair.of("Sault St. Marie", "Montreal");
        mappingName = "SaultSt.Marie-Montreal";
        p1 = new Point(1779, 220);
        p2 = new Point(1477, 329);

        r = new Route(color, weight, null, p, p1, p2, "BLACK", mappingName, FIVE);
        _M_nameToRoute.put(mappingName, r);


        //--------------------Chicago-Pittsburgh------------------//
        weight = 3;
        p = Pair.of("Chicago", "Pittsburgh");
        mappingName = "Chicago-Pittsburgh-2";
        p1 = new Point(1470, 579);
        p2 = new Point(1660, 555);

        r = new Route(color, weight, null, p, p1, p2, "BLACK", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);


        //-------------------Nashville-Raleigh------------------//
        weight = 3;
        p = Pair.of("Nashville", "Raleigh");
        mappingName = "Nashville-Raleigh";
        p1 = new Point(1559, 789);
        p2 = new Point(1739, 746);

        r = new Route(color, weight, null, p, p1, p2, "BLACK", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);


        //-------------------NewYork-Washington------------------//
        weight = 2;
        p = Pair.of("New York", "Washington");
        mappingName = new String("NewYork-Washington-2");
        p1 = new Point(1873, 496);
        p2 = new Point(1880, 619);

        r = new Route(color, weight, null, p, p1, p2, "BLACK", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-------------------YELLOW ROUTES------------------//
        color = -256; //android Color.YELLOW;
        weight = 3;
        p = Pair.of("San Francisco", "Los Angeles");
        mappingName = "SanFrancisco-LosAngeles-1";
        p1 = new Point(276, 857);
        p2 = new Point(389, 1016);

        r = new Route(color, weight, null, p, p1, p2, "YELLOW", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);

        //-------------------Seattle-Helena------------------//
        weight = 6;
        p = Pair.of("Seattle", "Helena");
        mappingName = "Seattle-Helena";
        p1 = new Point(372, 390);
        p2 = new Point(740, 474);

        r = new Route(color, weight, null, p, p1, p2, "YELLOW", mappingName, SIX);
        _M_nameToRoute.put(mappingName, r);

        //-------------------SaltLakeCity-Denver------------------//
        weight = 3;
        p = Pair.of("Salt Lake City", "Denver");
        mappingName = "SaltLakeCity-Denver-2";
        p1 = new Point(673, 721);
        p2 = new Point(856, 765);

        r = new Route(color, weight, null, p, p1, p2, "YELLOW", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);

        //-------------------ElPaso-OklahomaCity------------------//
        weight = 5;
        p = Pair.of("El Paso", "Oklahoma City");
        mappingName = "ElPaso-OklahomaCity";
        p1 = new Point(888, 1093);
        p2 = new Point(1150, 915);

        r = new Route(color, weight, null, p, p1, p2, "YELLOW", mappingName, FIVE);
        _M_nameToRoute.put(mappingName, r);


        //-------------------NewOrleans-Atlanta------------------//
        weight = 4;
        p = Pair.of("New Orleans", "Atlanta");
        mappingName = "NewOrleans-Atlanta-1";
        p1 = new Point(1460, 1086);
        p2 = new Point(1614, 882);

        r = new Route(color, weight, null, p, p1, p2, "YELLOW", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);


        //-------------------Nashville-Pittsburgh------------------//
        weight = 4;
        p = Pair.of("Nashville", "Pittsburgh");
        mappingName = "Nashville-Pittsburgh";
        p1 = new Point(1535, 793);
        p2 = new Point(1685, 596);

        r = new Route(color, weight, null, p, p1, p2, "YELLOW", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);


        //-------------------NewYork-Boston------------------//
        weight = 2;
        p = Pair.of("New York", "Boston");
        mappingName = "NewYork-Boston-1";
        p1 = new Point(1957, 369);
        p2 = new Point(1867, 453);

        r = new Route(color, weight, null, p, p1, p2, "YELLOW", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);

        //-------------------PINK ROUTES------------------//
        //----------------SanFrancisco-Portland-2-----------//
        color = 0xFFC0CB; //android Color.rgb(255,192,203);
        weight = 5;
        p = Pair.of("San Francisco", "Portland");
        mappingName = "SanFrancisco-Portland-2";
        p1 = new Point(311, 483);
        p2 = new Point(286, 803);

        r = new Route(color, weight, null, p, p1, p2, "PINK", mappingName, FIVE);
        _M_nameToRoute.put(mappingName, r);

        //-------------SanFrancisco-LosAngeles-2-----------//
        weight = 3;
        p = Pair.of("San Francisco", "Los Angeles");
        mappingName = "SanFrancisco-LosAngeles-2";
        p1 = new Point(296, 848);
        p2 = new Point(404, 1002);

        r = new Route(color, weight, null, p, p1, p2, "PINK", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);

        //-------------SaltLakeCity-Helena-------------//
        weight = 3;
        p = Pair.of("Salt Lake City", "Helena");
        mappingName = "SaltLakeCity-Helena";
        p1 = new Point(666, 679);
        p2 = new Point(754, 514);

        r = new Route(color, weight, null, p, p1, p2, "PINK", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);


        //-------------Denver-Omaha-------------//
        weight = 4;
        p = Pair.of("Denver", "Omaha");
        mappingName = "Denver-Omaha";
        p1 = new Point(906, 748);
        p2 = new Point(1141, 648);

        r = new Route(color, weight, null, p, p1, p2, "PINK", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);


        //-------------KansasCity-SaintLouis-2-------------//
        weight = 2;
        p = Pair.of("KansasCity", "SaintLouis");
        mappingName = "KansasCity-SaintLouis-2";
        p1 = new Point(1226, 743);
        p2 = new Point(1348, 740);

        r = new Route(color, weight, null, p, p1, p2, "PINK", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-------------Duluth-Toronto-------------//
        weight = 6;
        p = Pair.of("Duluth", "Toronto");
        mappingName = "Duluth-Toronto";
        p1 = new Point(1256, 460);
        p2 = new Point(1634, 399);

        r = new Route(color, weight, null, p, p1, p2, "PINK", mappingName, SIX);
        _M_nameToRoute.put(mappingName, r);


        //-------------Charleston-Miami-------------//
        weight = 4;
        p = Pair.of("Charleston", "Miami");
        mappingName = "Charleston-Miami";
        p1 = new Point(1807, 911);
        p2 = new Point(1873, 1155);

        r = new Route(color, weight, null, p, p1, p2, "PINK", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);



        //-------------------GRAY ROUTES------------------//

        //-----------------Portland-Seattle-1---------------//
        color = -7829368; //android Color.GRAY;
        weight = 1;
        p = Pair.of("Portland", "Seattle");
        mappingName = "Portland-Seattle-1";
        p1 = new Point(324, 387);
        p2 = new Point(305, 434);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, ONE);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Portland-Seattle-2---------------//
        weight = 1;
        p = Pair.of("Portland", "Seattle");
        mappingName = "Portland-Seattle-2";
        p1 = new Point(344, 395);
        p2 = new Point(327, 441);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, ONE);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Seattle-Vancouver-1---------------//
        weight = 1;
        p = Pair.of("Seattle", "Vancouver");
        mappingName = "Seattle-Vancouver-1";
        p1 = new Point(335, 299);
        p2 = new Point(335, 340);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, ONE);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Seattle-Vancouver-2---------------//
        weight = 1;
        p = Pair.of("Seattle", "Vancouver");
        mappingName = "Seattle-Vancouver-2";
        p1 = new Point(357, 299);
        p2 = new Point(357, 340);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, ONE);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Vancouver-Calgary----------------//
        weight = 3;
        p = Pair.of("Vancouver", "Calgary");
        mappingName = "Vancouver-Calgary";
        p1 = new Point(385, 257);
        p2 = new Point(555, 238);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Seattle-Calgary----------------//
        weight = 4;
        p = Pair.of("Seattle", "Calgary");
        mappingName = "Seattle-Calgary";
        p1 = new Point(376, 363);
        p2 = new Point(582, 260);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Calgary-Helena----------------//
        weight = 4;
        p = Pair.of("Calgary", "Helena");
        mappingName = "Calgary-Helena";
        p1 = new Point(770, 459);
        p2 = new Point(609, 258);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, FOUR);
        _M_nameToRoute.put(mappingName, r);


        //-----------------LosAngeles-LasVegas----------------//
        weight = 2;
        p = Pair.of("Los Angeles", "Las Vegas");
        mappingName = "LosAngeles-LasVegas";
        p1 = new Point(431, 990);
        p2 = new Point(505, 896);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-----------------LosAngeles-Phoenix----------------//
        weight = 3;
        p = Pair.of("Los Angeles", "Phoenix");
        mappingName = "LosAngeles-Phoenix";
        p1 = new Point(441, 1013);
        p2 = new Point(628, 1020);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Phoenix-ElPaso----------------//
        weight = 3;
        p = Pair.of("Phoenix", "El Paso");
        mappingName = "Phoenix-ElPaso";
        p1 = new Point(662, 1040);
        p2 = new Point(840, 1098);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Phoenix-SantaFe----------------//
        weight = 3;
        p = Pair.of("Phoenix", "Santa Fe");
        mappingName = "Phoenix-SantaFe";
        p1 = new Point(681, 1021);
        p2 = new Point(854, 952);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);

        //-----------------ElPaso-SantaFe----------------//
        weight = 2;
        p = Pair.of("El Paso", "Santa Fe");
        mappingName = "ElPaso-SantaFe";
        p1 = new Point(876, 960);
        p2 = new Point(868, 1081);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------SantaFe-Denver----------------//
        weight = 2;
        p = Pair.of("Santa Fe", "Denver");
        mappingName = "SantaFe-Denver";
        p1 = new Point(884, 794);
        p2 = new Point(876, 916);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Winnipeg-SaultSt.Marie----------------//
        weight = 6;
        p = Pair.of("Winnipeg", "Sault St. Marie");
        mappingName = "Winnipeg-SaultSt.Marie";
        p1 = new Point(1054, 255);
        p2 = new Point(1430, 333);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, SIX);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Duluth-SaultSt.Marie----------------//
        weight = 3;
        p = Pair.of("Duluth", "Sault St. Marie");
        mappingName = "Duluth-SaultSt.Marie";
        p1 = new Point(1260, 428);
        p2 = new Point(1434, 359);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);


        //-----------------SaultSt.Marie-Toronto----------------//
        weight = 2;
        p = Pair.of("Sault St. Marie", "Toronto");
        mappingName = "SaultSt.Marie-Toronto";
        p1 = new Point(1617, 373);
        p2 = new Point(1497, 350);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Omaha-Duluth-1----------------//
        weight = 2;
        p = Pair.of("Omaha", "Duluth");
        mappingName = "Omaha-Duluth-1";
        p1 = new Point(1166, 615);
        p2 = new Point(1200, 498);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Omaha-Duluth-2----------------//
        weight = 2;
        p = Pair.of("Omaha", "Duluth");
        mappingName = "Omaha-Duluth-2";
        p1 = new Point(1187, 620);
        p2 = new Point(1221, 504);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Omaha-KansasCity-1----------------//
        weight = 2;
        p = Pair.of("Omaha", "Kansas City");
        mappingName = "Omaha-KansasCity-1";
        p1 = new Point(1190, 714);
        p2 = new Point(1168, 662);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Omaha-KansasCity-2----------------//
        weight = 2;
        p = Pair.of("Omaha", "Kansas City");
        mappingName = "Omaha-KansasCity-2";
        p1 = new Point(1210, 706);
        p2 = new Point(1188, 653);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Oklahoma-KansasCity-1----------------//
        weight = 2;
        p = Pair.of("Oklahoma", "Kansas City");
        mappingName = "Oklahoma-KansasCity-1";
        p1 = new Point(1164, 877);
        p2 = new Point(1195, 761);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Oklahoma-KansasCity-2----------------//
        weight = 2;
        p = Pair.of("Oklahoma", "Kansas City");
        mappingName = "Oklahoma-KansasCity-2";
        p1 = new Point(1186, 884);
        p2 = new Point(1215, 767);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Oklahoma-Dallas-1----------------//
        weight = 2;
        p = Pair.of("Oklahoma", "Dallas");
        mappingName = "Oklahoma-Dallas-1";
        p1 = new Point(1192, 1044);
        p2 = new Point(1172, 924);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Oklahoma-Dallas-2----------------//
        weight = 2;
        p = Pair.of("Oklahoma", "Kansas City");
        mappingName = "Oklahoma-Dallas-2";
        p1 = new Point(1214, 1040);
        p2 = new Point(1194, 920);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Oklahoma-LittleRock----------------//
        weight = 2;
        p = Pair.of("Oklahoma", "Little Rock");
        mappingName = "Oklahoma-LittleRock";
        p1 = new Point(1195, 903);
        p2 = new Point(1317, 899);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Dallas-Houston-1---------------//
        weight = 1;
        p = Pair.of("Dallas", "Houston");
        mappingName = "Dallas-Houston-1";
        p1 = new Point(1252, 1127);
        p2 = new Point(1218, 1082);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, ONE);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Dallas-Houston-2---------------//
        weight = 1;
        p = Pair.of("Dallas", "Houston");
        mappingName = "Dallas-Houston-2";
        p1 = new Point(1270, 1114);
        p2 = new Point(1236, 1069);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, ONE);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Dallas-LittleRock---------------//
        weight = 2;
        p = Pair.of("Dallas", "Little Rock");
        mappingName = "Dallas-LittleRock";
        p1 = new Point(1241, 1024);
        p2 = new Point(1312, 926);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Houston-NewOrleans---------------//
        weight = 2;
        p = Pair.of("Houston", "New Orleans");
        mappingName = "Houston-NewOrleans";
        p1 = new Point(1307, 1135);
        p2 = new Point(1428, 1115);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-----------------LittleRock-SaintLouis---------------//
        weight = 2;
        p = Pair.of("Little Rock", "Saint Louis");
        mappingName = "LittleRock-SaintLouis";
        p1 = new Point(1365, 764);
        p2 = new Point(1346, 885);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------SaintLouis-Nashville---------------//
        weight = 2;
        p = Pair.of("Saint Louis", "Nashville");
        mappingName = "SaintLouis-Nashville";
        p1 = new Point(1388, 772);
        p2 = new Point(1505, 806);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Nashville-Atlanta---------------//
        weight = 1;
        p = Pair.of("Nashville", "Atlanta");
        mappingName = "Nashville-Atlanta";
        p1 = new Point(1609, 862);
        p2 = new Point(1561, 830);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, ONE);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Atlanta-Charleston---------------//
        weight = 2;
        p = Pair.of("Atlanta", "Charleston");
        mappingName = "Atlanta-Charleston";
        p1 = new Point(1661, 889);
        p2 = new Point(1783, 896);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Atlanta-Raleigh-1--------------//
        weight = 2;
        p = Pair.of("Atlanta", "Raleigh");
        mappingName = "Atlanta-Raleigh-1";
        p1 = new Point(1642, 858);
        p2 = new Point(1734, 775);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Atlanta-Raleigh-2--------------//
        weight = 2;
        p = Pair.of("Atlanta", "Raleigh");
        mappingName = "Atlanta-Raleigh-2";
        p1 = new Point(1657, 873);
        p2 = new Point(1748, 793);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Raleigh-Charleston--------------//
        weight = 2;
        p = Pair.of("Raleigh", "Charleston");
        mappingName = "Raleigh-Charleston";
        p1 = new Point(1774, 782);
        p2 = new Point(1822, 864);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Raleigh-Washington-1--------------//
        weight = 2;
        p = Pair.of("Raleigh", "Washington");
        mappingName = "Raleigh-Washington-1";
        p1 = new Point(1850, 655);
        p2 = new Point(1768, 747);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Raleigh-Washington-2--------------//
        weight = 2;
        p = Pair.of("Raleigh", "Washington");
        mappingName = "Raleigh-Washington-2";
        p1 = new Point(1867, 670);
        p2 = new Point(1784, 762);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);


        //-----------------Pittsburgh-Raleigh--------------//
        weight = 2;
        p = Pair.of("Pittsburgh", "Raleigh");
        mappingName = "Pittsburgh-Raleigh";
        p1 = new Point(1706, 607);
        p2 = new Point(1743, 722);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Pittsburgh-Washington--------------//
        weight = 2;
        p = Pair.of("Pittsburgh", "Washington");
        mappingName = "Pittsburgh-Washington";
        p1 = new Point(1730, 577);
        p2 = new Point(1836, 635);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Toronto-Pittsburgh--------------//
        weight = 2;
        p = Pair.of("Toronto", "Pittsburgh");
        mappingName = "Toronto-Pittsburgh";
        p1 = new Point(1685, 532);
        p2 = new Point(1671, 410);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Toronto-Montreal-------------//
        weight = 3;
        p = Pair.of("Toronto", "Montreal");
        mappingName = "Toronto-Montreal";
        p1 = new Point(1664, 365);
        p2 = new Point(1794, 237);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, THREE);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Montreal-Boston-1-------------//
        weight = 2;
        p = Pair.of("Montreal", "Boston");
        mappingName = "Montreal-Boston-1";
        p1 = new Point(1848, 266);
        p2 = new Point(1946, 342);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

        //-----------------Montreal-Boston-2-------------//
        weight = 2;
        p = Pair.of("Montreal", "Boston");
        mappingName = "Montreal-Boston-2";
        p1 = new Point(1862, 250);
        p2 = new Point(1958, 325);

        r = new Route(color, weight, null, p, p1, p2, "GRAY", mappingName, TWO);
        _M_nameToRoute.put(mappingName, r);

    }
}
