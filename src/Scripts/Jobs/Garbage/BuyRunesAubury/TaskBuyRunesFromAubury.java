package Scripts.Jobs.Garbage.BuyRunesAubury;

import Scripts.Tasks.MoneyMethods;
import Scripts.Tasks.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TaskBuyRunesFromAubury extends Task{
    MoneyMethods moneyMethods = new MoneyMethods(ctx);
    public TaskBuyRunesFromAubury(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx,taskTile,tileRange);
    }

    public TaskBuyRunesFromAubury(ClientContext ctx, Tile[] path) {
        super(ctx);
    }
    public TaskBuyRunesFromAubury(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
      return true;
    }

    @Override
    public void execute() {
      moneyMethods.buyMindRunesVarrock();
    }
}
