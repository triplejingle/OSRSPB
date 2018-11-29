package Scripts.Jobs.Garbage.WoodcuttingTreeAndBank;

import Scripts.Core.garbage.ItemIdCollection;
import Scripts.Core.Bank;
import Scripts.Core.garbage.InventoryMethods;
import Scripts.Core.garbage.LocationMethods;
import Scripts.Tasks.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskBankWood extends Task {

    InventoryMethods inventoryMethods = new InventoryMethods(ctx);
    Bank bankMethod = new Bank(ctx);
    ItemIdCollection idCollection = new ItemIdCollection();
    LocationMethods locationMethods = new LocationMethods(ctx);
    public TaskBankWood(ClientContext ctx, Tile taskTile, int tileRange){
       super(ctx, taskTile, tileRange);
       super.setTaskTile(taskTile);
    }

    @Override
    public boolean activate() {
//        int tilesWithinRange = 4;
////        if(!inventoryMethods.inventoryContainsItem(idCollection.getLogs())){
////            return false;
////        }
////        return locationMethods.currentLocationIsNearbyTile(super.getTaskTile(),tilesWithinRange);
        return false;
    }

    @Override
    public void execute() {
//       bankMethod.openBank();
//       bankMethod.depositAmount(idCollection.getLogs(),inventoryMethods.getMaxInventorySpace());
    }
}
