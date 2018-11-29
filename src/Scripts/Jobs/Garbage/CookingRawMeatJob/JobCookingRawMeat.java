package Scripts.Jobs.Garbage.CookingRawMeatJob;

import Scripts.Tasks.Task;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;
public class JobCookingRawMeat extends PollingScript<ClientContext> {


        List<Task> taskList = new ArrayList();

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
            Tile combatTile = new Tile(3037,3301,0);
            int tileRange =5;
            taskList.add(new TaskgetWood(ctx,combatTile,tileRange));
            taskList.add(new TaskCreateFire(ctx,combatTile,tileRange));
            taskList.add(new TaskCookRawMeat(ctx,combatTile,tileRange));
        }




}
