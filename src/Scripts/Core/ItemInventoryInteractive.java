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
        int triedIndex = 3;
        if(nrOfTries[triedIndex]>=maxTries){
            stopScript(triedIndex);
            return;
        }
        item = ctx.inventory.select().name(super.getName()).poll();
        if(ctx.inventory.select().name(super.getName()).count()>0){
            if(item.interact(action,super.getName())) {
                nrOfTries[triedIndex]=0;
                Condition.sleep(500);
            }else{
                nrOfTries[triedIndex]++;
            }
        }
    }

}
