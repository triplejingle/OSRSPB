package Scripts.Skills;

import Scripts.Core.Collection.Animations;
import Scripts.Core.GoalMethods.Goal;
import Scripts.Tools.ATimer;
import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;

import java.util.ArrayList;
import java.util.List;


public class Mining extends ClientAccessor {
    int[] iron = {7488,7455};
    int[] tin = {7485};
    int[] copper = {7453};
    int[] coal = {7489, 7456};
    Animations animations = new Animations();
    ATimer timer = new ATimer();
    Random random = new Random();
    ArrayList<Integer> rockList = new ArrayList<>();
    List<Tile> customRocks = new ArrayList();
    public void setCustomRocks(List<Tile> customRocks) {
        this.customRocks = customRocks;
    }
    ArrayList<Goal> goalList = new ArrayList<>();
    ATimer mineTimer = new ATimer();
    public void mineAndBank(){


    }

    public Mining(ClientContext arg0) {
        super(arg0);
        addArrayToList(rockList, tin);
        addArrayToList(rockList, copper);
        addArrayToList(rockList, iron);
        addArrayToList(rockList, coal);
    }

    private void addArrayToList(ArrayList<Integer> rocklist, int[] rockids){
        for(int i = 0;i< rockids.length;i++){
            rocklist.add(rockids[i]);
        }
    }

    public void customMine(){
        if (timer.isTime()) {
            timer.setPeriod(random.nextInt(1000, 2000));
            for(int i = 0 ;i<customRocks.size();i++) {
                TileMatrix t = new TileMatrix(ctx, customRocks.get(i));
                GameObject rock = ctx.objects.select().at(t.tile()).poll();
                if(rockList.contains(rock.id())){
                    System.out.println(rock.id());
                        if (ctx.players.local().animation() == animations.getNothing() && ctx.inventory.select().count() < 28) {
                            if (rock.inViewport()) {
                                t.interact("Mine");
                                break;
                            }else{
                                ctx.camera.turnTo(rock);
                            }
                        }
                }
            }
        }
    }

    GameObject rocks;
    public boolean tmo(String rockName){
        rocks= getRock(rockName);
        if(rocks!=null) {
            mineTimer.setPeriodBetween(300,2000);
            if(mineTimer.isTime()) {
                if (ctx.players.local().animation() == animations.getNothing() && ctx.inventory.select().count() < 28) {
                    return rocks.interact("Mine");
                }
            }
        }
        return false;
    }

    private GameObject getRock(String rockName){
        switch(rockName) {
            case "Tin":
                //Fclose(distance)
                //FMedium(distance)
                //FFar(distance)

                //locationToRocks Close AND People_None Then VeryDesirable
                //locationToRocks Close AND People_AFew Then Undesirable
                //locationToRocks Close AND People_Low Then Undesirable

                //locationToRocks Medium AND People_None Then Desirable
                //locationToRocks Medium AND People_AFew Then Undesirable
                //locationToRocks Medium AND People_Low Then Undesirable

                //locationToRocks Far AND People_None Then Desirable
                //locationToRocks Far AND People_AFew Then Undesirable
                //locationToRocks Far AND People_Low Then Undesirable

                BasicQuery<GameObject> tmp =  ctx.objects.select().within(5).id(tin);

                if(tmp!=null){
                    GameObject tmp1 = tmp.poll();
                    return tmp1;
                }
                return null;
            case "Copper":
            BasicQuery<GameObject> copperRocks = ctx.objects.select().within(5).id(copper);
            if(copperRocks!=null){
                GameObject tmp1 = copperRocks.poll();
                return tmp1;
            }
            return null;
            case "Iron":
                BasicQuery<GameObject> ironRocks = ctx.objects.select().within(5).id(iron);
                if(ironRocks!=null){
                    GameObject tmp1 = ironRocks.poll();
                    return tmp1;
                }
                return null;
            case "Coal":
                BasicQuery<GameObject> coalRocks = ctx.objects.select().within(5).id(iron);
                if(coalRocks!=null){
                    GameObject tmp1 = coalRocks.poll();
                    return tmp1;
                }
                return null;
        }
        return null;
    }

    public boolean rockDissappeared() {
        GameObject rock = ctx.objects.select(rocks.tile(),0).peek();
        int tmp = rock.id();
        int tmp2= rocks.id();
        return rocks.id()!=rock.id();
    }
}