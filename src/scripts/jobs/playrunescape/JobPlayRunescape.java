package scripts.jobs.playrunescape;

import org.powerbot.script.Script;
import scripts.core.JobCore;
import scripts.planner.PlannerFishing;

@Script.Manifest(name = "playRunescape", description = "for testing purposes", properties = "author=triplejingle; topic=999; client=4;")
public class JobPlayRunescape extends JobCore{

    @Override
    public void start() {
        planner = new PlannerFishing(ctx);
        planner.setXpGoal(5000);
        planner.plan();

        setPlanner(planner);
        addGoal(planner.getHighLevelGoal());
        ctx.properties.setProperty("randomevents.disable", "true");
    }
}




