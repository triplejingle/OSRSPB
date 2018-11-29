package Scripts.Tasks;

import Scripts.Core.Bank;
import Scripts.Core.WalkerMethods;
import Scripts.Skills.Cooking;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
//ids afhandelen
public class TrainCooking extends ClientAccessor {
    Cooking cooking = new Cooking(ctx);
    WalkerMethods walkToRange = new WalkerMethods(ctx);
    WalkerMethods walkToBank = new WalkerMethods(ctx);
    Bank bank = new Bank(ctx);
    static final Tile[] bankToRangeEdge = {new Tile(3094, 3490, 0), new Tile(3090, 3490, 0), new Tile(3089, 3494, 0), new Tile(3086, 3498, 0), new Tile(3083, 3501, 0), new Tile(3080, 3498, 0), new Tile(3078, 3494, 0)};
     Tile[] rangeToBankEdge = ctx.movement.newTilePath(bankToRangeEdge).reverse().toArray();
     Random random = new Random();

    public TrainCooking(ClientContext arg0) {
        super(arg0);
        walkToBank.addPath(rangeToBankEdge);
        walkToRange.addPath(bankToRangeEdge);
    }
    public void cookingStove(){
        String rawFood = "Raw trout";
        if(ctx.inventory.select().name(rawFood).count()>=1){
            if(ctx.players.local().tile().distanceTo(rangeToBankEdge[0])>6){
                walkToRange.walkPath();
            }else if(ctx.players.local().tile().distanceTo(rangeToBankEdge[0])<5 &&rangeToBankEdge[0].matrix(ctx).reachable()){
                cooking.cookWithRange(rawFood);
            }else{
                walkToRange.handleObstacle();
            }
        }else{
            if(ctx.players.local().tile().distanceTo(bankToRangeEdge[0])<5) {
                bank.openBank();
                if(ctx.inventory.select().count()==0) {
                    bank.withdraw(555, random.nextInt(28,10000));
                    if(ctx.inventory.select().name(rawFood).count()>=1) {
                        bank.closeBank();
                    }else{
                        System.out.println("Script is done");
                    }
                }else{
                    bank.depositAllItems();
                }

            }else{
                walkToBank.walkPath();
            }
        }
    }

    Tile getDestinationFromPath(Tile[] path){
        return path[path.length-1];
    }

}
