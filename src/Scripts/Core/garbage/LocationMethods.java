package Scripts.Core.garbage;

import Scripts.Core.WalkerTMPMethods;
import org.powerbot.script.rt4.*;
import org.powerbot.script.Tile;

public class LocationMethods extends ClientAccessor {
    private WalkerTMPMethods walkerMethods = new WalkerTMPMethods(ctx);
    public LocationMethods(ClientContext ctx){
        super(ctx);
    }

    void teleportToLocation(String location){

    }

    public void walkToLocation(final Tile[] path){
        walkerMethods.walkPath(path);
    }

    public void walkToLocationReverse(final Tile[] path){
            walkerMethods.walkPathReverse(path);
    }

    public boolean currentLocationIsNearbyTile(Tile tile,int tileRange){
        if(tile==null){
            return false;
        }
        int distance = (int)tile.distanceTo(ctx.players.local().tile());
        boolean isNearby =  tile.distanceTo(ctx.players.local().tile())<=tileRange;
        return  isNearby;
    }

    public double getDistanceToDestination(){
        return ctx.movement.destination().distanceTo(ctx.players.local());
    }

    public boolean isTileReachable(Tile tile) {
        return tile.matrix(ctx).reachable();
    }

    public boolean isTileNearTile(Tile nextTile, Tile nextTile1) {
        return (nextTile.distanceTo(nextTile1)<4);
    }
}
