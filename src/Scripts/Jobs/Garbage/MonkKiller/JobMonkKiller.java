package Scripts.Jobs.Garbage.MonkKiller;

import Scripts.Core.JobCore;
import org.powerbot.script.Script;

public class JobMonkKiller extends JobCore{

        @Override
        public void start() {
            taskList.add(new TaskKillMonk(ctx));
        }

}




