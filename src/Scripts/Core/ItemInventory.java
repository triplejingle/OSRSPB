package Scripts.Core;

import Scripts.Core.Interfaces.Core;
import Scripts.Core.Interfaces.IInventoryItem;
import Scripts.Tools.ATimer;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public  class ItemInventory extends Core implements IInventoryItem {
    Item item;
    ATimer ATimer = new ATimer();
    Random random = new Random();
    public Item getItem() {
        return item;
    }

    public ItemInventory(ClientContext arg0,String name) {
        super(arg0,name);
    }

    @Override
    public void use() {
        item = ctx.inventory.select().name(super.getName()).poll();
        if (item != null) {
            if(item.inventoryIndex()!=ctx.inventory.selectedItemIndex()) {
                item.interact("Use", super.getName());
            }
        }
    }

    public boolean dropItem(){
        item = ctx.inventory.select().name(super.getName()).poll();
        if (item != null) {
        return item.click();
        }
        return false;
    }

    public boolean dropAllItems(){
        for(Item item:ctx.inventory.select().limit(8).name(super.getName()).shuffle()){
           item.click();
        }
        return false;
    }
}
