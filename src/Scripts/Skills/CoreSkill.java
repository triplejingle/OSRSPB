package Scripts.Skills;

import Scripts.Tools.ATimer;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public abstract class CoreSkill extends ClientAccessor{
    ATimer ATimer = new ATimer();
    public CoreSkill(ClientContext arg0, int period) {
        super(arg0);
        ATimer.setPeriod(period);
    }
    public CoreSkill(ClientContext arg0) {
        super(arg0);
    }

}
