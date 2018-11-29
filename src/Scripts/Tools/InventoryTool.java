package Scripts.Tools;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

import java.util.ArrayList;

public class InventoryTool extends ClientAccessor{
    int[][] inventorySlots = new int[7][4];
    public InventoryTool(ClientContext arg0) {
        super(arg0);
        fillInventory();
    }
    public void fillInventory(){
        int itemIndex = 0;
        for(int i = 0 ;i<inventorySlots.length;i++) {
            for(int j = 0 ;j<inventorySlots[0].length;j++){
                inventorySlots[i][j] = itemIndex;
                System.out.println(inventorySlots[i][j]);
                itemIndex++;
            }
        }
    }

    public ArrayList<Integer> getAdjectent(int itemSlot){
        ArrayList<Integer> adjecentSlots = new ArrayList<>();
        int firstRow=getItemRow(itemSlot-inventorySlots[0].length);
        addAdjecentToArray(adjecentSlots,firstRow, getItemColumn(firstRow,itemSlot));
        int secondRow=getItemRow(itemSlot);
        addAdjecentToArray(adjecentSlots,secondRow, getItemColumn(secondRow,itemSlot));
        int thirdRow=getItemRow(itemSlot+inventorySlots[0].length);
        addAdjecentToArray(adjecentSlots,thirdRow, getItemColumn(thirdRow,itemSlot));
        return  adjecentSlots;
    }

    public void addAdjecentToArray(ArrayList<Integer> adjecentSlots,int row, int column){
        if(column-1>=0) {
            adjecentSlots.add(inventorySlots[row][column - 1]);
        }
        adjecentSlots.add(inventorySlots[row][column]);
        if(column+1<=inventorySlots[0].length) {
            adjecentSlots.add(inventorySlots[row][column + 1]);
        }
    }

    public int getItemRow(int itemSlot){
        for(int i = 0 ;i<inventorySlots.length;i++){
            for(int j = 0 ;j<inventorySlots[0].length;j++) {
                if(inventorySlots[i][0]>=itemSlot&&itemSlot<= inventorySlots[i][inventorySlots[0].length]){
                    return i;
                }
            }
        }
        return 99;
    }

    public int getItemColumn(int rowNumber, int itemSlot){
        for(int i = 0;i< inventorySlots[0].length;i++){
            if(inventorySlots[rowNumber][i]==itemSlot){
                return i;
            }
        }
        return 99;
    }
    public Item getRandomInventoryItem(String item){
        return null;
    }
}
