package org.blazzpa.tribot.bluedragons.actions.impl;

import org.blazzpa.tribot.bluedragons.actions.Task;
import org.blazzpa.tribot.bluedragons.bBlueDragonKiller;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Player;

public class Bank extends Task {

    public Bank(bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return getInstance().hasCompletedStartup() &&
                getInstance().getConstants().faladorBank.contains(Player.getPosition()) &&
                !getInstance().getMethods().isBankingComplete();
    }

    @Override
    public void execute() {
        if (Banking.openBank()) {
            if (Banking.depositAll() >= 0) {
                if (Banking.withdraw(1, getInstance().getConstants().faladorTeletabID)) {
                    if (Banking.withdraw(getInstance().getSettings().minimumFoodCount, getInstance().getSettings().selectedFoodID)) {
                        if (Banking.close()) {
                            getInstance().println("Banking successful.");
                        }
                    }
                }
            }
        }
    }
}
