package org.blazzpa.tribot.bluedragons.actions.impl;

import org.blazzpa.tribot.bluedragons.actions.Task;
import org.blazzpa.tribot.bluedragons.bBlueDragonKiller;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;

public class ClimbCrumbledWall extends Task {

    public ClimbCrumbledWall(bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return getInstance().getMethods().isBankingComplete() && Player.getPosition().getX() < 2934 &&
                Player.getPosition().getY() < 9000 &&
                Player.getPosition().distanceTo(getInstance().getConstants().crumbledWallTile) <= 6;
    }

    @Override
    public void execute() {
        final RSObject wall = Objects.find(6, "Crumbling wall")[0];
        if (wall != null) {
            if (wall.click("Climb-over")) ;
            if (Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Player.getAnimation() == 840;
                }
            }, 2000)) {
                getInstance().println("Climb crumbled wall successful.");
            }
        }
    }
}
