package scripts.org.blazzpa.tribot.bluedragons.actions.impl;

import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSItem;
import scripts.org.blazzpa.tribot.bluedragons.actions.Task;
import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

public class Teleport extends Task {

    public Teleport(final bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return (Inventory.isFull() || (Inventory.getCount(instance.settings.selectedFoodID) == 0 &&
                !instance.methods.hasEnoughHealth())) &&
                instance.methods.inDragonArea() && Inventory.getCount(instance.constants.faladorTeletabID) > 0;
    }

    @Override
    public void execute() {
        final RSItem[] teletab = Inventory.find(instance.constants.faladorTeletabID);
        if (teletab.length > 0) {
            if (teletab[0] != null) {
                if (Inventory.find(instance.constants.faladorTeletabID)[0].click("Break")) {
                    if (Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            return Player.getAnimation() == 4069 || Player.getAnimation() == 4071;
                        }
                    }, 5000)) {
                        instance.print("Teleportation successful.");
                    }
                }
            }
        }
    }
}
