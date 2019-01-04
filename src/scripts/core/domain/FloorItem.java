package scripts.core.domain;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;
import scripts.core.interfaces.EnvironmentDefault;
import scripts.core.interfaces.IFloorItem;

public class FloorItem extends EnvironmentDefault implements IFloorItem {
    private GroundItem groundItem;

    public FloorItem(ClientContext arg0, String name) {
        super(arg0, name);
    }

    @Override
    public void take() {
        for (GroundItem item : ctx.groundItems.select().name(super.getName()).nearest()) {
            if (item.inViewport() &&
                    item.tile().matrix(ctx).reachable()) {
                item.interact("Take");
            }
        }
    }

    @Override
    public boolean examine() {
        return groundItem.interact("Examine");
    }

    @Override
    public boolean cancel() {
        return groundItem.interact("cancel");
    }
}
