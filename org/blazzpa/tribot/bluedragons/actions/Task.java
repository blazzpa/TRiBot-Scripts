package scripts.org.blazzpa.tribot.bluedragons.actions;

import scripts.org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

public abstract class Task {

    protected final bBlueDragonKiller instance;

    protected Task(final bBlueDragonKiller instance) {
        this.instance = instance;
    }

    public abstract boolean validate();

    public abstract void execute();

}
