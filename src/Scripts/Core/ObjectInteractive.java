package Scripts.Core;

import Scripts.Core.Collection.Animations;
import Scripts.Core.Collection.BoundaryCollection;
import Scripts.Core.Interfaces.IObjectInteractive;
import Scripts.Tools.ATimer;
import org.powerbot.script.rt4.BasicQuery;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.ArrayList;

public class ObjectInteractive extends Object implements IObjectInteractive{
    ArrayList<GameObject> gameObjects = new ArrayList<>();
    BasicQuery<GameObject> queryList ;
    GameObject gameObject;
    BoundaryCollection boundaryCollection = new BoundaryCollection();
    ATimer aTimer = new ATimer();
    Animations animations = new Animations();
    int nrofTries = 0;
    public ObjectInteractive(ClientContext arg0,String name) {
        super(arg0,name);
    }

    @Override
    public void interact(String action) {
        gameObject = gameObjects.get(0);
       if(aTimer.isTime()) {
           if (gameObject.inViewport()&& nrofTries <4) {
               if (gameObject.interact(action, super.getName())) {
                   nrofTries++;
                   aTimer.setPeriodBetween(2000, 5000);
               }else{
                   aTimer.setPeriod(0);
               }
           } else {
               ctx.camera.turnTo(gameObject);
           }
       }

       if(ctx.players.local().animation()!=animations.getNothing()){
           nrofTries=0;
       }
    }

    public ObjectInteractive selectObstacle(){
        for(int i = 0; i< gameObjects.size(); i++){
            gameObjects.remove(i);
        }
        String actions[] = {"Open","Climb-up","Climb-down","Climb-over"};
        queryList = ctx.objects.select().name(super.getName()).action(actions);
        return this;
    }

    public ObjectInteractive select(){
        for(int i = 0; i< gameObjects.size(); i++){
            gameObjects.remove(i);
        }
        queryList = ctx.objects.select().name(super.getName());
        return this;
    }

    public ObjectInteractive within(int tiles){
        queryList =  queryList.within(tiles);
        for(GameObject gameObject: queryList){
            gameObjects.add(gameObject);
        }
        return this;
    }
    public ObjectInteractive reachable(){
        if(gameObjects.size()==0){
            return this;
        }
        for(int i = 0;i< gameObjects.size();i++){
            if(!gameObjects.get(i).tile().matrix(ctx).reachable()){
                gameObjects.remove(gameObject);
            }
        }
        return this;
    }

    public ObjectInteractive nearest(){
        gameObjects.add(queryList.nearest().poll());
        return this;
    }

    public void setBounds() {
        if(gameObjects.size()>0) {
            gameObject = gameObjects.get(0);
            gameObject.bounds(boundaryCollection.getBounds(boundaryCollection.getType(super.getName()), gameObject.orientation()));
            System.out.println(gameObject.orientation());
        }
    }

    public boolean inViewport(){
        if(gameObjects.size()>0) {
            return gameObjects.get(0).inViewport();
        }
        return false;
    }
}
