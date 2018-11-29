package Scripts.Tools.Factory;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class RunesFactory extends ClientAccessor{
    public RunesFactory(ClientContext arg0) {
        super(arg0);
    }

    public Component getComponent(String component){
        if(component==null){
            return null;
        }
        switch(component){
            case "Fire rune":
                return ctx.widgets.widget(300).component(16).component(1);
            case "Water rune":
                return ctx.widgets.widget(300).component(16).component(2);
            case "Air rune":
                return ctx.widgets.widget(300).component(16).component(3);
            case "Earth rune":
                return ctx.widgets.widget(300).component(16).component(4);
            case "Mind rune":
                return ctx.widgets.widget(300).component(16).component(5);
            case "Body rune":
                return ctx.widgets.widget(300).component(16).component(6);
            case "Chaos rune":
                return ctx.widgets.widget(300).component(16).component(7);
            case "Death rune":
                return ctx.widgets.widget(300).component(16).component(8);
            case "Fire rune pack":
                return ctx.widgets.widget(300).component(16).component(9);
            case "Water rune pack":
                return ctx.widgets.widget(300).component(16).component(10);
            case "Air rune pack":
                return ctx.widgets.widget(300).component(16).component(11);
            case "Earth rune pack":
                return ctx.widgets.widget(300).component(16).component(12);
            case "Mind rune pack":
                return ctx.widgets.widget(300).component(16).component(13);
            case "Chaos rune pack":
                return ctx.widgets.widget(300).component(16).component(14);
        }
        return null;
    }
}
