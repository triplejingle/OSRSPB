package scripts.core.domain;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.data.InventoryData;
import scripts.core.data.ItemData;
import scripts.core.interfaces.Core;
import scripts.core.interfaces.IInventory;

public class Inventory extends Core implements IInventory {
    private ItemData item;
    private InventoryData inventoryData;

    public Inventory(ClientContext arg0) {
        super(arg0);
        item = new ItemData(ctx);
        inventoryData = new InventoryData(ctx);
    }

    @Override
    public boolean use() {
        return item.interact("Use");
    }

    public boolean dropItem() {
        return item.interact("Drop");
    }

    public boolean dropAllItems() {
        int nrOfItemsInInventory = inventoryData.countInventory();
        for (int i = 0; i < nrOfItemsInInventory; i++) {
            dropItem();
        }
        return false;
    }

    public boolean hasItem(String[] item) {
        if (ctx.inventory.select().name(item).count() > 0) {
            return true;
        }
        //to do check if item is equipped
        return false;
    }
}
