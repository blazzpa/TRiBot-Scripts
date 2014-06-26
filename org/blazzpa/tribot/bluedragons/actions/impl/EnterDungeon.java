package org.blazzpa.tribot.bluedragons.actions.impl;

import org.blazzpa.tribot.bluedragons.actions.Task;
import org.blazzpa.tribot.bluedragons.bBlueDragonKiller;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;

public class EnterDungeon extends Task {

    public EnterDungeon(bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return getInstance().getMethods().isBankingComplete() && Player.getPosition().getX() >= 2934 &&
                Player.getPosition().getY() < 9000 &&
                Player.getPosition().distanceTo(getInstance().getConstants().dungeonEntranceTile) <= 6;
    }

    @Override
    public void execute() {
        final RSObject ladder = Objects.find(6, "Ladder")[0];
        if (ladder != null) {
            if (ladder.click("Climb-down")) {
                getInstance().println("Enter dungeon succesful.");
            }
        }
    }
}
