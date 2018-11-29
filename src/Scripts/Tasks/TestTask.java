package Scripts.Tasks;

import Scripts.Core.garbage.ItemIdCollection;
import Scripts.Core.garbage.InventoryMethods;
import Scripts.Tasks.Task;
import Scripts.Tasks.TrainCooking;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class TestTask extends Task {


    public TestTask(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx, taskTile, tileRange);
    }

    @Override
        public boolean activate() {
            return true;
        }

        @Override
        public void execute() {

        }
    }

