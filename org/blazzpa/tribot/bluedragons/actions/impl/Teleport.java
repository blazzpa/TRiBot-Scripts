package org.blazzpa.tribot.bluedragons.actions.impl;

import org.blazzpa.tribot.bluedragons.actions.Task;
import org.blazzpa.tribot.bluedragons.bBlueDragonKiller;
import org.tribot.api.Timing;
import org.tribot.api.rs3.Skills;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;

public class Teleport extends Task {

    public Teleport(bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return (Inventory.isFull() || (Inventory.getCount(getInstance().getSettings().selectedFoodID) == 0 &&
                Skills.getActualLevel(Skills.SKILLS.HITPOINTS) < getInstance().getSettings().minimumHitpoints)) &&
                Player.getPosition().getY() > 9000 && Inventory.getCount(getInstance().getConstants().faladorTeletabID) > 0;
    }

    @Override
    public void execute() {
        if (Inventory.find(getInstance().getConstants().faladorTeletabID)[0].click("Break")) {
            if (Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    return Player.getAnimation() == 4069 || Player.getAnimation() == 4071;
                }
            }, 2000)) {
                getInstance().println("Teleportation successful.");
            }
        }
    }
}
