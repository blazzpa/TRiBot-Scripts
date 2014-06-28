package scripts.org.blazzpa.tribot.bluedragons.actions.impl;

import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import scripts.org.blazzpa.tribot.bluedragons.actions.Task;
import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

public class WalkToCrumbledWall extends Task {
    public WalkToCrumbledWall(final bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return instance.methods.isBankingComplete()
                && !instance.methods.pastCrumbledWall()
                && !instance.methods.inDungeon()
                && Player.getPosition().distanceTo(instance.constants.crumbledWallTile) > 6
                && !Player.isMoving()
                && instance.methods.hasEnoughHealth();
    }

    @Override
    public void execute() {
        if (PathFinding.aStarWalk(instance.constants.crumbledWallTile)) {
            instance.print("Walk to crumbled wall successful.");
        }
    }
}
