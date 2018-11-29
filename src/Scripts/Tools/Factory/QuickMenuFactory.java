package Scripts.Tools.Factory;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class QuickMenuFactory extends ClientAccessor{
    public QuickMenuFactory(ClientContext arg0) {
        super(arg0);
    }
    public Component getComponent(String component) {
        if (component == null) {
            return null;
        }
        switch (component) {
            case "prayer":
                return ctx.widgets.widget(160).component(18);
            case "run":
                return ctx.widgets.widget(160).component(27);
            case "energy":
                return ctx.widgets.widget(160).component(23);
        }
        return null;
    }
}
