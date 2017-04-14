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
        longestPath = 0;
    }

    List<Node> _nodes;
    private int longestPath;
    
    //If the Pair doesn't work out, the key could be the left city of _destination, then the right city of _destination will be the
    //Edge's _pointingNode and the route weight will be the Edge's weight.

    public Node getNode(String nodeName){
        if(_nodes.isEmpty()){
            return null;
        }

        
        for(Node node : _nodes){
            if(node.get_nodeName().equals(nodeName)){
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
        Node leftCity = getNode(card.get_destination().getLeft());
        Node rightCity = getNode(card.get_destination().getRight());
        
        if (leftCity == null || rightCity == null) {
        	return false;
        }
        return findPath(leftCity, rightCity);
    }

    public Boolean findPath(Node leftCity, Node rightCity){
    	List<Edge> edgeList = new ArrayList<>();
    	if (!leftCity.get_edges().isEmpty()) { //If the node has edges
    		edgeList = leftCity.get_edges(); //Each node has an edge
    	
	        for(Edge e: edgeList){ //Checks from left most city to the right city
	            Node pointingNode = e.get_pointingNode();
	            if( pointingNode == rightCity){
	                return true;
	            }
	            if(!pointingNode.get_visited()) {
	            	pointingNode.set_visited(true);
	                return findPath(pointingNode, rightCity);
	            }
	        }
    	}
    	
    	resetNodeVisited(); //Reset so checking will work properly
    	if (!rightCity.get_edges().isEmpty()) { //If the node has edges
    		edgeList = rightCity.get_edges(); //Each node has an edge
    	
	        for(Edge e: edgeList){ //Checks from right most city to the left city
	            Node pointingNode = e.get_pointingNode();
	            if( pointingNode == leftCity){
	                return true;
	            }
	            if(!pointingNode.get_visited()) {
	            	pointingNode.set_visited(true);
	                return findPath(pointingNode, leftCity);
	            }
	        }
    	}

        return false;
    }

    public int getLongestPath(){
        for(Node n : _nodes){
            if(!n.get_reached()) {
                n.set_reached(true);
                int currentPathLength = 0;
                 currentPathLength = findLongestPath(n, 0); //Start path a
                if (currentPathLength > longestPath) {
                	longestPath = currentPathLength;
                }
            }
        }
        return longestPath;
    }

    public int findLongestPath(Node node, int pathLength){
        if(!node.get_visited()){
            node.set_visited(true);
            for(Edge e : node.get_edges()){

                Node branch = e.get_pointingNode();
                if(!branch.get_visited()) {
                    pathLength += e.get_weight();

                    if (pathLength > longestPath) {
                        longestPath = pathLength;
                    }

                    findLongestPath(branch, pathLength);

//                    pathLength -= e.get_weight();
                    branch.set_visited(false);
                }
            }
        }
        return pathLength;
    }

    public Boolean doesNodeExist(String nodeName) {
        if(_nodes.isEmpty()){
            return false; //Node doesn't exist if list is empty
        }

        
        for(Node node : _nodes){
            if(node.get_nodeName().equals(nodeName)){
                return true; //Node found
            }
        }
        return false; //List isn't empty but no node found
    }
    
    public void resetNodeVisited() {
    	for (Node node : _nodes) {
    		node.set_visited(false);
    	}
    }
    
    public List<Node> get_nodes() {
		return _nodes;
	}

}
