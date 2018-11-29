package Scripts.Tools;

import org.powerbot.script.ClientAccessor;
import org.powerbot.script.ClientContext;
import org.powerbot.script.Condition;
import org.powerbot.script.Random;

import java.awt.*;
import java.util.concurrent.Callable;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class ATimer {
     long end = 0;
     long start = 0;
    Random random = new Random();
    static long time = System.currentTimeMillis();

    public void setPeriod(int period) {
        if(this.period==0||period==0) {
            this.period = period;
            saveTime();
        }
    }

    public void setPeriodBetween(int begin, int end){
        setPeriod(random.nextInt(begin,end));
    }

    public long getPeriod() {
        return period;
    }

    private int period = 0;

    public ATimer() {

    }

    public boolean isTime() {
        if((end - System.currentTimeMillis())<=0) {
            saveTime();
            setPeriod(0);
            return true;
        }
        return false;
    }

    public void saveTime() {
        start = System.currentTimeMillis();
        end = start + period;
    }

    public void resetTimer(){
        start = 0;
        end = 0;
    }

    public void setMinuten(int minuten) {
        int minuutInMs = 60000;
        this.setPeriod(minuten*minuutInMs);
    }
}
