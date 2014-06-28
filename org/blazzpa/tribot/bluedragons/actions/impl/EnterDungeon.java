package scripts.org.blazzpa.tribot.bluedragons.actions.impl;

import org.tribot.api.DynamicClicking;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;
import scripts.org.blazzpa.tribot.bluedragons.actions.Task;
import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

public class EnterDungeon extends Task {

    public EnterDungeon(final bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return instance.methods.isBankingComplete() && instance.methods.pastCrumbledWall() &&
                !instance.methods.inDungeon() && Player.getPosition().distanceTo(instance.constants.dungeonEntranceTile)
                <= 6 && !Player.isMoving();
    }

    @Override
    public void execute() {
        final RSObject[] ladder = Objects.find(6, "Ladder");
        if (ladder.length > 0) {
            if (ladder[0] != null) {
                if (ladder[0].isOnScreen()) {
                    if (DynamicClicking.clickRSObject(ladder[0], "Climb-down")) {
                        if (Timing.waitCondition(new Condition() {
                            @Override
                            public boolean active() {
                                return Player.getAnimation() == 828;
                            }
                        }, 5000)) {
                            instance.print("Enter dungeon succesful.");
                        }
                    }
                } else {
                    Camera.turnToTile(ladder[0].getPosition());
                }
            }
        }
    }
}
