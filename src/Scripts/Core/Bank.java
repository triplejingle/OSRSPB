package Scripts.Core;


import Scripts.Core.Interfaces.Core;
import Scripts.Core.garbage.InventoryMethods;
import Scripts.Tools.ATimer;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;

public class Bank extends Core {

    InventoryMethods inventoryMethods = new InventoryMethods(ctx);
    ATimer withdrawATimer = new ATimer();
    ATimer openATimer = new ATimer();
    ATimer depositATimer = new ATimer();
    Random random = new Random();

    public Bank(ClientContext ctx) {
        super(ctx,"Bank");
    }

    public boolean bankWindowOpenend(){
        return ctx.bank.opened();
    }

    public void depositAmount(String itemName, int amount){
        int triedIndex = 0;
        int numberOfItemsInInventory = ctx.inventory.select().name(itemName).count();
        int noOfDesiredItems = numberOfItemsInInventory-amount;
        if(numberOfItemsInInventory==noOfDesiredItems){
            nrOfTries[triedIndex] = 0;
        }
        if(nrOfTries[triedIndex]>=maxTries){
            stopScript(triedIndex);
            return;
        }
        if(bankWindowOpenend()){
            if(amount == inventoryMethods.getMaxInventorySpace()) {
                depositAllItems();
            }else  {
                if(ctx.bank.deposit(ctx.inventory.name(itemName).peek().id(), amount)){
                    nrOfTries[triedIndex]++;
                }
            }
        }
    }

    public void depositAllItems(){
        if (bankWindowOpenend()) {
            depositATimer.setPeriod(random.nextInt(1000, 3000));
            if (depositATimer.isTime()) {
               ctx.bank.depositInventory();
            }
        }

    }
    ATimer aTimer = new ATimer();

    public void openBank(){
        int triedIndex = 2;
        if(bankWindowOpenend()){
            nrOfTries[triedIndex]=0;
        }
        if(nrOfTries[triedIndex]>=maxTries){
            stopScript(triedIndex);
            return;
        }
        if(!bankWindowOpenend()) {
            if (ctx.bank.inViewport()) {
                openATimer.setPeriod(random.nextInt(500,2000));
                if(openATimer.isTime()) {
                    if(ctx.bank.open()){
                        nrOfTries[triedIndex]++;
                    }
                }
            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }
        }
    }
    public void closeBank(){
        int triedIndex=3;
        if(!bankWindowOpenend()){
            nrOfTries[triedIndex] = 0;
        }
        if(nrOfTries[triedIndex]>=maxTries){
            stopScript(triedIndex);
            return;
        }
        if(bankWindowOpenend()) {
            if (ctx.bank.inViewport()) {
                if(ctx.bank.close()){
                    nrOfTries[triedIndex]++;
                }
            }
        }
        if(!bankWindowOpenend()){
            nrOfTries[triedIndex]=0;
        }
    }

    public void withdraw(int itemId, int amount){
        int triedIndex = 4;
        if(ctx.inventory.select().id(itemId).count()>0){
            nrOfTries[triedIndex]=0;
        }
        stopScript(triedIndex);
        if(bankWindowOpenend()) {
            withdrawATimer.setPeriod(random.nextInt(800,2000));
            if(withdrawATimer.isTime()) {
                if(ctx.inventory.select().count()<28) {
                    if (ctx.bank.withdraw(itemId, amount)) {
                        nrOfTries[triedIndex]++;
                    }
                }
            }
        }
    }

    public boolean inViewport() {
        return ctx.bank.inViewport();
    }

    public boolean isPlayerNearBank() {
        return ctx.players.local().tile().distanceTo(ctx.bank.nearest())>20;
    }
}
