package org.blazzpa.tribot.bluedragons.util;

import org.blazzpa.tribot.bluedragons.bBlueDragonKiller;
import org.tribot.api2007.Inventory;

public class Methods {

    private bBlueDragonKiller instance;

    public Methods(bBlueDragonKiller instance) {
        this.instance = instance;
    }

    public boolean isBankingComplete() {
        return Inventory.getCount(instance.getSettings().selectedFoodID) >= instance.getSettings().minimumFoodCount
                && Inventory.getCount(instance.getConstants().faladorTeletabID) > 0;
    }

}
