package Scripts.Core.garbage;


import Scripts.Core.Collection.Animations;
import Scripts.Core.Collection.BoundaryCollection;
import Scripts.Core.ENUM.BoundaryType;
import Scripts.Tools.Queue;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class ObjectMethods extends ClientAccessor {
    InventoryMethods inventoryMethods = new InventoryMethods(ctx);
    int queueSize = 10;
    Queue<GameObject> queue = new Queue<GameObject>(queueSize);
    ChatMethod chatMethod = new ChatMethod(ctx);
    Animations aIdCollection = new Animations();
    PlayerMethods playerMethods = new PlayerMethods(ctx);
    BoundaryCollection boundaryCollection = new BoundaryCollection();


    public ObjectMethods(ClientContext ctx) {
        super(ctx);
    }

    public void interactWithObject(String action){
        if(getObject().actions()[0].equals(action)){
            if (getObject().inViewport()&&getObject().tile().matrix(ctx).reachable()) {
                getObject().interact(action);
                queue.next();
            } else {
                ctx.camera.turnTo(getObject());
            }
        }
    }

    public boolean isObjectViewable(){
        return getObject().inViewport();
    }

    public void useItemWithObject(int itemid){
        inventoryMethods.selectItem(itemid);
        if(getObject().inViewport()){
            getObject().click();
        }else{
            ctx.camera.turnTo(getObject());
        }
    }

    public GameObject[] selectNearestObjects(String objectName, String action){
        Object[] stream = ctx.objects.name(objectName).action(action).within(10).stream().toArray();
        GameObject[] gameObjects = new GameObject[stream.length];
        for(int i = 0 ;i<stream.length;i++) {
            GameObject gameObject = (GameObject) stream[i];
            gameObjects[i] = gameObject;
        }
        return gameObjects;
    }

    public void selectObject(String objectName, String action){
       GameObject[] gameObject = (GameObject[]) ctx.objects.name(objectName).action(action).stream().toArray();
    }

    public void selectReachableObject(String objectName,String action) {
        GameObject[] gameObjects = selectNearestObjects(objectName,action);
        for(int i = 0 ;i<gameObjects.length;i++) {
            if (isObjectReachable(gameObjects[i].tile())) {
                queue.addToQueue(gameObjects[i]);
                addBoundaryToObject();
            }
        }
    }

    private void addBoundaryToObject(){
        BoundaryType boundaryType = boundaryCollection.getType(queue.getCurrent().name());
        if(boundaryType!=null){
            int orientation = queue.getCurrent().orientation();
           queue.getCurrent().bounds( boundaryCollection.getBounds(boundaryType,queue.getCurrent().orientation()));
        }
    }

    public GameObject getObject(){
        return queue.getCurrent()==null?null:queue.getCurrent();
    }

    public boolean isObjectReachable(final Tile t) {
        if(t.matrix(ctx).reachable()){
            return true;
        }
            final Tile[] tiles = {t.derive(-1, 0), t.derive(1, 0), t.derive(0, -1), t.derive(0, 1)};
            for (Tile tile : tiles) {
                if (tile.matrix(ctx).reachable()) {
                    return true;
                }
            }
            return false;
    }

    public boolean isObjectReachable() {
        final Tile t = getObject().tile();
        return isObjectReachable(t);
    }

    public void cook(final int itemId) {
//        if(ctx.players.local().animation()!=aIdCollection.getCooking()&&
//                inventoryMethods.inventoryContainsItem(itemId)&&
//                isObjectReachable()) {
//            useItemWithObject(itemId);
//            Condition.sleep(500);
//            chatMethod.selectMake(itemId);
//
//            inventoryMethods.waitUntilInventoryContainsItem(itemId);
//            inventoryMethods.waitWhileInventoryContainsItem(itemId);
//        }
    }

    public void chop(final int itemId) {
//        if(!playerMethods.isPlayer(aIdCollection.getChopping())&&
//                isObjectReachable()&&
//                inventoryMethods.howManyItemsInInventory()<inventoryMethods.getMaxInventorySpace()) {
//            interactWithObject("Chop");
//
//            playerMethods.waitUntilAnimation(aIdCollection.getChopping());
//            playerMethods.waitUntilAnimationOver(aIdCollection.getChopping());
//        }
    }



}
