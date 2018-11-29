package Scripts.Core.garbage;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public class TabMethods  extends ClientAccessor {
    public TabMethods(ClientContext arg0) {
        super(arg0);
    }

    public void switchToTab(String tabs){
        RandomizerMethods randomizer = new RandomizerMethods(ctx);
        if(randomizer.activateRandom()){

        }
    }
}
