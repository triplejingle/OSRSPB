package Scripts.Jobs.Garbage.Smithing;

import Scripts.Core.JobCore;
import org.powerbot.script.Script;


public class JobSmithing extends JobCore{

        @Override
        public void start() {
            taskList.add(new TaskSmith(ctx));
        }

}




