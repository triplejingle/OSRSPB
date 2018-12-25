package scripts.core;


import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import scripts.core.interfaces.Core;
import scripts.tools.ATimer;
import scripts.tools.factory.BankFactory;

public class Bank extends Core {
    ATimer withdrawATimer = new ATimer();
    ATimer openATimer = new ATimer();
    ATimer depositATimer = new ATimer();
    static Random random = new Random();
    BankFactory bankFactory = new BankFactory(ctx);

    public Bank(ClientContext ctx) {
        super(ctx,"Bank");
    }

    public boolean bankWindowOpenend(){
        return ctx.bank.opened();
    }

    public void depositAmount(String itemName, int amount){
        depositATimer.setPeriod(random.nextInt(1000, 3000));
        if (depositATimer.isTime()) {
            ctx.bank.deposit(ctx.inventory.name(itemName).peek().id(), amount);
        }
    }

    public boolean depositAllItems(){
        depositATimer.setPeriod(random.nextInt(1000, 3000));
        if (depositATimer.isTime()) {
          return ctx.bank.depositInventory();
        }
        return false;
    }

    public boolean depositAllItemsExcept(String[] item){
	    depositATimer.setPeriod(random.nextInt(1000, 3000));
	    if (depositATimer.isTime()) {
		    return ctx.bank.depositAllExcept(item);
	    }
	    return false;
    }

    public boolean openBank(){
        if (ctx.bank.inViewport()) {
            openATimer.setPeriod(random.nextInt(500,2000));
            if(openATimer.isTime()) {
               return ctx.bank.open();
            }
        } else {
            ctx.camera.turnTo(ctx.bank.nearest());
        }
        return false;
    }
    public boolean closeBank(){
        openATimer.setPeriod(random.nextInt(500,2000));
        if(openATimer.isTime()) {
            return ctx.bank.close();
        }
        return false;
    }

    public boolean withdraw(String itemName, int amount){
        withdrawATimer.setPeriod(random.nextInt(800,2000));
        if(withdrawATimer.isTime()) {
            int itemID =0;
            for(Item item : ctx.bank.select()){
                if(item.name().equals(itemName)){
                    itemID = item.id();
                    break;
                }
            }
            return ctx.bank.withdraw(itemID, amount);
        }
        return false;
    }

    public boolean inViewport() {
        return ctx.bank.inViewport();
    }

    public boolean isBankOpened() {
        return bankFactory.getComponent("screen").visible();
    }
}
