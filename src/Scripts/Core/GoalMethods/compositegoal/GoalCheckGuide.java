package Scripts.Core.GoalMethods.compositegoal;

import Scripts.Core.ENUM.state;
import Scripts.Core.GoalMethods.atomicgoal.*;
import Scripts.Core.Player;
import Scripts.Tools.LastSkillXpGained;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;

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

	public void activateIfInactive() {
			if(status== state.INACTIVE){
				status = state.ACTIVE;
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
			}
			if(status==state.ACTIVE&&!goalReached()){
				activate();
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
