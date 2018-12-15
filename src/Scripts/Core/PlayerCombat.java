package Scripts.Core;


import Scripts.Core.Interfaces.IPlayerCombat;
import org.powerbot.script.rt4.ClientContext;

public class PlayerCombat extends Player implements IPlayerCombat{
    PlayerCombat(ClientContext arg0, String name) {
        super(arg0, name);
    }

    @Override
    public void Attack() {

    }
}
