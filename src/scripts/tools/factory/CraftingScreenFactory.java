package scripts.tools.factory;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class CraftingScreenFactory extends ClientAccessor {
    public CraftingScreenFactory(ClientContext arg0) {
        super(arg0);
    }
    public Component getComponent(String component) {
        if (component == null) {
            return null;
        }
        switch (component) {
            case "ring":
                return ctx.widgets.widget(446).component(7);
            case "Sapphire ring":
                return ctx.widgets.widget(446).component(8);
            case "g ring":
                return ctx.widgets.widget(446).component(9);
            case "f ring":
                return ctx.widgets.widget(446).component(10);
            case "e ring":
                return ctx.widgets.widget(446).component(11);
            case "d ring":
                return ctx.widgets.widget(446).component(12);
            case "c ring":
                return ctx.widgets.widget(446).component(13);
            case "b ring":
                return ctx.widgets.widget(446).component(14);
            case "a":
                return ctx.widgets.widget(446).component(15);
        }
        return  null;
    }
}
