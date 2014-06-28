package scripts.org.blazzpa.tribot.bluedragons.actions.impl;

import org.tribot.api.DynamicClicking;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Camera;
import org.tribot.api2007.GroundItems;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.types.RSGroundItem;
import scripts.org.blazzpa.tribot.bluedragons.actions.Task;
import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

public class Loot extends Task {

    public Loot(final bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return instance.methods.inDragonArea() && !Inventory.isFull() &&
                GroundItems.find(instance.settings.selectedLoot).length > 0 && !instance.methods.isTeleporting() &&
                !instance.methods.isInteractingWithCorrectDragon();
    }

    @Override
    public void execute() {
        final RSGroundItem[] loot = GroundItems.find(instance.settings.selectedLoot);
        if (loot.length > 0) {
            for (final RSGroundItem item : loot) {
                if (item != null) {
                    if (item.isOnScreen()) {
                        final int previousCount = Inventory.getCount(item.getID());
                        instance.print("Taking " + item.getStack() + "x " + item.getID());
                        if (DynamicClicking.clickRSGroundItem(item, "Take " + item.getDefinition().getName())) {
                            if (Timing.waitCondition(new Condition() {
                                @Override
                                public boolean active() {
                                    return Inventory.getCount(item.getID()) > previousCount;
                                }
                            }, 500)) {
                                instance.print("Looting successful.");
                            }
                        }
                    } else {
                        Camera.turnToTile(item.getPosition());
                    }
                }
            }
        }
    }
}
