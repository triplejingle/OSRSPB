package scripts.tools;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.Constants;
import scripts.tools.factory.UIScreenFactory;

public class LastSkillXpGained extends ClientAccessor{
	UIScreenFactory uiScreenFactory = new UIScreenFactory(ctx);
	public LastSkillXpGained(ClientContext arg0) {
		super(arg0);
	}

	public int getSkill(){
		Component component = uiScreenFactory.getComponent("xp drops logo");
		int textureId = component.textureId();
		switch (textureId){
			case 214:
				return Constants.SKILLS_WOODCUTTING;
			case 211:
				return Constants.SKILLS_FISHING;
				default:
					return 214;
		}
	}
}
