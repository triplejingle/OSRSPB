package Scripts.Planners;


import org.powerbot.script.rt4.ClientContext;

public class PlannerMelee extends Planner  {

    public PlannerMelee(ClientContext arg0) {
        super(arg0);
    }

    @Override
    void chooseMethod() {

    }


    //stoppen met acties wanneer typen en gebruiken van muis
    //player has equipment
    //equipment halenx
    public void killSingleTarget(){
        String mob =  "Goblin";
        String food = "Salmon";
        int eatAtHealthInPercent = 50;
        int runWhenEnergyabove = 50;

    }

    public void killMultiTarget(){

    }

    public void killAfk(){

    }
}
