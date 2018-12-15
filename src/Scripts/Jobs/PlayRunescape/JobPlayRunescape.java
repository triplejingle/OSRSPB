package Scripts.Jobs.PlayRunescape;

import Scripts.Core.JobCore;
import Scripts.Planners.Planner;
import Scripts.Planners.PlannerRunescape;
import org.powerbot.script.Script;

@Script.Manifest(name = "playRunescape", description = "for testing purposes", properties = "author=triplejingle; topic=999; client=4;")
public class JobPlayRunescape extends JobCore{

    @Override
    public void start() {
        planner = new PlannerRunescape(ctx);
        planner.setXpGoal(5000);
        planner.plan();

        setPlanner(planner);
        addGoal(planner.getHighLevelGoal());
        ctx.properties.setProperty("randomevents.disable","True");
    }
}




