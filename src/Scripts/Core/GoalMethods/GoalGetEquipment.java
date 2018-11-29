package Scripts.Core.GoalMethods;

import Scripts.Core.Bank;
import Scripts.Core.ENUM.state;
import Scripts.Tools.ATimer;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;

public class GoalGetEquipment extends Goal {
    ArrayList<Integer> equipmentList = new ArrayList<>();
    Bank bank = new Bank(ctx);
    public GoalGetEquipment(ClientContext arg0) {
        super(arg0);
    }

    @Override
    public void activate() {
        bank.openBank();
        for(int i = 0;i<equipmentList.size();i++){
            bank.withdraw(equipmentList.get(i),1);
        }
    }

    @Override
    public state process() {
        activateIfInactive();
        if (isStuck()){
            System.out.println("task failed");
            status = state.FAILED;
        }else if (goalReached()){
            System.out.println("goalCompleted reached");
            status = state.COMPLETED;
        }
        return status;
    }

    private boolean goalReached() {
        for (int i = 0; i <equipmentList.size();i++){
            if(ctx.inventory.select().id(equipmentList.get(i)).count()>0){
                return true;
            }
        }
        return false;
    }

    private void activateIfInactive() {
        if(status==state.INACTIVE){
            System.out.println("getting equipment");
            status = state.ACTIVE;
        }
        if(status==state.ACTIVE&&!goalReached()){
            activate();
        }
    }

    @Override
    public void terminate() {

    }

    public void addEquipment(int equipmentId){
        equipmentList.add(equipmentId);
    }
    ATimer aTimer =new ATimer();
    public boolean isStuck() {
        aTimer.setPeriod(15000);
        if(aTimer.isTime()) {
            for (int i = 0; i <equipmentList.size();i++){
                if(ctx.inventory.select().id(equipmentList.get(i)).count()==0){
                    return true;
                }
            }
        }
        return false;
    }
}
