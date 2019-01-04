package scripts.core.domain;

import org.powerbot.script.rt4.ClientContext;
import scripts.core.data.NpcData;
import scripts.core.interfaces.EnvironmentDefault;

public class NPC extends EnvironmentDefault {
    private NpcData npcData ;

    public NPC(ClientContext arg0) {
        super(arg0);
        this.npcData = new NpcData(ctx);
    }

    public boolean talkTo() {
        return npcData.interact("Talk to");
    }

    @Override
    public boolean examine() {
        return npcData.interact("Examine");
    }

    @Override
    public boolean cancel() {
        return npcData.interact("Cancel");
    }

    public boolean interact(String action) { return npcData.interact(action); }

    public boolean trade() {
        return npcData.interact("Trade");
    }
}

