package Scripts.Jobs.Garbage.GetBucketOfMilk;

import Scripts.Core.Bank;
import Scripts.Core.garbage.InventoryMethods;
import Scripts.Core.garbage.RandomizerMethods;
import Scripts.Tasks.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskGetBucketFromBank extends Task {
    private int withdrawAmount;
    private int itemId;
    private boolean withdrawRandomAmount = false;
    RandomizerMethods randomizerMethods = new RandomizerMethods(ctx);
    InventoryMethods inventoryMethods = new InventoryMethods(ctx);
    Bank bankMethod = new Bank(ctx);

    public TaskGetBucketFromBank(ClientContext ctx, int itemId, boolean withdrawRandomAmount, Tile taskTile, int tileRange){
        super(ctx, taskTile, tileRange);
        super.setTaskTile(taskTile);
        this.itemId = itemId;
        this.withdrawRandomAmount = withdrawRandomAmount;
    }

    public TaskGetBucketFromBank(ClientContext ctx, int itemId, int withdrawAmount, Tile taskTile, int tileRange){
        super(ctx, taskTile, tileRange);
        super.setTaskTile(taskTile);
        this.itemId = itemId;
        this.withdrawAmount = withdrawAmount;
    }

    @Override
    public boolean activate() {
//        int tilesWithinRange = 6;
//        int oneItem = 1;
//        return !inventoryMethods.inventoryContainsItem(itemId)&&
//                getTaskTile().distanceTo((ctx.players.local()))<tilesWithinRange&&
//                inventoryMethods.howManyItemsInInventory()<inventoryMethods.getMaxInventorySpace()-oneItem;
        return false;
    }

    @Override
    public void execute() {
//        bankMethod.openBank();
//        if(!inventoryMethods.inventoryContainsItem(itemId)&&inventoryMethods.countItemsOfItemInInventory(itemId)<withdrawAmount) {
//            bankMethod.depositAllItems();
//            bankMethod.withdraw(itemId, withdrawAmount);
//        }else{
//            bankMethod.withdraw(itemId, withdrawAmount);
//        }

    }
}
