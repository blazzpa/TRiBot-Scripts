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

public class ClimbCrumbledWall extends Task {

    public ClimbCrumbledWall(final bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return instance.methods.isBankingComplete() && !instance.methods.pastCrumbledWall() && !instance.methods.
                inDungeon() && Player.getPosition().distanceTo(instance.constants.crumbledWallTile) <= 6 &&
                !Player.isMoving();
    }

    @Override
    public void execute() {
        final RSObject[] wall = Objects.find(6, "Crumbling wall");
        if (wall.length > 0) {
            if (wall[0] != null) {
                if (wall[0].isOnScreen()) {
                    if (DynamicClicking.clickRSObject(wall[0], "Climb-over")) {
                        if (Timing.waitCondition(new Condition() {
                            @Override
                            public boolean active() {
                                return Player.getAnimation() == 840;
                            }
                        }, 5000)) {
                            instance.print("Climb crumbled wall successful.");
                        }
                    }
                } else {
                    Camera.turnToTile(wall[0].getPosition());
                }
            }
        }
    }
}
