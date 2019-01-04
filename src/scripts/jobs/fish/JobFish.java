package scripts.jobs.fish;

import org.powerbot.script.Script;
import org.powerbot.script.rt4.Constants;
import scripts.gui.FishingGui;
import scripts.core.JobCore;
import scripts.paint.BackGround;
import scripts.planner.PlannerFishing;
import scripts.tools.SkillStats;

import java.io.IOException;
import java.net.URISyntaxException;

@Script.Manifest(name = "Random newbies fisher", description = "for testing purposes", properties = "author=triplejingle; topic=999; client=4;")
public class JobFish extends JobCore{
    FishingGui fishingGui = new FishingGui();
    public JobFish() {
        SkillStats.setSkill(Constants.SKILLS_FISHING);
        planner = new PlannerFishing(ctx);
        ctx.properties.setProperty("randomevents.disable", "true");
    }

    @Override
    public void start() {
        while(!fishingGui.isSettingsSet()){
            //wait for input
        }
        planner.plan();

        addGoal(planner.getHighLevelGoal());
    }
}




