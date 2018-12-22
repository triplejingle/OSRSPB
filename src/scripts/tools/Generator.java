package scripts.tools;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class Generator extends ClientAccessor{

    public Generator(ClientContext arg0) {
        super(arg0);
    }

    public void generateListBasedOnInventory(){
        System.out.println("public class ItemIds {\n");
        Item[] items =ctx.inventory.items();
        for(int i = 0;i<items.length;i++){
            Item item = items[i];
            if (!item.name().isEmpty()) {
                System.out.println("int " + item.name().replaceAll("\\s+", "") + "=" + item.id() + ";");
                System.out.println("public int get" + item.name().replaceAll("\\s+", "") + "(){\n" +
                        "return " + item.name().replaceAll("\\s+", "") + ";\n}");
            }
        }
        generateGetItemMethod();
        System.out.println("}");
        Condition.sleep(10000);

    }

    public void generateGetItemMethod(){
        Item[] items =ctx.inventory.items();
        System.out.println("public int getItem(String itemName){");
        System.out.println(" switch (itemName) {\n");
        for(int i = 0;i<items.length;i++){
            Item item = items[i];
            if (!item.name().isEmpty()) {

                System.out.println("case \""+item.name()+"\":\n");
                System.out.println("return get"+item.name().replaceAll("\\s+", "")+"();\n ");
            }
        }
        System.out.println("\n}\n return 0; \n}");
    }

}
