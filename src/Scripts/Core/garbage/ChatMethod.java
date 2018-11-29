package Scripts.Core.garbage;

import Scripts.Tools.ATimer;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class ChatMethod  extends ClientAccessor {
    InventoryMethods inventoryMethods = new InventoryMethods(ctx);
    ATimer ATimer = new ATimer();
    public ChatMethod(ClientContext ctx) {
        super(ctx);
    }

    public void clickContinue(){
           ctx.chat.canContinue();
    }

    public void selectMake(String usingItem){
            if(!ctx.inventory.name(usingItem).isEmpty()){
                Component c = ctx.widgets.widget(270).component(14).component(38);
                c.click();
            }
    }
}
