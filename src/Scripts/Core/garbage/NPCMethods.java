package Scripts.Core.garbage;

import Scripts.Core.Collection.Animations;
import Scripts.Tools.Queue;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.*;

import java.util.concurrent.Callable;

public class NPCMethods extends  ClientAccessor{

    Queue<Npc> queue = new Queue<>(5);

    PlayerMethods playerMethods = new PlayerMethods(ctx);
    Animations aIdCollection = new Animations();
    public NPCMethods(ClientContext ctx) {
        super(ctx);
    }


    public void attack(){ ;
            if (!isTargetingMe()&&queue.getCurrent().tile().matrix(ctx).reachable()&&!queue.getCurrent().inCombat()) {
                interactWith("Attack");
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return isTargetingMe();
                    }
                }, 1000, 3);
            }else if(isTargetingMe()&&queue.getCurrent().inCombat()){
                playerMethods.waitUntilOutOfCombat();
                nextNpc();
            }else {
                nextNpc();
            }
    }

    public boolean hasDied() {
       boolean tmp = getNpc().animation()==aIdCollection.getNpcDied();
       return tmp;
    }


    public boolean isTargetingMe(){
        return queue.getCurrent().interacting().name().equals(ctx.players.local().name());
    }


    public void interactWith(String action){
        if(queue.getCurrent().inViewport()) {
            queue.getCurrent().interact(action);
        }else{
            ctx.camera.turnTo(queue.getCurrent());
        }
    }

    public void selectNearestNpc(String npcName){
//        Npc npc = ctx.npcs.selectObstacle().name(npcName).nearest().poll();
//        stack.push(npc);
//
//        GameObject[] gameObjects = selectNearestObjects(objectName,action);
//        for(int i = 0 ;i<gameObjects.length;i++) {
//            if (isObjectReachable(gameObjects[i].tile())) {
//                queue.addToQueue(gameObjects[i]);
//                addBoundaryToObject();
//            }
//        }
    }
    public Npc createNpc(int npcId){
        return ctx.npcs.select().nearest().id(npcId).poll();
    }

    public void nextNpc(){
        queue.next();
    }
    public Npc getNpc(){
        return queue.getCurrent();
    }


}
