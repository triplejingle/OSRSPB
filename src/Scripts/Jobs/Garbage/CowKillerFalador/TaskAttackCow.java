package Scripts.Jobs.Garbage.CowKillerFalador;

import Scripts.Core.Collection.Animations;
import Scripts.Core.Mobs;
import Scripts.Core.WalkerTMPMethods;
import Scripts.Core.garbage.ItemIdCollection;
import Scripts.Core.garbage.NpcIdCollection;
import Scripts.Core.garbage.*;
import Scripts.Tasks.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskAttackCow extends Task {
    ItemIdCollection idCollection = new ItemIdCollection();
    NpcIdCollection npcIdCollection = new NpcIdCollection();
    NPCMethods npcMethods = new NPCMethods(ctx);
    PlayerMethods playerMethods = new PlayerMethods(ctx);
    LocationMethods locationMethods = new LocationMethods(ctx);
    InventoryMethods inventoryMethods = new InventoryMethods(ctx);
    Animations aIdCollection = new Animations();
    WalkerTMPMethods walkerMethods=new WalkerTMPMethods(ctx);
    int tileRange;
    public TaskAttackCow(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx, taskTile, tileRange);
        super.setTaskTile(taskTile);
        this.tileRange = tileRange;
    }

    @Override
    public boolean activate() {
        if(ctx.players.local().inCombat()){
            return false;
        }

        boolean tmp1 =!playerMethods.healthLow(70);
        boolean tmp2 =!inventoryMethods.ctx.inventory.name("Cooked meat").isEmpty();
        boolean tmp3 =playerMethods.isPlayer(aIdCollection.getNothing());
        boolean tmp4 =locationMethods.currentLocationIsNearbyTile(super.getTaskTile(), tileRange);
        return tmp1&&tmp2&&tmp3&&tmp4;

    }

    @Override
    public void execute() {
        if(locationMethods.currentLocationIsNearbyTile(super.getTaskTile(),tileRange)) {
            if(isWithinTaskRange(npcMethods.getNpc().tile())) {
              Mobs mobs = new Mobs(ctx,"Cow");
              mobs.attack();
            }else{
                npcMethods.nextNpc();
            }
        } else{
            Tile[] pathtoTask = {super.getTaskTile()};
            walkerMethods.walkPath(pathtoTask);
        }
    }
}
