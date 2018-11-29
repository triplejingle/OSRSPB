package Scripts.Jobs.Garbage.GetBucketOfMilk;

import Scripts.Core.Collection.BoundaryCollection;
import Scripts.Core.garbage.ItemIdCollection;
import Scripts.Core.garbage.NpcIdCollection;
import Scripts.Core.garbage.InventoryMethods;
import Scripts.Core.garbage.NPCMethods;
import Scripts.Core.garbage.PlayerMethods;
import Scripts.Tasks.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;

public class TaskGetMilkFromDairyCow extends Task {
    int desiredNOItems;

    BoundaryCollection boundaryCollection = new BoundaryCollection();
    ItemIdCollection idCollection = new ItemIdCollection();
    NpcIdCollection npcIdCollection = new NpcIdCollection();

    InventoryMethods inventoryMethods = new InventoryMethods(ctx);
    NPCMethods npcMethods = new NPCMethods(ctx);
    PlayerMethods playerMethods = new PlayerMethods(ctx);

    public TaskGetMilkFromDairyCow(ClientContext ctx, int desiredNOItems, Tile taskTile, int tileRange) {
        super(ctx, taskTile, tileRange);
        super.setTaskTile(taskTile);
        this.desiredNOItems = desiredNOItems;
    }

    @Override
    public boolean activate() {
//        return inventoryMethods.inventoryContainsItem(idCollection.getBucket()) &&
//                playerMethods.isPlayerNearbyLocation(getTaskTile());
        return false;
    }

    @Override
    public void execute() {
        String action = "Milk";
        String npcName = "Dairy cow";
//        if(!inventoryMethods.inventoryContainsItem(idCollection.getBucketOfMilk())) {
//            npcMethods.interactWith(action, npcIdCollection.getDairyCow(), boundaryCollection.getDaiyCowBounds());
//            Condition.wait(new Callable<Boolean>() {
//                @Override
//                public Boolean call() throws Exception {
//                    return inventoryMethods.inventoryContainsItem(idCollection.getBucketOfMilk());
//                }
//            });
//        }
    }

    public void setDesiredNOItems(int desiredNOItems) {
        this.desiredNOItems = desiredNOItems;
    }

}
