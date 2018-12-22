package scripts.core.collection;

import scripts.core.enumcollection.BoundaryType;

public class BoundaryCollection {


    public int[] getBounds(BoundaryType boundaryType, int orientation) {
        switch (boundaryType) {
            case DOOR:
               return getDoor(orientation);
            case GATE:
               return getGateBounds(orientation);
            case LADDER:
               return ladder(orientation);
               default:
                   return new int[]{0};
        }
    }


    public BoundaryType getType(String type){
       if(type.contains("Door")){
           return BoundaryType.DOOR;
       }else if(type.contains("Gate")){

           return BoundaryType.GATE;
        }else if(type.contains("Ladder")) {
           return BoundaryType.LADDER;
        }else {
           return BoundaryType.DOOR;
       }
    }

    public int[] ladder(int orientation){
        switch (orientation) {
            case 0:
            case 1:
            case 2:
                return new int[]{-20, 40, 20, -40, 0, -60};
            case 3:
            case 4:
            case 5:
                return new int[]{0, -40, -64, 0, -32, 32};
            case 6:
                return new int[]{-20, 40, 20, -40, 0, 60};
            case 7:
                return new int[]{10, 40, -64, 0, -32, 32};
        }
        return new int[]{0};
    }

    public int[] getGateBounds(int orientation) {
        switch (orientation) {
            case 0:
            case 1:
                return new int[]{0, 80, 0, -80, 118, 123};
            case 2:
                return new int[]{118, 123, 0, -80, 0, 80};
            case 3:
                return new int[]{10, 80, 0, -80, 15, 0};
            case 5:
                return new int[] {242, 44, -84, 0, 134, 118};
            case 4:
            case 6:
                return new int[]{5, 10, 0, -80, 20, 80};
        }
        return new int[]{0};
    }

    public int[] getDaiyCowBounds() {
        return DaiyCowBounds;
    }

    private final int[] DaiyCowBounds ={-41, 28, -103, -26, -23, 149};

    public int[] getDoor(int orientation) {
        switch (orientation) {
            case 0:
            case 1:
                return new int[]{0, 90, 0, -220, 110, 130};
            case 2:
                return new int[]{100, 120, 0, -220, 0, 90};
            case 3:
                return new int[]{0, 100, 0, -200, 10, 20};
            case 4:
            case 6:
                return new int[]{-5, 15, 0, -220, 0, 90};
        }
        return new int[]{0};
    }


}
