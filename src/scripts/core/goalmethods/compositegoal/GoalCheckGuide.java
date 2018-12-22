package scripts.core.goalmethods.compositegoal;

import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import scripts.core.Player;
import scripts.core.goalmethods.atomicgoal.*;
import scripts.tools.LastSkillXpGained;

public class GoalCheckGuide extends CompositeGoal{

	Player player = new Player(ctx,"its you but in code");
	LastSkillXpGained lastSkillXpGained = new LastSkillXpGained(ctx);
	public  GoalCheckGuide(ClientContext arg0) {
		super(arg0);
	}

	@Override
	public void activate() {
		super.activate();
		if(player.getHealth()<100){
			terminate();
			addSubGoal(new GoalRunAway(ctx));
		}
	}

	@Override
	protected void setup() {
		if(setup){
			setup=false;
			Random random = new Random();
			int seconds = random.nextInt(10,80);
			Game.Tab prevTab = ctx.game.tab();
			addSubGoal(new GoalSwitchTab(ctx,prevTab));
			addSubGoal(new GoalAntiBanCloseGuide(ctx));
			addSubGoal(new GoalIdle(ctx, seconds));
			addSubGoal(new GoalAntiBanScrollThroughGuide(ctx));
			addSubGoal(new GoalAntiBanMoveMouseToGuide(ctx));
			addSubGoal(new GoalAntiBanOpenGuide(ctx,lastSkillXpGained.getSkill()));
			addSubGoal(new GoalSwitchTab(ctx,Game.Tab.STATS));
			goal="withdraw equipment"+ System.currentTimeMillis()/1000;
		}
	}


		public boolean goalReached() {
			return (children.size()==0);
		}

		@Override
		public void terminate() {
			emptyStack();
		}

		public boolean isStuck() {
			return hasChildFailed();
		}
}
