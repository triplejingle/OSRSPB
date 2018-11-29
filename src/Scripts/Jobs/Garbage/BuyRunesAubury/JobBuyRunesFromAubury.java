package Scripts.Jobs.Garbage.BuyRunesAubury;

import Scripts.Core.JobCore;

import org.powerbot.script.Script;


public class JobBuyRunesFromAubury extends JobCore{

    @Override
        public void start() {
            taskList.add(new TaskBuyRunesFromAubury(ctx));
        }

}




