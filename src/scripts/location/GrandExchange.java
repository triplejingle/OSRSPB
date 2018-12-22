package scripts.location;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;
import scripts.tools.factory.ExchangeFactory;

public class GrandExchange extends ClientAccessor{
    ExchangeFactory componentFactory = new ExchangeFactory(ctx);
   // NPCInteractive grand_exchange_clerk = new NPCInteractive(ctx, "Grand ExchangeData Clerk");

    public GrandExchange(ClientContext arg0) {
        super(arg0);
    }

}
