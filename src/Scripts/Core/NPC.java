package Scripts.Core;

import Scripts.Core.Interfaces.EnvironmentDefault;
import org.powerbot.script.rt4.ClientContext;

public class NPC extends EnvironmentDefault{
    NPC npc;
    public NPC(ClientContext arg0,String name) {
        super(arg0,name);
    }

    public void talkTo() {

    }

    @Override
    public boolean examine() {
       return false;
    }

    @Override
    public boolean cancel() {
        return false;
    }
}
