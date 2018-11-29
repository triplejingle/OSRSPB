package Scripts.Jobs.Garbage.GetBucketOfMilk;

import Scripts.Core.garbage.InventoryMethods;
import Scripts.Core.garbage.LocationMethods;
import Scripts.Tasks.WalkTask;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskWalkToDairyCow extends WalkTask {
    InventoryMethods inventory = new InventoryMethods(ctx);
    LocationMethods locationMethods = new LocationMethods(ctx);

    public TaskWalkToDairyCow(ClientContext ctx, Tile[] path) {
        super(ctx, path);
    }

    @Override
    public boolean activate() {
//        int tilesWithinRange = 3;
//
//        return !inventory.inventoryContainsItem(idCollection.getBucketOfMilk()) &&
//                inventory.inventoryContainsItem(idCollection.getBucket())&&
//                !locationMethods.currentLocationIsNearbyTile(super.getTaskTile(),tilesWithinRange);
        return false;
    }
}
