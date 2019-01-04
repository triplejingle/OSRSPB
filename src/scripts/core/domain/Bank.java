package scripts.core.domain;


import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import scripts.core.interfaces.Core;
import scripts.tools.ATimer;
import scripts.tools.factory.BankFactory;

public class Bank extends Core {
    private ATimer aTimer = new ATimer();
    private BankFactory bankFactory = new BankFactory(ctx);

    public Bank(ClientContext ctx) {
        super(ctx, "Bank");
    }

    public boolean bankWindowOpenend() {
        return ctx.bank.opened();
    }

    public void depositAmount(String itemName, int amount) {
        aTimer.setPeriod(Random.nextInt(1000, 3000));
        if (aTimer.isTime()) {
            ctx.bank.deposit(ctx.inventory.name(itemName).peek().id(), amount);
        }
    }

    public boolean depositAllItems() {
        aTimer.setPeriod(Random.nextInt(1000, 3000));
        if (aTimer.isTime()) {
            return ctx.bank.depositInventory();
        }
        return false;
    }

    public boolean depositAllItemsExcept(String[] item) {
        aTimer.setPeriod(Random.nextInt(1000, 3000));
        if (aTimer.isTime()) {
            return ctx.bank.depositAllExcept(item);
        }
        return false;
    }

    public boolean openBank() {
        if (ctx.bank.inViewport()) {
            aTimer.setPeriod(Random.nextInt(500, 2000));
            if (aTimer.isTime()) {
                return ctx.bank.open();
            }
        } else {
            ctx.camera.turnTo(ctx.bank.nearest());
        }
        return false;
    }

    public boolean closeBank() {
        aTimer.setPeriod(Random.nextInt(500, 2000));
        if (aTimer.isTime()) {
            return ctx.bank.close();
        }
        return false;
    }

    public boolean withdraw(String itemName, int amount) {
        aTimer.setPeriod(Random.nextInt(800, 2000));
        if (aTimer.isTime()) {
            int itemID = 0;
            for (Item item : ctx.bank.select()) {
                if (item.name().equals(itemName)) {
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

    public Tile getTile() {
        return ctx.bank.nearest().tile();
    }
}
