package scripts.core.collection;
public class ItemIds {

    int Ironarrow=884;
    public int getIronarrow(){
        return Ironarrow;
    }
    int Rawbeef=2132;
    public int getRawbeef(){
        return Rawbeef;
    }
    int Logs=1511;
    public int getLogs(){
        return Logs;
    }
    int Bronzeaxe=1351;
    public int getBronzeaxe(){
        return Bronzeaxe;
    }
    int Bucketofmilk=1927;
    public int getBucketofmilk(){
        return Bucketofmilk;
    }
    int Cookedmeat=2142;
    public int getCookedmeat(){
        return Cookedmeat;
    }
    int Bucket=1925;
    public int getBucket(){
        return Bucket;
    }
    int Tinderbox=590;

    public int getTinderbox(){
        return Tinderbox;
    }
    public int getItem(String itemName){
        switch (itemName) {

            case "Iron arrow":

                return getIronarrow();

            case "Raw beef":

                return getRawbeef();

            case "Logs":

                return getLogs();

            case "Bronze axe":

                return getBronzeaxe();

            case "Bucket of milk":

                return getBucketofmilk();

            case "Cooked meat":

                return getCookedmeat();

            case "Bucket":

                return getBucket();

            case "Tinderbox":

                return getTinderbox();


        }
        return 0;
    }
}