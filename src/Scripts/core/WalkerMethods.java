package scripts.core;

import org.powerbot.script.Random;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.ArrayList;
import java.util.Collections;


public class WalkerMethods extends ClientAccessor {

    public WalkerMethods(ClientContext ctx) {
        super(ctx);
    }

    public void handleObstacle() {
        String actions[] = {"Open", "Climb-up", "Climb-down", "Climb-over"};
        String objectName[] = {"Door", "Gate", "Staircase", "Stile"};
        GameObject selectedObject = ctx.objects.select().name(objectName).action(actions).nearest().poll();
    }

    public Tile getRandomReachableTiles(Tile tile) {
        ArrayList<Tile> tileList = new ArrayList<>();
        tileList.add(tile);
        checkDirectlyConnectedTiles(tileList, tile);
        Collections.shuffle(tileList);
        System.out.println(tileList.get(0));
        if (tileList.size() > 0) {
            return tileList.get(0);
        }
        return tile;
    }

    public void checkDirectlyConnectedTiles(ArrayList<Tile> tileList, Tile tile) {

        int startValue=-1;
        for (int i = startValue; i < startValue+2; i++) {
            for (int j = startValue; j < startValue+2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                Tile newTile = tile.derive(i, j);
                if (newTile.matrix(ctx).reachable()) {
                    Collections.addAll(tileList, newTile);
                }
            }
        }
    }

    boolean isDestinationOnMap() {
        return ctx.movement.destination().matrix(ctx).onMap();
    }

    public Tile[] splicePath(Tile[] path, double pieces) {
        int getNewPathSize = (int) ((double) (path.length) / pieces) + 1;
        Tile[] newPath = new Tile[getNewPathSize];
        int index = 0;
        for (int i = 0; i < path.length; i++) {
            if (i % pieces == 0) {
                newPath[index] = path[i];
                index++;
            }
        }
        return newPath;
    }

    public boolean isNear(Tile tile, int tileRange) {
        return ctx.players.local().tile().distanceTo(tile) < tileRange;
    }

    public boolean walkToTile(Tile nextLocation) {
        return ctx.movement.step(getRandomReachableTiles(nextLocation));
    }

    public boolean isDestinationSetNearTile(Tile tile) {
        return ctx.movement.destination().distanceTo(tile) < 5;
    }
}
