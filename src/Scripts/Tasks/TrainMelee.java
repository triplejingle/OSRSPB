package Scripts.Tasks;


import Scripts.Core.Player;
import Scripts.Core.WalkerMethods;
import Scripts.Skills.Melee;
import Scripts.Tools.ATimer;
import Scripts.Tools.Factory.QuickMenuFactory;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

//naar locaties en uitvoeren van globale acties
public class TrainMelee extends ClientAccessor  {
    Tile taskTile = ctx.players.local().tile();

    QuickMenuFactory quickMenu = new QuickMenuFactory(ctx);
    int areaWidth = 50;
    Melee melee = new Melee(ctx);
    WalkerMethods walkerMethods = new WalkerMethods(ctx);
    private Random random = new Random();
    int nrofTries = 0;
    ATimer ATimer = new ATimer();
    Player player = new Player(ctx,"its you but in code!");
    public TrainMelee(ClientContext arg0) {
        super(arg0);
        System.out.println(ctx.players.local().tile());
        walkerMethods.addPath(new Tile[]{ctx.players.local().tile()});

    }

    //stoppen met acties wanneer typen en gebruiken van muis
    //player has equipment
    //equipment halenx
    int energy = random.nextInt(20,50);

    public void killSingleTarget(){
        String mob =  "Goblin";
        String food = "Salmon";
        int eatAtHealthInPercent = 50;
        int runWhenEnergyabove = 50;
        if(playerInArea()&& taskTile.matrix(ctx).reachable()) {
            if(ctx.players.local().healthPercent()>eatAtHealthInPercent) {
                melee.killInSingleArea(mob);
            }else  if(food!=""&&ctx.inventory.select().name(food).count()>0) {
                melee.eatWhenHealthIsBelow(eatAtHealthInPercent, food);
            }else {
             //banken
            }
            player.run(runWhenEnergyabove);
        }else {
            player.run(runWhenEnergyabove);
            ATimer.setPeriod(2000);
            walkBackToLocation();
        }
    }

    private boolean playerInArea() {
        return taskTile.distanceTo(ctx.players.local().tile())<areaWidth;
    }

    public void killMultiTarget(){

    }

    void walkBackToLocation(){//temporary placeholder
        walkerMethods.walkPath();
    }

    public void killAfk(){

    }
}
