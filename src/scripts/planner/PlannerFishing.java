package scripts.planner;

import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import scripts.core.Player;
import scripts.core.goalmethods.IGoal;
import scripts.core.goalmethods.atomicgoal.*;
import scripts.core.goalmethods.compositegoal.*;
import scripts.core.selector.PlayerSelector;
import scripts.tools.FishingUserSettings;

// to implement get equipment harpoon, lobster cage karamja

public class PlannerFishing extends Planner {

    Player player = new Player(ctx, "its you but in code");

    FishingUserSettings settings = new FishingUserSettings(ctx);

    public PlannerFishing(ClientContext arg0) {
        super(arg0);
        IGoal = settings.getGoal();
    }

    public void chooseMethod() {
        String method = settings.getMethod();
        switch (method) {
            case "fish and bank":
                fishAndBank();
                break;
            case "power fish":
                powerFish();
                break;
        }
    }

    private void powerFish() {
        if (player.isInventoryFull()) {
            System.out.println("drop fish");
        } else {
            IGoal.addSubGoal(new GoalIdle(ctx, random.nextInt(3, 16)));
            IGoal.addSubGoal(new GoalFish(ctx, settings.getCorrespondingFishingSpot(settings.getTool())));
            setPathToFishingSpot(settings.getDestination());
        }
        if (!player.hasItem(settings.getCorrespondingTool(settings.getTool()))) {
            IGoal.addSubGoal(new GoalGetEquipment(ctx, settings.getCorrespondingTool(settings.getTool())));
            IGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getCorrespondingPathToBank(settings.getDestination())[0], 5));
        }
    }


    private void fishAndBank() {
        if (player.isInventoryFull()) {
            setPathToDepositItems(settings.getDestination(), settings.getTool());
        } else {
            IGoal.addSubGoal(new GoalIdle(ctx, random.nextInt(3, 16)));
            IGoal.addSubGoal(new GoalFish(ctx, settings.getCorrespondingFishingSpot(settings.getDestination())));
            setPathToFishingSpot(settings.getDestination());
        }
        if (!player.hasItem(settings.getCorrespondingTool(settings.getTool()))) {
            IGoal.addSubGoal(new GoalGetEquipment(ctx, settings.getCorrespondingTool(settings.getTool())));
            IGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getCorrespondingPathToBank(settings.getDestination())[0], 5));
        }
    }

    public void setPathToFishingSpot(String destination) {
        switch (destination) {
            case "Barbarian village":
                IGoal.addSubGoal(new GoalWalkToLocation(ctx,settings.getBankToBarbarianvillageFish(), 5));
                break;
            case "Draynor village":
                break;
            case "Karamja":
                IGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getShipToKaramjaFishSpot(), 5));
                IGoal.addSubGoal(new GoalIdle(ctx, Random.nextInt(1, 3)));
                IGoal.addSubGoal(new GoalClickOnGangplank(ctx));
                PlayerSelector playerSelector = new PlayerSelector(ctx);
                if (playerSelector.local().tile().distanceTo(settings.getDepositToSeaman()[3]) < 20) {
                    IGoal.addSubGoal(new GoalIdle(ctx, 20));
                    IGoal.addSubGoal(new GoalContinueChat(ctx));
                    IGoal.addSubGoal(new GoalSelectOption(ctx, "Yes please."));
                    IGoal.addSubGoal(new GoalContinueChat(ctx));
                    IGoal.addSubGoal(new GoalPayFareShipper(ctx));
                    if (Random.nextBoolean()) {
                        IGoal.addSubGoal(new GoalMoveToNpc(ctx, "Seaman Lorris"));
                    }
                    IGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getDepositToSeaman(), 5));
                }
                break;
        }
    }

    public void setPathToDepositItems(String desiredLocation, String tool) {
        switch (desiredLocation) {
            case "Barbarian village":
                IGoal.addSubGoal(new GoalBankAllItemsExcept(ctx, settings.getCorrespondingTool(tool), settings.getBarbarianVillageFishToEdgeBank()));
                IGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getBankToBarbarianvillageFish(), 5));
                break;
            case "Draynor village":
                IGoal.addSubGoal(new GoalBankAllItemsExcept(ctx, settings.getCorrespondingTool(tool),settings.getDraynorBank()));
                IGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getDraynorBank(), 5));
                break;
            case "Karamja":
                IGoal.addSubGoal(new GoalDepositAllItemsExcept(ctx, settings.getCorrespondingTool(tool), settings.getSeamanToDeposit()));
                IGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getSeamanToDeposit(), 5));
                IGoal.addSubGoal(new GoalIdle(ctx, Random.nextInt(1, 3)));
                IGoal.addSubGoal(new GoalClickOnGangplank(ctx));
                if (ctx.players.local().tile().distanceTo(settings.getShipToKaramjaFishSpot()[6]) < 50) {
                    IGoal.addSubGoal(new GoalIdle(ctx, Random.nextInt(8, 15)));
                    IGoal.addSubGoal(new GoalContinueChat(ctx));
                    IGoal.addSubGoal(new GoalSelectOption(ctx, "Ok."));//1
                    IGoal.addSubGoal(new GoalContinueChat(ctx));
                    IGoal.addSubGoal(new GoalSelectOption(ctx, "Search away, I have nothing to hide."));//2
                    IGoal.addSubGoal(new GoalContinueChat(ctx));
                    IGoal.addSubGoal(new GoalSelectOption(ctx, "Can i journey on this ship?"));//1
                    IGoal.addSubGoal(new GoalContinueChat(ctx));
                    IGoal.addSubGoal(new GoalPayFareCustomOfficer(ctx));
                    IGoal.addSubGoal(new GoalMoveToNpc(ctx, "Customs officer"));
                    IGoal.addSubGoal(new GoalWalkToLocation(ctx, settings.getShipToKaramjaFishSpot(), 5));
                }
                break;
        }
    }
}
