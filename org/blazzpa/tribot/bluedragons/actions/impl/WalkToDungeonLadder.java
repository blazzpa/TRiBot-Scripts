package scripts.org.blazzpa.tribot.bluedragons.actions.impl;

import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import scripts.org.blazzpa.tribot.bluedragons.actions.Task;
import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

public class WalkToDungeonLadder extends Task {
    public WalkToDungeonLadder(final bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return instance.methods.isBankingComplete() && instance.methods.pastCrumbledWall() &&
                !instance.methods.inDungeon() && Player.getPosition().distanceTo(instance.constants
                .dungeonEntranceTile) > 6 && !Player.isMoving();
    }

    @Override
    public void execute() {
        if (instance.methods.inDungeon() || PathFinding.aStarWalk(instance.constants.dungeonEntranceTile)) {
            instance.print("Walk to dungeon entrance successful.");
        }
    }
}
