package Scripts.Core.garbage;

import java.util.Random;

public class NpcIdCollection {
    private final int DairyCow = 2691;

    public int getDairyCow() {
        return DairyCow;
    }


    public int[] getCow(){
        return Cow;
    }

    public int getCow_calf() {
        return Cow_calf;
    }

    private final int[] Cow = {2806, 2805,2808};

    private final int Cow_calf = 2816;

    public int getRandomNpcFromCollection(int[] npcs){
        Random random = new Random();
        int chosenNpc = random.nextInt(npcs.length);
        return npcs[chosenNpc];
    }
}
