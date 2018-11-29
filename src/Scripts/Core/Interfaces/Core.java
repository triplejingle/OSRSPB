package Scripts.Core.Interfaces;

import Scripts.Tools.ATimer;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public abstract class Core extends ClientAccessor {
    private String name;
    public int nrOfTries[] = {0,0,0,0,0,0,0,0,0,0,0,0} ;
    public int maxTries = 1;
    ATimer aTimer = new ATimer();
    int getIndex = 0;
    public int getIndex(){
        int giveIndex = getIndex;
        giveIndex++;
        return giveIndex;
    }
    public void stopScript(int triedIndex){
        aTimer.setPeriod(60000);
        if (aTimer.isTime()) {
            System.out.println("stopping script something went wrong");
            ctx.controller.stop();
        }
    }
    public String getName() {
        return name;
    }

    public Core(ClientContext arg0, String name) {
        super(arg0);
        this.name = name;
    }

}
