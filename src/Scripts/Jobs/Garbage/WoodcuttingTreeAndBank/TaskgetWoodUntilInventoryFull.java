package Scripts.Jobs.Garbage.WoodcuttingTreeAndBank;

import Scripts.Core.Collection.Animations;
import Scripts.Core.WalkerTMPMethods;
import Scripts.Core.garbage.*;
import Scripts.Tasks.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskgetWoodUntilInventoryFull extends Task{
    InventoryMethods inventoryMethods = new InventoryMethods(ctx);
    PlayerMethods playerMethods = new PlayerMethods(ctx);
    ObjectMethods objectMethods = new ObjectMethods(ctx);
    LocationMethods locationMethods = new LocationMethods(ctx);
    WalkerTMPMethods walkerMethods = new WalkerTMPMethods(ctx);
    Animations aIdCollection = new Animations();

    public TaskgetWoodUntilInventoryFull(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx,taskTile,tileRange);
    }

    @Override
    public boolean activate() {
//        if( inventoryMethods.howManyItemsInInventory()==inventoryMethods.getMaxInventorySpace()) {
//            return false;
//        }
//
//        return locationMethods.currentLocationIsNearbyTile(super.getTaskTile(),super.getAreaInNoTiles());
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
//            int[]treeIds = objectIdCollection.getTree();
//            for(int i = 0 ;i<treeIds.length;i++) {
//                objectMethods.addNearestObject(treeIds[i]);
//                if (isWithinTaskRange(objectMethods.getObject().tile())) {
//
//                    if(!objectMethods.isObjectReachable()) {
//                        Tile[] pathtoTask = {new Tile(3031,3313,0),new Tile(3031,3314,0)};
//
//                            walkerMethods.walkPath(pathtoTask);
//
//                    }
//                        objectMethods.chop(itemIdCollection.getLogs());
//                }else{
//                        objectMethods.removeGameObject();
//                    }
//                }
//
//        }else{
//            Tile[] pathtoTask = {super.getTaskTile()};
//            walkerMethods.walkPath(pathtoTask);
//        }
    }
}
