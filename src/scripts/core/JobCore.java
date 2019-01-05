package scripts.core;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.rt4.ClientContext;
import scripts.core.enumcollection.state;
import scripts.goal.IGoal;
import scripts.paint.BackGround;
import scripts.paint.Text;
import scripts.planner.Planner;
import scripts.tools.ATimer;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public abstract class JobCore extends PollingScript<ClientContext> implements KeyListener, MouseMotionListener, MouseListener,PaintListener {
    private boolean pauseScript = false;
    private scripts.tools.ATimer aTimer = new ATimer();
    private Stack<IGoal> iGoalStack = new Stack();
    protected Planner planner;
    private BackGround backGround = new BackGround();
    private Text text = new Text(ctx);

    @Override
    public void repaint(Graphics g) {
        backGround.paint(g);
        text.repaint(g);
        int x = (int)ctx.input.getLocation().getX();
        int y = (int) ctx.input.getLocation().getY();
        g.drawRect(x,y,5,5);
    }

    protected void addGoal(IGoal iGoal) {
        iGoalStack.add(iGoal);
    }

    @Override
    public void poll() {
        if (!iGoalStack.isEmpty()) {
            processGoal();
            unpauseScript();
            removeIfCompleted();
            replanIfFailed();
        }
        if (iGoalStack.isEmpty()) {
            planner.replan();
            iGoalStack.push(planner.getHighLevelGoal());
        }
        if (iGoalStack.peek().getSubGoals().isEmpty()) {
            planner.replan();
            iGoalStack.peek().getSubGoals().addAll(planner.getSubGoals());
        }
    }

    private void removeIfCompleted() {
        if (iGoalStack.peek().getState() == state.COMPLETED) {
            iGoalStack.pop();
            System.out.println("yeaye goal reached");
            stop();
        }
    }

    private void replanIfFailed() {
        if (iGoalStack.peek().getState() == state.FAILED) {
            emptyStack();
            planner.replan();
            System.out.println(" plan failed time for a new plan");
        }
    }

    private void emptyStack() {
        while (!iGoalStack.isEmpty()) {
            iGoalStack.pop();
        }
    }

    public void processGoal() {
        if (!pauseScript) {
            iGoalStack.peek().process();
        }
    }


    public void unpauseScript() {
        if (pauseScript) {
            aTimer.setPeriodBetween(5000, 8000);
            if (aTimer.isTime()) {
                pauseScript = false;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        pauseScript();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!ctx.input.keyboard()) {
            pauseScript();
            System.out.println("Pausing script");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pauseScript();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!ctx.input.blocking()) {
            pauseScript();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        blockInputWhenMouseMove(e.getX());
    }

    int prevX = 0;

    public void blockInputWhenMouseMove(int currentLocation) {
        if (!ctx.input.blocking() && currentLocation != prevX) {
            pauseScript();
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (!ctx.input.blocking()) {
            pauseScript();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!ctx.input.blocking()) {
            pauseScript();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!ctx.input.blocking()) {
            pauseScript();
        }
    }

    private void pauseScript() {
        aTimer.saveTime();
        pauseScript = true;
    }
}
