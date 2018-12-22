package scripts.core.goalmethods;

import scripts.core.enumcollection.state;

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

    String getGoal();
}
