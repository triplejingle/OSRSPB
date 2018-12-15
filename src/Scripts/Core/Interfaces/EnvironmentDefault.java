package Scripts.Core.Interfaces;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public abstract class   EnvironmentDefault extends Core implements IDefault{
    public EnvironmentDefault(ClientContext arg0, String name) {
        super(arg0, name);
    }

    public EnvironmentDefault(ClientContext ctx) {
        super(ctx);
    }

    public void walkHere(Tile tile){

    }


}
