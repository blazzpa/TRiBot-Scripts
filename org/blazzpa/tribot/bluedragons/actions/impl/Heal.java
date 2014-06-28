package scripts.org.blazzpa.tribot.bluedragons.actions.impl;

import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSItem;
import scripts.org.blazzpa.tribot.bluedragons.actions.Task;
import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

public class Heal extends Task {

    public Heal(final bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return Inventory.find(instance.settings.selectedFoodID).length > 0 && !instance.methods.hasEnoughHealth();
    }

    @Override
    public void execute() {
        final RSItem[] food = Inventory.find(instance.settings.selectedFoodID);
        if (food.length > 0) {
            if (food[0] != null) {
                if (food[0].click("Eat")) {
                    if (Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            return Player.getAnimation() == 829;
                        }
                    }, 2000)) {
                        instance.print("Heal succesful.");
                    }
                }
            }
        }
    }
}
