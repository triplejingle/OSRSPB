package Scripts.Core.garbage;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.*;

import java.util.concurrent.Callable;

public class GroundMethods extends ClientAccessor {
    InventoryMethods inventoryMethods = new InventoryMethods(ctx);
    public GroundMethods(ClientContext ctx) {
        super(ctx);
    }

    //met itemname zodat rechtermuisklik
     public void takeItem( String itemName){
        int checkEveryms = 1000;
        int nOfTimes = 10;
        if(getItem(itemName).inViewport()&&getItem(itemName).tile().matrix(ctx).reachable()) {
            final int currentNumberOfThatItem = ctx.inventory.name(itemName).count();
            getItem(itemName).interact("Take");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return  ctx.inventory.name(itemName).count()>currentNumberOfThatItem;
                }
            },checkEveryms , nOfTimes);
        }else{
            ctx.camera.turnTo(getItem(itemName));
        }
    }

    public void examineItem(){

    }
    public GroundItem getItem(String itemId){
        return  ctx.groundItems.select().nearest().name(itemId).poll();
    }
}
