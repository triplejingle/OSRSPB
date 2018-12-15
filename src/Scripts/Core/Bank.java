package Scripts.Core;


import Scripts.Core.Interfaces.Core;
import Scripts.Tools.ATimer;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;

public class Bank extends Core {
    ATimer withdrawATimer = new ATimer();
    ATimer openATimer = new ATimer();
    ATimer depositATimer = new ATimer();
    ATimer closeTimer = new ATimer();
    Random random = new Random();

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

    public void openBank(){
        if (ctx.bank.inViewport()) {
            openATimer.setPeriod(random.nextInt(500,2000));
            if(openATimer.isTime()) {
                ctx.bank.open();
            }
        } else {
            ctx.camera.turnTo(ctx.bank.nearest());
        }
    }
    public void closeBank(){
        openATimer.setPeriod(random.nextInt(500,2000));
        if(openATimer.isTime()) {
            ctx.bank.close();
        }
    }

    public boolean withdraw(int itemId, int amount){
        withdrawATimer.setPeriod(random.nextInt(800,2000));
        if(withdrawATimer.isTime()) {
            return ctx.bank.withdraw(itemId, amount);
        }
        return false;
    }

    public boolean inViewport() {
        return ctx.bank.inViewport();
    }
}
