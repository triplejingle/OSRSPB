package Scripts.Player;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Inventory;

public class Player extends ClientAccessor {
    Inventory inventory = ctx.inventory;
    public Player(ClientContext arg0) {
        super(arg0);
    }
    //public void checkStats()
}
