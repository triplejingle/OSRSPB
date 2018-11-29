package Scripts.Tools.Factory;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class ShopFactory extends ClientAccessor{
    public ShopFactory(ClientContext arg0) {
        super(arg0);
    }

    public Component getComponent(String component) {
        if (component == null) {
            return null;
        }
        switch (component.toLowerCase()) {
            case "screen":
                return ctx.widgets.widget(300).component(0);
            case "close":
                return ctx.widgets.widget(300).component(1).component(11);
        }
        return null;
    }
}
