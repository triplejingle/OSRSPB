package scripts.tools;

import java.util.Random;

public class ATimer {
     long end = 0;
     long start = 0;

    private static Random random = new Random();
    float multiplier;
    public void setPeriod(int period) {
        if(this.period==0||period==0) {
            this.period = period;
            saveTime();
        }
    }

    public void setPeriodBetween(int begin, int end){
        int boundEnd=random.nextInt((int) (end*multiplier));
        int boundStart=random.nextInt((int) (begin*multiplier));

        setPeriod(boundEnd+boundStart);
    }

    public long getPeriod() {
        return period;
    }

    private int period = 0;

    public ATimer() {
        multiplier = random.nextFloat()+1;
    }

    public boolean isTime() {
        if(end - System.currentTimeMillis()<=0) {
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
