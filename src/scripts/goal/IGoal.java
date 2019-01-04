package scripts.goal;

import scripts.core.enumcollection.state;

import java.util.Stack;

public interface IGoal {

    void activate() ;

    state process();

    boolean goalReached();

    boolean isStuck();

    boolean isCompleted();

    state getState();

    void addSubGoal(IGoal goal);

    Stack<IGoal> getSubGoals();

    String getGoal();

}
