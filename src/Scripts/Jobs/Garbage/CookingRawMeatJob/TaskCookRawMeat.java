package Scripts.Jobs.Garbage.CookingRawMeatJob;

import Scripts.Core.Collection.Animations;
import Scripts.Core.WalkerTMPMethods;
import Scripts.Core.garbage.*;
import Scripts.Tasks.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskCookRawMeat extends Task {
    LocationMethods locationMethods = new LocationMethods(ctx);
    InventoryMethods inventoryMethods = new InventoryMethods(ctx);
    ObjectMethods objectMethods = new ObjectMethods(ctx);
    PlayerMethods playerMethods = new PlayerMethods(ctx);
    WalkerTMPMethods walkerMethods = new WalkerTMPMethods(ctx);
    Animations aIdCollection = new Animations();
    int tileRange ;

    public TaskCookRawMeat(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx, taskTile, tileRange);
        super.setTaskTile(taskTile);
        this.tileRange = tileRange;
    }

    @Override
    public boolean activate() {
//        objectMethods.removeGameObject();
//        if(!inventoryMethods.inventoryContainsItem(itemIdCollection.getRaw_beef())  ) {
//            return false;
//        }
//        objectMethods.addNearestObject(objectIdCollection.getFire());
//        return       playerMethods.isPlayer(aIdCollection.getNothing())&&
//                objectMethods.isObjectViewable();
        return false;
    }

    @Override
    public void execute() {
//        if(locationMethods.currentLocationIsNearbyTile(super.getTaskTile(),tileRange)) {
//            objectMethods.addNearestObject(objectIdCollection.getFire());
//            if(isWithinTaskRange(objectMethods.getObject().tile())){
//                objectMethods.cook(itemIdCollection.getRaw_beef());
//            }else{
//                objectMethods.removeGameObject();
//            }
//        } else{
//            Tile[] pathtoTask = {super.getTaskTile()};
//            walkerMethods.walkPath(pathtoTask);
//        }
    }
}
