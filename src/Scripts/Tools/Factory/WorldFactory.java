package Scripts.Tools.Factory;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class WorldFactory extends ClientAccessor{

    public WorldFactory(ClientContext arg0) {
        super(arg0);
    }

    public Component getComponent(String component){
        if(component==null){
            return null;
        }
        switch(component.toLowerCase()){
            case "world 1":
                return ctx.widgets.widget(69).component(8).component(0);
            case "world 2":
                return ctx.widgets.widget(69).component(8).component(6);
            case "world 3":
                return ctx.widgets.widget(69).component(8).component(12);
            case "world 4":
                return ctx.widgets.widget(69).component(8).component(18);
            case "world 5":
                return ctx.widgets.widget(69).component(8).component(24);
            case "world 6":
                return ctx.widgets.widget(69).component(8).component(32);
            case "world 7":
                return ctx.widgets.widget(69).component(8).component(38);
            case "world 8":
                return ctx.widgets.widget(69).component(8).component(42);
        }
        return null;
    }
    //component factory

}

