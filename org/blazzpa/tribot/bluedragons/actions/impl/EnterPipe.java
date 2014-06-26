package org.blazzpa.tribot.bluedragons.actions.impl;

import org.blazzpa.tribot.bluedragons.actions.Task;
import org.blazzpa.tribot.bluedragons.bBlueDragonKiller;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;

public class EnterPipe extends Task {
    public EnterPipe(bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return getInstance().getMethods().isBankingComplete() && Player.getPosition().getX() <= 2887 &&
                Player.getPosition().getY() > 9000;
    }

    @Override
    public void execute() {
        final RSObject pipe = Objects.find(10, "Obstacle pipe")[0];
        if (pipe != null) {
            if (pipe.click("Squeeze-through")) {
                if (Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Player.getAnimation() == 749;
                    }
                }, 2000)) {
                    getInstance().println("Enter pipe successful.");
                }
            }
        }
    }
}
