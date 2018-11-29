package Scripts.Jobs.Garbage.CowKillerFalador;

import Scripts.Core.Collection.Animations;
import Scripts.Core.garbage.ItemIdCollection;
import Scripts.Core.garbage.GroundMethods;
import Scripts.Core.garbage.InventoryMethods;
import Scripts.Core.garbage.LocationMethods;
import Scripts.Core.garbage.PlayerMethods;
import Scripts.Tasks.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskPickUpItem extends Task {
    int tileRange ;
    InventoryMethods inventoryMethods = new InventoryMethods(ctx);
    GroundMethods floorMethods = new GroundMethods(ctx);
    LocationMethods locationMethods = new LocationMethods(ctx);
    PlayerMethods playerMethods = new PlayerMethods(ctx);
    ItemIdCollection itemIdCollection = new ItemIdCollection();
    Animations aIdCollection = new Animations();
    public TaskPickUpItem(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx, taskTile, tileRange);
        super.setTaskTile(taskTile);
        this.tileRange = tileRange;
    }

    @Override
    public boolean activate() {
//        if(inventoryMethods.howManyItemsInInventory()>=inventoryMethods.getMaxInventorySpace()-1){
//            return false;
//        }
//        boolean tmp1 = !playerMethods.isPlayerInCombat();
//        boolean tmp4 = playerMethods.isPlayer(aIdCollection.getNothing());
//        return tmp1&&tmp4;
        return false;

    }

    @Override
    public void execute() {
//        if(locationMethods.currentLocationIsNearbyTile(super.getTaskTile(),tileRange)){
//            floorMethods.takeItem(itemIdCollection.getRaw_beef(),"Beef");
//            floorMethods.takeItem(itemIdCollection.getIron_arrow(),"Iron arrow");
//            if(!inventoryMethods.inventoryContainsItem(itemIdCollection.getLogs())){
//                floorMethods.takeItem(itemIdCollection.getLogs(),"Logs");
//            }
//        }else{
//            Tile[] pathtoTask = {super.getTaskTile()};
//            super.getWalkerMethods().walkPath(pathtoTask);
//        }

    }
}
