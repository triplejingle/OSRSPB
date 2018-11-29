package Scripts.Core.garbage;

import Scripts.Tools.Stack;
import Scripts.Tools.ATimer;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class InventoryMethods extends ClientAccessor {
    private final int maxInventorySpace = 28;
    int prevNumberOfItems=0;
    Stack<Item> stack = new Stack(maxInventorySpace);
    ATimer ATimer = new ATimer();
    PlayerMethods playerMethods = new PlayerMethods(ctx);
    public InventoryMethods(ClientContext ctx) {
        super(ctx);
    }

    public int getMaxInventorySpace() {
        return maxInventorySpace;
    }
    public Item createItem(int itemId){
        return  ctx.inventory.select().id(itemId).poll();
    }

    public void selectItem(int itemId){
        createItem(itemId).interact("use");
    }

    public void dropItem(int itemId){
        createItem(itemId).interact("Drop");
    }

    public void examineItem(int itemId){

    }

    public void addObject(Item item){
        stack.push(item);
    }

    public void removeFirst(){
        stack.removeFirst();
    }
    public Item getObject(){
        return stack.peek();
    }

    boolean waitWhileInventoryContainsItem(int itemId){
//        ATimer.setPeriod(3000);
//        ATimer.saveTime();
//        while(inventoryContainsItem(itemId)){
//            if(ATimer.isTime()&&playerMethods.isPlayerDoingNothing()){
//                return true;
//            }else if(!playerMethods.isPlayerDoingNothing()){
//                ATimer.saveTime();
//            }
//        }
       return false;
    }

    boolean waitUntilInventoryContainsItem(String itemId){
        ATimer.setPeriod(3000);
        ATimer.saveTime();
        while(!ctx.inventory.select().name(itemId).isEmpty()){
            if(ATimer.isTime()&&playerMethods.isPlayerDoingNothing()){
            return true;
            }else if(!playerMethods.isPlayerDoingNothing()){
                ATimer.saveTime();
            }
        }
        return false;
    }
}
