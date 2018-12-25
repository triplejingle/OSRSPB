package scripts.core;

import org.powerbot.script.PollingScript;
import org.powerbot.script.rt4.ClientContext;
import scripts.Gui.FishingGui;
import scripts.core.enumcollection.state;
import scripts.core.goalmethods.IGoal;
import scripts.planner.Planner;
import scripts.tools.ATimer;

import java.awt.event.*;
import java.util.Stack;

public abstract class JobCore extends PollingScript<ClientContext> implements KeyListener, MouseMotionListener, MouseListener {
    public boolean pauseScript = false;
    scripts.tools.ATimer ATimer = new ATimer();
    Stack<IGoal> IGoalStack = new Stack();
    public Planner planner;
    public void setPlanner(Planner planner) {
        this.planner = planner;
    }
    public void addGoal(IGoal IGoal){
        IGoalStack.add(IGoal);
    }

    @Override
    public void poll() {
        if(IGoalStack.size()>0) {
            processGoal();
            unpauseScript();
            replanIfFailed();
            removeIfCompleted();
        }
        if(IGoalStack.peek().getSubGoals().size()==0){
            planner.replan();
            IGoalStack.peek().getSubGoals().addAll(planner.getSubGoals());
        }
    }

    private void removeIfCompleted(){
        if(IGoalStack.peek().getState()==state.COMPLETED){
            IGoalStack.pop();
            System.out.println("yeaye goal reached");
            stop();
        }
    }
    private void replanIfFailed(){
        if (IGoalStack.peek().getState() == state.FAILED) {
            emptyStack();
            planner.replan();
            System.out.println(" plan failed time for a new plan");
        }
    }

    private void emptyStack(){
        while(IGoalStack.size()>0){
            IGoalStack.pop();
        }
    }

    public void processGoal(){
        if(!pauseScript) {
            IGoalStack.peek().process();
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
            ATimer.saveTime();
            pauseScript = true;
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
