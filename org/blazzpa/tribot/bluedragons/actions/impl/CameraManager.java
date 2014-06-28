package scripts.org.blazzpa.tribot.bluedragons.actions.impl;

import org.tribot.api2007.Camera;
import scripts.org.blazzpa.tribot.bluedragons.actions.Task;
import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

public class CameraManager extends Task {

    public CameraManager(final bBlueDragonKiller instance) {
        super(instance);
    }

    @Override
    public boolean validate() {
        return (Camera.getCameraAngle() < 30 || Camera.getCameraAngle() > 60) ||
                (instance.methods.inDragonArea() &&
                        (Camera.getCameraRotation() < 130 || Camera.getCameraRotation() > 190));
    }

    @Override
    public void execute() {
        if (Camera.getCameraRotation() < 130 || Camera.getCameraRotation() > 190) {
            Camera.setCameraRotation(instance.methods.nextInt(130, 190));
        } else {
            Camera.setCameraAngle(instance.methods.nextInt(30, 60));
        }
    }
}
