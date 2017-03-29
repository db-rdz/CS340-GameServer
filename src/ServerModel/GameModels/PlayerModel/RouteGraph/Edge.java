package ServerModel.GameModels.PlayerModel.RouteGraph;

/**
 * Created by benjamin on 28/03/17.
 */
public class Edge {

    public Edge(int w, Node np){
        _weight = w;
        _nodePointer = np;
    }

    private int _weight;
    private Node _nodePointer;


    //---------------------------------GETTERS AND SETTERS-------------------------------//

    public int get_weight() { return _weight; }
    public void set_weight(int _weight) { this._weight = _weight; }

    public Node get_n1() { return _nodePointer; }
    public void set_n1(Node _n1) { this._nodePointer = _n1; }

}
