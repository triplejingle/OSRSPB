package Scripts.Tasks;

import Scripts.Core.NPCInteractive;
import Scripts.Core.Player;
import Scripts.Core.WalkerTMPMethods;
import Scripts.Location.GrandExchange;
import Scripts.Location.RSWorld;
import Scripts.Tools.ATimer;
import Scripts.Tools.Factory.RunesFactory;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;

public class MoneyMethods extends ClientAccessor{
    NPCInteractive aubury = new NPCInteractive(ctx,"Aubury");
    Inventory inventory =ctx.inventory;
    GrandExchange grandExchange = new GrandExchange(ctx);
    RSWorld world = new RSWorld(ctx);
    Player player = new Player(ctx,"Don't read this");
    public final Tile[] ExchangeToAubury = {new Tile(3164, 3487, 0), new Tile(3164, 3482, 0), new Tile(3164, 3477, 0), new Tile(3164, 3472, 0), new Tile(3165, 3467, 0), new Tile(3166, 3462, 0), new Tile(3171, 3461, 0), new Tile(3176, 3459, 0), new Tile(3180, 3456, 0), new Tile(3185, 3454, 0), new Tile(3189, 3450, 0), new Tile(3192, 3446, 0), new Tile(3196, 3442, 0), new Tile(3200, 3439, 0), new Tile(3205, 3438, 0), new Tile(3205, 3433, 0), new Tile(3209, 3429, 0), new Tile(3214, 3429, 0), new Tile(3217, 3425, 0), new Tile(3221, 3421, 0), new Tile(3225, 3418, 0), new Tile(3227, 3413, 0), new Tile(3232, 3411, 0), new Tile(3235, 3407, 0), new Tile(3235, 3402, 0), new Tile(3240, 3402, 0), new Tile(3245, 3402, 0), new Tile(3249, 3399, 0), new Tile(3253, 3402, 0)};
    public final Tile[] AuburyToExchange = ctx.movement.newTilePath(ExchangeToAubury).reverse().toArray();
    RunesFactory runesFactory = new RunesFactory(ctx);
    WalkerTMPMethods walkerTMPMethods = new WalkerTMPMethods(ctx);
  int state = 0;
    public MoneyMethods(ClientContext arg0) {
        super(arg0);
    }

    public void buyMindRunesVarrock() {
        int minimalPriceOfMindRunePack = 330;
        int buyAmount = 50;
        String mindRunePack = "Mind rune pack";
        String mindRune = "Mind rune";
        String coins = "Coins";
        if(state ==0) {
            if (ctx.players.local().tile().distanceTo(ExchangeToAubury[ExchangeToAubury.length - 1]) < 10 &&
                    inventory.select().name(coins).count(true) > minimalPriceOfMindRunePack) {
                aubury.trade();
                Component item = runesFactory.getComponent(mindRunePack);
                int stack  =item.itemStackSize();
              if(stack>=10) {
                  aubury.buyRunes(item, buyAmount);
                  aubury.closeRuneShop();
                  Game.Tab tab = ctx.game.tab();
                  ctx.game.tab(Game.Tab.INVENTORY);
                  ATimer ATimer = new ATimer();
                  ATimer.setPeriod(1000);
                  while (tab != Game.Tab.NONE) {
                      if (ATimer.isTime()) {
                          ctx.game.tab(Game.Tab.INVENTORY);
                          break;
                      }
                  }
                  player.openRunePack(mindRunePack);
              }
              if(stack<13) {
                aubury.closeRuneShop();
                world.switchToRandomFreeWorld();
            }
            }else{
                state = 1;
            }
        }else if(state ==1) {
            walkerTMPMethods.walkPath(AuburyToExchange);
            if(ctx.players.local().tile().distanceTo(AuburyToExchange[AuburyToExchange.length-1])<8){
                state = 2;
            }
        }else if(state == 2){
            if (ctx.players.local().tile().distanceTo(AuburyToExchange[AuburyToExchange.length - 1]) < 10 &&
                    inventory.select().name(coins).count(true) < minimalPriceOfMindRunePack) {
                grandExchange.sellItem(mindRune);
                grandExchange.collect();
                grandExchange.closeExchangeScreen();
            }else{
                state = 3;
            }
        }else if(state == 3){
            walkerTMPMethods.walkPath(ExchangeToAubury);
            if(ctx.players.local().tile().distanceTo(ExchangeToAubury[ExchangeToAubury.length-1])<8){
                state = 0;
            }
        }
    }
}
