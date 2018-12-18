package Scripts.Tools.Factory;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;

public class UIScreenFactory extends ClientAccessor{
	public UIScreenFactory(ClientContext ctx) {
		super(ctx);
	}

	public Component getComponent(String component) {
		switch (component){
			case "xp drops logo":
				return ctx.widgets.widget(122).component(8);
			default:
				return null;
		}
	}
}
