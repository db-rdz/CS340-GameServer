package ServerModel.GameModels.PlayerModel.RouteGraph;

import ServerModel.GameModels.CardsModel.DestCard;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjamin on 28/03/17.
 */
public class Graph {

    public Graph(){
        _nodes = new ArrayList<>();
    }

    List<Node> _nodes;

    public Node getNode(String nodeName){
        if(_nodes.isEmpty()){
            return null;
        }

        for(Node node : _nodes){
            if(node.get_nodeName() == nodeName){
                return node;
            }
        }

        return null;
    }

    public Boolean addNode(String name){
        Node n = new Node(name);
        if(_nodes.add(n)){
            return true;
        }
        return false;
    }

    public Boolean evaluateDestCar(DestCard card){
        card.
    }



}
