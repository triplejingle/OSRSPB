package Scripts.Core.Interfaces;

import Scripts.Tools.ATimer;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public abstract class Core extends ClientAccessor {
    private String name;

    public Core(ClientContext ctx) {
        super(ctx);
    }

    public void stopScript(){
        System.out.println("stopping script something went wrong");
        ctx.controller.stop();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Core(ClientContext arg0, String name) {
        super(arg0);
        this.name = name;
    }

}
