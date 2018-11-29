package Scripts.Jobs.Garbage.CowKillerFalador;

import Scripts.Core.garbage.GroundItemIdCollection;
import Scripts.Tasks.Task;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;
// The code is used as example code by triplejingle
public class JobCowKillerFalador extends PollingScript<ClientContext> {

        List<Task> taskList = new ArrayList();
        GroundItemIdCollection groundItemIdCollection = new GroundItemIdCollection();


        @Override
        public void poll() {
            for(int i = 0;i<taskList.size();i++){
                Task task = taskList.get(i);
                if(task.activate()){
                    task.execute();
                }
            }
        }

        @Override
        public void start(){
            Tile combatTile = new Tile(3031,3305,0);
            Tile interactWithObject = new Tile(3039,3300,0);
            int tileRange =12;
//              taskList.add(new TaskPickUpItem(ctx,combatTile,tileRange));
//               taskList.add(new TaskDropItem(ctx));
//            taskList.add(new TaskEatWhenHealthIsLow(ctx));
            taskList.add(new TaskAttackCow(ctx,combatTile,tileRange));
        }

        Tile getDestinationFromPath(Tile[] path){
            return path[path.length-1];
        }

    }


