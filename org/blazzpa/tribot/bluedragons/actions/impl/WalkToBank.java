package org.blazzpa.tribot.bluedragons.actions.impl;

import org.blazzpa.tribot.bluedragons.actions.Task;
import org.blazzpa.tribot.bluedragons.bBlueDragonKiller;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;

public class WalkToBank extends Task {

    public WalkToBank(bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return !getInstance().getConstants().faladorBank.contains(Player.getPosition()) &&
                Player.getPosition().getY() < 9000 && !getInstance().getMethods().isBankingComplete();
    }

    @Override
    public void execute() {
        if (PathFinding.aStarWalk(getInstance().getConstants().centralBankTile)) {
            getInstance().println("Walk to bank successful.");
        }
    }
}
