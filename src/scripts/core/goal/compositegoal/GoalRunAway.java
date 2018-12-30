package scripts.core.goal.compositegoal;

import org.powerbot.script.rt4.ClientContext;

public class GoalRunAway extends CompositeGoal {
    public GoalRunAway(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
       //ren weg via de vaste vlucht
        //doe prayer aan als het kan
        //zet rennen aan
        //eet ondertussen als het kan
    }

    @Override
    protected void setup() {
        if(setup){
            setup=false;
        }
    }

    public boolean goalReached() {
        return true;//afstand is groter dan 40 tiles gerekend vanaf het punt van wegrennen
    }

    @Override
    public void terminate() {

    }


    public boolean isStuck() {
        return false;//binnen 20 secondes is het eindpunt niet bereikt.
    }
}
