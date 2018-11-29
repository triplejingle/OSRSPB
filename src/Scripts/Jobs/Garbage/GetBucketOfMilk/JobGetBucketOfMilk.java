package Scripts.Jobs.Garbage.GetBucketOfMilk;
import Scripts.Core.garbage.ItemIdCollection;
import Scripts.Tasks.Task;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;
//this code is used as example made by triplejingle.
public class JobGetBucketOfMilk extends PollingScript<ClientContext> {
    private static final Tile DairyCowToFaladorBank[] = {new Tile(3038, 3301, 0), new Tile(3038, 3306, 0), new Tile(3036, 3311, 0), new Tile(3032, 3314, 0), new Tile(3028, 3317, 0), new Tile(3023, 3318, 0), new Tile(3019, 3321, 0), new Tile(3014, 3320, 0), new Tile(3009, 3321, 0), new Tile(3008, 3326, 0), new Tile(3008, 3331, 0), new Tile(3008, 3336, 0), new Tile(3008, 3341, 0), new Tile(3008, 3346, 0), new Tile(3008, 3351, 0), new Tile(3008, 3356, 0), new Tile(3012, 3359, 0), new Tile(3015, 3355, 0)};
    private final Tile[] FaladorBankToDairyCow =  ctx.movement.newTilePath(DairyCowToFaladorBank).reverse().toArray();

    ItemIdCollection idCollection = new ItemIdCollection();
    List<Task> taskList = new ArrayList();

   @Override
    public void poll() {
      for(int i = 0;i<taskList.size();i++){
          Task task = taskList.get(i);
          if(task.activate()){
              task.execute();
              break;
          }
      }
    }


    @Override
    public void start(){
       taskList.add(new TaskWalkToBank(ctx,DairyCowToFaladorBank));
        taskList.add(new TaskGetBucketFromBank(ctx,idCollection.getBucket(),TRUE,getDestinationFromPath(DairyCowToFaladorBank),10));
       taskList.add(new TaskWalkToDairyCow(ctx,FaladorBankToDairyCow));
        taskList.add(new TaskGetMilkFromDairyCow(ctx,28,getDestinationFromPath(FaladorBankToDairyCow),10));
        taskList.add(new TaskBankBucketOfMilk(ctx,getDestinationFromPath(DairyCowToFaladorBank),10));
    }
    Tile getDestinationFromPath(Tile[] path){
       return path[path.length-1];
    }
    @Override
    public void stop(){
        System.out.println("asdasdad stop");
    }
}
