package Scripts.Jobs.AntiBan;

import Scripts.Core.Player;
import Scripts.Tasks.MoneyMethods;
import Scripts.Tasks.Task;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.awt.*;


public class TaskAntiBan extends Task{
    Player player = new Player(ctx,"its you except in code");
    Robot robot;
    {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public TaskAntiBan(ClientContext ctx, Tile taskTile, int tileRange) {
        super(ctx,taskTile,tileRange);
    }

    public TaskAntiBan(ClientContext ctx, Tile[] path) {
        super(ctx);
    }
    public TaskAntiBan(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
      return true;
    }

    @Override
    public void execute() {
      player.checkStatsGuide("Defence");
        System.out.println(MouseInfo.getPointerInfo().getLocation().getX());
    }
}
