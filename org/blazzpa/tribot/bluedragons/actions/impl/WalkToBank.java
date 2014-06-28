package scripts.org.blazzpa.tribot.bluedragons.actions.impl;

import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import scripts.org.blazzpa.tribot.bluedragons.actions.Task;
import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

public class WalkToBank extends Task {

    public WalkToBank(final bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return !instance.constants.faladorBank.contains(Player.getPosition()) &&
                !instance.methods.inDungeon() && !instance.methods.isBankingComplete() && !Player.isMoving();
    }

    @Override
    public void execute() {
        if (PathFinding.aStarWalk(instance.constants.centralBankTile)) {
            instance.print("Walk to bank successful.");
        }
    }
}
