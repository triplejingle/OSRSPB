package Scripts.Jobs.Garbage.CowKillerFalador;

import Scripts.Core.garbage.ItemIdCollection;
import Scripts.Core.garbage.PlayerMethods;
import Scripts.Tasks.Task;
import org.powerbot.script.rt4.ClientContext;

public class TaskEatWhenHealthIsLow extends Task {
    PlayerMethods playerMethods = new PlayerMethods(ctx);
    ItemIdCollection itemIdCollection = new ItemIdCollection();
    public TaskEatWhenHealthIsLow(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
       // return playerMethods.healthLow(70);
        return true;
    }

    @Override
    public void execute() {
//        playerMethods.eat(itemIdCollection.getCooked_meat());
    }
}
