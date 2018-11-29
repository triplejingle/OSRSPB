package Scripts.Core.garbage;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

import java.util.Random;

public class RandomizerMethods extends ClientAccessor {
    public RandomizerMethods(ClientContext arg0) {
        super(arg0);
    }

    public boolean activateRandom(){
        int max = 1000;
        double median = Math.floor(max);
        return getRandomNumberBasedOn(max)>=median?true:false;
    }

   public int getRandomNumberBasedOn(int number){
        Random random = new Random();
        return random.nextInt(number);
    }


}
