package scripts.planner;

import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import scripts.core.data.InventoryData;
import scripts.goal.atomicgoal.*;
import scripts.goal.compositegoal.*;
import scripts.core.selector.PlayerSelector;
import scripts.tools.FishingUserSettings;

// to implement get equipment harpoon, lobster cage karamja

public class PlannerFishing extends Planner {

    InventoryData inventory = new InventoryData(ctx);

    FishingUserSettings settings = new FishingUserSettings(ctx);
    PlayerSelector playerSelector = new PlayerSelector(ctx);
    public PlannerFishing(ClientContext arg0) {
        super(arg0);
    }

    public void chooseMethod() {
        iGoal = settings.getGoal();
        inventory.setInventory();
        String method = settings.getMethod();
        switch (method) {
            case "fish and bank":
                fishAndBank();
                break;
            case "power fish":
                powerFish();
                break;
            default:
                break;
        }
    }

    private void powerFish() {
        if (inventory.isFull()) {
            iGoal.addSubGoal(new GoalDropItemsAntiPattern(ctx, settings.getCorrespondingFish()));
            iGoal.addSubGoal(new GoalSwitchTab(ctx, Game.Tab.INVENTORY));
        } else {
            iGoal.addSubGoal(new GoalIdle(ctx, Random.nextInt(3, 16)));
            iGoal.addSubGoal(new GoalFish(ctx, settings.getCorrespondingFishingSpot()));
            setPathToFishingSpot(settings.getDestination());
        }
        if (!inventory.contains(settings.getCorrespondingTool())) {
            iGoal.addSubGoal(new GoalGetEquipment(ctx, settings.getCorrespondingTool()));
            iGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getCorrespondingPathToBank()[0], 5));
        }
    }


    private void fishAndBank() {
        if (inventory.isFull()) {
            setPathToDepositItems();
        } else {
            iGoal.addSubGoal(new GoalIdle(ctx, Random.nextInt(3, 16)));
            iGoal.addSubGoal(new GoalFish(ctx, settings.getCorrespondingFishingSpot()));
            setPathToFishingSpot(settings.getDestination());
        }
        if (!inventory.contains(settings.getCorrespondingTool())) {
            iGoal.addSubGoal(new GoalGetEquipment(ctx, settings.getCorrespondingTool()));
            iGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getCorrespondingPathToBank()[0], 5));
        }
    }

    public void setPathToFishingSpot(String destination) {
        switch (destination) {
            case "Barbarian village":
                iGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getBankToBarbarianvillageFish(), 5));
                break;
            case "Draynor village":
                break;
            case "Karamja":
                //effect fish spot
                //requirement standing near ship
                //kost 5
                //if near karamja
                playerSelector.local();
                System.out.println(ctx.movement.distance(settings.getShipToKaramjaFishSpot()[2],PlayerSelector.getPlayer().tile()));
                if (ctx.movement.distance(settings.getShipToKaramjaFishSpot()[2],PlayerSelector.getPlayer().tile())<50) {
                    iGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getShipToKaramjaFishSpot(), 5));

                    //effect get off ship
                    // bein on ship
                    //kost 1
                    iGoal.addSubGoal(new GoalIdle(ctx, Random.nextInt(1, 3)));
                    iGoal.addSubGoal(new GoalClickOnGangplank(ctx));
                }
                //go to karamja
                //talking to shipper
                //kost 3

                if (playerSelector.getPlayer().tile().distanceTo(settings.getDepositToSeaman()[3]) < 50) {
                    iGoal.addSubGoal(new GoalIdle(ctx, Random.nextInt(10, 15)));
                    iGoal.addSubGoal(new GoalContinueChat(ctx));
                    iGoal.addSubGoal(new GoalSelectOption(ctx, "Yes please."));
                    iGoal.addSubGoal(new GoalContinueChat(ctx));
                    iGoal.addSubGoal(new GoalClickOnGangplank(ctx));
                    //go to karamje
                    //near shipper and not talking to shipper
                    // kost 5
                    iGoal.addSubGoal(new GoalPayFareShipper(ctx));
                    if (Random.nextBoolean()) {
                        iGoal.addSubGoal(new GoalMoveToNpc(ctx, "Seaman Lorris"));
                    }
                    //go to seaman
                    //near seaman but not close enough
                    //kost 10
                    iGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getDepositToSeaman(), 5));
                }
                break;
            default:
                break;
        }
    }

    public void setPathToDepositItems() {
        switch (settings.getDestination()) {
            case "Barbarian village":
                iGoal.addSubGoal(new GoalBankAllItemsExcept(ctx, settings.getCorrespondingTool(), settings.getBarbarianVillageFishToEdgeBank()));
                iGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getBankToBarbarianvillageFish(), 5));
                break;
            case "Draynor village":
                iGoal.addSubGoal(new GoalBankAllItemsExcept(ctx, settings.getCorrespondingTool(), settings.getDraynorBank()));
                iGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getDraynorBank(), 5));
                break;
            case "Karamja":
                iGoal.addSubGoal(new GoalDepositAllItemsExcept(ctx, settings.getCorrespondingTool(), settings.getSeamanToDeposit()));
                iGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getSeamanToDeposit(), 5));
                iGoal.addSubGoal(new GoalIdle(ctx, Random.nextInt(1, 3)));
                iGoal.addSubGoal(new GoalClickOnGangplank(ctx));
                if (ctx.players.local().tile().distanceTo(settings.getShipToKaramjaFishSpot()[6]) < 50) {
                    iGoal.addSubGoal(new GoalIdle(ctx, Random.nextInt(10, 15)));
                    iGoal.addSubGoal(new GoalContinueChat(ctx));
                    iGoal.addSubGoal(new GoalSelectOption(ctx, "Ok."));//1
                    iGoal.addSubGoal(new GoalContinueChat(ctx));
                    iGoal.addSubGoal(new GoalSelectOption(ctx, "Search away, I have nothing to hide."));//2
                    iGoal.addSubGoal(new GoalContinueChat(ctx));
                    iGoal.addSubGoal(new GoalSelectOption(ctx, "Can i journey on this ship?"));//1
                    iGoal.addSubGoal(new GoalContinueChat(ctx));
                    iGoal.addSubGoal(new GoalPayFareCustomOfficer(ctx));
                    iGoal.addSubGoal(new GoalMoveToNpc(ctx, "Customs officer"));
                    iGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getKaramjaFishSpotToShip(), 5));
                }
                break;
            default:
                break;
        }
    }
}
