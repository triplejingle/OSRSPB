package scripts.jobs.playrunescape;

import org.powerbot.script.Script;
import scripts.Gui.FishingGui;
import scripts.core.JobCore;
import scripts.planner.PlannerFishing;
import scripts.tools.FishingUserSettings;

@Script.Manifest(name = "Random newbies fisher", description = "for testing purposes", properties = "author=triplejingle; topic=999; client=4;")
public class JobFish extends JobCore{
    FishingGui fishingGui = new FishingGui();
    @Override
    public void start() {
        while(!fishingGui.isSettingsSet()){
        }

        planner = new PlannerFishing(ctx);
        planner.setXpGoal(5000);
        planner.plan();

        setPlanner(planner);
        addGoal(planner.getHighLevelGoal());
        ctx.properties.setProperty("randomevents.disable", "true");
    }
}




