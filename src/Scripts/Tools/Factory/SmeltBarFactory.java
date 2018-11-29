package Scripts.Tools.Factory;

import org.powerbot.bot.rt4.client.Client;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class SmeltBarFactory extends ClientAccessor {
    public SmeltBarFactory(ClientContext arg0) {
        super(arg0);
    }
    public Component getComponent(String component){
        if(component==null){
            return null;
        }
        switch(component){
            case "Bronze bar":
                return ctx.widgets.widget(270).component(14).component(29);
            case "Iron bar":
                return ctx.widgets.widget(270).component(15).component(29);
            case "Silver bar":
                return ctx.widgets.widget(270).component(16).component(29);
            case "Steel bar":
                return ctx.widgets.widget(270).component(17).component(29);
            case "Gold bar":
                return ctx.widgets.widget(270).component(18).component(29);
            case "Mithril bar":
                return ctx.widgets.widget(270).component(19).component(29);
            case "Adamant bar":
                return ctx.widgets.widget(270).component(20).component(29);
            case "Runite bar":
                return ctx.widgets.widget(270).component(21).component(29);
        }
        return null;
    }
}
