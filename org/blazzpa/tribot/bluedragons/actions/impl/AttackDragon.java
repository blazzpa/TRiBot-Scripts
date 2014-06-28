package scripts.org.blazzpa.tribot.bluedragons.actions.impl;

import org.tribot.api.DynamicClicking;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSTile;
import scripts.org.blazzpa.tribot.bluedragons.actions.Task;
import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

public class AttackDragon extends Task {

    public AttackDragon(final bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return instance.methods.inDragonArea() && GroundItems.find(instance.constants.loot).length == 0 &&
                !Inventory.isFull() && (Inventory.getCount(instance.settings.selectedFoodID) > 0 ||
                instance.methods.hasEnoughHealth());
    }

    @Override
    public void execute() {
        if ((Combat.getAttackingEntities().length > 0 && instance.methods.isInteractingWithCorrectDragon()) ||
                !instance.methods.isInSafespot(instance.constants.safespot1)) {
            final RSTile randomTile = instance.methods.getRandomSafespotTile((instance.constants.safespot1));
            if (Walking.walkTo(randomTile)) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return instance.methods.isInSafespot(instance.constants.safespot1);
                    }
                }, 1500);
            }
        } else {
            if (!instance.methods.isPlayerInteracting() || (Interfaces.isInterfaceValid(167))) {
                final RSNPC[] dragons = NPCs.find(new Filter<RSNPC>() {
                    @Override
                    public boolean accept(RSNPC npc) {
                        return npc.getID() == instance.constants.blueDragon1 &&
                                npc.getPosition().distanceTo(Player.getPosition()) <= 6 &&
                                !npc.isInCombat();
                    }
                });
                if (dragons.length > 0) {
                    final RSNPC dragon = dragons[0];
                    if (dragon != null) {
                        if (dragon.isOnScreen()) {
                            if (DynamicClicking.clickRSNPC(dragon, "Attack")) {
                                Timing.waitCondition(new Condition() {
                                    @Override
                                    public boolean active() {
                                        return instance.methods.isPlayerInteracting();
                                    }
                                }, 2000);
                            }
                        } else {
                            Camera.turnToTile(dragon.getPosition());
                        }
                    }
                }
            }
        }
    }
}
