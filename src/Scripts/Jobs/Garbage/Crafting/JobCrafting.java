package Scripts.Jobs.Garbage.Crafting;

import Scripts.Core.JobCore;

import org.powerbot.script.Script;

//bepaal welke taak er wordt uitgevoerd
public class JobCrafting extends JobCore{

    @Override
    public void start() {
        taskList.add(new Scripts.Jobs.Garbage.Crafting.TaskCrafting(ctx));
    }

}




