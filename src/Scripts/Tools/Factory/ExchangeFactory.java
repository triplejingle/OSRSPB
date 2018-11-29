package Scripts.Tools.Factory;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class ExchangeFactory extends ClientAccessor{

    public ExchangeFactory(ClientContext arg0) {
        super(arg0);
    }

        public Component getComponent(String component) {
            if (component == null) {
                return null;
            }
            switch (component) {
                case "screen":
                    return ctx.widgets.widget(465).component(2).component(0);
                case "collect":
                    return ctx.widgets.widget(465).component(6).component(1);
                case "confirm":
                    return ctx.widgets.widget(465).component(24).component(54);
                case "close":
                    return ctx.widgets.widget(465).component(2).component(11);
                case "history":
                    return ctx.widgets.widget(465).component(3).component(9);
                case "Buy1":
                    return ctx.widgets.widget(465).component(7).component(0);
                case "Buy2":
                    return ctx.widgets.widget(465).component(8).component(0);
                case "Buy3":
                    return ctx.widgets.widget(465).component(9).component(0);
                case "Buy4":
                    return ctx.widgets.widget(465).component(10).component(0);
                case "Buy5":
                    return ctx.widgets.widget(465).component(11).component(0);
                case "Buy6":
                    return ctx.widgets.widget(465).component(12).component(0);
                case "Buy7":
                    return ctx.widgets.widget(465).component(13).component(0);
                case "Buy8":
                    return ctx.widgets.widget(465).component(14).component(0);
            }
            return null;
        }

}
