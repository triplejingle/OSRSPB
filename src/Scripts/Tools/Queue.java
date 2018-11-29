package Scripts.Tools;

public class Queue<C> {
    C[] queue;
    int index = 0;
    int endOfLine = 0;
    public Queue(int size){
        queue = (C[]) new Object[size];
    }
    public int getSize(){
        return queue.length;
    }
    public void addToQueue(C item){
        queue[endOfLine] = item;
        if(endOfLine>=queue.length-1){
            endOfLine = 0;
        }else{
            endOfLine++;
        }
    }
    public C getCurrent(){
        C value = queue[index];
        return  value;
    }
    public void next(){
        if(index>=queue.length-1) {
            index = 0;
        }else{
            index++;
        }
    }

    public boolean isInQueue(C item) {
        if(index>=1) {
            for (int i = 0; i < queue.length - 1; i++) {
                if (queue[i].equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }

}
