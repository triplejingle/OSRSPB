package scripts.tools.factory;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import scripts.tools.algoritm.Vertex;

import java.util.ArrayList;
import java.util.List;

public class VertexGenerator extends ClientAccessor {
    public List<Vertex> getVertices() {
        return vertices;
    }
    public VertexGenerator(ClientContext ctx){
        super(ctx);
        createVertex("Lumbridge");
        createVertex("DraynorVillage");
        createVertex("BarbarianVillage");
        createVertex("GrandExchange");
        createVertex("Varrock");
        for(int i = 0 ;i<20;i++) {
            createVertex("Lumbridge"+i);
            createVertex("DraynorVillage"+i);
            createVertex("BarbarianVillage"+i);
            createVertex("GrandExchange"+i);
            createVertex("Varrock"+i);
        }
    }

    Vertex startingLocation ;
    Vertex destination;

    public void setStartingLocation(){
        for(int i = 0 ;i<vertices.size();i++) {
            if (vertices.get(i).isNearby(ctx.players.local().tile())){
                startingLocation=vertices.get(i);
                break;
            }
        }
    }

    public void setDestination(String destinationName){
        for(int i = 0;i<vertices.size();i++){
            if(vertices.get(i).getName().equals(destinationName)){
                destination = vertices.get(i);
                break;
            }
        }
    }
    ArrayList<Vertex> vertices = new ArrayList<>();
    public Vertex getVertex(String name){
        for(int i =0;i<vertices.size();i++){
            if(vertices.get(i).getName().equals(name)){
                return vertices.get(i);
            }
        }
        return null;
    }
    Vertex createVertex(String name){
        Vertex v = new Vertex();
        v.setName(name);
        vertices.add(v);
        return v;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getStartingLocation() {
        return startingLocation;
    }
}
