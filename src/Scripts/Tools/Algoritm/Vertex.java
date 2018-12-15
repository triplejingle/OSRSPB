package Scripts.Tools.Algoritm;


import org.powerbot.script.Tile;

import java.util.HashMap;
import java.util.Map;

public class Vertex {
    public String getName() {
        return name;
    }

   private String name;

    public Vertex getPrevious() {
        return previous;
    }

    private Vertex previous;
    private HashMap<Vertex, Edge> adjacentNodes = new HashMap<>();
    private double cost = Integer.MAX_VALUE;
    boolean visited = false;

    public Map<Vertex, Edge> getNeighbours() {
        return adjacentNodes;
    }

    public void addNeighbour(Vertex v, Edge edge){
        adjacentNodes.put(v,edge);
    }
    public Tile[] getPath(Vertex v){
        Tile[] path = new Tile[0];
        path = getPath(v,path);
        return path;
    }
    public Tile[] getPath(Vertex v,Tile[] path){
        if(getPrevious()!=null) {
            Tile[] tmp =  getPrevious().adjacentNodes.get(v).getPath();
             path = concatenate(tmp,path);
             path = getPrevious().getPath(getPrevious(),path);
        }
        return path;
    }

    public  Tile[] concatenate(Tile[] first, Tile[] second) {
        Tile[] arr = new Tile[first.length + second.length];
        System.arraycopy(first, 0, arr, 0, first.length);
        System.arraycopy(second, 0, arr, first.length, second.length);
        return arr;
    }
    public boolean isVisited(){
        return visited;
    }
    //getters and setters
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setCost(double cost){
        this.cost = cost;
    }
    public double getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    public boolean isNearby(Tile tile) {
        for(Edge edge: getNeighbours().values()){
            if(edge.getPath()[0].distanceTo(tile)>30){
                continue;
            }
            for(int i = 0;i<(edge.getPath().length)/2;i++){
                if(edge.getPath()[i].distanceTo(tile)<8){
                    return true;
                }
            }
        }
        return false;
    }
}
