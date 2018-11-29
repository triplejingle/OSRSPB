package Scripts.Jobs.Garbage.CookingRawMeatJob;

import Scripts.Core.Collection.Animations;
import Scripts.Core.WalkerTMPMethods;
import Scripts.Core.garbage.*;
import Scripts.Tasks.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskgetWood extends Task{
    InventoryMethods inventoryMethods = new InventoryMethods(ctx);
    PlayerMethods playerMethods = new PlayerMethods(ctx);
    ObjectMethods objectMethods = new ObjectMethods(ctx);
    LocationMethods locationMethods = new LocationMethods(ctx);
    WalkerTMPMethods walkerMethods = new WalkerTMPMethods(ctx);
    Animations aIdCollection = new Animations();

    public TaskgetWood(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx,taskTile,tileRange);
    }

    @Override
    public boolean activate() {

//        if( ! inventoryMethods.inventoryContainsItem(itemIdCollection.getRaw_beef()) ){
//            return false;
//        }
//
//        boolean tmp5 = playerMethods.isPlayer(aIdCollection.getNothing());
//        boolean tmp6 = !objectMethods.selectNearestObjects(objectIdCollection.getFire()).inViewport();
//        return tmp5&&tmp6;
//        return  !objectMethods.isNearestObjectNearTaskLocation(objectIdCollection.getTree(),super.getTaskTile())&&
//                !playerMethods.isPlayerInCombat()&&
//                inventoryMethods.inventoryContainsItem(itemIdCollection.getRaw_beef())&&
//                !playerMethods.isPlayer(aIdCollection.getChopping()) &&
//                playerMethods.isPlayer(aIdCollection.getNothing())&&
//                !objectMethods.isObjectViewable(objectIdCollection.getFire())&&
//                !playerMethods.isPlayerMoving();
        return false;
    }

    @Override
    public void execute() {
//        if(locationMethods.currentLocationIsNearbyTile(super.getTaskTile(),super.getAreaInNoTiles())) {
//            objectMethods.addNearestObject(objectIdCollection.getTree()[0]);
//            if(isWithinTaskRange(objectMethods.getObject().tile())){
//                objectMethods.chop(itemIdCollection.getLogs());
//            }else{
//                objectMethods.removeGameObject();
//            }
//        }else{
//            Tile[] pathtoTask = {super.getTaskTile()};
//            walkerMethods.walkPath(pathtoTask);
//        }
    }
}
