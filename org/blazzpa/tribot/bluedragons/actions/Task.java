package org.blazzpa.tribot.bluedragons.actions;

import org.blazzpa.tribot.bluedragons.bBlueDragonKiller;

public abstract class Task {

    private bBlueDragonKiller instance;

    public abstract boolean validate();

    public abstract void execute();

    public Task(bBlueDragonKiller instance) {
        this.instance = instance;
    }

    public bBlueDragonKiller getInstance() {
        return instance;
    }
}
