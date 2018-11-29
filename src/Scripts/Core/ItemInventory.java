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
        int triedIndex = 0;
        if(nrOfTries[triedIndex]>=maxTries){
            stopScript(triedIndex);
            return;
        }
        item = ctx.inventory.select().name(super.getName()).poll();
        if (item != null) {
            if(item.inventoryIndex()!=ctx.inventory.selectedItemIndex()) {
                if(item.interact("Use", super.getName())){
                     nrOfTries[triedIndex]= 0;
                }
            }
        }
    }
    public void dropItem(){
        int triedIndex = 1;
        if(nrOfTries[triedIndex]>=maxTries){
            stopScript(triedIndex);
            return;
        }
        item = ctx.inventory.select().name(super.getName()).poll();
        if (item != null) {
            if(item.interact("Drop")){
                nrOfTries[triedIndex] =0;
            }
        }
    }
    public void dropAllItems(){
        int triedIndex = 2;
        stopScript(triedIndex);
        for(Item item:ctx.inventory.select().name(super.getName()) ){
               if(item.interact("Drop")){
                  nrOfTries[triedIndex]++;
               }
        }
    }
}
