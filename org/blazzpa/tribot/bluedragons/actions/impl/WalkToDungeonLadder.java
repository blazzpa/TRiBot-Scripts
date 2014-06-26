package org.blazzpa.tribot.bluedragons.actions.impl;

import org.blazzpa.tribot.bluedragons.actions.Task;
import org.blazzpa.tribot.bluedragons.bBlueDragonKiller;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;

public class WalkToDungeonLadder extends Task {
    public WalkToDungeonLadder(bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return getInstance().getMethods().isBankingComplete() && Player.getPosition().getX() >= 2934 &&
                Player.getPosition().getY() < 9000 &&
                Player.getPosition().distanceTo(getInstance().getConstants().dungeonEntranceTile) > 6;
    }

    @Override
    public void execute() {
        if (PathFinding.aStarWalk(getInstance().getConstants().dungeonEntranceTile)) {
            getInstance().println("Walk to dungeon entrance successful.");
        }
    }
}
