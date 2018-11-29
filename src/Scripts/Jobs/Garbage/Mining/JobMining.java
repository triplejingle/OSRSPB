package Scripts.Jobs.Garbage.Mining;

import Scripts.Core.JobCore;

import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.GameObject;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


public class JobMining extends JobCore{
    List<Tile> rocks = new ArrayList();

    @Override
    public void start() {
        taskList.add(new TaskMining(ctx, rocks));
    }

    public void mouseClicked(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e)){
            System.out.println("MouseX "+e.getX());
            System.out.println("mouse Y ="+e.getY());
            for (GameObject object : ctx.objects.select().name("Rocks").within(20)) {
                int rockX = object.nextPoint().getLocation().x;
                int rockY = object.nextPoint().getLocation().y;
                if (e.getX() >=rockX-14 && e.getX() <= rockX+23
                       && e.getY() <= rockY+38 && e.getY() >= rockY-10) {
                    if(!rocks.contains(object.tile())) {
                        rocks.add(object.tile());
                    }
                    System.out.println(object.tile());
                    System.out.println(object.id());
                }
            }
        }else if(SwingUtilities.isRightMouseButton(e)){
            for (GameObject object : ctx.objects.select().name("Rocks").within(5)) {
                int rockX = object.nextPoint().getLocation().x;
                int rockY = object.nextPoint().getLocation().y;
                if (e.getX() >=rockX-14 && e.getX() <= rockX+23
                        && e.getY() <= rockY+38 && e.getY() >= rockY-10) {
                    System.out.println(object.tile());
                    rocks.remove(object.tile());
                }
            }
        }
    }
}




