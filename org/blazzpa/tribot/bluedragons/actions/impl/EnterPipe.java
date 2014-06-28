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

public class EnterPipe extends Task {
    public EnterPipe(final bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return instance.methods.isBankingComplete() && !instance.methods.inDragonArea() &&
                instance.methods.inDungeon() && !Player.isMoving();
    }

    @Override
    public void execute() {
        final RSObject[] pipe = Objects.find(10, "Obstacle pipe");
        if (pipe.length > 0) {
            if (pipe[0] != null) {
                if (pipe[0].isOnScreen()) {
                    if (DynamicClicking.clickRSObject(pipe[0], "Squeeze-through")) {
                        if (Timing.waitCondition(new Condition() {
                            @Override
                            public boolean active() {
                                return Player.getAnimation() == 749;
                            }
                        }, 5000)) {
                            instance.print("Enter pipe successful.");
                        }
                    }
                } else {
                    Camera.turnToTile(pipe[0].getPosition());
                }
            }
        }
    }
}
