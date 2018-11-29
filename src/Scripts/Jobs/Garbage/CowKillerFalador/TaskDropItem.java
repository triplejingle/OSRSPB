package Scripts.Jobs.Garbage.CowKillerFalador;

import Scripts.Core.garbage.ItemIdCollection;
import Scripts.Core.garbage.InventoryMethods;
import Scripts.Tasks.Task;
import org.powerbot.script.rt4.ClientContext;

public class TaskDropItem extends Task{

        InventoryMethods inventoryMethods = new InventoryMethods(ctx);
        ItemIdCollection itemIdCollection= new ItemIdCollection();
        public TaskDropItem(ClientContext ctx) {
            super(ctx);
        }

        @Override
        public boolean activate() {
           return true;
        }

        @Override
        public void execute() {
//            int[] dropitems = {itemIdCollection.getBurnt_meat(),itemIdCollection.getCowhide(),itemIdCollection.getBones()};
//            for(int i = 0 ;i<dropitems.length-1;i++) {
//                while (inventoryMethods.inventoryContainsItem(dropitems[i])) {
//                    inventoryMethods.dropItem(dropitems[i]);
//                }
//            }
        }


}
