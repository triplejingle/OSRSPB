package Scripts.Jobs.PlayRunescape;

import Scripts.Core.JobCore;
import org.powerbot.script.Script;

@Script.Manifest(name = "AAAPlayRunescape", description = "for testing purposes", properties = "author=triplejingle; topic=999; client=4;")

public class JobPlayRunescape extends JobCore{

    @Override
    public void start() {
            taskList.add(new TaskPlayRunescape(ctx));
    }

}




