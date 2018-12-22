package scripts.planner;

import org.powerbot.script.rt4.ClientContext;

public class PlannerRunescape extends Planner {
    Planner planner;
    public PlannerRunescape(ClientContext arg0) {
        super(arg0);
    }

    @Override
    void chooseMethod() {
        switch (method){
            case 0:
                planner = new PlannerMining(ctx);
                IGoal = planner.plan();

                break;
            case 1:
                planner = new PlannerFishing(ctx);
                planner.plan();

                break;
        }
    }



}
