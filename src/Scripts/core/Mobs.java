package scripts.core;

import org.powerbot.script.rt4.BasicQuery;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import scripts.core.interfaces.EnvironmentDefault;
import scripts.core.interfaces.IMob;

import java.util.ArrayList;

public class Mobs extends EnvironmentDefault implements IMob {
    BasicQuery<Npc> queryList;
    int queueSize = 5;
    ArrayList<Npc> targets = new ArrayList<>(queueSize);
    public Npc target;

    public Mobs(ClientContext arg0, String name) {
        super(arg0,name);
    }

    public Mobs select(){
        for(int i = 0;i<targets.size();i++){
            targets.remove(i);
        }
       queryList = ctx.npcs.select().name(super.getName());
        for(Npc mob:queryList){
            targets.add(mob);
        }
        return this;
    }

    public Mobs nearest(){
        targets.add(queryList.nearest().poll());
        return this;
    }

    public Mobs within(int tiles){
        for(int i = 0;i<targets.size();i++){
            targets.remove(i);
        }
        queryList =  queryList.within(tiles);
        for(Npc mob: queryList){
            targets.add(mob);
            if(targets.size()==queueSize){
                return this;
            }
        }
        return this;
    }

    public void poll(){
        targets.add( queryList.poll());
    }

    public Mobs notInCombat(){
        if(targets.size()==0){
            return this;
        }
        for(int i = 0;i< targets.size();i++){
            if(targets.get(i).inCombat()){
                targets.remove(targets.get(i));
            }
        }
        return this;
    }

    public Mobs reachable(){
        if(targets.size()==0){
            return this;
        }
        for(Npc mob: targets){
            if(!mob.tile().matrix(ctx).reachable()){
                targets.remove(mob);
            }
        }
       return this;
    }

    public void attack(){
        getFirstTarget();
        if (target.inViewport()) {
           target.interact("Attack", super.getName());
        }

    }
    public Npc getFirstTarget(){
        int first = 0;
        if(targets.size()>0) {
            if ( target == null ) {
                target = targets.get(first);
                return target;
            }
        }
        return target;
    }
    @Override
    public boolean examine() {
        return false;
    }

    @Override
    public boolean cancel() {
        return false;
    }

    public boolean inViewPort(){
        if(target ==null){
            return false;
        }
        boolean tmp = target.inViewport();
        return tmp;
    }

    public int healthInPercentage(){
        if(target !=null) {
            return target.healthPercent();
        }
        return 0;
    }

    public boolean isMobDead(){
        if(target !=null) {
            int tmp = target.healthPercent();
           return target.healthPercent()<=0;
        }
        return false;
    }

    public boolean inCombatWithMe() {
        if(target !=null){
            return target.interacting().name().equals(ctx.players.local().name());
        }
        return false;
    }

    public boolean inCombat(){
        if(target !=null) {
            Boolean tmp= target.inCombat();
            return tmp;
        }
        return false;
    }

    public boolean isReachable() {
        if(target !=null) {
            return target.tile().matrix(ctx).reachable();
        }
        return false;
    }
}
