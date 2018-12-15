package Scripts.Core;

import Scripts.Core.Interfaces.IInventoryItemInteractive;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

public class ItemInventoryInteractive extends ItemInventory implements IInventoryItemInteractive {

    public ItemInventoryInteractive(ClientContext arg0,String name) {
        super(arg0,name);
    }

    @Override
    public void action(String action) {
        item = ctx.inventory.select().name(super.getName()).poll();
        if(ctx.inventory.select().name(super.getName()).count()>0){
            item.interact(action,super.getName());
        }
    }

}
