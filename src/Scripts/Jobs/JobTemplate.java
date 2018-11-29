package Scripts.Jobs;

import Scripts.Core.JobCore;
import org.powerbot.script.Script;
@Script.Manifest(name = "template task", description = "for testing purposes", properties = "author=triplejingle; topic=999; client=4;")

public class JobTemplate extends JobCore{

    @Override
    public void start() {
            taskList.add(new TaskTemplate(ctx));
        }

}




