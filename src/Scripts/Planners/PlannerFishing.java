package Scripts.Planners;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class PlannerFishing extends Planner {
    public Tile[] BankToBarbarianvillageFish = {new Tile(3094, 3491, 0), new Tile(3090, 3488, 0), new Tile(3095, 3486, 0), new Tile(3098, 3482, 0), new Tile(3098, 3477, 0), new Tile(3098, 3472, 0), new Tile(3098, 3467, 0), new Tile(3093, 3465, 0), new Tile(3088, 3464, 0), new Tile(3088, 3459, 0), new Tile(3089, 3454, 0), new Tile(3090, 3449, 0), new Tile(3093, 3444, 0), new Tile(3097, 3441, 0), new Tile(3101, 3438, 0), new Tile(3103, 3433, 0)};
    public final Tile[] barbarianVillageFishToEdgeBank = ctx.movement.newTilePath(BankToBarbarianvillageFish).reverse().toArray();
    Tile[] fishDraynor = {new Tile(3090, 3232, 0)};
    Tile[] draynorBank = {new Tile(3094, 3243, 0)};

    public PlannerFishing(ClientContext arg0){
        super(arg0);
    }

    public void chooseMethod() {
        Random random = new Random();
        method =0; //random.nextInt(0,1);
        switch(method){
            case 0:
                System.out.println("fish and bank");
                fishAndBank();
            break;
        }
    }

    private void fishAndBank(){

    }
    private void bankInventory(){
        int fishingNet = 303;
        if(ctx.inventory.select().count()>=28) {

        }
    }
    private void getEquipment(){
        int fishingNet = 303;

    }
}
