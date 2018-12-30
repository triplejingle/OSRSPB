package scripts.core.collection;

public class Animations {
    static final int DEFEND = 395;
    static final int NPC_DIED = 5851;
    static final int NOTHING = -1;
    static final int CHOPPING = 879;
    static final int YOU_ATTEMPT_TO_LIGHT_THE_LOGS = 733;
    static final int MELEE_ATTACK = 395;
    static final int EAT = 829;
    static final int COOKING = 897;

    public int getEat() {
        return EAT;
    }

    public int getDefend() {
        return DEFEND;
    }

    public int getCooking() {
        return COOKING;
    }

    public int getChopping() {
        return CHOPPING;
    }

    public int getYouAttemptToLightTheLogs() {
        return YOU_ATTEMPT_TO_LIGHT_THE_LOGS;
    }

    public int getMeleeAttack() {
        return MELEE_ATTACK;
    }

    public int getNothing() {
        return NOTHING;
    }

    public int getNpcDied() {
        return NPC_DIED;
    }
}
