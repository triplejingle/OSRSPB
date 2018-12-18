package Scripts.Core;

import Scripts.Tools.ATimer;
import Scripts.Tools.Queue;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class WalkerMethods  extends ClientAccessor {
    Tile nextTile;
    Queue<Tile>queue;
    ATimer ATimer = new ATimer();
    Random random = new Random();
    ObjectInteractive object;
    Tile destination;
    int nextPointRange = random.nextInt(5,7);

    public WalkerMethods(ClientContext ctx) {
        super(ctx);
    }

    public void addPath(Tile[] path){
        queue = new Queue<>(path.length);
        for(int i = 0 ;i<path.length;i++) {
            queue.addToQueue(path[i]);
        }
        System.out.println(nextTile);
        destination = path[path.length-1];
        nextTile = queue.getCurrent();
    }

    public void setPath(){
        double distance = 100;
        Tile closestTile= null;
        for(int i = 0;i<queue.getSize();i++){
            if(ctx.players.local().tile().distanceTo(queue.getCurrent())<distance){
                distance = ctx.players.local().tile().distanceTo(queue.getCurrent());
                closestTile = queue.getCurrent();
            }
            queue.next();
        }
        for(int i = 0;i<queue.getSize();i++){
            if(queue.getCurrent().equals(closestTile)) {
                nextTile = queue.getCurrent();
                break;
            }
            queue.next();
        }
    }
    int randomEnergyLevel = random.nextInt(30,60);
    public void walkPath() {
        if (ctx.movement.energyLevel() >randomEnergyLevel){
            randomEnergyLevel = random.nextInt(30, 60);
            ctx.movement.running(true);
        }
        ATimer.setPeriod(1000);
        if(ctx.players.local().tile().distanceTo(destination)>10) {
            if (nextTile.matrix(ctx).reachable()) {
                takeStep();
            } else {
                setPath();
                if (ATimer.isTime()) {
                    handleObstacle();
                }
            }
        }else if(!ctx.players.local().inMotion()){
            queue.next();
            nextTile = queue.getCurrent();
        }
    }

    public void handleObstacle() {
        String actions[] = {"Open","Climb-up","Climb-down","Climb-over"};
        String objectName[] = {"Door", "Gate", "Staircase","Stile"};
        GameObject selectedObject = ctx.objects.select().name(objectName).action(actions).nearest().poll();
        if (selectedObject.name().equals("Door")) {
            handleDoor();
        }else if(selectedObject.name().equals("Gate")){
            handleGate();
        }
    }
    ATimer stepTimer = new ATimer();
    ATimer cameraTimer = new ATimer();
    Tile stepTo;
    private void takeStep(){
        cameraTimer.setPeriodBetween(3000,5000);
        if(cameraTimer.isTime()) {
            ctx.camera.turnTo(stepTo);
        }
        stepTimer.setPeriodBetween(500,1000);
        if(stepTimer.isTime()) {
            if (!ctx.players.local().inMotion() || ctx.players.local().tile().distanceTo(ctx.movement.destination()) < nextPointRange) {
                nextPointRange = random.nextInt(5, 10);
                stepTo = getReachableTile(nextTile, 2, 2);
                System.out.println(stepTo);
                ctx.movement.step(stepTo);
                if (!nextTile.equals(destination)) {
                    queue.next();
                    nextTile = queue.getCurrent();
                }
            }
        }
    }
    public Tile getReachableTile(Tile tile,int x, int y){
        Tile newTile = tile.derive(random.nextInt(0-x,+x),random.nextInt(0-y,y));
        if(newTile.matrix(ctx).reachable()){
            return newTile;
        }else{
            return getReachableTile(tile, x,y);
        }
    }
    boolean isDestinationOnMap(){
        return ctx.movement.destination().matrix(ctx).onMap();
    }

    public void handleGate(){
        object= new ObjectInteractive(ctx,"Gate");
        object.selectObstacle().reachable().nearest();
        object.setBounds();
        object.interact("Open");
    }
    public void handleDoor(){
        object= new ObjectInteractive(ctx,"Door");
        object.selectObstacle().reachable().nearest();
        object.setBounds();
        object.interact("Open");
    }

    public Tile[] splicePath(Tile[] path, double pieces) {
        int getNewPathSize = (int)((double)(path.length)/pieces)+1;
        Tile[] newPath = new Tile[getNewPathSize];
        int index = 0;
        for(int i = 0;i<path.length;i++){
            if(i%pieces==0){
                newPath[index] = path[i];
                index++;
            }
        }
        return newPath;
    }

    public boolean isNearNextTile(){
        return isNear(stepTo);
    }
    public boolean isNearDestination(){
        return ctx.players.local().tile().distanceTo(destination)<10;
    }

    public boolean isNear(Tile tile) {
        return ctx.players.local().tile().distanceTo(tile)<8;
    }

    public boolean walkToTile(Tile nextLocation) {

        return ctx.movement.step(getReachableTile(nextLocation,3,3));
    }
}
