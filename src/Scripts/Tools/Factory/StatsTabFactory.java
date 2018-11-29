package Scripts.Tools.Factory;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class StatsTabFactory extends ClientAccessor {

    public StatsTabFactory(ClientContext arg0) {
        super(arg0);
    }

    public Component getComponent(String component) {
        if (component == null) {
            return null;
        }
        switch (component) {
            case "Attack":
                return ctx.widgets.widget(320).component(1);
            case "Strength":
                return ctx.widgets.widget(320).component(2);
            case "Defence":
                return ctx.widgets.widget(320).component(3);
            case "Ranged":
                return ctx.widgets.widget(320).component(4);
            case "Prayer":
                return ctx.widgets.widget(320).component(5);
            case "Magic":
                return ctx.widgets.widget(320).component(6);
            case "Runecrafting":
                return ctx.widgets.widget(320).component(7);
            case "Construction":
                return ctx.widgets.widget(320).component(8);
            case "Hitpoints":
                return ctx.widgets.widget(320).component(9);
            case "Agility":
                return ctx.widgets.widget(320).component(10);
            case "Herblore":
                return ctx.widgets.widget(320).component(11);
            case "Thieving":
                return ctx.widgets.widget(320).component(12);
            case "Crafting":
                return ctx.widgets.widget(320).component(13);
            case "Fletching":
                return ctx.widgets.widget(320).component(14);
            case "Slayer":
                return ctx.widgets.widget(320).component(15);
            case "Hunter":
                return ctx.widgets.widget(320).component(16);
            case "Mining":
                return ctx.widgets.widget(320).component(17);
            case "Smithing":
                return ctx.widgets.widget(320).component(18);
            case "Fishing":
                return ctx.widgets.widget(320).component(19);
            case "Cooking":
                return ctx.widgets.widget(320).component(20);
            case "Firemaking":
                return ctx.widgets.widget(320).component(21);
            case "Woodcutting":
                return ctx.widgets.widget(320).component(22);
            case "Farming":
                return ctx.widgets.widget(320).component(23);
            case "Close":
                return ctx.widgets.widget(214).component(25);
            case "Guide":
                return ctx.widgets.widget(214).component(7);
        }
        return null;
    }
}
