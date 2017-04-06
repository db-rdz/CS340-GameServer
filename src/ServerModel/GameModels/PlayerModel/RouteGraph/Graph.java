package ServerModel.GameModels.PlayerModel.RouteGraph;

import ServerModel.GameModels.CardsModel.DestCard;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Created by benjamin on 28/03/17.
 */
public class Graph {

    public Graph(){
        _nodes = new ArrayList<>();
        _M_DestinationWithEdge = new HashMap<>();
    }

    List<Node> _nodes;
    
    //Nathan: My idea is to have a map of the destination card path (_destination variable) and it's weight will be the route weight
    //If the Pair doesn't work out, the key could be the left city of _destination, then the right city of _destination will be the
    //Edge's _pointingNode and the route weight will be the Edge's weight.
    Map<Pair<String, String>, Edge> _M_DestinationWithEdge; //Each pair of cities has an edge in between

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
        if(!_nodes.contains(n)){ //if the node is in the list
        	_nodes.add(n);
            return true;
        }
        return false;
    }

    public Boolean evaluateDestCard(DestCard card){
        String from = card.get_destination().getLeft();
        String to = card.get_destination().getRight();
        return findPath(getNode(from),getNode(to));
    }

    public Boolean findPath(Node n1, Node n2){
        List<Edge> edgeList = n1.get_edges(); //Each node has an edge

        for(Edge e: edgeList){
            Node pointingNode = e.get_pointingNode();
            if( pointingNode == n2){
                return true;
            }
            if(!pointingNode.get_visited()) {
                return findPath(pointingNode, n2);
            }
        }

        return false;
    }

    public int getLongestPath(){
        int longest_path = 0;
        for(Node n : _nodes){
            if(!n.get_reached()) {
                int currentPathLength = 0;
                findLongestPath(n, 0, currentPathLength);
                if (currentPathLength > longest_path) {
                    longest_path = currentPathLength;
                }
            }
        }
        return longest_path;
    }

    public void findLongestPath(Node node, int pathLength, int longestPath){
        if(!node.get_visited()){
            node.set_visited(true);
            node.set_reached(true);
            for(Edge e : node.get_edges()){

                Node branch = e.get_pointingNode();
                if(!branch.get_visited()) {
                    pathLength += e.get_weight();

                    if (pathLength > longestPath) {
                        longestPath = pathLength;
                    }

                    findLongestPath(branch, pathLength, longestPath);

                    pathLength -= e.get_weight();
                    branch.set_visited(false);
                }
            }
        }
    }


    public Map<Pair<String, String>, Edge> get_M_DestinationWithEdge() {
		return _M_DestinationWithEdge;
	}


}
