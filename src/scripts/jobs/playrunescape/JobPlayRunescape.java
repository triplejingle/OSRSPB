package scripts.jobs.playrunescape;

import org.powerbot.script.Script;
import scripts.Gui.FishingGui;
import scripts.core.JobCore;
import scripts.planner.PlannerFishing;

@Script.Manifest(name = "playRunescape", description = "for testing purposes", properties = "author=triplejingle; topic=999; client=4;")
public class JobPlayRunescape extends JobCore{
    FishingGui fishingGui = new FishingGui();
    @Override
    public void start() {
        String[] userSettings =null;
        while(userSettings==null){
            userSettings= fishingGui.getUserSettings();
        }
        planner = new PlannerFishing(ctx,userSettings);
        planner.setXpGoal(5000);
        planner.plan();

        setPlanner(planner);
        addGoal(planner.getHighLevelGoal());
        ctx.properties.setProperty("randomevents.disable", "true");
    }
}




