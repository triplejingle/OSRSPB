package scripts.tools.factory;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class BankFactory extends ClientAccessor{
    public BankFactory(ClientContext arg0) {
        super(arg0);
    }

    public Component getComponent(String component) {
        if (component == null) {
            return null;
        }
        switch (component.toLowerCase()) {
            case "screen":
                return ctx.widgets.widget(12).component(2);
            case "close":
                return ctx.widgets.widget(12).component(3).component(11);
                default:
                    return null;
        }
    }
}
