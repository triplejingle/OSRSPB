package Scripts.Core;

import Scripts.Core.Interfaces.EnvironmentDefault;
import Scripts.Core.Interfaces.INPC;
import org.powerbot.script.rt4.ClientContext;

public class NPC extends EnvironmentDefault implements INPC{
    NPC npc;
    public NPC(ClientContext arg0,String name) {
        super(arg0,name);
    }

    @Override
    public void talkTo() {
        int triedIndex = getIndex();
        if( nrOfTries[triedIndex] ==maxTries){
            return;
        }
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
