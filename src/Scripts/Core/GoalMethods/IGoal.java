package Scripts.Core.GoalMethods;

import Scripts.Core.ENUM.state;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

import java.util.List;
import java.util.Stack;

public interface IGoal {

    public void activate() ;

    public state process();

    public void terminate();

    abstract boolean goalReached();

    abstract boolean isStuck();

    boolean isCompleted();

    public state getState();

    public void addSubGoal(IGoal goal);

    Stack<IGoal> getSubGoals();
}
