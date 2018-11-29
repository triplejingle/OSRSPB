package Scripts.Core;

import Scripts.Tasks.Task;
import Scripts.Tools.ATimer;
import org.powerbot.script.PollingScript;
import org.powerbot.script.rt4.ClientContext;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public abstract class JobCore extends PollingScript<ClientContext> implements KeyListener, MouseMotionListener, MouseListener {
    public boolean pauseScript = false;
    Scripts.Tools.ATimer ATimer = new ATimer();
    public List<Task> taskList = new ArrayList();
    @Override
    public void poll() {
        unpauseScript();
        execTasks();
    }
    public void execTasks(){
        if(!pauseScript) {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (task.activate()) {
                    task.execute();
                    break;
                }
            }
        }
    }


    public void unpauseScript(){
        if(pauseScript) {
            ATimer.setPeriodBetween(10000,20000);
            if (ATimer.isTime()) {
                pauseScript = false;
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
//            ATimer.saveTime();
//            pauseScript = true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!ctx.input.keyboard()) {
            pauseScript = true;
            ATimer.saveTime();
            System.out.println("Pausing script");
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        ATimer.saveTime();
        pauseScript = true;
    }

    @Override
    public void mouseDragged(MouseEvent e){
        if(!ctx.input.blocking()) {
            ATimer.saveTime();
            pauseScript = true;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        blockInputWhenMouseMove(e.getX());
    }
    int prevX = 0;
    public void blockInputWhenMouseMove(int currentLocation){
        if(!ctx.input.blocking()){
            if(currentLocation!=prevX){
                ATimer.saveTime();
                pauseScript =true;
            }
        }
    }
    public void mouseClicked(MouseEvent e) {
        ATimer.saveTime();
        pauseScript =true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ATimer.saveTime();
        pauseScript =true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ATimer.saveTime();
        pauseScript =true;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!ctx.input.blocking()) {
            ATimer.saveTime();
            pauseScript = true;
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(!ctx.input.blocking()) {
            ATimer.saveTime();
            pauseScript = true;
        }
    }
}
