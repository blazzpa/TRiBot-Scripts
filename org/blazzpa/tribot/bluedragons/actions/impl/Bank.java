package scripts.org.blazzpa.tribot.bluedragons.actions.impl;

import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import scripts.org.blazzpa.tribot.bluedragons.actions.Task;
import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

public class Bank extends Task {

    public Bank(final bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return instance.constants.faladorBank.contains(Player.getPosition()) &&
                !instance.methods.isBankingComplete() && !Player.isMoving();
    }

    @Override
    public void execute() {
        if (Banking.isBankScreenOpen() || Banking.openBank()) {
            instance.addBankedBones(Inventory.getCount(instance.constants.dragonBones));
            instance.addBankedHides(Inventory.getCount(instance.constants.blueDragonhide));
            Banking.depositAll();
            if (Banking.withdraw(1, instance.constants.faladorTeletabID)) {
                if (Banking.withdraw(instance.settings.minimumFoodCount, instance.settings.selectedFoodID)) {
                    if (instance.settings.drinkRangingPotion) {
                        for (final int id : instance.constants.rangingPotions) {
                            if (Banking.withdraw(1, id)) {
                                break;
                            }
                        }
                    }
                    if (Banking.close()) {
                        instance.print("Banking successful.");
                    }
                }
            }
        }
    }
}
