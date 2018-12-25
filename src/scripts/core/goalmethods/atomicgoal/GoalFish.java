package scripts.core.goalmethods.atomicgoal;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import scripts.core.NPC;
import scripts.core.Player;
import scripts.core.data.NpcData;
import scripts.core.data.StatsData;
import scripts.core.selector.NpcSelector;

public class GoalFish extends AtomicGoal {
    private String fishingSpot;
    private Player player = new Player(ctx, "its you but in code!:)");
    NpcSelector npcSelector = new NpcSelector(ctx);
    NPC npc = new NPC(ctx);
    public GoalFish(ClientContext arg0, String fishingSpot) {
        super(arg0);
        this.fishingSpot=fishingSpot;
    }
    @Override
    protected void setup() {
        if(setup){
            setup=false;
            activateTimer.setPeriodBetween(8000,10000);
            NpcData.setNpc(npcSelector.select().name("Rod Fishing spot").nearest().poll());
            System.out.println("i'm fishing");
            goal="fish"+ System.currentTimeMillis()/1000;
            StatsData.addSkill(Constants.SKILLS_FISHING);
        }
    }

    public  void activate() {
        if(madeAttempt==false) {
            if (npc.interact(fishingSpot)) {
                madeAttempt = true;
                executeTimer.setPeriodBetween(10000,20000);
            }
        }
    }

    public boolean goalReached() {
        return !player.isDoingNothing();
    }

    public  void terminate(){

    }

    public boolean isStuck() {
        if(!player.isDoingNothing()) {
            return false;
        }
        if(madeAttempt){
            return  executeTimer.isTime();
        }
        return activateTimer.isTime();
    }
}
