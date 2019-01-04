package scripts.tools.factory;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class PrayerTabFactory extends ClientAccessor{
    public PrayerTabFactory(ClientContext arg0) {
        super(arg0);
    }

    public Component getComponent(String component) {
        if (component == null) {
            return null;
        }
        switch (component) {
            case "Thick Skin":
                return ctx.widgets.widget(541).component(4);
            case "Burst of Strength":
                return ctx.widgets.widget(541).component(5);
            case "Clarity of Thought":
                return ctx.widgets.widget(541).component(6);
            case "a":
                return ctx.widgets.widget(541).component(7);
            case "b":
                return ctx.widgets.widget(541).component(8);
            case "d":
                return ctx.widgets.widget(541).component(9);
            case "e":
                return ctx.widgets.widget(541).component(10);
            case "f":
                return ctx.widgets.widget(541).component(11);
            case "g":
                return ctx.widgets.widget(541).component(12);
            case "h":
                return ctx.widgets.widget(541).component(13);
            case "j":
                return ctx.widgets.widget(541).component(14);
            case "1":
                return ctx.widgets.widget(541).component(15);
            case "2":
                return ctx.widgets.widget(541).component(16);
            case "3":
                return ctx.widgets.widget(541).component(17);
            case "4":
                return ctx.widgets.widget(541).component(18);
            case "5":
                return ctx.widgets.widget(541).component(19);
            case "6":
                return ctx.widgets.widget(541).component(20);
            case "7":
                return ctx.widgets.widget(541).component(21);
            case "8":
                return ctx.widgets.widget(541).component(22);
            case "9":
                return ctx.widgets.widget(541).component(23);
            case "10":
                return ctx.widgets.widget(541).component(24);
            case "11":
                return ctx.widgets.widget(541).component(25);
            case "12":
                return ctx.widgets.widget(541).component(26);
            case "13":
                return ctx.widgets.widget(541).component(27);
            case "14":
                return ctx.widgets.widget(541).component(28);
            case "15":
                return ctx.widgets.widget(541).component(29);
            case "16":
                return ctx.widgets.widget(541).component(30);
            case "17":
                return ctx.widgets.widget(541).component(31);
            case "18":
                return ctx.widgets.widget(541).component(32);
            default:
                return null;
        }
    }

}
