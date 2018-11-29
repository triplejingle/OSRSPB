package Scripts.Jobs.Garbage.Woodcutting;

import Scripts.Tasks.Task;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;


public class JobWoodcutting extends PollingScript<ClientContext>{

    List<Task> taskList = new ArrayList();

    @Override
    public void poll() {
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.activate()) {
                task.execute();
                break;
            }
        }
    }

        @Override
        public void start() {
            taskList.add(new TaskWoodcutting(ctx));
        }

}




