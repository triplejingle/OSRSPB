package Scripts.Location;

import Scripts.Core.ItemInventoryInteractive;
import Scripts.Core.NPCInteractive;
import Scripts.Tools.ATimer;
import Scripts.Tools.Factory.ExchangeFactory;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class GrandExchange extends ClientAccessor{
    ExchangeFactory componentFactory = new ExchangeFactory(ctx);
    NPCInteractive grand_exchange_clerk = new NPCInteractive(ctx, "Grand Exchange Clerk");

    public GrandExchange(ClientContext arg0) {
        super(arg0);
    }

    public void sellItem(String item){
        ItemInventoryInteractive mindRune = new ItemInventoryInteractive(ctx, item);
        if(ctx.inventory.select().name(item).count()>0) {
            if (!componentFactory.getComponent("screen").visible()) {
                grand_exchange_clerk.exchange();
            }
            mindRune.action("Offer");
            Component confirm = componentFactory.getComponent("confirm");
            confirm.click();
            ATimer ATimer = new ATimer();
            ATimer.setPeriod(2000);
            ATimer.saveTime();
            while (confirm.inViewport()) {
                if (ATimer.isTime()) {
                    if (confirm.visible()) {
                        confirm.click();
                        ATimer.saveTime();
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public void collect(){
        Component collectGold = componentFactory.getComponent("collect");
        ATimer ATimer = new ATimer();
        ATimer.setPeriod(1000);
        ATimer.saveTime();
        while(!isSaleSold()){
            if(ATimer.isTime()){
                if(isSaleSold()) {
                    break;
                }else{
                    ATimer.saveTime();
                }
            }
        }

        while(isSaleSold()){
            if(ATimer.isTime()){
                if(!collectGold.visible()){
                    break;
                }
                collectGold.click();
            }
        }
    }

    public void closeExchangeScreen(){
        if(componentFactory.getComponent("screen").inViewport()) {
            Condition.sleep(1000);
            Component closeExchangeScreen = componentFactory.getComponent("close");
            closeExchangeScreen.click();
            ATimer ATimer = new ATimer();
            ATimer.setPeriod(2000);
            ATimer.saveTime();
            while(componentFactory.getComponent("screen").inViewport()){
                if(ATimer.isTime()){
                    if(componentFactory.getComponent("screen").inViewport()) {
                        closeExchangeScreen.click();
                        break;
                    }else {
                        ATimer.saveTime();
                    }
                }
            }
        }
    }

    public boolean isSaleSold(){
        if(componentFactory.getComponent("screen").inViewport()) {
            Component collectGold = componentFactory.getComponent("collect");
            return collectGold.visible();
        }
        return false;
    }
}
