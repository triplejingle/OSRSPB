package Scripts.Core;

import Scripts.Core.Interfaces.EnvironmentDefault;
import Scripts.Core.Interfaces.IGroundItem;
import org.powerbot.script.rt4.ClientContext;

public class GroundItem extends EnvironmentDefault implements IGroundItem {
    org.powerbot.script.rt4.GroundItem groundItem;
    public GroundItem(ClientContext arg0, String name) {
        super(arg0, name);
    }

    @Override
    public void take() {
        for(org.powerbot.script.rt4.GroundItem item: ctx.groundItems.select().name(super.getName()).nearest()){
            if (item.inViewport() &&
                    item.tile().matrix(ctx).reachable()) {
                item.interact("Take");
            }
        }
    }

    @Override
    public boolean examine() {
        return   groundItem.interact("Examine");
    }

    @Override
    public boolean cancel() {
        return  groundItem.interact("cancel");
    }
}
