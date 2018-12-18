package Scripts.Tools.Factory;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Constants;

public class StatsTabFactory extends ClientAccessor {

    public StatsTabFactory(ClientContext arg0) {
        super(arg0);
    }

    public Component getComponent(int component) {

        switch (component) {
            case Constants.SKILLS_ATTACK:
                return ctx.widgets.widget(320).component(1);
            case Constants.SKILLS_STRENGTH:
                return ctx.widgets.widget(320).component(2);
            case Constants.SKILLS_DEFENSE:
                return ctx.widgets.widget(320).component(3);
            case Constants.SKILLS_RANGE:
                return ctx.widgets.widget(320).component(4);
            case Constants.SKILLS_PRAYER:
                return ctx.widgets.widget(320).component(5);
            case Constants.SKILLS_MAGIC:
                return ctx.widgets.widget(320).component(6);
            case Constants.SKILLS_RUNECRAFTING:
                return ctx.widgets.widget(320).component(7);
            case Constants.SKILLS_CONSTRUCTION:
                return ctx.widgets.widget(320).component(8);
            case Constants.SKILLS_HITPOINTS:
                return ctx.widgets.widget(320).component(9);
            case Constants.SKILLS_AGILITY:
                return ctx.widgets.widget(320).component(10);
            case Constants.SKILLS_HERBLORE:
                return ctx.widgets.widget(320).component(11);
            case Constants.SKILLS_THIEVING:
                return ctx.widgets.widget(320).component(12);
            case Constants.SKILLS_CRAFTING:
                return ctx.widgets.widget(320).component(13);
            case Constants.SKILLS_FLETCHING:
                return ctx.widgets.widget(320).component(14);
            case Constants.SKILLS_SLAYER:
                return ctx.widgets.widget(320).component(15);
            case Constants.SKILLS_HUNTER:
                return ctx.widgets.widget(320).component(16);
            case Constants.SKILLS_MINING:
                return ctx.widgets.widget(320).component(17);
            case Constants.SKILLS_SMITHING:
                return ctx.widgets.widget(320).component(18);
            case Constants.SKILLS_FISHING:
                return ctx.widgets.widget(320).component(19);
            case Constants.SKILLS_COOKING:
                return ctx.widgets.widget(320).component(20);
            case Constants.SKILLS_FIREMAKING:
                return ctx.widgets.widget(320).component(21);
            case Constants.SKILLS_WOODCUTTING:
                return ctx.widgets.widget(320).component(22);
            case Constants.SKILLS_FARMING:
                return ctx.widgets.widget(320).component(23);
            case 999://close
                return ctx.widgets.widget(214).component(25);
            case 998://guide
                return ctx.widgets.widget(214).component(7);
                default:
                    return ctx.widgets.widget(320).component(9);
        }
    }
}
