package Scripts.Tools;

public class Stack<C>{
    C[] stack;
    int index = 0;

    public Stack(int length){
        stack = (C[]) new Object[length];
    }

   public C pop(){
        C value = peek();
        removeFirst();
        return value;
    }

    public boolean isInStack(C item) {
        if(index>=1) {
            for (int i = 0; i < stack.length - 1; i++) {
                if (stack[i].equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    public C getFromStack(C item){
        if(index>=1) {
            for (int i = 0; i < stack.length - 1; i++) {
                if (stack[i].equals(item)) {
                    return stack[i];
                }
            }
        }
        return null;
    }
    public int getStackSize(){
        return stack.length;
    }

    public void emptyStack(){
        for(int i = 0 ;i<stack.length-1;i++) {
            removeFirst();
        }
    }
    public void push(C tmp){
        if(index<stack.length) {
            stack[index] = tmp;
            index++;
        }
    }

    public void pushArray(C[] tmp){
        for(int i = tmp.length;i>0;i--){
            push(tmp[i]);
        }
    }

    public C peek(){
        return stack[index];
    }

    public void removeFirst(){
        if(index>0) {
            index--;
        }
        stack[index] = null;
    }
}
