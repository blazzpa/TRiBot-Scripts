package scripts.org.blazzpa.tribot.bluedragons.actions.impl;

import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Player;
import org.tribot.api2007.Skills;
import org.tribot.api2007.types.RSItem;
import scripts.org.blazzpa.tribot.bluedragons.actions.Task;
import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

public class DrinkPotion extends Task {

    public DrinkPotion(final bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return instance.settings.drinkRangingPotion && instance.methods.inDungeon() &&
                Skills.getCurrentLevel(Skills.SKILLS.RANGED) <=
                        Skills.getActualLevel(Skills.SKILLS.RANGED) + instance.settings.redoseAt && !Player.isMoving()
                && Player.getAnimation() == -1 && Inventory.find(instance.constants.rangingPotions).length > 0;
    }

    @Override
    public void execute() {
        final RSItem[] potion = Inventory.find(instance.constants.rangingPotions);
        if (potion.length > 0) {
            if (potion[0] != null) {
                if (potion[0].click("Drink")) {
                    if (Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            return Skills.getCurrentLevel(Skills.SKILLS.RANGED) >
                                    Skills.getActualLevel(Skills.SKILLS.RANGED) + instance.settings.redoseAt;
                        }
                    }, 2000)) {
                        instance.print("Drink potion successful.");
                    }
                }
            }
        }
    }
}
