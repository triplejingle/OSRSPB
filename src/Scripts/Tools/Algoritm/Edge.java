package Scripts.Tools.Algoritm;

import org.powerbot.script.Tile;

public class Edge {
    private String name;

    public Tile[] getPath() {
        return path;
    }

    public void setPath(Tile[] path) {
        this.path = path;
    }

    private Tile[] path;
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    private double cost;

    public void setName(String name) {
        this.name = name;
    }
}
