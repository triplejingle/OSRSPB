package Scripts.Jobs.Garbage.GetBucketOfMilk;

import Scripts.Core.garbage.ItemIdCollection;
import Scripts.Core.Bank;
import Scripts.Core.garbage.InventoryMethods;
import Scripts.Tasks.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskBankBucketOfMilk extends Task {
    InventoryMethods inventoryMethods = new InventoryMethods(ctx);
    Bank bankMethod = new Bank(ctx);
    ItemIdCollection idCollection = new ItemIdCollection();
    public TaskBankBucketOfMilk(ClientContext ctx, Tile taskTile, int tileRange){
       super(ctx, taskTile, tileRange);
       super.setTaskTile(taskTile);
    }

    @Override
    public boolean activate() {
//        int tilesWithinRange = 6;
//        return inventoryMethods.inventoryContainsItem(idCollection.getBucketOfMilk()) &&
//                getTaskTile().distanceTo((ctx.players.local()))<tilesWithinRange;
        return false;
    }

    @Override
    public void execute() {
//       bankMethod.openBank();
//       bankMethod.depositAmount(idCollection.getBucketOfMilk(),28);
    }
}
