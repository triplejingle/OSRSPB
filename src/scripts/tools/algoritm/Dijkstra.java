package scripts.tools.algoritm;
import org.powerbot.script.Tile;

import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {
    private PriorityQueue<Vertex> visitedVertices = new PriorityQueue<>();
    private PriorityQueue<Vertex> unvisitedVeritices = new PriorityQueue<>();
    public void addToUnvisited(Vertex v){
        unvisitedVeritices.add(v);
    }
    public Tile[] findShortestPath(Vertex startingFrom, Vertex destination){
        Vertex initialnode = startingFrom;
        initialnode.setCost(0);
        Vertex currentNode = new Vertex();
        while(unvisitedVeritices.size()>0) {
            currentNode = getLowestCostVertex();
            unvisitedVeritices.remove(currentNode);
            for ( Map.Entry<Vertex, Edge> vertex : currentNode.getNeighbours().entrySet()) {
                Vertex adjacentVertex = vertex.getKey();
                Edge adjacentEdge = vertex.getValue();
                double distance = currentNode.getCost() + adjacentEdge.getCost();
                if (distance < adjacentVertex.getCost()) {
                    adjacentVertex.setCost(distance);
                    adjacentVertex.setPrevious(currentNode);
                }

            }
            currentNode.setVisited(true);
            visitedVertices.add(currentNode);
        }

        return destination.getPath(destination);

    }

    private Vertex getLowestCostVertex(){
        Vertex node = null;
        for(Vertex v: unvisitedVeritices){
            if(node==null){
                node = v;
            }
            if(v.getCost()<node.getCost()){
                node = v;
            }
        }

        return node;
    }

    public void visited(Vertex node){
        node.setVisited(true);
    }
}
