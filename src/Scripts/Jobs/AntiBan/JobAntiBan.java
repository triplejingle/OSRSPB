package Scripts.Jobs.AntiBan;

import Scripts.Core.JobCore;
import Scripts.Tasks.Task;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name = "AntiBan", description = "develop antiban", properties = "author=triplejingle; topic=999; client=4;")
public class JobAntiBan extends JobCore{

        @Override
        public void start() {
            taskList.add(new TaskAntiBan(ctx));
        }

}




